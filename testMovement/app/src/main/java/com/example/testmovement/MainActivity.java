package com.example.testmovement;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MotionEventCompat;

import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.Timer;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener
{

    //???
    private GestureDetector gestureDetector = new GestureDetector(this);
    ImageView testImage;

    //Image Dimensions
    int imageX;
    int imageY;

    //Screen Dimensions
    private int screenWidth;
    private int screenHeight;

    private Handler handler = new Handler();
    private Timer timer = new Timer();

    public boolean onTouchEvent(MotionEvent event)
    {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testImage = findViewById(R.id.rikaRun);

        //Gets phone screen size
        WindowManager wm = getWindowManager();
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenWidth = size.x;
        screenHeight = size.y;

        System.out.println("Screen Size Width: " + screenWidth + " Screen Size Height: " + screenHeight);
        System.out.println(testImage.getWidth() + " " + testImage.getHeight());
    }

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent)
    {
    }

    @Override
    public boolean onFling(MotionEvent pressDown, MotionEvent moveFinger, float xAxis, float yAxis)
    {
        float xDistance = moveFinger.getX() - pressDown.getX(); //Distance of swipe in X direction
        float yDistance = moveFinger.getY() - pressDown.getY(); //Distance of swipe in Y direction

        if(Math.abs(xDistance) > Math.abs(yDistance)) //If swiped farther in X direction then Y
        {
            if(Math.abs(xDistance) > 50 && Math.abs(xAxis) > 50)
            {
                if(xDistance > 0) //Swiped right
                {
                    goRight();
                    return true;
                }
                else //Swiped left
                {
                    goLeft();
                    return true;
                }
            }
        }
        else
        {
            if(Math.abs(yDistance) > 50 && Math.abs(yAxis) > 50)
            {
                if(yDistance < 0) //Swiped up, Up is considered the negative direction????
                {
                    goUp();
                    return true;
                }
                else //Swiped down
                {
                    goDown();
                    return true;
                }
            }
        }

        return false;
    }

    private void goRight()
    {
        moveImage("Right");

    }

    private void goLeft()
    {
        moveImage("Left");
    }

    private void goUp()
    {
        moveImage("Up");
    }

    private void goDown()
    {
        moveImage("Down");
    }

    private void moveImage(String Direction)
    {
        switch(Direction)
        {
            case "Up":
            {
                imageY = imageY - 100;

                if(imageY < 0)
                {
                    imageY = 0;
                    testImage.setY(imageY);
                }
                else
                {
                    testImage.setY(imageY);
                }

                break;
            }
            case "Down":
            {
                imageY = imageY + 100;

                if(imageY > screenHeight)
                {
                    imageY = screenHeight;
                    testImage.setY(imageY);
                }
                else
                {
                    testImage.setY(imageY);
                }

                break;
            }
            case "Left":
            {
                imageX = imageX - 100;

                if(imageX < 0)
                {
                    imageX = 0;
                    testImage.setX(imageX);
                }
                else
                {
                    testImage.setX(imageX);
                }

                break;
            }
            case "Right":
            {
                imageX = imageX + 100;

                if(imageX > screenWidth)
                {
                    imageX = screenWidth;
                    testImage.setX(imageX);
                }
                else
                {
                    testImage.setX(imageX);
                }

                break;
            }
        }

        System.out.println("Direction moved: " + Direction + " x coordinates: " + imageX + " y coordinates: " + imageY);
    }


}
