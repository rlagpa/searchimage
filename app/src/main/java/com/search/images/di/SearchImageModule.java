package com.search.images.di;

import android.content.Context;

import com.search.images.service.SearchService;
import com.search.images.ui.SearchRecyclerAdapter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * define inject information
 */

@Module
public class SearchImageModule {
    Context context;

    public SearchImageModule(Context context) { this.context = context; }

    @Provides
    SearchRecyclerAdapter imageRecyclerAdapter() {
        return new SearchRecyclerAdapter(context);
    }

    @Provides @Singleton
    SearchService SearchService() {
        return new SearchService();
    }

}
