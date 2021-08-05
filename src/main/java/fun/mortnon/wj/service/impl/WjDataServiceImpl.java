package fun.mortnon.wj.service.impl;

import com.fasterxml.jackson.core.type.TypeReference;
import fun.mortnon.wj.constants.WjApiConstants;
import fun.mortnon.wj.model.*;
import fun.mortnon.wj.model.utils.AssertUtils;
import fun.mortnon.wj.model.utils.JacksonUtil;
import fun.mortnon.wj.service.WjDataService;
import fun.mortnon.wj.service.WjService;
import fun.mortnon.wj.vo.WjBaseResponse;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author Moon Wu
 * @date 2021/8/2
 */
public class WjDataServiceImpl implements WjDataService {
    /**
     * 基础服务
     */
    private final WjService wjService;

    public WjDataServiceImpl(WjService wjService) {
        this.wjService = wjService;
    }

    @Override
    public WjPage<Webhook> listWebhook(Long surveyId) {
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "问卷ID不能为空");

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.LIST_WEBHOOKS, surveyId.toString()));

        WjBaseResponse<WjPage<Webhook>> webhookWjBaseResponse = wjService.doGetWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<WjPage<Webhook>>>() {
                }));
        return webhookWjBaseResponse.getData();
    }

    @Override
    public Webhook getWebhookDetail(Long surveyId, Long webHookId) {
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "问卷ID不能为空");
        AssertUtils.nonNull(webHookId, ErrorCode.InvalidArgument, "webhook ID不能为空");

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.GET_WEBHOOK, surveyId.toString(), webHookId.toString()));

        WjBaseResponse<Webhook> webhookWjBaseResponse = wjService.doGetWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<Webhook>>() {
                }));
        return webhookWjBaseResponse.getData();
    }

    @Override
    public Webhook createWebhook(Long surveyId, String url, Boolean isActive) {
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "问卷ID不能为空");
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "推送 url 不能为空");

        Map<String, Object> body = new HashMap<>(2);
        body.put("url", url);
        body.put("is_active", isActive);

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.CREATE_WEBHOOK, surveyId.toString()))
                .setJsonBody(JacksonUtil.objectToJson(body));

        WjBaseResponse<Webhook> webhookWjBaseResponse = wjService.doPostWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<Webhook>>() {
                }));
        return webhookWjBaseResponse.getData();
    }

    @Override
    public Webhook modifyWebhook(Long surveyId, Long webhookId, String url, Boolean isActive) {
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "问卷ID不能为空");
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "webhook ID不能为空");
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "推送 url 不能为空");

        Map<String, Object> body = new HashMap<>(2);
        body.put("url", url);
        body.put("is_active", isActive);

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.MODIFY_WEBHOOK, surveyId.toString(), webhookId))
                .setJsonBody(JacksonUtil.objectToJson(body));

        WjBaseResponse<Webhook> webhookWjBaseResponse = wjService.doPostWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<Webhook>>() {
                }));
        return webhookWjBaseResponse.getData();
    }

    @Override
    public boolean deleteWebhook(Long surveyId, Long webHookId) {
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "问卷ID不能为空");
        AssertUtils.nonNull(surveyId, ErrorCode.InvalidArgument, "webhook ID不能为空");

        RequestContent requestContent = new RequestContent()
                .setUrl(String.format(WjApiConstants.DELETE_WEBHOOK, surveyId.toString(), webHookId.toString()));

        WjBaseResponse<ResponseResult> webhookWjBaseResponse = wjService.doDeleteWithToken(requestContent, () ->
                JacksonUtil.jsonToObject(requestContent.getResult(), new TypeReference<WjBaseResponse<ResponseResult>>() {
                }));

        return "success".equalsIgnoreCase(webhookWjBaseResponse.getData().getResult());
    }
}
