package com.imooc.enums;

/**
 * 分类的不同
 */
public enum Grade {
    FIRST(1,"First"),
    SECOND(2,"Second"),
    THIRD(3,"Third");


    public final Integer type;
    public final String value;

    Grade(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
