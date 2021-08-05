package fun.mortnon.wj.service.impl;

import fun.mortnon.wj.model.Webhook;
import fun.mortnon.wj.model.WjPage;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.service.WjDataService;
import fun.mortnon.wj.service.WjManageService;
import fun.mortnon.wj.service.WjService;
import fun.mortnon.wj.service.WjStorageConfig;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author Moon Wu
 * @date 2021/8/3
 */
public class WjDataServiceImplTest {
    private WjDataService wjDataService;

    @Before
    public void setUp() throws Exception {
        WjStorageConfig wjStorageConfig = new WjDefaultStorageConfigImpl();
        WjService wjService = new WjServiceImpl(wjStorageConfig, "", "");
        wjDataService = wjService.getWjDataService();
    }

    @Test
    public void testListWebhook() {
        WjPage<Webhook> webhookWjPage = wjDataService.listWebhook(8807430L);
        int total = webhookWjPage.getTotal();
        Assert.assertTrue(total != 0);
        List<Webhook> list = webhookWjPage.getList();
        System.out.println(JacksonUtil.objectToJson(list));
    }

    @Test
    public void testGetWebhookDetail() {
        Webhook webhookDetail = wjDataService.getWebhookDetail(8807430L, 4231334L);
        Assert.assertEquals("http://membertest.quilimen.com/api/wj/webhook", webhookDetail.getUrl());
        System.out.println(JacksonUtil.objectToJson(webhookDetail));
    }

    @Test
    public void testCreateWebhook() {
        Webhook webhook = wjDataService.createWebhook(8806096L, "http://zq4kis.natappfree.cc/api/wj/webhook3", true);
        Assert.assertNotNull(webhook);
        System.out.println(JacksonUtil.objectToJson(webhook));
    }

    @Test
    public void testModifyWebhook() {
        Webhook webhook = wjDataService.modifyWebhook(8806096L, 4356498L, "http://zq4kis.natappfree.cc/api/wj/webhook5", false);
        Assert.assertNotNull(webhook);
        System.out.println(JacksonUtil.objectToJson(webhook));
    }

    @Test
    public void testDeleteWebhook() {
        boolean b = wjDataService.deleteWebhook(8806096L, 4336147L);
        Assert.assertTrue(b);
    }
}