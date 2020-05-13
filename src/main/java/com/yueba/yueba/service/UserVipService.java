package com.yueba.yueba.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yueba.yueba.common.PageResult;
import com.yueba.yueba.mapper.UserVipMapper;
import com.yueba.yueba.model.UserVip;
import com.yueba.yueba.model.vo.UserVipVo;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/10
 * @description
 **/
@Service
public class UserVipService {
    @Autowired
    private UserVipMapper userVipMapper;

    @Transactional(rollbackFor = Exception.class)
    public boolean save(Long userId) {
        val queryWrapper = Wrappers.<UserVip>lambdaQuery().eq(UserVip::getUserId, userId);
        List<UserVip> userVips = userVipMapper.selectList(queryWrapper);
        if (userVips != null && userVips.size() >= 1) {
            return false;
        }
        UserVip userVip = new UserVip();
        userVip.setStatus(0);
        userVip.setUserId(userId);
        userVip.setCreatedAt(new Date());
        userVipMapper.insert(userVip);
        return true;
    }

    public PageResult queryAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        val list = userVipMapper.selectAll();
        PageInfo<UserVipVo> pageList = new PageInfo<UserVipVo>(list);
        PageResult pageResult = new PageResult();
        pageResult.setPage(page);
        pageResult.setTotal(pageList.getPages());
        pageResult.setRows(list);
        pageResult.setRecords(pageList.getTotal());
        return pageResult;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(Long userId) {
        val userVip = userVipMapper.selectById(userId);
        userVip.setStatus(1);
        userVipMapper.updateById(userVip);
    }
}
