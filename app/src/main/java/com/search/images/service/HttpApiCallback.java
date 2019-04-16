package com.search.images.service;

import com.search.images.model.search.SearchResult;
import com.search.images.model.vision.Face;
import com.search.images.model.vision.Vision;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public interface HttpApiCallback<T> extends Callback<T> {

    default void sendEvent(T result) {
        EventBus.getDefault().post(result);
    }
}

class SearchApiCallback implements HttpApiCallback<SearchResult> {

    @Override
    public void onResponse(Call<SearchResult> call, Response<SearchResult> response) {
        if (response.body() == null) {
            sendEvent(SearchResult.INVALID);
        } else {
            SearchResult result = response.body();
            result.setInvalid(false);
            sendEvent(result);
        }
    }

    @Override
    public void onFailure(Call<SearchResult> call, Throwable t) {

    }
}

class VisionApiCallback implements HttpApiCallback<Vision> {

    @Override
    public void onResponse(Call<Vision> call, Response<Vision> response) {
        if (response.body() == null || response.body().getResult() == null) {
            sendEvent(Vision.INVALID);
        }

        List<Face> faces = response.body().getResult().getFaces();
        if (faces == null || faces.size() == 0) {
            sendEvent(Vision.INVALID);
        } else {
            Vision result = response.body();
            result.setInValid(false);
            sendEvent(result);
        }
    }

    @Override
    public void onFailure(Call<Vision> call, Throwable t) {

    }
}
