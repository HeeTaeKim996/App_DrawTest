package com.example.a33_plus_drawtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.a33_plus_drawtest.databinding.MainTestBinding;

public class TestIntetface
{
    private MainTestBinding mainBinding;
    private PosDrawser currPicked;

    public TestIntetface(Context context)
    {
        mainBinding = MainTestBinding.inflate(LayoutInflater.from(context));
        mainBinding.pictureTest.Initialize(this);
        mainBinding.textTest.Initialize(this);

        mainBinding.getRoot().setOnClickListener(v->
        {
            if(currPicked != null)
            {
                currPicked.SetIsPicked(false);
                currPicked = null;
            }
        });
    }
    public View GetRootView()
    {
        return mainBinding.getRoot();
    }

    public void RequestPick(PosDrawser requestDrawser)
    {
        if(currPicked != null)
        {
            currPicked.SetIsPicked(false);
        }
        requestDrawser.SetIsPicked(true);
        currPicked = requestDrawser;
    }
}
