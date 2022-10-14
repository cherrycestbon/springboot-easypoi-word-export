package com.example.demo.service;

import com.example.demo.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletResponse;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lenovo
 * @since 2022-10-13
 */
public interface UserService extends IService<User> {
    /**
     * word文档导出
     * @param response
     * @throws Exception
     */
    void exportUsersToWord(HttpServletResponse response) throws Exception;
}
