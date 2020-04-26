package com.ayit.demo.common;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.RoutingContext;


/**
 * Created by lny on 2018/07/03.
 */
public class HttpUtils {

    public static void fireJsonResponse(HttpServerResponse response, int statusCode, String payload) {
        response.putHeader("content-type", "application/json; charset=utf-8").setStatusCode(statusCode).end(payload);
    }

    public static void fireTextResponse(RoutingContext routingContext, String text) {
        routingContext.response().putHeader("content-type", "text/html; charset=utf-8").end(text);
    }
}
