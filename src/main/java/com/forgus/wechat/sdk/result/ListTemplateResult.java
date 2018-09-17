package com.forgus.wechat.sdk.result;

import com.forgus.wechat.sdk.model.Template;
import com.forgus.wechat.sdk.result.base.BaseResult;
import java.util.List;
import lombok.Data;

/**
 * @author Wenbin Chen
 */
@Data
public class ListTemplateResult extends BaseResult {

    private List<Template> template_list;

}
