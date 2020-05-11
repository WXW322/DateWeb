package com.yueba.yueba.model.enumm;

/**
 * @author 徐塬峰
 * @email 986771570@qq.com
 * @date 2020/5/11
 * @description
 **/
public enum RoleEnum {
    /**
     * 普通用户
     */
    USER("普通用户", 0),

    /**
     * 超级用户
     */
    SUPER_USER("超级用户", 1),
    /**
     * 管理员用户
     */
    ADMIN_USER("管理员", 2);

    private String name;
    private int role;

    RoleEnum(String name, int i) {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }
}
