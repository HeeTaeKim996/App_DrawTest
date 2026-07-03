package com.example.a33_plus_drawtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

import com.example.a33_plus_drawtest.databinding.MainTestBinding;

public class TestIntetface
{
    private MainTestBinding mainBinding;
    private PosView currPicked;

    public TestIntetface(Context context)
    {
        mainBinding = MainTestBinding.inflate(LayoutInflater.from(context));
        mainBinding.pictureTest.Initialize(this);
        mainBinding.textTest.Initialize(this);
        mainBinding.getRoot().Initialize(this);
        mainBinding.pageView1.Initialize(this);
        mainBinding.pageView2.Initialize(this);
    }
    public View GetRootView()
    {
        return mainBinding.getRoot();
    }

    public void RequestPick(PosView requestDrawser)
    {
        if(currPicked != null)
        {
            currPicked.SetIsPicked(false);
        }
        requestDrawser.SetIsPicked(true);
        currPicked = requestDrawser;
    }

    public void OnNullClicked()
    {
        if(currPicked != null)
        {
            currPicked.SetIsPicked(false);
            currPicked = null;
        }
    }

}
