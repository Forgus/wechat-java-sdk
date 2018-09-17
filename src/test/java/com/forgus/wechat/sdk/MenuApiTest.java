package com.forgus.wechat.sdk;

import com.forgus.wechat.sdk.model.*;
import com.forgus.wechat.sdk.request.CreateMenuParams;
import com.forgus.wechat.sdk.result.GetMenuResult;
import com.forgus.wechat.sdk.result.base.BaseResult;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Wenbin Chen
 */
public class MenuApiTest extends ApiTest {

    @Test
    public void createMenu() throws Exception {
        CreateMenuParams params = new CreateMenuParams();
        List<Button> buttonList = new ArrayList<>();
        buttonList.add(ClickButton.build("今日歌曲", "V1001_TODAY_MUSIC"));
        List<Button> subButtonList = new ArrayList<>();
        subButtonList.add(ViewButton.build("搜索", "http://www.soso.com/"));
        subButtonList.add(ClickButton.build("赞我们一下", "V1001_GOOD"));
        buttonList.add(SubButton.build("菜单").setSubButton(subButtonList));
        params.setButton(buttonList);
        BaseResult result = MenuApi.createMenu(params);
        assertSuccess(result);
    }

    @Test
    public void getMenu() throws Exception {
        GetMenuResult menuResult = MenuApi.getMenu();
        Assert.assertEquals(2,menuResult.getMenu().getButton().size());
    }

    @Test
    public void deleteMenu() throws Exception {
        BaseResult baseResult = MenuApi.deleteMenu();
        assertSuccess(baseResult);
    }

    private void assertSuccess(BaseResult result) {
        Assert.assertEquals("0", result.getErrcode());
    }

}