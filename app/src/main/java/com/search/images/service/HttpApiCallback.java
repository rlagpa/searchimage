package com.search.images.service;

import com.search.images.model.search.SearchResultVO;
import com.search.images.model.vision.FaceVO;
import com.search.images.model.vision.VisionVO;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by hyemi on 15/04/2019.
 */
public interface HttpApiCallback<T> extends Callback<T> {

    default void sendEvent(T result) {
        EventBus.getDefault().post(result);
    }
}

class SearchApiCallback implements HttpApiCallback<SearchResultVO> {

    @Override
    public void onResponse(Call<SearchResultVO> call, Response<SearchResultVO> response) {
        if (response.body() == null) {
            sendEvent(SearchResultVO.INVALID);
        } else {
            SearchResultVO result = response.body();
            result.setInvalid(false);
            sendEvent(result);
        }
    }

    @Override
    public void onFailure(Call<SearchResultVO> call, Throwable t) {

    }
}

class VisionApiCallback implements HttpApiCallback<VisionVO> {

    @Override
    public void onResponse(Call<VisionVO> call, Response<VisionVO> response) {
        if (response.body() == null) {
            sendEvent(VisionVO.INVALID);
        }

        List<FaceVO> faces = response.body().getResult().getFaces();
        if (faces == null || faces.size() == 0) {
            sendEvent(VisionVO.INVALID);
        } else {
            VisionVO result = response.body();
            result.setInValid(false);
            sendEvent(result);
        }
    }

    @Override
    public void onFailure(Call<VisionVO> call, Throwable t) {

    }
}
