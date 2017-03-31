package com.app.demo.widget.loadmore;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.demo.R;

/**
 * 项目名称：integrationwall
 * 类的描述：
 * 创建人：alan
 * 创建时间：2016/8/24 17:50
 */
public class LoadMoreFooterView extends LinearLayout implements LoadMoreUIHandler,View.OnClickListener {

    private ProgressBar progressBar;
    private TextView errorMessage;

    public LoadMoreFooterView(Context context) {
        this(context, null);
    }

    public LoadMoreFooterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void initView() {
        LayoutInflater.from(getContext()).inflate(R.layout.loadmore_footerview, this);
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        errorMessage = (TextView) findViewById(R.id.errorMessage);
        errorMessage.setOnClickListener(this);
    }

    @Override
    public void onLoading() {
        progressBar.setVisibility(VISIBLE);
        errorMessage.setVisibility(GONE);
    }

    public void onLoadFinish(boolean hasMore) {
        progressBar.setVisibility(GONE);
        if (hasMore) {
            onLoading();
        } else {
            setVisibility(GONE);
        }
    }

    @Override
    public void onLoadError() {
        progressBar.setVisibility(GONE);
        errorMessage.setVisibility(VISIBLE);
    }

    public void onClick(View v) {
        onLoading();
    }
}
