package com.yueba.yueba.controller;

import com.google.common.collect.Maps;
import com.yueba.yueba.common.CommonUtils;
import com.yueba.yueba.common.ImageCodeUtils;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/10
 * @description
 **/
@Controller
@RequestMapping("/api/user")
@ApiModel(value = "用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserVipService userVipService;

    public String fileUploadPath;

    @Value("${fileUpload}")
    public void setFileUpload(String fileUpload) {
        fileUploadPath = fileUpload;
    }

    @RequestMapping("/queryAll")
    @ApiOperation(value = "根据用户Id获取所有的用户基本信息,例如userId = 1 ", notes = "不同角色的返回数据量不同 普通用户10条 会员30条 ")
    public JsonResult queryAll() {
        Map<String, String> paramMap = Maps.newHashMap();
        return JsonResult.ok(userService.queryAll(paramMap));
    }


    @ResponseBody
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

    @ResponseBody
    @RequestMapping("/beVip")
    @ApiOperation(value = "申请会员")
    public JsonResult beVip(Long userId) {
        if (userId == null || userId == 0) {
            return JsonResult.ok("申请失败，请先登陆");
        }
        boolean flag = userVipService.save(userId);
        if (flag) {
            return JsonResult.ok("申请完毕");
        }
        return JsonResult.ok("管理员审核中，请不要重复提交");
    }

    @ResponseBody
    @RequestMapping("/queryAllUserVip")
    @ApiOperation(value = "查询所有的用户申请信息")
    public JsonResult queryAllUserVip(Integer page, Integer pageSize) {
        return JsonResult.ok(userVipService.queryAll(page, pageSize));
    }

    @ResponseBody
    @RequestMapping("/beVipPass")
    @ApiOperation(value = "通过用户会员信息")
    public JsonResult beVipPass(Long userId) {
        if (userId == null) {
            return JsonResult.errorMsg("用户id不存在 ");
        }
        val user = userService.selectOneById(userId);
        if (user == null) {
            return JsonResult.errorMsg("当前用户不存在");
        }
        user.setRole(1);
        userService.update(user);
        userVipService.updateStatus(user.getId());
        return JsonResult.ok();
    }

    @RequestMapping("/profile/{userId}")
    @ApiOperation(value = "用户详情页面")
    public String profile(@PathVariable Long userId, ModelMap modelMap) {
        if (userId == null) {
            return "/error/500";
        }
        val user = userService.selectOneById(userId);
        if (user == null) {
            return "/error/500";
        }
        modelMap.put("user", user);
        return "/user/profile";
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "/login";
    }

    @PostMapping("/loginSubmit")
    @ApiOperation(value = "用户/管理 提交登陆请求 ")
    @ResponseBody
    public JsonResult loginSubmit(HttpServletRequest request, String username, String password, String verifyCode, Integer role) {
        if (CommonUtils.isEmpty(username) || CommonUtils.isEmpty(password) || CommonUtils.isEmpty(verifyCode) || role == null) {
            return JsonResult.errorMsg("账号密码验证码不能为空");
        }
        if (!ImageCodeUtils.checkImageCode(request.getSession(), verifyCode)) {
            return JsonResult.errorMsg("验证码错误");
        }
        val flag = userService.checkLogin(username, password, role);
        if (flag) {
            val user = userService.selectOneByUserName(username);
            request.getSession().setAttribute("user", user);
            return JsonResult.ok("登陆成功");
        } else {
            return JsonResult.errorMsg("登陆失败,账号密码错误");
        }
    }
}
