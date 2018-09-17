package com.levislv.stickyrecyclerview.widget;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.levislv.stickyrecyclerview.R;

import java.util.List;

public class StickyRecyclerView extends RelativeLayout {

    private View mLayoutPreTitle;
    private TextView mTvPreTitle;
    private View mLayoutTitle;
    private TextView mTvTitle;
    private RecyclerView mRecyclerView;

    private StickyAdapter mStickyAdapter;

    public StickyRecyclerView(Context context) {
        this(context, null);
    }

    public StickyRecyclerView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StickyRecyclerView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initData(context);
        initView(context);
    }

    private void initData(Context context) {
        mStickyAdapter = new StickyAdapter(context);
    }

    private void initView(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.widget_stickyrecyclerview, this, true);

        mLayoutPreTitle = view.findViewById(R.id.layout_pre_title);
        mTvPreTitle = view.findViewById(R.id.tv_pre_title);
        mLayoutTitle = view.findViewById(R.id.layout_title);
        mTvTitle = view.findViewById(R.id.tv_title);
        mRecyclerView = view.findViewById(R.id.recycler_view);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(mStickyAdapter);
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                View view = recyclerView.findChildViewUnder(0, 0);
                if (view == null) {
                    mLayoutPreTitle.setVisibility(View.INVISIBLE);
                    mLayoutTitle.setVisibility(View.INVISIBLE);
                } else {
                    mLayoutPreTitle.setVisibility(View.VISIBLE);
                    Object tag = view.getTag();
                    if (tag instanceof Bundle) {
                        Bundle bundle = (Bundle) tag;
                        String letter = bundle.getString(StickyAdapter.BUNDLE_KEY_LETTER);
                        mTvTitle.setText(letter);
                        if (bundle.getBoolean(StickyAdapter.BUNDLE_KEY_HAS_STICKY_VIEW)) {
                            if (view.getTop() > -mLayoutTitle.getMeasuredHeight()) {
                                mTvPreTitle.setText(bundle.getString(StickyAdapter.BUNDLE_KEY_PRE_LETTER));
                                mLayoutPreTitle.setTranslationY(view.getTop()); // 随之滑动
                                mLayoutTitle.setTranslationY(view.getTop()); // 随之滑动
                                mLayoutTitle.setVisibility(View.VISIBLE);
                            } else {
                                mTvPreTitle.setText(letter); // 替换内容
                                mLayoutPreTitle.setTranslationY(0); // 恢复原样
                                mLayoutTitle.setVisibility(View.GONE);
                            }
                        } else {
                            mTvPreTitle.setText(letter);
                            mLayoutPreTitle.setTranslationY(0);
                            mLayoutTitle.setVisibility(View.GONE);
                        }
                    }
                }
            }
        });
    }

    public void setDataSource(List<Model> list) {
        mStickyAdapter.setDataSource(list);
        if (list == null || list.isEmpty()) {
            mLayoutTitle.setVisibility(View.INVISIBLE);
        } else {
            mLayoutTitle.setVisibility(View.VISIBLE);
            mRecyclerView.scrollToPosition(0);
            mTvTitle.setText(list.get(0).getLetter());
        }
    }

    public RecyclerView get() {
        return mRecyclerView;
    }

    public StickyAdapter getAdapter() {
        return mStickyAdapter;
    }
}
