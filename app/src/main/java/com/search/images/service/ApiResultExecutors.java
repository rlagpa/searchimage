package com.search.images.service;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * thread pool for api callback
 */
class ApiResultExecutors {

    static final Executor callbackExecutor = Executors.newFixedThreadPool(3);
}
