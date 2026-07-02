package com.example.a33_plus_drawtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class PngDrawView extends View
{
    private float touchX = -100f;
    private float touchY = -100f;
    private float width = 200f;
    private float height = 160f;

    private Paint paint;
    private Bitmap imageBitmap;
    private RectF destRect;


    public PngDrawView(Context context)
    {
        super(context);
        init();
    }

    public PngDrawView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }

    public PngDrawView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        paint = new Paint();

        // 이미지 필터링 활성화 (이미지 크기가 조절될 때 깨짐을 방지하고 부드럽게 처리)
        paint.setFilterBitmap(true);

        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flag_image);
        destRect = new RectF();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(imageBitmap == null || touchX < 0 || touchY < 0) return;

        float left =    touchX - width / 2.f;
        float right =   touchX + width / 2.f;
        float top =     touchY - height / 2.f;
        float bottom =  touchY + height / 2.f;

        destRect.set(left, top, right, bottom);

        canvas.drawBitmap(imageBitmap, null, destRect, paint);
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
