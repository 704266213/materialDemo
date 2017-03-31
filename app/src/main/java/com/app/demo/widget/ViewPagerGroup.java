package com.app.demo.widget;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Scroller;

import com.app.demo.ScollerViewPagerAcitivy;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/4/25 19:24
 */
public class ViewPagerGroup extends ViewGroup {

    private Context mContext;
    private Scroller mScroller;
    private int mTouchSlop;

    public ViewPagerGroup(Context context) {
        super(context);
        mContext = context;
        init(context);
    }

    public ViewPagerGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(context);
    }

    private void init(Context context) {
        // 初始化3个 LinearLayout控件
        LinearLayout oneLL = new LinearLayout(mContext);
        oneLL.setBackgroundColor(Color.RED);
        addView(oneLL);

        LinearLayout twoLL = new LinearLayout(mContext);
        twoLL.setBackgroundColor(Color.YELLOW);
        addView(twoLL);

        LinearLayout threeLL = new LinearLayout(mContext);
        threeLL.setBackgroundColor(Color.BLUE);
        addView(threeLL);

        mScroller = new Scroller(context);
        final ViewConfiguration configuration = ViewConfiguration.get(context);
        mTouchSlop = configuration.getScaledTouchSlop();
    }

    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(width, height);
        int childcount = getChildCount();
        for (int i = 0; i < childcount; i++) {
            View child = getChildAt(i);
            // 设置每个子视图的大小 ， 即全屏
            child.measure(ScollerViewPagerAcitivy.screenWidth, ScollerViewPagerAcitivy.scrrenHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int startLeft = 0; // 每个子视图的起始布局坐标
        int startTop = 10; // 间距设置为10px 相当于 android：marginTop= "10px"
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = getChildAt(i);
            child.layout(startLeft, startTop, startLeft + ScollerViewPagerAcitivy.screenWidth,
                    startTop + ScollerViewPagerAcitivy.scrrenHeight);
            startLeft = startLeft + ScollerViewPagerAcitivy.screenWidth; //校准每个子View的起始布局位置
            //三个子视图的在屏幕中的分布如下 [0 , 320] / [320,640] / [640,960]
        }
    }


    private int mLastX;
    private int mLastY;
    private int curscreen = 0;

    private boolean isScollRight = false;
    //处理触摸的速率
    private VelocityTracker mVelocityTracker = null ;
    //处理触摸事件 ~
    public static int  SNAP_VELOCITY = 200 ;

    public boolean onTouchEvent(MotionEvent ev) {
        if(mVelocityTracker == null){
            mVelocityTracker = VelocityTracker.obtain();
        }
        mVelocityTracker.addMovement(ev);

        int currentX = (int) ev.getX();
        int currentY = (int) ev.getY();
        float deltaX;
        int shiftX = Math.abs(currentX - mLastX);
        int shiftY = Math.abs(currentY - mLastY);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                if(mScroller != null){
                    if(!mScroller.isFinished()){
                        mScroller.abortAnimation();
                    }
                }
                mLastX = currentX;
                mLastY = currentY;
                break;
            case MotionEvent.ACTION_MOVE:
                if(mVelocityTracker != null)
                mVelocityTracker.addMovement(ev);

//                Log.e("TAG", "===currentY========================" + currentY);
                deltaX = currentX - mLastX;
//                Log.e("TAG", "===deltaY========================" + deltaY);
                if (shiftX > mTouchSlop && shiftX > shiftY) {
                    scrollBy((int) (-deltaX), 0);
                }
                mLastX = currentX;
                mLastY = currentY;
                break;
            case MotionEvent.ACTION_UP:
                final VelocityTracker velocityTracker = mVelocityTracker;
                velocityTracker.computeCurrentVelocity(1000);
                int velocityX = (int)  velocityTracker.getXVelocity();
                //滑动速率达到了一个标准(快速向右滑屏，返回上一个屏幕) 马上进行切屏处理
                if (velocityX > SNAP_VELOCITY && curScreen > 0) {
                    // Fling enough to move left
                    Log.e("TAG", "snap left");
                    snapToScreen(curScreen - 1);
                }
                //快速向左滑屏，返回下一个屏幕)
                else if(velocityX < -SNAP_VELOCITY && curScreen < (getChildCount()-1)){
                    Log.e("TAG", "snap right");
                    snapToScreen(curScreen + 1);
                }
                //以上为快速移动的 ，强制切换屏幕
                else{
                    //我们是缓慢移动的，因此先判断是保留在本屏幕还是到下一屏幕
                    snapToDestination();
                }

                if (mVelocityTracker != null) {
                    mVelocityTracker.recycle();
                    mVelocityTracker = null;
                }
                break;
            default:
                break;
        }
        return super.onTouchEvent(ev);
    }

    ////我们是缓慢移动的
    private void snapToDestination(){
        //当前的偏移位置
        int scrollX = getScrollX() ;
        int scrollY = getScrollY() ;

        Log.e("TAG", "### onTouchEvent snapToDestination ### scrollX is " + scrollX);

        //判断是否超过下一屏的中间位置，如果达到就抵达下一屏，否则保持在原屏幕
        //直接使用这个公式判断是哪一个屏幕 前后或者自己
        //判断是否超过下一屏的中间位置，如果达到就抵达下一屏，否则保持在原屏幕
        // 这样的一个简单公式意思是：假设当前滑屏偏移值即 scrollCurX 加上每个屏幕一半的宽度，除以每个屏幕的宽度就是
        //  我们目标屏所在位置了。 假如每个屏幕宽度为320dip, 我们滑到了500dip处，很显然我们应该到达第二屏
        int destScreen = (getScrollX() + getWidth() / 2 ) / getWidth() ;


        Log.e("TAG", "### onTouchEvent  ACTION_UP### dx destScreen " + destScreen);

        snapToScreen(destScreen);
    }

    private void snapToScreen(int whichScreen){
        //简单的移到目标屏幕，可能是当前屏或者下一屏幕
        //直接跳转过去，不太友好
        //scrollTo(mLastScreen * getWidth(), 0);
        //为了友好性，我们在增加一个动画效果
        //需要再次滑动的距离 屏或者下一屏幕的继续滑动距离

        curScreen = whichScreen ;

        if(curScreen > getChildCount() - 1)
            curScreen = getChildCount() - 1 ;

        int dx = curScreen*getWidth() - getScrollX() ;

        Log.e("TAG", "### onTouchEvent  ACTION_UP### dx is " + dx);

        mScroller.startScroll(getScrollX(), 0, dx, 0,Math.abs(dx) * 2);

        //此时需要手动刷新View 否则没效果
        invalidate();

    }


    public void computeScroll() {
        Log.e("TAG", "===computeScroll========================");
        // 如果返回true，表示动画还没有结束
        // 因为前面startScroll，所以只有在startScroll完成时 才会为false
        if (mScroller.computeScrollOffset()) {
            // 产生了动画效果 每次滚动一点
            scrollTo(mScroller.getCurrX(), mScroller.getCurrY());

            //刷新View 否则效果可能有误差
            postInvalidate();
        }

    }

//    public void scrollBy(int x, int y) {
//        Log.e("TAG", "===scrollBy========================");
//        super.scrollBy(x, y);
//    }
//
//    public void scrollTo(int x, int y) {
//        Log.e("TAG", "===scrollTo========================");
//        super.scrollTo(x, y);
//    }


    private int curScreen = 0;  //当前屏

    public void startMove() {
        curScreen++;
        Log.e("TAG", "----startMove---- curScreen " + curScreen);
        mScroller.startScroll((curScreen - 1) * getWidth(), 0, getWidth(), 0, 3000);
        //暴力点直接到目标出
        //scrollTo(curScreen * getWidth(), 0);
        //其实在点击按钮的时候，就回触发View绘制流程，这儿我们在强制绘制下View
        invalidate();
    }

    //停止滑屏
    public void stopMove() {
        if (mScroller != null) {
            //如果动画还没结束，我们就按下了结束的按钮，那我们就结束该动画，即马上滑动指定位置
            if (!mScroller.isFinished()) {
                int scrollCurX = mScroller.getCurrX();
                int descScreen = (scrollCurX + getWidth() / 2) / getWidth();
                mScroller.abortAnimation();

                //停止了动画，我们马上滑倒目标位置
                scrollTo(descScreen * getWidth(), 0);
                mScroller.forceFinished(true);
                curScreen = descScreen;
            }
        }
    }

}
