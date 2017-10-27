package com.itsjae.www.howmanyanimals;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class GameActivity extends AppCompatActivity {

    private ImageView iv_count;
    private ImageView iv_animal1;
    private ImageView iv_animal2;
    private ImageView iv_animal3;
    private ImageView iv_animal4;
    private ImageView iv_animal5;
    private ImageView iv_animal6;
    private TextView tv_question;
    private ProgressBar progressBar;
    private Button bt1;
    private Button bt2;
    private Button bt3;
    private Button bt4;

    private int progress;
    private int progressTick;
    private int defaultTime;
    private int[] figures = new int[6];
    private boolean running;
    private int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        // 1 = Easy
        // 2 = Normal
        // 3 = Hard
        // 4 = Extreme
        int difficulty = getIntent().getExtras().getInt("Difficulty");

        // Count Down
        iv_count = (ImageView) findViewById(R.id.iv_count);

        // Figures
        iv_animal1 = (ImageView) findViewById(R.id.iv_animal1);
        iv_animal2 = (ImageView) findViewById(R.id.iv_animal2);
        iv_animal3 = (ImageView) findViewById(R.id.iv_animal3);
        iv_animal4 = (ImageView) findViewById(R.id.iv_animal4);
        iv_animal5 = (ImageView) findViewById(R.id.iv_animal5);
        iv_animal6 = (ImageView) findViewById(R.id.iv_animal6);


        // Question
        tv_question = (TextView) findViewById(R.id.tv_question);

        // Answer Buttons
        // Deactivate
        bt1 = (Button) findViewById(R.id.bt_answer1);
        bt2 = (Button) findViewById(R.id.bt_answer2);
        bt3 = (Button) findViewById(R.id.bt_answer3);
        bt4 = (Button) findViewById(R.id.bt_answer4);

        // Progress Bar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progress = 100;

        // Game Engine
        running = true;
        while(running) {
            iv_count.setVisibility(View.VISIBLE);
            iv_animal1.setVisibility(View.INVISIBLE);
            iv_animal2.setVisibility(View.INVISIBLE);
            iv_animal3.setVisibility(View.INVISIBLE);
            iv_animal4.setVisibility(View.INVISIBLE);
            iv_animal5.setVisibility(View.INVISIBLE);
            iv_animal6.setVisibility(View.INVISIBLE);
            tv_question.setVisibility(View.INVISIBLE);
            bt1.setClickable(false);
            bt2.setClickable(false);
            bt3.setClickable(false);
            bt4.setClickable(false);
            progressBar.setProgress(100);

            running = false;
            start(difficulty);
        }
    }

    private void start(int difficulty) {
        countDown(difficulty);
        figure(difficulty);
    }

    // Count Down
    private void countDown(final int difficulty) {
        new CountDownTimer(6000, 1000) {
            int currentCount = 4;

            public void onTick(long millisUntilFinished) {
                currentCount--;
                if (currentCount == 3) {
                    iv_count.setImageResource(R.drawable.number_3);
                    return;
                }
                if (currentCount == 2) {
                    iv_count.setImageResource(R.drawable.number_2);
                    return;
                }
                if (currentCount == 1) {
                    iv_count.setImageResource(R.drawable.number_1);
                    return;
                }
                if (currentCount == 0) {
                    iv_count.setImageResource(R.drawable.number_go);
                    return;
                }
                iv_count.setVisibility(View.INVISIBLE);
                iv_animal1.setVisibility(View.VISIBLE);
                iv_animal2.setVisibility(View.VISIBLE);
                iv_animal3.setVisibility(View.VISIBLE);
                iv_animal4.setVisibility(View.VISIBLE);
                iv_animal5.setVisibility(View.VISIBLE);
                iv_animal6.setVisibility(View.VISIBLE);

                if (difficulty == 4) {
                    defaultTime = 3;
                    progressTick = 3;
                } else {
                    defaultTime = 5;
                    progressTick = 2;
                }

                int totalTime = defaultTime * 1000;

                new CountDownTimer(totalTime, 100) {


                    @Override
                    public void onTick(long l) {
                        progress = progress - progressTick;
                        progressBar.setProgress(progress);
                    }

                    @Override
                    public void onFinish() {
                        progressBar.setProgress(0);
                        iv_animal1.setVisibility(View.INVISIBLE);
                        iv_animal2.setVisibility(View.INVISIBLE);
                        iv_animal3.setVisibility(View.INVISIBLE);
                        iv_animal4.setVisibility(View.INVISIBLE);
                        iv_animal5.setVisibility(View.INVISIBLE);
                        iv_animal6.setVisibility(View.INVISIBLE);

                        // Show Question when done
                        question(difficulty);
                    }
                }.start();
            }

            public void onFinish() {
                iv_count.setVisibility(View.INVISIBLE);
            }
        }.start();
    }

    // Generate figures
    private void figure(int difficulty) {

        Random random = new Random();

            // Easy Mode
            // Only Black Figures
        if (difficulty == 1) {
            figures[0] = random.nextInt(4);
            figures[1] = random.nextInt(4);
            while(figures[1] == figures[0]){
                figures[1] = random.nextInt(4);
            }
            figures[2] = random.nextInt(4);
            while(figures[2] == figures[1] || figures[2] == figures[0]){
                figures[2] = random.nextInt(4);
            }
            figures[3] = random.nextInt(4);
            while(figures[3] == figures[2] || figures[3] == figures[1] || figures[3] == figures[0]){
                figures[3] = random.nextInt(4);
            }
            figures[4] = random.nextInt(4);
            figures[5] = random.nextInt(4);
        } else {
            figures[0] = random.nextInt(16) + 4;
            figures[1] = random.nextInt(16) + 4;
            while(figures[1] == figures[0] || figures[1]/4 == figures[0]/4 || figures[1]%4 == figures[0]%4){
                figures[1] = random.nextInt(16) + 4;
            }
            figures[2] = random.nextInt(16) + 4;
            while(figures[2] == figures[1] || figures[2]/4 == figures[1]/4 || figures[2]%4 == figures[1]%4 ||
                    figures[2] == figures[0] || figures[2]/4 == figures[0]/4 || figures[2]%4 == figures[0]%4){
                figures[2] = random.nextInt(16) + 4;
            }
            figures[3] = random.nextInt(16) + 4;
            while(figures[3] == figures[2] || figures[3]/4 == figures[2]/4 || figures[3]%4 == figures[2]%4 ||
                    figures[3] == figures[1] || figures[3]/4 == figures[1]/4 || figures[3]%4 == figures[1]%4 ||
                    figures[3] == figures[0] || figures[3]/4 == figures[0]/4 || figures[3]%4 == figures[0]%4){
                figures[3] = random.nextInt(16) + 4;
            }
            figures[4] = random.nextInt(16) + 4;
            figures[5] = random.nextInt(16) + 4;
        }
        changeFigure(figures[0], iv_animal1);
        changeFigure(figures[1], iv_animal2);
        changeFigure(figures[2], iv_animal3);
        changeFigure(figures[3], iv_animal4);
        changeFigure(figures[4], iv_animal5);
        changeFigure(figures[5], iv_animal6);
    }

    // Change figures
    private void changeFigure(int number, ImageView figure) {
        if (number == 0) {
            figure.setImageResource(R.drawable.bunny);
        }
        if (number == 1) {
            figure.setImageResource(R.drawable.mouse);
        }
        if (number == 2) {
            figure.setImageResource(R.drawable.dog);
        }
        if (number == 3) {
            figure.setImageResource(R.drawable.goat);
        }
        if (number == 4) {
            figure.setImageResource(R.drawable.bunny_red);
        }
        if (number == 5) {
            figure.setImageResource(R.drawable.mouse_red);
        }
        if (number == 6) {
            figure.setImageResource(R.drawable.dog_red);
        }
        if (number == 7) {
            figure.setImageResource(R.drawable.goat_red);
        }
        if (number == 8) {
            figure.setImageResource(R.drawable.bunny_blue);
        }
        if (number == 9) {
            figure.setImageResource(R.drawable.mouse_blue);
        }
        if (number == 10) {
            figure.setImageResource(R.drawable.dog_blue);
        }
        if (number == 11) {
            figure.setImageResource(R.drawable.goat_blue);
        }
        if (number == 12) {
            figure.setImageResource(R.drawable.bunny_yellow);
        }
        if (number == 13) {
            figure.setImageResource(R.drawable.mouse_yellow);
        }
        if (number == 14) {
            figure.setImageResource(R.drawable.dog_yellow);
        }
        if (number == 15) {
            figure.setImageResource(R.drawable.goat_yellow);
        }
        if (number == 16) {
            figure.setImageResource(R.drawable.bunny_green);
        }
        if (number == 17) {
            figure.setImageResource(R.drawable.mouse_green);
        }
        if (number == 18) {
            figure.setImageResource(R.drawable.dog_green);
        }
        if (number == 19) {
            figure.setImageResource(R.drawable.goat_green);
        }
    }

    private void question(final int difficulty){
        // Buttons Activate

        Random questionGen = new Random();
        int question;
        // Easy and Normal
        if (difficulty == 1) {
            question = questionGen.nextInt(4);
        } else if (difficulty == 2) {
            question = questionGen.nextInt(8);
        } else {
            // Hard and Extreme
            // More questions
            question = questionGen.nextInt(24);
        }
        if (question == 0) {
            tv_question.setText(R.string.countBunny);
            answer = countAnimal(0);
        }
        if (question == 1) {
            tv_question.setText(R.string.countMouse);
            answer = countAnimal(1);
        }
        if (question == 2) {
            tv_question.setText(R.string.countDog);
            answer = countAnimal(2);
        }
        if (question == 3) {
            tv_question.setText(R.string.countGoat);
            answer = countAnimal(3);
        }
        if (question == 4) {
            tv_question.setText(R.string.countRed);
            answer = countColor(1);
        }
        if (question == 5) {
            tv_question.setText(R.string.countBlue);
            answer = countColor(2);
        }
        if (question == 6) {
            tv_question.setText(R.string.countYellow);
            answer = countColor(3);
        }
        if (question == 7) {
            tv_question.setText(R.string.countGreen);
            answer = countColor(4);
        }
        if (question == 8) {
            tv_question.setText(R.string.countRedBunny);
            answer = countExactAnimal(4);
        }
        if (question == 9) {
            tv_question.setText(R.string.countRedMouse);
            answer = countExactAnimal(5);
        }
        if (question == 10) {
            tv_question.setText(R.string.countRedDog);
            answer = countExactAnimal(6);
        }
        if (question == 11) {
            tv_question.setText(R.string.countRedGoat);
            answer = countExactAnimal(7);
        }
        if (question == 12) {
            tv_question.setText(R.string.countBlueBunny);
            answer = countExactAnimal(8);
        }
        if (question == 13) {
            tv_question.setText(R.string.countBlueMouse);
            answer = countExactAnimal(9);
        }
        if (question == 14) {
            tv_question.setText(R.string.countBlueDog);
            answer = countExactAnimal(10);
        }
        if (question == 15) {
            tv_question.setText(R.string.countBlueGoat);
            answer = countExactAnimal(11);
        }
        if (question == 16) {
            tv_question.setText(R.string.countYellowBunny);
            answer = countExactAnimal(12);
        }
        if (question == 17) {
            tv_question.setText(R.string.countYellowMouse);
            answer = countExactAnimal(13);
        }
        if (question == 18) {
            tv_question.setText(R.string.countYellowDog);
            answer = countExactAnimal(14);
        }
        if (question == 19) {
            tv_question.setText(R.string.countYellowGoat);
            answer = countExactAnimal(15);
        }
        if (question == 20) {
            tv_question.setText(R.string.countGreenBunny);
            answer = countExactAnimal(16);
        }
        if (question == 21) {
            tv_question.setText(R.string.countGreenMouse);
            answer = countExactAnimal(17);
        }
        if (question == 22) {
            tv_question.setText(R.string.countGreenDog);
            answer = countExactAnimal(18);
        }
        if (question == 23) {
            tv_question.setText(R.string.countGreenGoat);
            answer = countExactAnimal(19);
        }

        // Show Question
        tv_question.setVisibility(View.VISIBLE);

        // Activate Buttons
        bt1.setClickable(true);
        bt2.setClickable(true);
        bt3.setClickable(true);
        bt4.setClickable(true);

        // If press 0
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer == 0){
                    // Correct
                    Toast.makeText(getBaseContext(), R.string.right, Toast.LENGTH_SHORT).show();
                    saveScore(difficulty, 1);
                } else {
                    // Incorrect
                    Toast.makeText(getBaseContext(), R.string.wrong, Toast.LENGTH_SHORT).show();
                    saveScore(difficulty, 0);
                }
                recreate();
            }
        });

        // If press 1
        bt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer == 1){
                    // Correct
                    Toast.makeText(getBaseContext(), R.string.right, Toast.LENGTH_SHORT).show();
                    saveScore(difficulty, 1);
                } else {
                    // Incorrect
                    Toast.makeText(getBaseContext(), R.string.wrong, Toast.LENGTH_SHORT).show();
                    saveScore(difficulty, 0);
                }
                recreate();
            }
        });

        // If press 2
        bt3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer == 2){
                    // Correct
                    Toast.makeText(getBaseContext(), R.string.right, Toast.LENGTH_SHORT).show();
                    saveScore(difficulty, 1);
                } else {
                    // Incorrect
                    Toast.makeText(getBaseContext(), R.string.wrong, Toast.LENGTH_SHORT).show();
                    saveScore(difficulty, 0);
                }
                recreate();
            }
        });

        // If press 3
        bt4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(answer == 3){
                    // Correct
                    Toast.makeText(getBaseContext(), R.string.right, Toast.LENGTH_SHORT).show();
                    saveScore(difficulty, 1);
                } else {
                    // Incorrect
                    Toast.makeText(getBaseContext(), R.string.wrong, Toast.LENGTH_SHORT).show();
                    saveScore(difficulty, 0);
                }
                recreate();
            }
        });
    }

    private int countAnimal(int animal){
        int result = 0;

        for(int i = 0; i < 6; i++){
            if(figures[i]%4 == animal){
                result++;
            }
        }
        return result;
    }

    private int countColor(int color) {
        int result = 0;

        for(int i = 0; i < 6; i++){
            if(figures[i]/4 == color){
                result++;
            }
        }
        return result;
    }

    private int countExactAnimal(int animal){
        int result = 0;

        for(int i = 0; i < 6; i++){
            if(figures[i] == animal){
                result++;
            }
        }
        return result;
    }

    // Save Score
    private void saveScore(int difficulty, int bool){
        SharedPreferences sharedPref = getSharedPreferences("Score", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();

        // bool = 1 if correct

        // Easy
        if(difficulty == 1) {
            if(bool == 1){
                int easy_correct = sharedPref.getInt("Easy Correct", 0);
                editor.putInt("Easy Correct", easy_correct + 1);
            } else {
                int easy_incorrect = sharedPref.getInt("Easy Incorrect", 0);
                editor.putInt("Easy Incorrect", easy_incorrect + 1);
            }
        }

        // Normal
        if(difficulty == 2) {
            if(bool == 1){
                int normal_correct = sharedPref.getInt("Normal Correct", 0);
                editor.putInt("Normal Correct", normal_correct + 1);
            } else {
                int normal_incorrect = sharedPref.getInt("Normal Incorrect", 0);
                editor.putInt("Normal Incorrect", normal_incorrect + 1);
            }
        }


        // Hard
        if(difficulty == 3) {
            if (bool == 1) {
                int hard_correct = sharedPref.getInt("Hard Correct", 0);
                editor.putInt("Hard Correct", hard_correct + 1);
            } else {
                int hard_incorrect = sharedPref.getInt("Hard Incorrect", 0);
                editor.putInt("Hard Incorrect", hard_incorrect + 1);
            }
        }

        // Extreme
        if(difficulty == 4) {
            if (bool == 1) {
                int extreme_correct = sharedPref.getInt("Extreme Correct", 0);
                editor.putInt("Extreme Correct", extreme_correct + 1);
            } else {
                int extreme_incorrect = sharedPref.getInt("Extreme Incorrect", 0);
                editor.putInt("Extreme Incorrect", extreme_incorrect + 1);
            }
        }
        editor.apply();
    }
}
