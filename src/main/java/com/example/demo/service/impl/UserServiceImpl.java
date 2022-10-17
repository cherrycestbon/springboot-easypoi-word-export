package com.example.demo.service.impl;

import cn.afterturn.easypoi.entity.ImageEntity;
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
import java.util.concurrent.atomic.AtomicInteger;

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

    @Override
    public void exportUsersToWordAll(HttpServletResponse response) throws Exception {
        //准备导出数据
        Map<String, Object> mapList = new HashMap<>(4);
        List<Map<String, Object>> listUsers = new ArrayList<>();
        //查询所有用户数据
        List<User> users = this.list();
        //设置一个原子整型。
        AtomicInteger i= new AtomicInteger(1);
        //循环添加到一个集合中。
        users.forEach(user -> {
            Map<String, Object> map = new HashMap<>(6);
            //生成序号
            map.put("id", i.getAndIncrement());
            map.put("name", user.getName());
            map.put("age", user.getAge());
            map.put("sex", user.getSex());
            map.put("address", user.getAddress());
            map.put("describes", user.getDescribes());
            //添加到集合中，一个map就是一行
            listUsers.add(map);
        });
        //添加到返回集合中
        mapList.put("users", listUsers);
        //设置班级
        mapList.put("class", "六(1)班");
        //设置操作人
        mapList.put("operator","admin");
        //设置当前时间
        mapList.put("createTime","2022/02/17");
        //导出word并指定word导出模板
        XWPFDocument doc = WordExportUtil.exportWord07("templates/user_template_m.docx", mapList);
        //设置编码格式
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        //设置内容类型
        response.setContentType("application/octet-stream");
        //设置头及文件命名。
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("学生基本信息表.docx", StandardCharsets.UTF_8.name()));
        //写入
        doc.write(response.getOutputStream());
    }

    @Override
    public void exportUsersToWordImage(HttpServletResponse response) throws Exception {
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
            //添加简单图片
            map.put("image", this.imgFormatting("static/image/avatar.png", 100, 100));
            //添加
            listUsers.add(map);
        });
        //导出word并指定word导出模板
        XWPFDocument doc = WordExportUtil.exportWord07("templates/user_template_image.docx", listUsers);
        //设置编码格式
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        //设置内容类型
        response.setContentType("application/octet-stream");
        //设置头及文件命名。
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("学生基本信息表.docx", StandardCharsets.UTF_8.name()));
        //写入
        doc.write(response.getOutputStream());
    }

    @Override
    public void exportUsersToWordAllImage(HttpServletResponse response) throws Exception {
//准备导出数据
        Map<String, Object> mapList = new HashMap<>(4);
        List<Map<String, Object>> listUsers = new ArrayList<>();
        //查询所有用户数据
        List<User> users = this.list();
        //设置一个原子整型。
        AtomicInteger i= new AtomicInteger(1);
        //循环添加到一个集合中。
        users.forEach(user -> {
            Map<String, Object> map = new HashMap<>(6);
            //生成序号
            map.put("id", i.getAndIncrement());
            map.put("name", user.getName());
            map.put("age", user.getAge());
            map.put("sex", user.getSex());
            map.put("address", user.getAddress());
            map.put("describes", user.getDescribes());
            //添加简单图片
            map.put("image", this.imgFormatting("static/image/avatar.png", 100, 100));
            //添加到集合中，一个map就是一行
            listUsers.add(map);
        });
        //添加到返回集合中
        mapList.put("users", listUsers);
        //设置班级
        mapList.put("class", "六(1)班");
        //设置操作人
        mapList.put("operator","admin");
        //设置当前时间
        mapList.put("createTime","2022/02/17");
        //导出word并指定word导出模板
        XWPFDocument doc = WordExportUtil.exportWord07("templates/user_template_m_all_image.docx", mapList);
        //设置编码格式
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        //设置内容类型
        response.setContentType("application/octet-stream");
        //设置头及文件命名。
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("学生基本信息表.docx", StandardCharsets.UTF_8.name()));
        //写入
        doc.write(response.getOutputStream());
    }

    /**
     * 图片格式化，Word导出图片格式
     *
     * @param imgPath 图片路径
     */
    private ImageEntity imgFormatting(String imgPath, int width, int height) {
        //设置图片
        ImageEntity image = new ImageEntity(imgPath, width, height);
        //表格外添加简单图片
        image.setType(ImageEntity.URL);
        return image;
    }
}
