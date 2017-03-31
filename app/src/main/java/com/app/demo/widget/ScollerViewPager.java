package com.app.demo.widget;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/8/24 10:19
 */
public class ScollerViewPager extends ViewPager {


    public ScollerViewPager(Context context) {
        super(context);
    }

    public ScollerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

//    private int lastX;
//    private int lastY;
//
//    public boolean dispatchTouchEvent(MotionEvent e) {
//        int curX = (int) e.getX();
//        int curY = (int) e.getY();
//
//        switch (e.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                break;
//            case MotionEvent.ACTION_MOVE:
//
//                int offersetX = Math.abs(curX) - Math.abs(lastX);
//                int offersetY = Math.abs(curY) - Math.abs(lastY);
//                if (offersetX < offersetY) {
//                    return true;
//                }
//
//                lastX = curX;
//                lastY = curY;
//                break;
//            case MotionEvent.ACTION_UP:
//                break;
//            case MotionEvent.ACTION_CANCEL:
//                break;
//        }
//        return super.dispatchTouchEvent(e);
//    }


}
