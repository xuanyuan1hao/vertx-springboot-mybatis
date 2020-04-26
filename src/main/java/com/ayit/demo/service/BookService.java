package com.ayit.demo.service;

import com.ayit.demo.entity.Book;
import com.ayit.demo.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.StreamSupport;

import static java.util.stream.Collectors.toList;

@Service
public class BookService {

    //@Autowired
    //BookRepository bookRepository;
    @Autowired
    BookMapper bookRepository;

    public int save(Book book) {
        return bookRepository.save(book);
    }

    public List<Book> getAll() {
        List<Book> all = bookRepository.findAll();
       /* Iterable<Book> all = new ArrayList<>();
        Book book = null;
        for (int i=0;i<5;i++){
            book = new Book();
            book.setId(0L);
            book.setName("name _ "+i);
            ((ArrayList<Book>) all).add(book);
        }*/
        System.out.print("books.size = " + ((ArrayList<Book>) all).size());
        return StreamSupport.stream(all.spliterator(), false).collect(toList());
    }

}
