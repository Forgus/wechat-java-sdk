package com.forgus.wechat.sdk.model;

import com.forgus.wechat.sdk.constant.ButtonTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @author Wenbin Chen
 */
@Data
public class SubButton extends Button {

    private List<Button> sub_button;

    private SubButton() {

    }

    private SubButton(String name) {
        this.name = name;
    }

    public static SubButton build(String name) {
        return new SubButton(name);
    }

    public SubButton setSubButton(List<Button> button) {
        this.sub_button = button;
        return this;
    }

    @Override
    public String getType() {
        return ButtonTypeEnum.sub_button.name();
    }
}
