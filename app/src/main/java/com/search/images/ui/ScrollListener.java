package com.search.images.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
/**
 * Created by hyemi on 14/04/2019.
 */
public class ScrollListener extends RecyclerView.OnScrollListener {
    private LinearLayoutManager layoutManager;
    private boolean loading = true;
    private int pastVisibleItems;
    private int visibleItemCount;
    private int totalItemCount;

    public ScrollListener(LinearLayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        if(dy > 0) //check for scroll down
        {
            visibleItemCount = layoutManager.getChildCount();
            totalItemCount = layoutManager.getItemCount();
            pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

            if (loading)
            {
                if ( (visibleItemCount + pastVisibleItems) >= totalItemCount)
                {
                    loading = false;
                    Log.e("...", "Last Item Wow !");
                    //loadData();
                }
            }
        }
    }
}
