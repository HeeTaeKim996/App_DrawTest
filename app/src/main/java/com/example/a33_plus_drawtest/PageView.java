package com.example.a33_plus_drawtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import androidx.constraintlayout.widget.ConstraintLayout;
public class PageView extends ConstraintLayout
{
    private TestIntetface intetface;

    public void Initialize(TestIntetface intetface)
    {
        this.intetface = intetface;
    }

    public PageView(Context context)
    {super(context);                        init();}
    public PageView(Context context, AttributeSet attrs)
    {super(context, attrs);                 init();}
    public PageView(Context context, AttributeSet attrs, int defStyleSet)
    {super(context, attrs, defStyleSet);    init();}

    private void init()
    {
        android.widget.LinearLayout.LayoutParams params
                = new android.widget.LinearLayout.LayoutParams(989, 1632);
        setLayoutParams(params);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(event.getAction() == MotionEvent.ACTION_DOWN)
        {
            intetface.OnNullClicked();
        }
        return false;
    }

}
