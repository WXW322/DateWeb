package com.yueba.yueba.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yueba.yueba.model.UserVip;
import com.yueba.yueba.model.vo.UserVipVo;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserVipMapper extends BaseMapper<UserVip> {
    @Select("select * from user")
    List<UserVip> selectUserVip();

    @Select("select * from `user` uzi inner join user_vip uv " +
            "on uzi.id = uv.user_id ")
    List<UserVipVo> selectAll();
}
