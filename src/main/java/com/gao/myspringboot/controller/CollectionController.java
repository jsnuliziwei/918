package com.gao.myspringboot.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.myspringboot.pojo.User;
import com.gao.myspringboot.pojo.Word;
import com.gao.myspringboot.service.Impl.WordServiceImpl;
import com.gao.myspringboot.service.MyCollectionService;
import com.gao.myspringboot.service.UserWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CollectionController {
    @Autowired
    WordServiceImpl wordService;
    @Autowired
    UserWordService userWordService;
    @Autowired
    MyCollectionService myCollectionService;
    //去收藏or单词页面
    @GetMapping("/toCollection")
    public String toCollection() {
        return "user/collection";
    }

    //去单词收藏
    @GetMapping("/wordCollection")
    public String wordCollection(Model model, HttpSession session) {
        return "redirect:/wordCollection/1";
    }
    @Value("${pageSize}")
    public int pageSize;

    //去单词收藏
    @GetMapping("/wordCollection/{pageId}")
    public String wordCollectionByPage(@PathVariable("pageId") int pageId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Page<Word> p = myCollectionService.getCollectionWordsByPage(loginUser.getUserId(), pageId, pageSize);
        List<Word> word = p.getRecords();

        model.addAttribute("word", word);
        model.addAttribute("page", p);
        return "user/word/collection-word";
    }

    //取消单词收藏
    @GetMapping("/deleteWordCollection/{wordId}")
    public String deleteWordCollection(@PathVariable("wordId") Integer wordId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        myCollectionService.changeCollection(loginUser.getUserId(), wordId);
        return "redirect:/wordCollection";
    }

    //去生词本[已背过的单词]
    @GetMapping("/wordRaw")
    public String wordRaw(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        List<Word> word = userWordService.getOneUserAllWord(loginUser.getUserId());
        model.addAttribute("word", word);
        return "user/word/raw-word";
    }


    // 清除背诵记录
    @RequestMapping("/deleteWordRaw/{wordId}")
    public String deleteWordRaw(@PathVariable("wordId") Integer wordId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        userWordService.clearRemember(loginUser.getUserId(), wordId);
        return "redirect:/wordRaw";
    }
}
