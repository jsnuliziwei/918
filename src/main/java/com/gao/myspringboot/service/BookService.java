package com.gao.myspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.myspringboot.pojo.Book;

import java.util.List;

public interface BookService extends IService<Book> {
    //增加书
    int addBook(Book book);

    //删除书
    int deleteBook(Integer bookId);

    //修改书
    int updateBook(Book book);

}
