package com.forgus.wechat.sdk.model;

import com.forgus.wechat.sdk.constant.ButtonTypeEnum;
import lombok.Getter;

/**
 * @author Wenbin Chen
 */
@Getter
public class ClickButton extends Button {

    private String key;

    private ClickButton() {

    }

    private ClickButton(String name,String key) {
        this.name = name;
        this.key = key;
    }

    public static ClickButton build(String name,String key) {
        return new ClickButton(name,key);
    }

    @Override
    public String getType() {
        return ButtonTypeEnum.click.name();
    }
}
