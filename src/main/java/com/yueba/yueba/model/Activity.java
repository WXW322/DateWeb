package com.yueba.yueba.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.util.Date;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/14
 * @description
 **/
@Data
public class Activity {
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    private String name;
    private Date createdAt;
    private String description;
    private String location;
    private Date startedAt;
    private Integer status;
}
