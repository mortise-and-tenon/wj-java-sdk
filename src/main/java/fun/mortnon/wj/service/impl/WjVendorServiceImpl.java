package fun.mortnon.wj.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import fun.mortnon.wj.constants.WjApiConstants;
import fun.mortnon.wj.model.AuthUser;
import fun.mortnon.wj.model.LoginCode;
import fun.mortnon.wj.model.RequestContent;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.service.WjService;
import fun.mortnon.wj.service.WjVendorService;
import fun.mortnon.wj.vo.WjBaseResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongfangzan
 * @date 6.8.21 4:18 下午
 */
public class WjVendorServiceImpl implements WjVendorService {

    /** 问卷基础服务 */
    private final WjService wjService;

    public WjVendorServiceImpl(WjService wjService) {
        this.wjService = wjService;
    }

    @Override
    public Long createUser(String openId, String nickname, String avatar) {
        Map<String, Object> jsonMap = new HashMap<>(3);
        jsonMap.put("openid", openId);
        jsonMap.put("nickname", nickname);
        jsonMap.put("avatar", avatar);

        RequestContent requestContent = new RequestContent().setUrl(WjApiConstants.REGISTER_USER)
                .setJsonBody(JacksonUtil.objectToJson(jsonMap));

        return wjService.doPostWithToken(requestContent, () -> JacksonUtil.jsonToObject(requestContent.getResult(),
                new TypeReference<WjBaseResponse<AuthUser>>() {})).getData().getUserId();
    }

    @Override
    public String getLoginCode(Long userId, String sceneType, String allowDomain) {
        Map<String, Object> jsonMap = new HashMap<>(2);
        jsonMap.put("scene_type", sceneType);
        jsonMap.put("user_id",userId);
        //官方文档已取消这个字段
        //jsonMap.put("allow_domain", allowDomain);

        RequestContent requestContent = new RequestContent()
                .setUrl(WjApiConstants.GET_LOGIN_CODE)
                .setJsonBody(JacksonUtil.objectToJson(jsonMap));


        return wjService.doPostWithToken(requestContent, () -> JacksonUtil.jsonToObject(requestContent.getResult(),
                new TypeReference<WjBaseResponse<LoginCode>>() {})).getData().getCode();
    }

    @Override
    public WjBaseResponse getUser(String openId) {
        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.GET_USER, openId));

        return wjService.doGetWithToken(requestContent, () -> JacksonUtil.jsonToObject(requestContent.getResult(),
                new TypeReference<WjBaseResponse<AuthUser>>() {}));
    }
}
