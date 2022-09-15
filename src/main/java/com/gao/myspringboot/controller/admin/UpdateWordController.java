package com.gao.myspringboot.controller.admin;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.myspringboot.pojo.Word;
import com.gao.myspringboot.service.Impl.WordServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UpdateWordController {
    @Autowired
    WordServiceImpl wordService;

    /**
     * 查看单词
     */
    @GetMapping("/admin/viewWord")
    public String viewWord(Model model) {
        return "redirect:/admin/viewWord/1";
    }

    @Value("${pageSize}")
    public int pageSize;

    /**
     * 按页查看单词
     *
     * @param pageId
     * @param model
     * @return
     */
    @GetMapping("/admin/viewWord/{pageId}")
    public String viewWordByPage(@PathVariable("pageId") int pageId, Model model) {
        Page<Word> p = wordService.selectWordByPage(pageId, pageSize);
        List<Word> word = p.getRecords();
        model.addAttribute("page", p);
        model.addAttribute("pageId", pageId);

        model.addAttribute("word", word);
        return "admin/Word-View";
    }

    /**
     * 修改单词
     */
    @GetMapping("/admin/toUpdateWord/{wordId}")
    public String toUpdateWord(@PathVariable("wordId") Integer wordId, Model model) {


        Word word = wordService.queryWordById(wordId);
        model.addAttribute("word", word);
        return "admin/Word-Update";
    }

    //进行修改提交，回到view页面
    @PostMapping("/admin/updateWord/{wordId}")
    public String updateNotice(@PathVariable("wordId") Integer wordId, Word word) {

        wordService.updateWord(word);
        return "redirect:/admin/viewWord";
    }

    /**
     * 删除单词
     */

    @RequestMapping("/admin/deleteWord/{wordId}")
    public String deleteNotice(@PathVariable("wordId") Integer wordId) {

        wordService.deleteWord(wordId);


        return "redirect:/admin/viewWord";
    }

    /**
     * 增加单词
     */
    @GetMapping("/admin/toAddWord")
    public String toAddWord() {
        return "admin/Word-Add";
    }

    @PostMapping("/admin/addWord")
    public String addWord(Word word) {
        wordService.addWord(word);
        return "redirect:/admin/viewWord";
    }
}
