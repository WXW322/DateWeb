package com.yueba.yueba.service;

import com.google.common.collect.Lists;
import com.yueba.yueba.mapper.UserMapper;
import com.yueba.yueba.model.User;
import com.yueba.yueba.model.vo.UserVo;
import org.springframework.beans.BeanUtils;
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
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<UserVo> queryAll() {
        List<User> userList = userMapper.selectList(null);
        List<UserVo> userVos = Lists.newArrayList();
        userList.stream().forEach(user -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVos.add(userVo);
        });
        return userVos;
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(User user) {
        userMapper.updateById(user);
    }

    public User selectOneById(Long userId) {
        return userMapper.selectById(userId);
    }
}
