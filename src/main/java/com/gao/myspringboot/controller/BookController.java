package com.gao.myspringboot.controller;

import com.gao.myspringboot.pojo.Book;
import com.gao.myspringboot.service.Impl.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class BookController {
    @Autowired
    BookServiceImpl bookService;

    //查看所有书籍
    @GetMapping("/viewbook")
    public String ViewBook(Model model) {
        List<Book> book = bookService.list();
        model.addAttribute("book", book);
        return "user/book/select-book";
    }

    //具体进入某一书籍
    @GetMapping("/watchBook/{bookId}")
    public String watchBook(@PathVariable("bookId") Integer bookId, Model model) {
        Book book = bookService.getById(bookId);
        model.addAttribute("book", book);
        return "user/book/watch-book";
    }

}
