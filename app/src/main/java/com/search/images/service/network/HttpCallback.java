package com.search.images.service.network;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Retrofit Result Callback (Site Url, Image Url)
 * get stream for making imageUrlList
 */
public class HttpCallback<T> implements Callback<T> {
    HttpResponseListener<T> listener;

    public HttpCallback(HttpResponseListener<T> listener) {
        this.listener = listener;
    }

    public void onResponse(Call<T> call, Response<T> response) {
        if (listener != null) {
            listener.onSuccess(response);
        }
    }

    public void onFailure(Call<T> call, Throwable t) {
        if (listener != null) {
            listener.onFail();
        }
    }
}
