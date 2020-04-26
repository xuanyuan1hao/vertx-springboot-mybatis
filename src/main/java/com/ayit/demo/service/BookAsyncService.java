package com.ayit.demo.service;

import com.ayit.demo.entity.Book;
import io.vertx.codegen.annotations.ProxyGen;
import io.vertx.core.AsyncResult;
import io.vertx.core.Handler;

import java.util.List;


@ProxyGen
public interface BookAsyncService {



    /**
     * The service address on the Vert.x event bus.
     */
    String ADDRESS = BookAsyncService.class.getName();

    /**
     * 添加书
     * @param book
     * @param resultHandler
     */
    void add(Book book, Handler<AsyncResult<Integer>> resultHandler);

    /**
     * 查询所有书
     * @param resultHandler
     */
    void getAll(Handler<AsyncResult<List<Book>>> resultHandler);
}
