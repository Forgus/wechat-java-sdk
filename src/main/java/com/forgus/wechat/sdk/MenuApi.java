package com.forgus.wechat.sdk;

import com.alibaba.fastjson.JSON;
import com.forgus.wechat.sdk.request.CreateMenuParams;
import com.forgus.wechat.sdk.result.GetMenuResult;
import com.forgus.wechat.sdk.result.base.BaseResult;

/**
 * 菜单Api
 * @author Wenbin Chen
 */
public class MenuApi extends BaseWechatApi {

    private static final String CREATE_MENU_URL_TEMPLATE = API_URL + "/cgi-bin/menu/create?access_token=%s";
    private static final String GET_MENU_URL_TEMPLATE = API_URL + "/cgi-bin/menu/get?access_token=%s";
    private static final String DELETE_MENU_URL_TEMPLATE = API_URL + "/cgi-bin/menu/delete?access_token=%s";


    /**
     * 创建菜单
     * @param createMenuParams
     * @return
     * @throws Exception
     */
    public static BaseResult createMenu(CreateMenuParams createMenuParams) throws Exception {
        String createMenuUrl = String.format(CREATE_MENU_URL_TEMPLATE,accessToken);
        return post(createMenuUrl, JSON.toJSONString(createMenuParams),BaseResult.class);
    }

    /**
     * 获取菜单
     * @return
     * @throws Exception
     */
    public static GetMenuResult getMenu() throws Exception {
        String getMenuUrl = String.format(GET_MENU_URL_TEMPLATE,accessToken);
        return get(getMenuUrl,GetMenuResult.class);
    }

    /**
     * 删除菜单
     * @return
     * @throws Exception
     */
    public static BaseResult deleteMenu() throws Exception {
        String deleteMenuUrl = String.format(DELETE_MENU_URL_TEMPLATE,accessToken);
        return get(deleteMenuUrl,BaseResult.class);
    }

}
