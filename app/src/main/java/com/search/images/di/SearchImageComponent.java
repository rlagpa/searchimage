package com.search.images.di;

import com.search.images.ui.ImageFragment;
import com.search.images.ui.VisionFragment;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = SearchImageModule.class)
public interface SearchImageComponent {
    void inject(ImageFragment fragment);
    void inject(VisionFragment fragment);
}
