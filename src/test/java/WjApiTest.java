import fun.mortnon.wj.exception.WjException;
import fun.mortnon.wj.model.AccessToken;
import fun.mortnon.wj.model.Survey;
import fun.mortnon.wj.model.WjAccessTokenResponse;
import fun.mortnon.wj.model.WjPage;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.service.WjManageService;
import fun.mortnon.wj.service.WjService;
import fun.mortnon.wj.service.WjStorageConfig;
import fun.mortnon.wj.service.impl.WjDefaultStorageConfigImpl;
import fun.mortnon.wj.service.impl.WjServiceImpl;
import org.junit.Test;

/**
 * @author dongfangzan
 * @date 27.7.21 10:20 上午
 */
public class WjApiTest {


    @Test()
    public void testAccessToken() throws WjException {
        WjService wjService = new WjServiceImpl();

        AccessToken accessToken = wjService.accessToken("", "", null);

        System.out.println(JacksonUtil.objectToJson(accessToken));
    }

    @Test
    public void testApi() {
        WjStorageConfig wjStorageConfig = new WjDefaultStorageConfigImpl();
        WjService wjService = new WjServiceImpl(wjStorageConfig, "", "");
        WjManageService wjManageService = wjService.getWjManageService();

        WjPage<Survey> surveyWjPage = wjManageService.listSurvey(60007372856L, null, 1, 20);
        System.out.println(JacksonUtil.objectToJson(surveyWjPage));
    }
}
