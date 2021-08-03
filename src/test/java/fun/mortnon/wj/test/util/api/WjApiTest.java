package fun.mortnon.wj.test.util.api;

import fun.mortnon.wj.exception.WjException;
import fun.mortnon.wj.model.AccessToken;
import fun.mortnon.wj.model.Survey;
import fun.mortnon.wj.model.WjPage;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.service.WjManageService;
import fun.mortnon.wj.service.WjService;
import fun.mortnon.wj.service.WjStorageConfig;
import fun.mortnon.wj.service.impl.WjDefaultStorageConfigImpl;
import fun.mortnon.wj.service.impl.WjServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static fun.mortnon.wj.test.util.WjTestConst.APP_ID;
import static fun.mortnon.wj.test.util.WjTestConst.SECRET;

/**
 * 问卷基础服务测试
 *
 * @author dongfangzan
 * @date 27.7.21 10:20 上午
 */
public class WjApiTest {

    /** 问卷基础服务 */
    private WjService wjService;

    @Before
    public void init() {
        WjStorageConfig wjStorageConfig = new WjDefaultStorageConfigImpl();
        wjService = new WjServiceImpl(wjStorageConfig, APP_ID, SECRET);
    }



    @Test()
    public void testAccessToken() throws WjException {
        AccessToken accessToken = wjService.accessToken(APP_ID, SECRET, null);
        System.out.println(JacksonUtil.objectToJson(accessToken));

        Assert.assertNotNull(accessToken);
        Assert.assertNotNull(accessToken.getToken());
    }
}
