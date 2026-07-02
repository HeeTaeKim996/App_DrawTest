package com.example.a33_plus_drawtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CircleDrawView extends View
{
    private float touchX = -100f;
    private float touchY = -100f;
    private final float circleRadius = 150f;

    private Paint paint;

    public CircleDrawView(Context context)
    {
        super(context);
        init();
    }

    public CircleDrawView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public CircleDrawView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE); // 테두리 모드로 설정
        paint.setStrokeWidth(10f);          // 테두리 두께
        paint.setAntiAlias(true);           // 계단 현상 방지
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        canvas.drawCircle(touchX, touchY, circleRadius, paint);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                touchX = event.getX();
                touchY = event.getY();

                invalidate();
                return true;
        }

        return super.onTouchEvent(event);
    }


}
