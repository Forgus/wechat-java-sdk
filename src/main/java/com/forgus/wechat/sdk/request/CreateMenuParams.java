package com.forgus.wechat.sdk.request;

import com.forgus.wechat.sdk.model.Button;
import lombok.Data;

import java.util.List;

/**
 * @author Wenbin Chen
 */
@Data
public class CreateMenuParams {

    List<Button> button;

}
