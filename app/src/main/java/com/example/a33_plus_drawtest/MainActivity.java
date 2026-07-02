package com.example.a33_plus_drawtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity
{
//    CircleDrawView circleDrawView;
//    PngDrawView pngDrawView;
//    PainterView painterView;


    MainInterface mainInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

//        circleDrawView = new CircleDrawView(this);
//        setContentView(circleDrawView);
//        pngDrawView = new PngDrawView(this);
//        setContentView(pngDrawView);
//        painterView = new PainterView(this);
//        setContentView(painterView);

        mainInterface = new MainInterface(this);
        setContentView(mainInterface.GetRootView());

    }
}