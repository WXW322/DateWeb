package com.yueba.yueba.controller;

import com.yueba.yueba.common.JsonResult;
import com.yueba.yueba.model.Remark;
import com.yueba.yueba.service.RemarkService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/11
 * @description
 **/
@RestController
@RequestMapping("/api/remark")
public class RemarkController {
    @Autowired
    private RemarkService remarkService;
    @RequestMapping("/saveRemark")
    @ApiOperation(value = "保存留言信息",notes = "userId ,被留言用户id。markId 留言用户id ，remark 内容")
    public JsonResult saveRemark(Integer userId, Integer markId, String remark) {
        Remark r = new Remark();
        r.setCreatedAt(new Date());
        r.setUserId(userId);
        r.setMarkId(markId);
        r.setRemark(remark);
        remarkService.insert(r);
        return JsonResult.ok();
    }
    @RequestMapping("/queryAllRemark")
    @ApiOperation(value = "根据用户id查询出所有的留言信息")
    public JsonResult queryAllRemark(Long userId) {
        return JsonResult.ok(remarkService.queryAllByUserId(userId));
    }

}
