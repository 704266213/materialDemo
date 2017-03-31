package com.app.demo.widget.loadmore;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.util.AttributeSet;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/8/25 10:31
 */
public class LinearLayoutRecyclerView extends AbstractRecyclerView {

    private LinearLayoutManager linearLayoutManager;

    public LinearLayoutRecyclerView(Context context) {
        super(context);
    }

    public LinearLayoutRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LinearLayoutRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setLayoutManager() {
        linearLayoutManager = new LinearLayoutManager(getContext());
        setLayoutManager(linearLayoutManager);
    }


    @Override
    public LinearLayoutManager getLinearLayoutManager() {
        return linearLayoutManager;
    }
}
