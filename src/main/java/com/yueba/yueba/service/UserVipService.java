package com.yueba.yueba.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yueba.yueba.mapper.UserVipMapper;
import com.yueba.yueba.model.UserVip;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
        userVipMapper.insert(userVip);
        return true;
    }

    public List<UserVip> queryAll() {
        return userVipMapper.selectList(null);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateStatus(Long userId) {
        val userVip = userVipMapper.selectById(userId);
        userVip.setStatus(1);
        userVipMapper.updateById(userVip);
    }
}
