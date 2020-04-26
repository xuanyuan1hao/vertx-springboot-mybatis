/*
 * Copyright 2017 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package com.ayit.demo.service.impl;

import com.ayit.demo.entity.Book;
import com.ayit.demo.service.BookAsyncService;
import com.ayit.demo.service.BookService;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * Implements the {@link BookAsyncService}, delegating calls to the transactional {@link BookService}.
 *
 * @author Thomas Segismont
 */
@Component
public class BookAsyncServiceImpl implements BookAsyncService {

  @Autowired
  BookService bookService;

  @Override
  public void add(Book book, Handler<AsyncResult<Integer>> resultHandler) {
    try {
      int saved = bookService.save(book);
      Future.succeededFuture(saved).setHandler(resultHandler);
    }catch (Exception ex){
      ex.printStackTrace();
    }
  }

  @Override
  public void getAll(Handler<AsyncResult<List<Book>>> resultHandler) {
    System.out.print("BookAsyncServiceImpl.getAll \n");
    List<Book> all = bookService.getAll();
    System.out.print("books.size = "+((ArrayList<Book>) all).size());
    Future.succeededFuture(all).setHandler(resultHandler);
  }
}
