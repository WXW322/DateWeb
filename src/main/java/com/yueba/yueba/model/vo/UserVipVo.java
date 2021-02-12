package com.yueba.yueba.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/11
 * @description
 **/
@Data
public class UserVipVo {
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Integer status;
    private String nickname;
    private Date createdAt;
}
