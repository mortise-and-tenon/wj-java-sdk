package fun.mortnon.wj.test.util.api;

import fun.mortnon.wj.model.Survey;
import fun.mortnon.wj.model.SurveyDetail;
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
 * @author dongfangzan
 * @date 3.8.21 8:28 上午
 */
public class WjManageServiceTest {

    /** 问卷基础服务 */
    private WjService wjService;

    @Before
    public void init() {
        WjStorageConfig wjStorageConfig = new WjDefaultStorageConfigImpl();
        wjService = new WjServiceImpl(wjStorageConfig, APP_ID, SECRET);
    }


    @Test
    public void testListSurvey() {
        WjManageService wjManageService = wjService.getWjManageService();

        WjPage<Survey> surveyWjPage = wjManageService.listSurvey(60005907214L, null, 1, 20);
        System.out.println(JacksonUtil.objectToJson(surveyWjPage));
        Assert.assertNotNull(surveyWjPage);
        Assert.assertNotNull(surveyWjPage.getList());
        Assert.assertTrue(surveyWjPage.getTotal() > 0);
    }

    @Test
    public void testGetSurveyDetail() {
        WjManageService wjManageService = wjService.getWjManageService();
        SurveyDetail surveyDetail = wjManageService.getSurveyDetail(8827032L);

        System.out.println(JacksonUtil.objectToJson(surveyDetail));
    }
}
