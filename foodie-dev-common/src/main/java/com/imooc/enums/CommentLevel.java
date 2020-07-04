package com.imooc.enums;

/**
 * 分类的不同 
 */
public enum CommentLevel {
    GOOD(1,"好评"),
    BAD(2,"差评"),
    NORMAL(3,"中评");


    public final Integer type;
    public final String value;

    CommentLevel(Integer type, String value) {
        this.type = type;
        this.value = value;
    }
}
