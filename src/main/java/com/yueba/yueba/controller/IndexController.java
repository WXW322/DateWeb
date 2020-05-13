package com.yueba.yueba.controller;

import com.yueba.yueba.common.ImageCodeUtils;
import com.yueba.yueba.model.SysMenu;
import com.yueba.yueba.service.SystemMenuService;
import com.yueba.yueba.service.UserService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/12
 * @description
 **/
@Controller
@RequestMapping("")
public class IndexController {
    @Autowired
    private SystemMenuService menuService;
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    @ApiOperation(value = "登陆页面", notes = "登陆页面")
    public String login() {
        return "login";
    }

    @RequestMapping("/loginSubmit")
    @ApiOperation(value = "登陆请求", notes = "登陆请求")
    @ResponseBody
    public String loginSubmit() {
        return "index";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("")
    @ApiOperation(value = "首页", notes = "首页")
    public String main(ModelMap modelMap) {
        modelMap.put("users", userService.queryAll());
        return "index2";
    }

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("index")
    @ApiOperation(value = "首页", notes = "首页")
    public String index(ModelMap modelMap) {
        List<SysMenu> menus = menuService.selectAllMenus();
        modelMap.put("menus", menus);
        return "index";
    }

    /**
     * 首页2
     *
     * @return
     */
    @RequestMapping("index2")
    @ApiOperation(value = "首页", notes = "首页")
    public String index2(ModelMap modelMap) {

        modelMap.put("users", userService.queryAll());
        return "index2";
    }

    /**
     * 图片验证码
     *
     * @param response
     * @param request
     */
    @ApiOperation(value = "发送图片验证码", notes = "发送图片验证码")
    @RequestMapping("/imageCode")
    public void sendImageCode(HttpServletResponse response, HttpServletRequest request) {
        ImageCodeUtils.sendImageCode(request.getSession(), response);
    }

    @RequestMapping("/layouts")
    @ApiOperation(value = "布局", notes = "布局")
    public String layouts() {
        return "layouts";
    }

    @ApiOperation(value = "欢迎页面", notes = "欢迎页面")
    @RequestMapping("/welcome")
    public String welcome() {
        return "welcome";
    }
}