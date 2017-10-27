package com.itsjae.www.howmanyanimals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class DifficultyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_difficulty);

        ImageView iv_easy = (ImageView) findViewById(R.id.iv_easy);
        ImageView iv_normal = (ImageView) findViewById(R.id.iv_normal);
        ImageView iv_hard = (ImageView) findViewById(R.id.iv_hard);
        ImageView iv_extreme = (ImageView) findViewById(R.id.iv_extreme);

        // Easy Button
        iv_easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play = new Intent(getBaseContext(), GameActivity.class);
                play.putExtra("Difficulty", 1);
                startActivity(play);
                finish();
            }
        });

        // Normal Button
        iv_normal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play = new Intent(getBaseContext(), GameActivity.class);
                play.putExtra("Difficulty", 2);
                startActivity(play);
                finish();
            }
        });

        // Hard Button
        iv_hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play = new Intent(getBaseContext(), GameActivity.class);
                play.putExtra("Difficulty", 3);
                startActivity(play);
                finish();
            }
        });

        // Extreme Button
        iv_extreme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play = new Intent(getBaseContext(), GameActivity.class);
                play.putExtra("Difficulty", 4);
                startActivity(play);
                finish();
            }
        });
    }
}
