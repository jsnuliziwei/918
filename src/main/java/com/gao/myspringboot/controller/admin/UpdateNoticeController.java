package com.gao.myspringboot.controller.admin;

import com.gao.myspringboot.pojo.Notice;
import com.gao.myspringboot.service.Impl.NoticeServiceImpl;
import com.gao.myspringboot.util.DataUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
public class UpdateNoticeController {
    @Autowired
    NoticeServiceImpl noticeService;
    @Autowired
    DataUtils dataUtils;

    /**
     * 查看公告
     */
    @GetMapping("/admin/viewNotice")
    public String viewNotice(Model model) {
        List<Notice> notice = noticeService.queryAllNotice();
        model.addAttribute("notice", notice);
        return "admin/Notice-View";
    }


    /**
     * 修改公告
     */
    //去修改页面
    @GetMapping("/admin/toUpdateNotice/{noticeId}")
    public String toUpdateNotice(@PathVariable("noticeId") Integer noticeId, Model model) {

        Notice notice = noticeService.queryNoticeById(noticeId);
        model.addAttribute("notice", notice);
        return "admin/Notice-Update";
    }

    //进行修改提交，回到view页面
    @PostMapping("/admin/updateNotice/{noticeId}")
    public String updateNotice(@PathVariable("noticeId") Integer noticeId, Notice notice) {

        Date creatTime = notice.getCreatTime();
        System.out.println(creatTime);
        noticeService.updateNotice(notice);
        return "redirect:/admin/viewNotice";
    }

    /**
     * 删除公告
     */

    @RequestMapping("/admin/deleteNotice/{noticeId}")
    public String deleteNotice(@PathVariable("noticeId") Integer noticeId) {

        noticeService.deleteNotice(noticeId);

        return "redirect:/admin/viewNotice";
    }

    /**
     * 增加公告
     */
    @GetMapping("/admin/toAddNotice")
    public String toAddNotice() {
        return "admin/Notice-Add";
    }

    @PostMapping("/admin/addNotice")
    public String addNotice(Notice notice) throws ParseException {
        //得到当前日期
        Date date = dataUtils.getDate();
        notice.setCreatTime(date);

        noticeService.addNotice(notice);
        return "redirect:/admin/viewNotice";
    }

}
