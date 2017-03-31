package com.app.demo.widget.refresh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;

import com.app.demo.R;
import com.app.demo.widget.refresh.header.MaterialHeader;
import com.app.demo.widget.refresh.util.PtrLocalDisplay;


/**
 * 项目名称：goldwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/4/19 18:47
 */
public class MaterialFrameLayout extends PtrFrameLayout {

    private MaterialHeader header;

    public MaterialFrameLayout(Context context) {
        super(context);
        initViews();
    }

    public MaterialFrameLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        initViews();
    }

    public MaterialFrameLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initViews();
    }

    private void initViews() {
        header = new MaterialHeader(getContext());
        int[] colors = getResources().getIntArray(R.array.google_colors);
        header.setColorSchemeColors(colors);
        header.setLayoutParams(new PtrFrameLayout.LayoutParams(-1, -2));
        header.setPadding(0, PtrLocalDisplay.dp2px(50), 0, PtrLocalDisplay.dp2px(10));
        header.setPtrFrameLayout(this);
        setHeaderView(header);
        addPtrUIHandler(header);
        setPinContent(true);
    }

    public static boolean canChildScrollUp(View view) {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (view instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) view;
                return absListView.getChildCount() > 0
                        && (absListView.getFirstVisiblePosition() > 0 || absListView.getChildAt(0)
                        .getTop() < absListView.getPaddingTop());
            } else {
                return view.getScrollY() > 0;
            }
        } else {
            return view.canScrollVertically(-1);
        }
    }

    /**
     * Default implement for check can perform pull to refresh
     *
     * @param frame
     * @param content
     * @param header
     * @return
     */
    public static boolean checkContentCanBePulledDown(PtrFrameLayout frame, View content, View header) {
        return !canChildScrollUp(content);
    }

}
