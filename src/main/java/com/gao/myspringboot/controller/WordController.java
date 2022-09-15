package com.gao.myspringboot.controller;

import com.gao.myspringboot.pojo.Grade;
import com.gao.myspringboot.pojo.User;
import com.gao.myspringboot.pojo.Word;
import com.gao.myspringboot.service.Impl.GradeServiceImpl;
import com.gao.myspringboot.service.Impl.WordServiceImpl;
import com.gao.myspringboot.service.MyCollectionService;
import com.gao.myspringboot.service.RedisService;
import com.gao.myspringboot.service.UserService;
import com.gao.myspringboot.service.UserWordService;
import com.gao.myspringboot.util.DataUtils;
import com.gao.myspringboot.util.ProgressUtils;
import com.gao.myspringboot.util.ScoreUtils;
import com.gao.myspringboot.util.WordRedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WordController {
    @Autowired
    private WordServiceImpl wordService;
    @Autowired
    GradeServiceImpl gradeService;
    @Autowired
    private ProgressUtils progressUtils;
    @Autowired
    private DataUtils dataUtils;
    @Autowired
    private UserWordService userWordService;
    @Autowired
    private UserService userService;
    @Autowired
    private ScoreUtils scoreUtils;
    @Autowired
    private MyCollectionService myCollectionService;
    @Autowired
    private WordRedisUtils wordRedisUtils;
    /**
     * 学习操作
     */
    //选择英语书籍
    @GetMapping("/selectWord")
    public String selectWord(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        List<Grade> grades = gradeService.list();
        Grade cet4 = grades.get(0);
        Grade cet6 = grades.get(1);
        //拿出id选择
        model.addAttribute("cet4", cet4.getGradeId());
        model.addAttribute("cet6", cet6.getGradeId());
        /**
         * 四级操作，查出已记住单词数量与所有单词数量，得到百分比
         */
        int Cet4Remember = userWordService.getOneUserAllWordNumber(loginUser.getUserId(), cet4.getGradeId());
        int Cet4Number = wordService.queryAllWordNumberByGrade(cet4.getGradeId());
        model.addAttribute("Cet4Remember", Cet4Remember);
        model.addAttribute("Cet4Number", Cet4Number);
        //调用工具类得到百分比
        String percent4 = dataUtils.percent(Cet4Remember, Cet4Number);
        model.addAttribute("percent4", percent4);
        /**
         * 六级操作，查出已记住单词数量与所有单词数量，得到百分比
         */
        int Cet6Remember = userWordService.getOneUserAllWordNumber(loginUser.getUserId(), cet6.getGradeId());
        int Cet6Number = wordService.queryAllWordNumberByGrade(cet6.getGradeId());
        model.addAttribute("Cet6Number", Cet6Number);
        model.addAttribute("Cet6Remember", Cet6Remember);
        //调用工具类得到百分比
        String percent6 = dataUtils.percent(Cet6Remember, Cet6Number);
        model.addAttribute("percent6", percent6);
        return "user/word/select-word";
    }

    //开始学习新单词
    @GetMapping("/studyWord/{grade}")
    public String studyWord(@PathVariable("grade") Integer grade, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Word word = userWordService.getOneNotStudyedWord(loginUser.getUserId(), grade); //根据选择不同的grade 背不同的单词
        if (!userService.isNewFinishedToday(loginUser.getUserId()) && word != null) { //如果用户今天没有完成新词任务，还有单词可以背
            //如果有就背，没有就提示为空
            model.addAttribute("word", word);
            wordRedisUtils.setWordCache(word);
            //progress工具是为了展示js动态页面
            progressUtils.progress(model, loginUser.getUserId(), grade);
            //查询是否收藏过单词
            progressUtils.getCollection(model, loginUser.getUserId(), word.getWordId());
            return "user/word/study-word";
        } else if (!userService.isReviewFinishedToday(loginUser.getUserId())) { //如果用户今天没有完成复习任务，还有单词可以背
            word = userWordService.getOneTodayUnreviewWord(loginUser.getUserId(), grade);
            model.addAttribute("word", word);
            wordRedisUtils.setWordCache(word);
            progressUtils.progress(model, loginUser.getUserId(), grade);
            progressUtils.getCollection(model, loginUser.getUserId(), word.getWordId());
            return "user/word/study-word";
        } else { //
            progressUtils.progress(model, loginUser.getUserId(), grade);
            model.addAttribute("grade", grade);
            String content = scoreUtils.addFinishScore(loginUser.getUserId());
            model.addAttribute("msg", content);
            session.setAttribute("loginUser", userService.queryUserById(loginUser.getUserId()));
            System.out.println("已完成今日学习任务");
            return "user/word/empty-word";
        }
    }


    //   学习下一个单词
    @GetMapping("/studyNextWord/{grade}")
    public String studyNextWord(@PathVariable("grade") Integer grade) {
        return "redirect:/studyWord/" + grade;
    }

    // 重新学习四级单词
    @GetMapping("/ResetWord/{grade}")
    public String resetWord(@PathVariable("grade") Integer grade, Model model, HttpSession session) {
        //把study,remember清为0
        User loginUser = (User) session.getAttribute("loginUser");
        userWordService.removeOneUserAllUserWords(loginUser.getUserId(), grade);
        return "redirect:/studyWord/" + grade;
    }


    //记住单词该单词，数据库remember记为1，显示解释
    @GetMapping("/rememberWord/{wordId}")
    public String rememberWord(@PathVariable("wordId") Integer wordId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Word word = wordRedisUtils.getWordCache(wordId);
        if (userWordService.isUserWord(loginUser.getUserId(), wordId)) {
            userWordService.addRemember(loginUser.getUserId(), wordId); //本单词 记忆记录加1
            userService.addOneNumWordsReviewToday(loginUser.getUserId()); //本用户 今日复习单词数加1
        } else {
            userWordService.addRemember(loginUser.getUserId(), wordId); //本单词 记忆记录加1
            userService.addNumWordsToday(loginUser.getUserId()); //本用户 今日学习单词数加1
        }
        model.addAttribute("word", word);
        progressUtils.progress(model, loginUser.getUserId(), word.getGrade());
        return "user/word/remember-word";
    }


    //第一次忘记，数据库不修改数据，直接显示解释
    @GetMapping("/unrememberWord/{wordId}")
    public String unRememberWord(@PathVariable("wordId") int wordId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Word word = wordRedisUtils.getWordCache(wordId);
        userWordService.testAndSetUserWord(loginUser.getUserId(), wordId);
        userService.addNumWordsToday(loginUser.getUserId()); //今日学习单词数加1
        model.addAttribute("word", word);
        progressUtils.progress(model, loginUser.getUserId(), word.getGrade());
        return "user/word/unremembered-word";
    }


    //第一次记住，第二次忘记，数据库修改remember为0，继续下个单词学习
    @GetMapping("/forgetWord/{wordId}")
    public String forgetWord(@PathVariable("wordId") int wordId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        Word word = wordRedisUtils.getWordCache(wordId);
        Integer grade = word.getGrade();

        userWordService.minusOneRemember(loginUser.getUserId(), wordId);
        progressUtils.progress(model, loginUser.getUserId(), word.getGrade());
        return "redirect:/studyWord/" + grade;
    }

    @GetMapping("/addCollection/{wordId}")
    public String addCollection(@PathVariable("wordId") int wordId, Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        myCollectionService.changeCollection(loginUser.getUserId(), wordId);

        Word word = wordRedisUtils.getWordCache(wordId);
        model.addAttribute("word", word);
        progressUtils.progress(model, loginUser.getUserId(), word.getGrade());
        progressUtils.getCollection(model, loginUser.getUserId(), word.getWordId());
        return "user/word/study-word";
    }
}
