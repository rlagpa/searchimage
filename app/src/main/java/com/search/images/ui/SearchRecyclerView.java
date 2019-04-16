package com.search.images.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.search.images.model.search.SearchResult;

public class SearchRecyclerView extends RecyclerView {
    private SearchRecyclerAdapter adapter;


    public SearchRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public SearchRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SearchRecyclerView(Context context) {
        super(context);
    }

    public void setData(SearchResult vo) {
        adapter.setData(vo.getDocuments());
    }

    public void initAdapter(SearchRecyclerAdapter adapter) {
        this.adapter = adapter;
        setAdapter(this.adapter);
    }
}
