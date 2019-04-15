package com.search.images.di;

import android.content.Context;

import com.search.images.service.SearchService;
import com.search.images.ui.ImageRecyclerAdapter;

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
    ImageRecyclerAdapter imageRecyclerAdapter() {
        return new ImageRecyclerAdapter(context);
    }

    @Provides @Singleton
    SearchService SearchService() {
        return new SearchService();
    }

}
