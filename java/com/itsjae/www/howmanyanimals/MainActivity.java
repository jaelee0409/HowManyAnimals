package com.itsjae.www.howmanyanimals;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_play = (ImageView) findViewById(R.id.iv_play);
        ImageView iv_stats = (ImageView) findViewById(R.id.iv_stats);
        ImageView iv_options = (ImageView) findViewById(R.id.iv_options);

        // Play Button
        iv_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent play = new Intent(getBaseContext(), DifficultyActivity.class);
                startActivity(play);
            }
        });

        // Stats Button
        iv_stats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent stats = new Intent(getBaseContext(), StatsActivity.class);
                startActivity(stats);
            }
        });

        // Options Button
        iv_options.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent options = new Intent(getBaseContext(), OptionsActivity.class);
                startActivity(options);
            }
        });
    }
}