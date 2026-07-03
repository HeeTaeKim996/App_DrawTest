package com.example.a33_plus_drawtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PosView extends View
{
    private float lastPosX;
    private float lastPosY;

    private Paint posPaint;

    private TestIntetface testIntetface;

    protected float strokeWidth = 2.f;

    protected boolean isPicked = false;

    public void Initialize(TestIntetface testIntetface)
    {
        this.testIntetface = testIntetface;
    }

    public PosView(Context context)
    {
        super(context);
        PosInit();
    }

    public PosView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        PosInit();
    }

    public PosView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        PosInit();
    }

    private void PosInit()
    {
        posPaint = new Paint();
        posPaint.setStyle(Paint.Style.STROKE);
        posPaint.setColor(Color.BLUE);
        posPaint.setStrokeWidth(strokeWidth);


    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if (isPicked)
        {
            float halfStroke = posPaint.getStrokeWidth() / 2.f;
            canvas.drawRect(halfStroke, halfStroke,
                    getWidth() - halfStroke, getHeight() - halfStroke,
                    posPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch (event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                if (isPicked)
                {
                    lastPosX = event.getRawX();
                    lastPosY = event.getRawY();
                    break;
                }
                return false;

            case MotionEvent.ACTION_MOVE:
                if (isPicked)
                {
                    if(getParent() != null)
                    {
                        getParent().requestDisallowInterceptTouchEvent(true);
                    }

                    float currPosX = event.getRawX();
                    float currPosY = event.getRawY();

                    float deltaX = currPosX - lastPosX;
                    float deltaY = currPosY - lastPosY;

                    setTranslationX(getTranslationX() + deltaX);
                    setTranslationY(getTranslationY() + deltaY);

                    lastPosX = currPosX;
                    lastPosY = currPosY;

                    break;
                }
                return false;

            case MotionEvent.ACTION_UP:
                if (isPicked == false)
                {
                    int pos[] = new int[2];
                    getLocationOnScreen(pos);

                    float rawX = event.getRawX();
                    float rawY = event.getRawY();

                    if ((rawX >= pos[0] && rawX <= pos[0] + getWidth())
                            &&
                            (rawY >= pos[1] && rawY <= pos[1] + getHeight()))
                    {
                        testIntetface.RequestPick(this);
                        break;
                    }
                }
                return false;

            default:
                return false;

        }
        invalidate();
        return true;
    }

    public void SetIsPicked(boolean isPicked)
    {
        this.isPicked = isPicked;
        invalidate();
    }


}
