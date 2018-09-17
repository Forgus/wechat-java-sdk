package com.forgus.wechat.sdk.result;

import com.forgus.wechat.sdk.result.base.BaseResult;
import lombok.Data;

/**
 * @author Wenbin Chen
 */
@Data
public class GetTemplateIdResult extends BaseResult {

    //模板ID
    private String template_id;

}
