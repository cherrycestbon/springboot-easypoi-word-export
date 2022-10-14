package com.example.demo.service.impl;

import cn.afterturn.easypoi.word.WordExportUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lenovo
 * @since 2022-10-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Override
    public void exportUsersToWord(HttpServletResponse response) throws Exception {
        //准备导出数据
        List<Map<String, Object>> listUsers = new ArrayList<>();
        //查询所有用户数据
        List<User> users = this.list();
        users.forEach(user -> {
            //一个map就对应一个模板
            Map<String, Object> map = new HashMap<>();
            map.put("name", user.getName());
            map.put("age", user.getAge());
            map.put("sex", user.getSex());
            map.put("address", user.getAddress());
            map.put("describes", user.getDescribes());
            //添加
            listUsers.add(map);
        });
        //导出word并指定word导出模板
        XWPFDocument doc = WordExportUtil.exportWord07("templates/user_template.docx", listUsers);
        //设置编码格式
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        //设置内容类型
        response.setContentType("application/octet-stream");
        //设置头及文件命名。
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("学生基本信息表.docx", StandardCharsets.UTF_8.name()));
        //写入
        doc.write(response.getOutputStream());
    }
}
