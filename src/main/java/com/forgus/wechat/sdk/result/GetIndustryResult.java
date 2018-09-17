package com.forgus.wechat.sdk.result;

import com.forgus.wechat.sdk.model.Industry;
import com.forgus.wechat.sdk.result.base.BaseResult;
import lombok.Data;

/**
 * @author Wenbin Chen
 */
@Data
public class GetIndustryResult extends BaseResult {

    /**
     * 主行业
     */
    private Industry primary_industry;
    /**
     * 副行业
     */
    private Industry secondary_industry;

}
