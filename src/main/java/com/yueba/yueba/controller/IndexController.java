package com.yueba.yueba.controller;

import com.yueba.yueba.common.ImageCodeUtils;
import com.yueba.yueba.model.SysMenu;
import com.yueba.yueba.model.vo.UserVo;
import com.yueba.yueba.service.ActivityService;
import com.yueba.yueba.service.SystemMenuService;
import com.yueba.yueba.service.UserService;
import io.swagger.annotations.ApiOperation;
import lombok.val;
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
    @Autowired
    private ActivityService activityService;

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

    @RequestMapping("developNext")
    @ApiOperation(value = "该页面待开发中", notes = "该页面待开发中")
    public String developNext() {
        return "/error/405";
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

    @RequestMapping("/search")
    @ApiOperation(value = "搜索查找")
    public String search() {
        return "search";
    }

    @RequestMapping("/searchForm")
    @ApiOperation(value = "搜索查找")
    public String searchForm(Integer startAge, Integer endAge, Integer startMoney, Integer endMoney, Integer male, ModelMap modelMap) {
        List<UserVo> userVos = userService.search(startAge, endAge, startMoney, endMoney, male);
        modelMap.put("users", userVos);
        return "/search";
    }


    @RequestMapping("/project")
    @ApiOperation(value = "项目")
    public String project(ModelMap modelMap) {
        val activityList = activityService.selectAll();
        modelMap.put("projects", activityList);
        return "project";
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