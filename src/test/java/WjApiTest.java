import fun.mortnon.wj.exception.WjException;
import fun.mortnon.wj.model.WjAccessTokenResponse;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.service.WjService;
import fun.mortnon.wj.service.impl.WjServiceImpl;
import org.junit.Test;

/**
 * @author dongfangzan
 * @date 27.7.21 10:20 上午
 */
public class WjApiTest {


    @Test(expected = WjException.class)
    public void testAccessToken() throws WjException {
        WjService wjService = new WjServiceImpl();

        WjAccessTokenResponse wjAccessTokenResponse = wjService.accessToken("123", "123", null);

        System.out.println(JacksonUtil.objectToJson(wjAccessTokenResponse));
    }
}
