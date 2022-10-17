package com.example.demo.controller;

import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author lenovo
 * @since 2022-10-13
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    /**
     * word文档导出
     */
    @GetMapping("export-word")
    public void exportUsersToWord(HttpServletResponse response) throws Exception {
        userService.exportUsersToWord(response);
    }

    /**
     * word文档导出到一个模板中
     */
    @GetMapping("export-word-all")
    public void exportUsersToWordAll(HttpServletResponse response) throws Exception {
        userService.exportUsersToWordAll(response);
    }

    /**
     * word文档导出
     */
    @GetMapping("export-word-image")
    public void exportUsersToWordImage(HttpServletResponse response) throws Exception {
        userService.exportUsersToWordImage(response);
    }

    /**
     * word文档导出到一个模板中
     */
    @GetMapping("export-word-all-image")
    public void exportUsersToWordAllImage(HttpServletResponse response) throws Exception {
        userService.exportUsersToWordAllImage(response);
    }


}
