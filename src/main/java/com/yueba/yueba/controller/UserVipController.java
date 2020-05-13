package com.yueba.yueba.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/13
 * @description
 **/
@Controller
@RequestMapping("/userVip")
public class UserVipController {
    @RequestMapping("/list")
    @ApiOperation(value = "用户列表")
    public String list() {
        return "/userVip/list";
    }
}
