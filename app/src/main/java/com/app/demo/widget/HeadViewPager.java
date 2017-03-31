package com.app.demo.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/4/29 13:53
 */
public class HeadViewPager extends LinearLayout {


    private View headView;
    private int headHeight;
    private Scroller mScroller;
    private VelocityTracker mVelocityTracker;
    private int mTouchSlop;

    public OnScollerListener onScollerListener;

    public void setOnScollerListener(OnScollerListener onScollerListener) {
        this.onScollerListener = onScollerListener;
    }

    public HeadViewPager(Context context) {
        super(context);
        initView(context);
    }

    public HeadViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public void initView(Context context) {
        mScroller = new Scroller(context);
        final ViewConfiguration conf = ViewConfiguration.get(getContext());
        mTouchSlop = conf.getScaledTouchSlop();
    }

    protected void onFinishInflate() {
        if (headView != null && !headView.isClickable()) {
            headView.setClickable(true);
        }
        super.onFinishInflate();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        headView = getChildAt(0);
        measureChildWithMargins(headView, widthMeasureSpec, 0, MeasureSpec.UNSPECIFIED, 0);
        headHeight = headView.getMeasuredHeight();
        super.onMeasure(widthMeasureSpec, heightMeasureSpec + headHeight);
        Log.e("TAG", "==============================" + headHeight);
        Log.e("TAG", "==============================" + getMeasuredHeight());
    }


    private int lastY;
    private int deltaY;

    public boolean dispatchTouchEvent(MotionEvent e) {
        int curY = (int) e.getY();
        Log.e("TAG", "=====curY=========================" + curY);
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:

//                initOrResetVelocityTracker();
//                mVelocityTracker.addMovement(e);
                if (mScroller != null) {
                    if (!mScroller.isFinished()) {
                        mScroller.forceFinished(true);
                    }
                }
                lastY = curY;
                break;
            case MotionEvent.ACTION_MOVE:

//                initVelocityTrackerIfNotExists();
//                mVelocityTracker.addMovement(e);
                Log.e("TAG", "============ACTION_MOVE==================" );
                deltaY = curY - lastY;
                int shiftY = Math.abs(deltaY);
                Log.e("TAG", "==============================" + (deltaY));

                Log.e("TAG", "============scroller==================" + getScrollY());

//                if (shiftY > mTouchSlop) {
//                    offsetTopAndBottom((int)(deltaY/1.0f));
//                    scrollBy(0, (int) (-deltaY + 0.5));
//                    postInvalidate();
//                }

//                if(getScrollY() >= headHeight && !onScollerListener.isOnTop()){
//                    return super.dispatchTouchEvent(e);
//                }

//                if(!onScollerListener.isOnTop() && lastY > 0 ){
                scrollBy(0, (int) (-deltaY + 0.5));
//                }
                lastY = curY;

                break;
            case MotionEvent.ACTION_UP:
                Log.e("TAG", "============ACTION_UP==================");
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return super.dispatchTouchEvent(e);
    }

    boolean isTop = false;

    public void scrollBy(int x, int y) {
        int scrollY = getScrollY();
        int toY = scrollY + y;
        if (toY >= headHeight) {
            toY = headHeight;
            isTop = true;
        } else if (toY <= 0) {
            toY = 0;
        }
        y = toY - scrollY;
        super.scrollBy(x, y);
    }

    public void scrollTo(int x, int y) {
        if (y >= headHeight) {
            y = headHeight;
            isTop = true;
        } else if (y <= 0) {
            y = 0;
            isTop = false;
        }
//        mCurY = y;
        super.scrollTo(x, y);
    }


    public void computeScroll() {
        // 因为前面startScroll，所以只有在startScroll完成时 才会为false
        if (mScroller.computeScrollOffset()) {
            final int currY = mScroller.getCurrY();
            scrollTo(0, currY);
        }
        invalidate();
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

    private void recycleVelocityTracker() {
        if (mVelocityTracker != null) {
            mVelocityTracker.recycle();
            mVelocityTracker = null;
        }
    }

    public interface OnScollerListener {
        boolean isOnTop();
    }
}
