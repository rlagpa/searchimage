package com.search.images.service.network;

import retrofit2.Response;

/**
 * Retrofit 통신 성공 시 response를 listener로 전달
 * to parsing making imageUrlList
 */
public interface HttpResponseListener<T> {
    void onSuccess(Response<T> response);
    default void onFail() {}
}
