package com.gao.myspringboot.controller.admin;

import com.gao.myspringboot.pojo.Listen;
import com.gao.myspringboot.pojo.Notice;
import com.gao.myspringboot.service.Impl.NoticeServiceImpl;
import com.gao.myspringboot.service.ListenService;
import com.gao.myspringboot.util.DataUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Controller
public class UpdateListenController {
    @Autowired
    ListenService listenService;
    @Autowired
    DataUtils dataUtils;

    /**
     * 查看听力
     */
    @GetMapping("/admin/viewListen")
    public String viewListen(Model model) {
        List<Listen> listen = listenService.list();
        model.addAttribute("listen", listen);
        return "admin/Listen-View";
    }


    /**
     * 修改听力内容
     */
    @GetMapping("/admin/toUpdateListen/{listenId}")
    public String toUpdateListen(@PathVariable("listenId") Integer listenId, Model model) {

        Listen listen = listenService.getById(listenId);
        model.addAttribute("listen", listen);
        return "admin/Listen-Update";
    }

    @PostMapping("/admin/updateListen/{listenId}")
    public String updateListen(@PathVariable("listenId") Integer listenId, Listen listen) {
        listenService.updateById(listen);
        return "redirect:/admin/viewListen";
    }

    /**
     * 删除听力内容
     */

    @RequestMapping("/admin/deleteListen/{listenId}")
    public String deleteListen(@PathVariable("listenId") Integer listenId) {
        listenService.removeById(listenId);
        return "redirect:/admin/viewListen";
    }

    /**
     * 增加听力内容
     */

    @GetMapping("/admin/toAddListen")
    public String toAddListen() {
        return "admin/Listen-Add";
    }

    @PostMapping("/admin/addListen")
    public String addListen(Listen listen) throws ParseException {
        listenService.save(listen);
        return "redirect:/admin/viewListen";
    }

}
