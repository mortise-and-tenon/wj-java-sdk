package fun.mortnon.wj.service.impl;

import fun.mortnon.wj.constants.WjApiConstants;
import fun.mortnon.wj.model.AccessToken;
import fun.mortnon.wj.model.ErrorCode;
import fun.mortnon.wj.model.RequestContent;
import fun.mortnon.wj.model.utils.AssertUtils;
import fun.mortnon.wj.model.utils.HttpClientTemplate;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.service.*;
import fun.mortnon.wj.vo.WjAccessTokenResponse;
import fun.mortnon.wj.vo.WjBaseResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * 问卷基本服务
 *
 * @author dongfangzan
 * @date 27.7.21 11:04 上午
 */
public class WjServiceImpl implements WjService {

    @Getter
    @Setter
    private WjStorageConfig wjStorageConfig;

    /** appId */
    @Getter
    @Setter
    private String appId;

    /** 密钥 */
    @Getter
    @Setter
    private String secret;

    @Getter
    @Setter
    private WjManageService wjManageService = new WjManageServiceImpl(this);

    @Getter
    @Setter
    private WjAddressListService wjAddressListService = new WjAddressListServiceImpl(this);

    @Getter
    @Setter
    private WjDataService wjDataService = new WjDataServiceImpl(this);

    @Getter
    @Setter
    private WjVendorService wjVendorService = new WjVendorServiceImpl(this);

    public WjServiceImpl(WjStorageConfig wjStorageConfig, String appId, String secret) {
        this.wjStorageConfig = wjStorageConfig;
        this.appId = appId;
        this.secret = secret;
    }

    public WjServiceImpl() {
    }

    @Override
    public <T> WjBaseResponse<T> doGet(RequestContent requestContent, Supplier<WjBaseResponse<T>> handler) {
        requestContent.setUrl(WjApiConstants.DOMAIN_NAME + requestContent.getUrl());
        return HttpClientTemplate.doGet(requestContent, handler);
    }

    @Override
    public <T> WjBaseResponse<T> doGetWithToken(RequestContent requestContent, Supplier<WjBaseResponse<T>> handler) {
        Map<String, Object> params = requestContent.getParam();
        if (Objects.isNull(params) || 0 == params.size()) {
            params = new HashMap<>(2);
        }

        params.put("appid", appId);
        params.put("access_token", getAccessToken());

        requestContent.setParam(params);

        return doGet(requestContent, handler);
    }

    @Override
    public <T> WjBaseResponse<T> doPost(RequestContent requestContent, Supplier<WjBaseResponse<T>> handler) {
        requestContent.setUrl(WjApiConstants.DOMAIN_NAME + requestContent.getUrl());
        return HttpClientTemplate.doPost(requestContent, handler);
    }

    @Override
    public <T> WjBaseResponse<T> doDeleteWithToken(RequestContent requestContent, Supplier<WjBaseResponse<T>> handler) {
        Map<String, Object> params = requestContent.getParam();
        if (Objects.isNull(params) || 0 == params.size()) {
            params = new HashMap<>(2);
        }

        params.put("appid", appId);
        params.put("access_token", getAccessToken());

        requestContent.setParam(params);

        return doDelete(requestContent, handler);
    }

    @Override
    public <T> WjBaseResponse<T> doDelete(RequestContent requestContent, Supplier<WjBaseResponse<T>> handler) {
        requestContent.setUrl(WjApiConstants.DOMAIN_NAME + requestContent.getUrl());
        return HttpClientTemplate.doDelete(requestContent, handler);
    }

    @Override
    public <T> WjBaseResponse<T> doPostWithToken(RequestContent requestContent, Supplier<WjBaseResponse<T>> handler) {
        Map<String, Object> params = requestContent.getParam();
        if (Objects.isNull(params) || 0 == params.size()) {
            params = new HashMap<>(2);
        }

        params.put("appid", appId);
        params.put("access_token", getAccessToken());
        requestContent.setParam(params);

        return doPost(requestContent, handler);
    }

    @Override
    public AccessToken accessToken(String appId, String secret, String grantType) {
        Map<String, Object> params = new HashMap<>(3);
        params.put("appid", appId);
        params.put("secret", secret);
        params.put("grant_type", "client_credential");

        RequestContent requestContent = new RequestContent(WjApiConstants.OAUTH_ACCESS_TOKEN, params, null);

        WjAccessTokenResponse response = (WjAccessTokenResponse) doGet(requestContent, () -> {
            String result = requestContent.getResult();
            WjAccessTokenResponse wjAccessTokenResponse = JacksonUtil.jsonToObject(result, WjAccessTokenResponse.class);
            AssertUtils.isEquals(ErrorCode.OK, wjAccessTokenResponse.getCode(),
                    wjAccessTokenResponse.getCode(), wjAccessTokenResponse.getError(), wjAccessTokenResponse.getCode().getDesc());
            return wjAccessTokenResponse;
        });
        return response.getData();
    }

    @Override
    public String getAccessToken() {
        if (System.currentTimeMillis() > wjStorageConfig.getAccessTokenExpiresTime() || Objects.isNull(wjStorageConfig.getAccessToken())) {
            // token超时，刷新token
            AccessToken accessToken = accessToken(appId, secret, null);
            wjStorageConfig.setAccessToken(accessToken);
        }

        return wjStorageConfig.getAccessToken().getToken();
    }
}
