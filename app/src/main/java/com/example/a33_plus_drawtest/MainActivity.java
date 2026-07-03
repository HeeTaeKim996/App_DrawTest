package com.example.a33_plus_drawtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.a33_plus_drawtest.databinding.MainTestBinding;

public class MainActivity extends AppCompatActivity
{
//    CircleDrawView circleDrawView;
//    PngDrawView pngDrawView;
//    PainterView painterView;


    MainInterface mainInterface;
    TestIntetface testIntetface;

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

//        mainInterface = new MainInterface(this);
//        setContentView(mainInterface.GetRootView());

//        setContentView(R.layout.main_test);

        testIntetface = new TestIntetface(this);
        setContentView(testIntetface.GetRootView());
    }
}