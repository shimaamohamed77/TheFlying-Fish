package com.Fish.theflyingfish;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class FlyingFishView extends View
{

    Bitmap fish[] = new Bitmap[2];
    int fishX = 10;
    int fishY;
    int fishSpeed;

    int canvesWidth, canvesHeight;

    int yellowX, yellowY, yellowSpeed = 10;
    Paint yellowPaint = new Paint();

    int greenX, greenY, greenSpeed = 20;
    Paint greenPaint = new Paint();

    int redX, redY, redSpeed = 25;
    Paint redPaint = new Paint();

    int score, lifeCounterOfFish;

    boolean touch = false;

    Bitmap backgroundImage;
    Paint paint = new Paint();
    Bitmap life[] = new Bitmap[2];

    public FlyingFishView(Context context)
    {
        super(context);

        fish[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish1);
        fish[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish2);
        backgroundImage = BitmapFactory.decodeResource(getResources(), R.drawable.background);

        yellowPaint.setColor(Color.YELLOW);
        yellowPaint.setAntiAlias(false);

        greenPaint.setColor(Color.GREEN);
        greenPaint.setAntiAlias(false);

        redPaint.setColor(Color.RED);
        redPaint.setAntiAlias(false);

        paint.setColor(Color.WHITE);
        paint.setTextSize(70);
        paint.setTypeface(Typeface.DEFAULT_BOLD);
        paint.setAntiAlias(true);

        life[0] = BitmapFactory.decodeResource(getResources(), R.drawable.hearts);
        life[1] = BitmapFactory.decodeResource(getResources(), R.drawable.heart_grey);

        fishY = 550;
        score = 0;
        lifeCounterOfFish = 3;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvesWidth = canvas.getWidth();
        canvesHeight = canvas.getHeight();

        canvas.drawBitmap(backgroundImage, 0, 0, null );

        int minFishY = fish[0].getHeight();
        int maxFishY = canvesHeight - fish[0].getHeight() * 3;
        fishY = fishY + fishSpeed;


        if (fishY < minFishY)
        {
            fishY = minFishY;
        }

        if (fishY > maxFishY)
        {
            fishY = maxFishY;
        }
        fishSpeed = fishSpeed + 2;

        if (touch)
        {
            canvas.drawBitmap(fish[1], fishX, fishY, null);
            touch = false;
        }
        else
        {
            canvas.drawBitmap(fish[0], fishX, fishY, null);
        }

        yellowX = yellowX - yellowSpeed;
        if (hitBallChecker(yellowX, yellowY))
        {
            score = score + 10;
            yellowX = -100;
        }

        if (yellowX < 0)
        {
            yellowX = canvesWidth + 21;
            yellowY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(yellowX, yellowY, 25, yellowPaint);

        greenX = greenX - greenSpeed;

        if (hitBallChecker(greenX, greenY))
        {
            score = score + 20;
            greenX = -100;
        }

        if (greenX < 0)
        {
            greenX = canvesWidth + 21;
            greenY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(greenX, greenY, 30, greenPaint);

        redX = redX - redSpeed;
        if (hitBallChecker(redX, redY))
        {
            redX = -100;
            lifeCounterOfFish--;
            if (lifeCounterOfFish == 0)
            {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                Intent gameOverIntent = new Intent(getContext(), GameOverActivity.class);
                gameOverIntent.putExtra("score", score);
                getContext().startActivity(gameOverIntent);
            }
        }

        if (redX < 0)
        {
            redX = canvesWidth + 21;
            redY = (int) Math.floor(Math.random() * (maxFishY - minFishY)) + minFishY;
        }
        canvas.drawCircle(redX, redY, 30, redPaint);


        canvas.drawText("Score :" + score, 20, 60, paint);


        for (int i=0; i<3; i++)
        {
            int x = (int) (580 + life[0].getWidth() * 1.5 * i);
            int y = 30;

            if (i < lifeCounterOfFish)
            {
                canvas.drawBitmap(life[0], x, y, null);
            }
            else
            {
                canvas.drawBitmap(life[1], x, y, null);
            }
        }

    }

    private boolean hitBallChecker(int x, int y)
    {
        if (fishX < x && x < (fishX + fish[0].getWidth()) && fishY < y && y < (fishY + fish[0].getHeight()))
        {
            return true;
        }
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if (event.getAction() == MotionEvent.ACTION_DOWN)
        {
            touch = true;

            fishSpeed = -22;
        }
        return true;
    }
}
