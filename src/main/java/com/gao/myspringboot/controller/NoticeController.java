package com.gao.myspringboot.controller;

import com.gao.myspringboot.pojo.Notice;
import com.gao.myspringboot.service.Impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    NoticeServiceImpl noticeService;

    //去历史公告页面
    @GetMapping("/toViewNotice")
    public String toViewNotice(Model model) {
        List<Notice> notice = noticeService.queryAllNotice();
        model.addAttribute("notice", notice);
        return "user/notice/select-notice";
    }

    //具体查看公告
    @GetMapping("/viewNotice/{noticeId}")
    public String viewNotice(@PathVariable("noticeId") Integer noticeId, Model model) {

        Notice notice = noticeService.queryNoticeById(noticeId);
        model.addAttribute("notice", notice);
        return "user/notice/watch-notice";
    }
}
