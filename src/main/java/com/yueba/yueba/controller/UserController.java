package com.yueba.yueba.controller;

import com.yueba.yueba.common.JsonResult;
import com.yueba.yueba.model.User;
import com.yueba.yueba.service.UserService;
import com.yueba.yueba.service.UserVipService;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.val;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/10
 * @description
 **/
@RestController
@RequestMapping("/api/user")
@ApiModel(value = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserVipService userVipService;

    public  String fileUploadPath;

    @Value("${fileUpload}")
    public void setFileUpload(String fileUpload) {
        fileUploadPath = fileUpload;
    }

    @RequestMapping("/queryAll")
    @ApiOperation(value = "根据用户Id获取所有的用户基本信息", notes = "不同角色的返回数据量不同 普通用户10条 会员30条 ")
    public JsonResult queryAll() {
        return JsonResult.ok(userService.queryAll());
    }

    @PostMapping("/edit")
    @ApiOperation(value = "编辑用户基本信息", notes = "编辑用户基本信息 上传用户头像  修改名称  修改其他资料")
    public JsonResult upload(Long id, String nickname, Integer male,
                             Integer age, String location,
                             Integer money, Integer height,
                             String description, MultipartFile multipartFile) throws IOException {
        //处理文件上传
        String filePath = UUID.randomUUID().toString() + ".jpg";
        String fileName = "/image/" + filePath;
        System.out.println(fileUploadPath);
        IOUtils.copy(multipartFile.getInputStream(), new FileOutputStream(fileUploadPath + filePath));
        User user = new User();
        user.setId(id);
        user.setNickname(nickname);
        user.setMale(male);
        user.setAge(age);
        user.setLocation(location);
        user.setMoney(money);
        user.setHeight(height);
        user.setDescription(description);
        user.setFace(fileName);
        userService.update(user);
        return JsonResult.ok();
    }

    @RequestMapping("/beVip")
    @ApiOperation(value = "申请会员")
    public JsonResult beVip(Long userId) {
        boolean flag = userVipService.save(userId);
        if (flag) {
            return JsonResult.ok("申请完毕");
        }
        return JsonResult.ok("请不要重复提交");
    }

    @RequestMapping("/queryAllUserVip")
    @ApiOperation(value = "查询所有的用户申请信息")
    public JsonResult queryAllUserVip() {
        return JsonResult.ok(userVipService.queryAll());
    }

    @RequestMapping("/beVipPass")
    @ApiOperation(value = "通过用户会员信息")
    public JsonResult beVipPass(Long userId) {
        val user = userService.selectOneById(userId);
        if (user == null) {
            return JsonResult.errorMsg("当前用户不存在");
        }
        user.setRole(1);
        userService.update(user);
        userVipService.updateStatus(userId);
        return JsonResult.ok();
    }

}
