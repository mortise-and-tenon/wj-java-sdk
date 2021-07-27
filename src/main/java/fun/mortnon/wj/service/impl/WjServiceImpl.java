package fun.mortnon.wj.service.impl;

import fun.mortnon.wj.constants.WjApiConstants;
import fun.mortnon.wj.model.ErrorCode;
import fun.mortnon.wj.model.RequestContent;
import fun.mortnon.wj.model.WjAccessTokenResponse;
import fun.mortnon.wj.model.utils.AssertUtils;
import fun.mortnon.wj.model.utils.HttpClientTemplate;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.service.WjService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongfangzan
 * @date 27.7.21 11:04 上午
 */
public class WjServiceImpl implements WjService {

    @Override
    public WjAccessTokenResponse accessToken(String appId, String secret, String grantType) {
        Map<String, String> params = new HashMap<>(3);
        params.put("appid", appId);
        params.put("secret", secret);
        params.put("grant_type", "client_credential");

        RequestContent requestContent = new RequestContent(WjApiConstants.OAUTH_ACCESS_TOKEN, params, null);

        return (WjAccessTokenResponse) HttpClientTemplate.doGet(requestContent, () -> {
            String result = requestContent.getResult();
            WjAccessTokenResponse wjAccessTokenResponse = JacksonUtil.jsonToObject(result, WjAccessTokenResponse.class);
            AssertUtils.isEquals(ErrorCode.OK, wjAccessTokenResponse.getCode(),
                    wjAccessTokenResponse.getCode(), wjAccessTokenResponse.getError(), wjAccessTokenResponse.getCode().getDesc());
            return wjAccessTokenResponse;
        });

    }
}
