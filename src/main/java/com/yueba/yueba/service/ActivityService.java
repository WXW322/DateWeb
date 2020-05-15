package com.yueba.yueba.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yueba.yueba.common.PageResult;
import com.yueba.yueba.mapper.ActivityMapper;
import com.yueba.yueba.model.Activity;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/14
 * @description
 **/
@Service
public class ActivityService {
    @Autowired
    private ActivityMapper activityMapper;

    public PageResult queryAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        val list = activityMapper.selectList(null);
        PageInfo<Activity> pageList = new PageInfo<Activity>(list);
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setTotal(pageList.getPages());
        pageResult.setRows(list);
        pageResult.setRecords(pageList.getTotal());
        return pageResult;
    }

    public List<Activity> selectAll() {
        return activityMapper.selectList(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(Activity activity) {
        activityMapper.insert(activity);
    }
}
