package com.ycit.user.bean.enums;

public enum RedisKeyEnum {

    USER("user", "user key")
    ;

    private String key;
    private String desc;

    RedisKeyEnum(String key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
