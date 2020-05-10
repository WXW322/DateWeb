package com.yueba.yueba.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/10
 * @description 用户申请vip 队列
 **/
@Data
@Builder(toBuilder = true)
public class UserVip {
    @Tolerate
    public UserVip() {

    }
    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private Integer status;
}
