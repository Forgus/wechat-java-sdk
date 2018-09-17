package com.forgus.wechat.sdk.model;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * @author Wenbin Chen
 */
@Data
public class Menu {

    private List<JSONObject> button;

}
