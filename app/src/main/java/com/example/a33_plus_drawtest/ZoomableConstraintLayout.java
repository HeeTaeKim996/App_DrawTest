package com.example.a33_plus_drawtest;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;

import androidx.constraintlayout.widget.ConstraintLayout;

public class ZoomableConstraintLayout extends ConstraintLayout
{
    private TestIntetface testIntetface;
    private ScaleGestureDetector scaleDetector;
    private float scaleFactor = 1.f;
    private final float minScale = 1.f;
    private final float maxScale = 5.f;

    public void Initialize(TestIntetface testIntetface)
    {
        this.testIntetface = testIntetface;
    }


    public ZoomableConstraintLayout(Context context)
    {super(context);                        init(context);}
    public ZoomableConstraintLayout(Context context, AttributeSet attrs)
    {super(context, attrs);                 init(context);}
    public ZoomableConstraintLayout(Context context, AttributeSet attrs, int defStyleAttr)
    {super(context, attrs, defStyleAttr);   init(context);}

    public void init(Context context)
    {
        scaleDetector = new ScaleGestureDetector(context,
                new ScaleGestureDetector.SimpleOnScaleGestureListener()
        {
            @Override
            public boolean onScale(ScaleGestureDetector detector)
            {
                scaleFactor *= detector.getScaleFactor();
                scaleFactor = Math.max(minScale, Math.min(maxScale, scaleFactor));

                setScaleX(scaleFactor);
                setScaleY(scaleFactor);

                setPivotX(detector.getFocusX());
                setPivotY(detector.getFocusY());

                invalidate();
                return true;
            }

        });
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) 
    // 자식 뷰에 onTouchEvent 가 발동하기 전에, 위 함수가 먼저 발동됨
    {
        // 두 손가락 이상 터치시, 자식 뷰들의 터치 이벤트를 가로채서 줌 동작 수행
        if(ev.getPointerCount() > 1)
        {
            return true;
        }
        return super.onInterceptTouchEvent(ev);
        // retur true 일시, 자식 뷰의 onTouchEvent를 차단하며, 자신의 onTouchEvent 를 발동.
        // return false 일시, 차단하지 않음
    }

    @Override
    public boolean onTouchEvent(MotionEvent event)
    {
        scaleDetector.onTouchEvent(event);


        return true;
        // ACTION_DOWN 에서,
        // return true 일시, 현 View 가 이벤트를 소유하여,
        // 후속 이벤트(ACTION_MOVE, ACTION_UP..) 등을 받을 수 있지만,
        // return false 라면, 소유권을 갖지 않고, 부모 뷰에 onTouchEvent를 발동

        // ACTION_DOWN 제외에서는, true 일시, 소유권을 유지. false 일시, 소유권을 갖지 않고,
        // 아무도 소유권을 갖지 않음(이벤트 종료)
    }

}
