package com.Fish.theflyingfish;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity
{

    FlyingFishView flyingFishView;
    Handler handler = new Handler();
    final static long Interval = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        flyingFishView = new FlyingFishView(getApplicationContext());
        setContentView(flyingFishView);

        Timer timer = new Timer();
        timer.schedule(new TimerTask()
        {
            @Override
            public void run()
            {
                handler.post(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        flyingFishView.invalidate();
                    }
                });
            }
        }, 0, Interval);

    }
}