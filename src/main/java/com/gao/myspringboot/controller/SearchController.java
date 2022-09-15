package com.gao.myspringboot.controller;

import com.gao.myspringboot.pojo.Word;
import com.gao.myspringboot.service.Impl.WordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class SearchController {

    @Autowired
    private WordServiceImpl wordService;

    @RequestMapping(value = "/viewSearch", method = RequestMethod.GET)
    public String viewSearch(Model model) {

        return "user/search/search";
    }

    @RequestMapping(value = "/Search",method = RequestMethod.POST)
    public String searchResult(String word, Model model) {
        Word result_word = wordService.queryWordByWord(word);
        model.addAttribute("word", result_word);
        return "user/search/search";
    }
    @PostMapping("/searchExplanation")
    public String searchResultExplanation(String explanation, Model model) {
        List<Word> result_words = wordService.queryWordByExplanation(explanation);
        System.out.println(result_words);
        model.addAttribute("result_words", result_words);
        return "user/search/search";
    }
}
