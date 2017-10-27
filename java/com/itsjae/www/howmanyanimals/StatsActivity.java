package com.itsjae.www.howmanyanimals;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        TextView tv_easy_correct = (TextView) findViewById(R.id.tv_easy_correct);
        TextView tv_easy_incorrect = (TextView) findViewById(R.id.tv_easy_incorrect);
        TextView tv_normal_correct = (TextView) findViewById(R.id.tv_normal_correct);
        TextView tv_normal_incorrect = (TextView) findViewById(R.id.tv_normal_incorrect);
        TextView tv_hard_correct = (TextView) findViewById(R.id.tv_hard_correct);
        TextView tv_hard_incorrect = (TextView) findViewById(R.id.tv_hard_incorrect);
        TextView tv_extreme_correct = (TextView) findViewById(R.id.tv_extreme_correct);
        TextView tv_extreme_incorrect = (TextView) findViewById(R.id.tv_extreme_incorrect);

        SharedPreferences sharedPref = getSharedPreferences("Score", Context.MODE_PRIVATE);
        tv_easy_correct.setText("" + sharedPref.getInt("Easy Correct", 0));
        tv_easy_incorrect.setText("" + sharedPref.getInt("Easy Incorrect", 0));

        tv_normal_correct.setText("" + sharedPref.getInt("Normal Correct", 0));
        tv_normal_incorrect.setText("" + sharedPref.getInt("Normal Incorrect", 0));

        tv_hard_correct.setText("" + sharedPref.getInt("Hard Correct", 0));
        tv_hard_incorrect.setText("" + sharedPref.getInt("Hard Incorrect", 0));

        tv_extreme_correct.setText("" + sharedPref.getInt("Extreme Correct", 0));
        tv_extreme_incorrect.setText("" + sharedPref.getInt("Extreme Incorrect", 0));
    }
}
