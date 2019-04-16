package com.search.images.di;

import com.search.images.ui.SearchFragment;
import com.search.images.ui.VisionFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = SearchImageModule.class)
public interface SearchImageComponent {
    void inject(SearchFragment fragment);
    void inject(VisionFragment fragment);
}
