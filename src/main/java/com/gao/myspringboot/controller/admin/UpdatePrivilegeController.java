package com.gao.myspringboot.controller.admin;

import com.gao.myspringboot.pojo.Privilege;
import com.gao.myspringboot.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.ParseException;
import java.util.List;

@Controller
public class UpdatePrivilegeController {
    @Autowired
    private PrivilegeService privilegeService;

    /**
     * 查看权限
     */
    @GetMapping("/admin/viewPrivilege")
    public String viewPrivilege(Model model) {
        List<Privilege> privileges = privilegeService.list();
        model.addAttribute("privileges", privileges);
        return "admin/Privilege-View";
    }

    /**
     * 修改权限
     */
    @GetMapping ("/admin/toUpdatePrivilege/{privilegeId}")
    public String toUpdatePrivilege(@PathVariable("privilegeId") Integer privilegeId, Model model) {
        Privilege privilege = privilegeService.getById(privilegeId);
        model.addAttribute("privilege", privilege);
        return "admin/Privilege-Update";
    }

    //进行修改提交，回到view页面
    @PostMapping("/admin/updatePrivilege/{privilegeId}")
    public String updatePrivilege(@PathVariable("privilegeId") Integer privilegeId, Privilege privilege) {
        privilege.setId(privilegeId);
        privilegeService.updateById(privilege);
        return "redirect:/admin/viewPrivilege";
    }

    /**
     * 删除权限
     */

    @RequestMapping("/admin/deletePrivilege/{privilegeId}")
    public String deletePrivilege(@PathVariable("privilegeId") Integer privilegeId) {
        privilegeService.removeById(privilegeId);
        return "redirect:/admin/viewPrivilege";
    }

    /**
     * 增加权限
     */
    @GetMapping("/admin/toAddPrivilege")
    public String toAddPrivilege() {
        return "admin/Privilege-Add";
    }

    @PostMapping("/admin/addPrivilege")
    public String addPrivilege(Privilege privilege) throws ParseException {
        privilegeService.save(privilege);
        return "redirect:/admin/viewPrivilege";
    }
}
