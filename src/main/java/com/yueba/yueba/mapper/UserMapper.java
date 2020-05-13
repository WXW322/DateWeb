package com.yueba.yueba.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yueba.yueba.model.User;
import org.apache.ibatis.annotations.Select;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/10
 * @description
 **/
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username = #{username} and role = #{role}")
    User selectOneByUserNameAndRole(String username, Integer role);

    @Select("select * from user where username = #{username}")
    User selectOneByUserName(String username);
}
