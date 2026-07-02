package com.example.a33_plus_drawtest;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a33_plus_drawtest.databinding.ActivityMainBinding;

public class MainInterface
{
    ActivityMainBinding mainBinding;

    public MainInterface(Context context)
    {
        mainBinding = ActivityMainBinding.inflate(LayoutInflater.from(context));

        mainBinding.buttonSetColorBlack.setOnClickListener(v->
        {
            mainBinding.paintCanvasView.SetPaintColor(Color.parseColor("#000000"));
        });
        mainBinding.buttonSetColorBlue.setOnClickListener(v->
        {
            mainBinding.paintCanvasView.SetPaintColor(Color.parseColor("#0000FF"));
        });
        mainBinding.buttonEraseAll.setOnClickListener(v->
        {
            mainBinding.paintCanvasView.EraseAll();
        });
        mainBinding.buttonEraser.setOnClickListener(v->
        {
            mainBinding.paintCanvasView.SetEraserMode();
        });
        mainBinding.buttonSave.setOnClickListener(v->
        {
            mainBinding.paintCanvasView.SaveCanvas(context);
        });
        mainBinding.buttonLoad.setOnClickListener(v->
        {
            mainBinding.paintCanvasView.LoadCanvas(context);
        });
        mainBinding.buttonChangeSize.setOnClickListener(v->
        {
            mainBinding.paintCanvasView.ChangeSize();
        });
    }

    public View GetRootView()
    {
        return mainBinding.getRoot();
    }
}

