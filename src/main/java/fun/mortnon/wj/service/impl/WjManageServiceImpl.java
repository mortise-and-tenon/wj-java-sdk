package fun.mortnon.wj.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import fun.mortnon.wj.constants.WjApiConstants;
import fun.mortnon.wj.model.*;
import fun.mortnon.wj.model.utils.AssertUtils;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.model.utils.StringUtils;
import fun.mortnon.wj.service.WjManageService;
import fun.mortnon.wj.service.WjService;
import fun.mortnon.wj.vo.WjBaseResponse;
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

        WjBaseResponse<WjPage<Survey>> wjSurveyResponse = wjService.doGetWithToken(requestContent,
                () -> JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<WjPage<Survey>>>() {
                }));
        return wjSurveyResponse.getData();
    }

    @Override
    public SurveyDetailLegacy getSurveyDetailLegacy(Long surveyId) {
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "问卷ID不能为空");
        AssertUtils.isTrue(surveyId > 0, ErrorCode.InvalidArgument, "不合法的问卷ID：" + surveyId);
        RequestContent requestContent = new RequestContent();
        requestContent.setUrl(String.format(WjApiConstants.SURVEY_LEGACY, surveyId));

        WjBaseResponse<SurveyDetailLegacy> legacyResponse = wjService.doGetWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<SurveyDetailLegacy>>() {}));
        return legacyResponse.getData();
    }

    @Override
    public WjPage<Answer> listAnswer(Long surveyId, Integer perPage, Long lastAnswerId) {
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "问卷ID为不能为空");
        AssertUtils.nonNull(perPage, ErrorCode.InvalidArgument, "每页显示条数不能为空");

        Map<String, Object> params = new HashMap<>(2);
        params.put("per_page", perPage);
        if (Objects.nonNull(lastAnswerId)) {
            params.put("last_answer_id", lastAnswerId);
        }

        RequestContent requestContent = new RequestContent(String.format(WjApiConstants.SURVEY_ANSWERS,
                surveyId.toString()), params, null);
        WjBaseResponse<WjPage<Answer>> result = wjService.doGetWithToken(requestContent, () ->
            JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<WjPage<Answer>>>() {})
        );
        return result.getData();
    }

    @Override
    public Answer getAnswer(Long surveyId, Long answerId) {
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "问卷ID为不能为空");
        AssertUtils.nonNull(answerId, ErrorCode.InvalidArgument, "答案ID不能为空");

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.SURVEY_ANSWERS_LEGACY, surveyId.toString(), answerId.toString()));

        WjBaseResponse<Answer> wjBaseResponse = wjService.doGetWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<Answer>>() {
                }));

        return wjBaseResponse.getData();
    }
}
