package com.yueba.yueba.model.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/10
 * @description
 **/
@Data
@Builder(toBuilder = true)
public class UserVo {
    @Tolerate
    public UserVo() {

    }

    private Long id;
    private String nickname;
    private String face;
    private int role;
    private int male;
    private int age;
    private String location;
    private int money;
    private int height;
    private String description;
    private String major;
}
