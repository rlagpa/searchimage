package com.search.images.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hyemi on 15/04/2019.
 */
class ApiResultExecutors {

    static final Executor callbackExecutor = Executors.newFixedThreadPool(3);
}
