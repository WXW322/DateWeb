package com.yueba.yueba.model;

import lombok.Data;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/10
 * @description
 **/
@Data
public class User {
    private Long id;
    private String username;
    private String password;
    private String salt;
    private String nickname;
    private String face;
    private Integer role;
    private Integer male;
    private Integer age;
    private String location;
    private Integer money;
    private Integer height;
    private String description;
    private String major;
}
