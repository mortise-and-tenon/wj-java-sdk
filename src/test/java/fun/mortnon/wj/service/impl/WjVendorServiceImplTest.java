package fun.mortnon.wj.service.impl;

import fun.mortnon.wj.service.WjService;
import fun.mortnon.wj.service.WjStorageConfig;
import fun.mortnon.wj.service.WjVendorService;
import fun.mortnon.wj.test.util.WjTestConst;
import fun.mortnon.wj.vo.WjBaseResponse;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author qiansl
 * @version 1.0
 * @date 2022/3/8 3:42 下午
 * @description
 */
public class WjVendorServiceImplTest {

    /**
     * 问卷基础服务
     */
    private WjService wjService;

    private WjVendorService wjVendorService;

    @Before
    public void init() {
        WjStorageConfig wjStorageConfig = new WjDefaultStorageConfigImpl();
        wjService = new WjServiceImpl(wjStorageConfig, WjTestConst.APP_ID, WjTestConst.SECRET);
        wjVendorService = wjService.getWjVendorService();
    }

    @Test
    public void createUser() {
        String openid = "a12ba6e8606d111ba21cf64d5fc81bbf";
        String nickname = "qsl";
        String avatar = "https://wj.gtimg.com/default/default_headimg.png";
        Long userId = wjVendorService.createUser(openid, nickname, avatar);

        Assert.assertNotNull(userId);
    }

    @Test
    public void getLoginCode() {
        Long userId = 60014899179L;
        String sceneType = "user";
        String allowDomain = "";
        String code = wjVendorService.getLoginCode(userId, sceneType, allowDomain);
        Assert.assertNotNull(code);
    }

    @Test
    public void getUser() {
        String openId = "a12ba6e8606d111ba21cf64d5fc81bbf";
        WjBaseResponse wjBaseResponse = wjVendorService.getUser(openId);
        System.out.println(wjBaseResponse);
    }
}