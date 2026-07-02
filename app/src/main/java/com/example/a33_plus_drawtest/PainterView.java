package com.example.a33_plus_drawtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import androidx.core.graphics.BitmapCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;


public class PainterView extends View
{
    private Bitmap canvasBitmap;
    private Canvas bitmapCanvas;

    private Paint paint;
    private Path drawPath;
    private Paint canvasPaint;


    private  boolean isEraseMode = false;

    public PainterView(Context context)
    {
        super(context);
        init();
    }
    public PainterView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }
    public PainterView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void init()
    {
        drawPath = new Path();

        paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStrokeWidth(12f);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);


        // 비트맵을 화면에 뿌릴 때 사용할 Paint
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh)
    {
        super.onSizeChanged(w, h, oldw, oldh);

        // 화면 크기와 동일한 RGBA_8888 의 비트맵을 생성
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        // 생성한 비트맵에 대응되는 가상의 캔버스를 생성
        bitmapCanvas = new Canvas(canvasBitmap);
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(canvasBitmap != null)
        {
            canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        float touchX = event.getX();
        float touchY = event.getY();

        switch(event.getAction())
        {
            case MotionEvent.ACTION_DOWN:
                drawPath.moveTo(touchX, touchY);
                break;

            case MotionEvent.ACTION_MOVE:
                drawPath.lineTo(touchX, touchY);

                bitmapCanvas.drawPath(drawPath, paint);
                drawPath.reset();
                drawPath.moveTo(touchX, touchY);

                break;

            case MotionEvent.ACTION_UP:
                bitmapCanvas.drawPath(drawPath, paint);
                drawPath.reset();
                break;

            default:
                return false;
        }

        invalidate();
        return true;
    }

    public void EraseAll()
    {
        if(bitmapCanvas != null)
        {
            bitmapCanvas.drawColor(Color.TRANSPARENT, android.graphics.PorterDuff.Mode.CLEAR);
        }
        invalidate();
    }

    public void SetPaintColor(int color)
    {

        paint.setXfermode(null);
        paint.setColor(color);
        paint.setStrokeWidth((12f));
    }

    public void SetEraserMode()
    {
        paint.setXfermode(new android.graphics.PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        paint.setStrokeWidth(80f);
    }



    public void SaveCanvas(Context context)
    {
        if(canvasBitmap == null) return;

        File file = new File(context.getFilesDir(), "BitmapData.dat");

        try
        {
            FileOutputStream fos = new FileOutputStream(file);
            canvasBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

    }

    public void LoadCanvas(Context context)
    {
        File file = new File(context.getFilesDir(), "BitmapData.dat");
        if(file.exists() == false) return;

        Bitmap srcBitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
        if(srcBitmap == null) return;

        // 픽셀 수정이 가능하도록 (2)mutable 을 true
        canvasBitmap = srcBitmap.copy(Bitmap.Config.ARGB_8888, true);

        bitmapCanvas = new Canvas(canvasBitmap);

        srcBitmap.recycle(); // 메모리 해제

        invalidate();
    }


    private  boolean sizeBool = false;
    private int initWidth = -1;
    private int initHeight = -1;

    public void ChangeSize()
    {
        ViewGroup.LayoutParams params = this.getLayoutParams();



        if(params != null)
        {
            if(initWidth == -1)
            {
                initWidth = params.width;
                initHeight = params.height;
            }


            if(sizeBool)
            {
                params.width = initWidth;
                params.height = initHeight;
            }
            else
            {
                params.width = (int)(initWidth * 0.7f);
                params.height = (int)(initHeight * 0.7f);
            }

            this.setLayoutParams(params);
            sizeBool = !sizeBool;
        }

    }

}





