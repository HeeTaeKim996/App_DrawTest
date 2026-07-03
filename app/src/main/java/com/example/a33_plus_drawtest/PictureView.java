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

import androidx.constraintlayout.widget.ConstraintSet;

public class PictureView extends PosDrawser
{
    private Paint picturePaint;
    private Bitmap imageBitmap;
    private RectF destRect;

    public PictureView(Context context)
    {
        super(context);
        init();
    }
    public PictureView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }
    public PictureView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init()
    {
        picturePaint = new Paint();
        picturePaint.setFilterBitmap(true);
        imageBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.flag_image);
        destRect = new RectF();

        width = imageBitmap.getWidth();
        height = imageBitmap.getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);
        if(imageBitmap == null) return;

        destRect.set(strokeWidth, strokeWidth, width, height);
        canvas.drawBitmap(imageBitmap, null, destRect, picturePaint);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(super.onTouchEvent(event) == false) return true;

        switch(event.getAction())
        {

        }

        invalidate();
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int desiredWidth = imageBitmap != null ? imageBitmap.getWidth() + (int)strokeWidth : 0;
        int desiredHeight = imageBitmap != null ? imageBitmap.getHeight() + (int)strokeWidth: 0;

        int width = resolveSizeAndState(desiredWidth, widthMeasureSpec, 0);
        int height = resolveSizeAndState(desiredHeight, heightMeasureSpec, 0);
        // widthMeasureSpec : 부모 레이아웃이 XML 설정(WRAP_CONTENT, MATCH_PARENT..) 을 바탕으로
        //                    자식 뷰에게 허용하는 한도 크기
        // (3) 은 기본 0으로 알자.
        // (1) 원본 크기 , (2) 부모가 허용한 크기 를 고려하여, 최종 width, height 결정

        setMeasuredDimension(width, height);
    }





}
