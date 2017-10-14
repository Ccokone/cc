package com.tutorial.gandalf.web;

import com.tutorial.gandalf.PageInfo;
import com.tutorial.gandalf.user.User;
import com.tutorial.gandalf.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户信息控制器
 *
 * @author Cc
 */
@RequestMapping("/user")
@Controller
public class UserController {

    @RequestMapping("/list")
    public String getList(Model model,
                          @RequestParam(value = "page", required = false, defaultValue = "1") int page,
                          @RequestParam(value = "pageSize", required = false, defaultValue = "5") int pageSize) {
        UserService userService = new UserService();
        PageInfo<User> pageInfo = userService.getPageInfo(page, pageSize);
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("page", page);
        model.addAttribute("pageSize", pageSize);
        return "user_list";
    }
}
