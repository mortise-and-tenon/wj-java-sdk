package fun.mortnon.wj.service.impl;

import fun.mortnon.wj.constants.WjApiConstants;
import fun.mortnon.wj.model.*;
import fun.mortnon.wj.model.utils.AssertUtils;
import fun.mortnon.wj.model.utils.HttpClientTemplate;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.model.utils.StringUtils;
import fun.mortnon.wj.service.WjManageService;
import fun.mortnon.wj.service.WjService;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author dongfangzan
 * @date 27.7.21 5:30 下午
 */
public class WjManageServiceImpl implements WjManageService {

    @Getter
    private final WjService wjService;

    public WjManageServiceImpl(WjService wjService) {
        this.wjService = wjService;
    }

    @Override
    public WjPage<Survey> listSurvey(Long userId, String openId, Integer page, Integer perPage) {
        AssertUtils.nonNull(page, ErrorCode.InvalidArgument, "页码不能为空");
        AssertUtils.nonNull(perPage, ErrorCode.InvalidArgument, "每页数量不能为空");

        Map<String, Object> params = new HashMap<>(8);
        if (Objects.nonNull(userId)) {
            params.put("user_id", userId);
        }

        if (StringUtils.isNotBlank(openId)) {
            params.put("openid", openId);
        }

        params.put("page", page);
        params.put("per_page", perPage);

        RequestContent requestContent = new RequestContent();
        requestContent.setParam(params);
        requestContent.setUrl(WjApiConstants.SURVEYS);

        WjSurveyResponse wjSurveyResponse = (WjSurveyResponse) wjService.doGetWithToken(requestContent,
                () -> JacksonUtil.jsonToObject(requestContent.getResult(), WjSurveyResponse.class));
        return wjSurveyResponse.getData();
    }
}
