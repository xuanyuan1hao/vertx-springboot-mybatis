package com.ayit.demo.handler;

import com.ayit.demo.common.HttpUtils;
import com.ayit.demo.entity.Book;
import com.ayit.demo.service.BookAsyncService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.json.JsonArray;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.templ.freemarker.FreeMarkerTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static java.net.HttpURLConnection.HTTP_CREATED;
import static java.net.HttpURLConnection.HTTP_OK;

public class BookHandler {

    public static void addBook(RoutingContext routingContext, BookAsyncService bookAsyncService) {

        Book book = new Book();
        book.setName("十万个为什么" + (new Random()).nextInt(999999));

        bookAsyncService.add(book, new Handler<AsyncResult<Integer>>() {
            @Override
            public void handle(AsyncResult<Integer> bookAsyncResult) {
                System.out.print("BookHandler.addBook \n");
                if (bookAsyncResult.succeeded()) {
                    routingContext.response().setStatusCode(HTTP_CREATED).end("success");
                } else {
                    routingContext.fail(bookAsyncResult.cause());
                }
            }
        });
    }


    public static void getAllBooks(RoutingContext routingContext, BookAsyncService bookAsyncService) {

        bookAsyncService.getAll(new Handler<AsyncResult<List<Book>>>() {
            @Override
            public void handle(AsyncResult<List<Book>> listAsyncResult) {
                System.out.print("BookHandler.getAllBooks \n");
                List<Book> result = listAsyncResult.result();
                JsonArray jsonArray = new JsonArray(result);
                HttpUtils.fireJsonResponse(routingContext.response(), HTTP_OK, jsonArray.encodePrettily());
            }
        });
    }

    public static void index(RoutingContext routingContext, FreeMarkerTemplateEngine templateEngine) {
        Map<String, Object> data = new HashMap<>();
        data.put("title", "Wiki home");
        try {
            System.out.println("=========*********===============");
            data.put("usersCount", 9999999);
            templateEngine.render(data, "template/index.ftl", bufferAsyncResult -> {
                routingContext.response().putHeader("Content-Type", "text/html");
                routingContext.response().end(bufferAsyncResult.result());
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            routingContext.response().end(ex.getMessage());
        }
    }
}
