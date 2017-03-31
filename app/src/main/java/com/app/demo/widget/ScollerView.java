package com.app.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.ViewConfiguration;
import android.widget.FrameLayout;
import android.widget.Scroller;

/**
 * Created by Administrator on 2016/4/12.
 */
public class ScollerView extends FrameLayout {

    private Scroller mScroller;
    private int mTouchSlop;
    private int mMinimumVelocity;
    private int mMaximumVelocity;
    private VelocityTracker mVelocityTracker;


    public ScollerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScollerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        mScroller = new Scroller(context);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
        mMinimumVelocity = configuration.getScaledMinimumFlingVelocity();
        mMaximumVelocity = configuration.getScaledMaximumFlingVelocity();
    }


    private int mLastX;
    private int mLastY;

    public boolean dispatchTouchEvent(MotionEvent ev) {
        int currentX = (int) ev.getX();
        int currentY = (int) ev.getY();
        float deltaY;
        int shiftX = Math.abs(currentX - mLastX);
        int shiftY = Math.abs(currentY - mLastY);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mLastX = currentX;
                mLastY = currentY;
                initOrResetVelocityTracker();
                mVelocityTracker.addMovement(ev);
                if(mScroller != null){
                    if(!mScroller.isFinished()){
                        mScroller.forceFinished(true);
                    }
                }

                break;
            case MotionEvent.ACTION_MOVE:
                initVelocityTrackerIfNotExists();
                mVelocityTracker.addMovement(ev);
                Log.e("TAG", "===currentY========================" + currentY);
                deltaY = currentY - mLastY;
                Log.e("TAG", "===deltaY========================" + deltaY);
                if (shiftY > mTouchSlop && shiftY > shiftX) {
//                    scrollBy(0, (int) (-deltaY + 0.5));
                    getChildAt(1).offsetTopAndBottom((int) (deltaY));
                }

                mLastX = currentX;
                mLastY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                break;
            default:
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    public void computeScroll() {
        invalidate();
    }
//
//    @Override
//    public void scrollBy(int x, int y) {
//        int scrollY = getScrollY();
//        int toY = scrollY + y;
//        y = toY - scrollY;
//        super.scrollBy(x, y);
//    }

    @Override
    public void scrollTo(int x, int y) {
        super.scrollTo(x, y);
    }

    private void initOrResetVelocityTracker() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        } else {
            mVelocityTracker.clear();
        }
    }

    private void initVelocityTrackerIfNotExists() {
        if (mVelocityTracker == null) {
            mVelocityTracker = VelocityTracker.obtain();
        }
    }

}
