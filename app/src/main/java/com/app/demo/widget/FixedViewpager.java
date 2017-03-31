package com.app.demo.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 项目名称：goldwall
 * 类的描述：自定义自动滚动的ViewPager
 * 创建人：alan
 * 创建时间：2016/4/18 16:34
 */
public class FixedViewpager extends ViewPager {


    public FixedViewpager(Context context) {
        this(context, null);
    }

    public FixedViewpager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    //自定义ViewPager宽高比
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMeasuredWidth();
        int height = 0;
        if (width != 0) {
            height = MeasureSpec.makeMeasureSpec((width / 2), MeasureSpec.EXACTLY);
        }
        super.onMeasure(widthMeasureSpec, height);
    }

//    public boolean dispatchTouchEvent(MotionEvent ev) {
//        Log.e("TAG"," ===========parent ===========" + getParent().getParent().getParent().getParent());
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                getParent().getParent().getParent().getParent().requestDisallowInterceptTouchEvent(true);
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }


}
