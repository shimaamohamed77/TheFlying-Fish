package com.Fish.theflyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameOverActivity extends AppCompatActivity
{

    Button buttonPlayAgain;
    TextView textViewScore;
    String score;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        initView();
        clickView();
    }

    private void initView()
    {
        score = getIntent().getExtras().get("score").toString();
        buttonPlayAgain = findViewById(R.id.btn_play_again);
        textViewScore = findViewById(R.id.txt_view_score);
        textViewScore.setText("Score = " + score);
    }

    private void clickView()
    {
        buttonPlayAgain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }
}