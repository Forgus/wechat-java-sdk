package com.forgus.wechat.sdk.model;

import lombok.Builder;
import lombok.Data;

/**
 * 模板内容字段所需数据
 * @author Wenbin Chen
 */
@Data
@Builder
public class Keyword {

    //模板内容字段值
    private String value;
    //模板内容字体颜色，可选，默认为黑色
    private String color;

    private Keyword() {

    }

    private Keyword(String value) {
        this.value = value;
    }

    private Keyword(String value, String color) {
        this.value = value;
        this.color = color;
    }

    public static Keyword create(String value) {
        return new Keyword(value);
    }

    public static Keyword create(String value,String color) {
        return new Keyword(value,color);
    }
}
