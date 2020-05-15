package com.yueba.yueba.controller;

import com.yueba.yueba.common.DateUtils;
import com.yueba.yueba.common.JsonResult;
import com.yueba.yueba.model.Activity;
import com.yueba.yueba.service.ActivityService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.beans.PropertyEditorSupport;
import java.util.Date;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/14
 * @description
 **/
@Controller
@RequestMapping("/activity")
public class ActivityController {

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    @Autowired
    private ActivityService activityService;

    @RequestMapping("/list")
    @ApiOperation(value = "用户列表")
    public String list() {
        return "/activity/list";
    }

    @RequestMapping("/pageList")
    @ApiOperation(value = "列表")
    @ResponseBody
    public JsonResult pageList(Integer page, Integer pageSize) {
        return JsonResult.ok(activityService.queryAll(page, pageSize));
    }

    @RequestMapping("/queryAll")
    @ApiOperation(value = "列表")
    @ResponseBody
    public JsonResult pageList() {
        return JsonResult.ok(activityService.selectAll());
    }

    @RequestMapping("/add")
    public String add() {
        return "/activity/add";
    }


    @RequestMapping("/addSubmit")
    @ResponseBody
    public JsonResult addSubmit(Activity activity) {
        activity.setCreatedAt(new Date());
        activityService.insert(activity);
        return JsonResult.ok();
    }

}
