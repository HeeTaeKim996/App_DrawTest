package com.example.a33_plus_drawtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class TextView extends PosView
{
    private TextPaint textPaint;
    private StaticLayout staticLayout; // 텍스트를 측정하여 줄바꿈 자동 수행하며, draw 를 하는 역할
    private String text = "텍스트 표시. 내용이 길어지면 자동으로 줄넘김 됩니다";

    private int maxAvaiableWidth = 300;

    public TextView(Context context)
    {
        super(context);
        init();
    }
    public TextView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
        init();
    }
    public TextView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init()
    {
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(40f);

        InitStaticLayout();
    }

    private void InitStaticLayout()
    {
        if(android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.N)
        {
            staticLayout = StaticLayout.Builder.obtain(text, 0, text.length()
                    , textPaint, maxAvaiableWidth).setAlignment(Layout.Alignment.ALIGN_NORMAL)
                    .setLineSpacing(0.f, 1.f)
                    .setIncludePad(false)
                    .build();
        }
        else
        {
            staticLayout = new StaticLayout(text, textPaint, maxAvaiableWidth,
                    Layout.Alignment.ALIGN_NORMAL, 1.f, 0.f, false);
        }
    }


    public void SetText(String text)
    {
        this.text = text;
        InitStaticLayout();
        requestLayout(); // 크기가 바뀔 수 있으므로 재요청
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        super.onDraw(canvas);

        if(staticLayout == null) return;


        canvas.save(); // 현재 캔버스의 상태를 스택에 저장 ( == PUSH )

        // 테두리 두께만큼 안쪽으로 원점을 이동
        canvas.translate(strokeWidth, strokeWidth);
        // 원점을 영구 수정하기에, canvas.save, restore 로 원점을 복구하는 로직을 추가힘
        
        staticLayout.draw(canvas);

        canvas.restore(); // 스택에 저장했던 상태를 스택에 POP ( == POP )
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        if(super.onTouchEvent(event) == false) return true;

        invalidate();
        return true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        int desiredWidth = staticLayout != null ?
                staticLayout.getWidth() + (int) strokeWidth * 2 : 0;
        int desiredHeight = staticLayout != null ?
                staticLayout.getHeight() + (int) strokeWidth * 2 : 0;

        int finalWidth = resolveSizeAndState(desiredWidth, widthMeasureSpec, 0);
        int finalHeight = resolveSizeAndState(desiredHeight, heightMeasureSpec, 0);

        setMeasuredDimension(finalWidth, finalHeight);
    }


}
