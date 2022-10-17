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

    /**
     * word文档导出到一个模板中
     * @param response
     * @throws Exception
     */
    void exportUsersToWordAll(HttpServletResponse response) throws Exception;

    /**
     * word文档导出带图片
     * @param response
     * @throws Exception
     */
    void exportUsersToWordImage(HttpServletResponse response) throws Exception;

    /**
     * word文档导出到一个模板中带图片
     * @param response
     * @throws Exception
     */
    void exportUsersToWordAllImage(HttpServletResponse response) throws Exception;
}
