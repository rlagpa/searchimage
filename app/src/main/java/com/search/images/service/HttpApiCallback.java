package com.search.images.service;

import android.support.annotation.NonNull;

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
    T convertResponse(Response<T> response);
}

class SearchApiCallback implements HttpApiCallback<SearchResult> {

    @Override
    public void onResponse(@NonNull Call<SearchResult> call, @NonNull Response<SearchResult> response) {
        sendEvent(convertResponse(response));
    }

    @Override
    public SearchResult convertResponse(Response<SearchResult> response) {
        if (response.body() == null) {
            return SearchResult.INVALID;
        } else {
            SearchResult result = response.body();
            result.setInvalid(false);
            return result;
        }
    }

    @Override
    public void onFailure(@NonNull Call<SearchResult> call, @NonNull Throwable t) {

    }
}

class VisionApiCallback implements HttpApiCallback<Vision> {

    @Override
    public void onResponse(@NonNull Call<Vision> call, @NonNull Response<Vision> response) {
        sendEvent(convertResponse(response));
    }

    @Override
    public Vision convertResponse(Response<Vision> response) {
        if (response.body() == null || response.body().getResult() == null) {
            return Vision.INVALID;
        }

        List<Face> faces = response.body().getResult().getFaces();
        if (faces == null || faces.size() == 0) {
            return Vision.INVALID;
        } else {
            Vision result = response.body();
            result.setInValid(false);
            return result;
        }
    }

    @Override
    public void onFailure(@NonNull Call<Vision> call, @NonNull Throwable t) {

    }
}
