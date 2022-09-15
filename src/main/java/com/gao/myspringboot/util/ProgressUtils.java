package com.gao.myspringboot.util;

import com.gao.myspringboot.service.Impl.WordServiceImpl;
import com.gao.myspringboot.service.MyCollectionService;
import com.gao.myspringboot.service.UserWordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


/**
 * 调用DataUtils处理数据生成百分比，
 * 然后，往页面传值
 */
@Controller
public class ProgressUtils {
    @Autowired
    WordServiceImpl wordService;
    @Autowired
    UserWordService userWordService;
    @Autowired
    DataUtils dataUtils;
    @Autowired
    MyCollectionService myCollectionService;

    /**
     * 得到已背诵单词的数目
     *
     * @param model
     * @param userid
     * @param grade
     */
    public void progress(Model model, int userid, int grade) {
        int remembered_words_num = userWordService.getOneUserRememberedWordNumberByGrade(userid, grade);
        int grade_words_all_num = wordService.queryAllWordNumberByGrade(grade);
        int study_words_num = userWordService.getOneUserAllWordNumber(userid, grade);

        String studypercent = dataUtils.percent(study_words_num, grade_words_all_num);
        String percent = dataUtils.percent(remembered_words_num, grade_words_all_num);
        String unpercent = dataUtils.unpercent(remembered_words_num, grade_words_all_num);

        model.addAttribute("remember", percent);
        model.addAttribute("unremembered", unpercent);
        model.addAttribute("study", studypercent);
        return;
    }

    //得到收藏信息
    public void getCollection(Model model, int userid, int wordid) {
        //查询是否收藏过单词
        boolean isCollection = myCollectionService.isCollection(userid,wordid);
        if (isCollection == false) {
            model.addAttribute("msg", "加入收藏");
        } else {
            model.addAttribute("msg", "已经收藏");
        }
    }

}
