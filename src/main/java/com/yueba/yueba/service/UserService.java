package com.yueba.yueba.service;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.google.common.collect.Lists;
import com.sun.org.apache.xpath.internal.operations.Gt;
import com.yueba.yueba.common.CommonUtils;
import com.yueba.yueba.mapper.UserMapper;
import com.yueba.yueba.model.User;
import com.yueba.yueba.model.enumm.RoleEnum;
import com.yueba.yueba.model.vo.UserVo;
import lombok.val;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

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

    public List<UserVo> queryAll(Map<String, String> paramMap) {
        int role = Integer.parseInt(Optional.ofNullable(paramMap.get("role")).orElse("0"));
        val queryWrappers = Wrappers.<User>lambdaQuery().ne(User::getRole, 2);
        //普通用户10条
        if (role == RoleEnum.USER.getRole()) {
            queryWrappers.last("limit 10");
        }
        //会员用户30条
        else if (role == RoleEnum.SUPER_USER.getRole()) {
            queryWrappers.last("limit 30");
        }
        List<User> userList = userMapper.selectList(queryWrappers);
        List<UserVo> userVos = Lists.newArrayList();
        userList.stream().forEach(user -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVos.add(userVo);
        });
        return userVos;
    }

    public List<UserVo> queryAll() {
        val queryWrappers = Wrappers.<User>lambdaQuery().ne(User::getRole, 2);
        List<User> userList = userMapper.selectList(queryWrappers);
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

    public boolean checkLogin(String username, String password, Integer role) {
        User user = null;
        //如果是管理员
        if (role == 2) {
            user = userMapper.selectOneByUserNameAndRole(username, role);
        } else {//用户
            user = userMapper.selectOneByUserName(username);
        }
        if (user == null) {
            return false;
        }
        if ((CommonUtils.calculateMD5(user.getSalt() + password)).equalsIgnoreCase(user.getPassword())) {
            return true;
        } else {
            return false;
        }
    }

    public User selectOneByUserName(String username) {
        return userMapper.selectOneByUserName(username);
    }

    @Transactional(rollbackFor = Exception.class)
    public void insert(User user) {
        userMapper.insert(user);

    }

    public List<UserVo> search(Integer startAge, Integer endAge, Integer startMoney, Integer endMoney, Integer male) {

        val queryWrappers = Wrappers.<User>lambdaQuery().ne(User::getRole,2);
        if (startAge != null || endAge != null) {
            queryWrappers.between(User::getAge, startAge, endAge);
        }
        if (startMoney != null || endMoney != null) {
            queryWrappers.between(User::getMoney, startMoney, endMoney);
        }
        if (male != null) {
            queryWrappers.eq(User::getMale, male);
        }
        val userList = userMapper.selectList(queryWrappers);
        List<UserVo> userVos = Lists.newArrayList();
        userList.stream().forEach(user -> {
            UserVo userVo = new UserVo();
            BeanUtils.copyProperties(user, userVo);
            userVos.add(userVo);
        });
        return userVos;
    }
}
