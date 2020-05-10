package com.yueba.yueba.controller;

import com.yueba.yueba.common.JsonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/10
 * @description
 **/
@RestController
@RequestMapping("/welcome")
public class WelcomeController {
    @RequestMapping("")
    public JsonResult welcome() {
        return JsonResult.ok("欢迎页面");
    }
}
