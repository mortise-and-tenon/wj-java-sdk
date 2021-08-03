package fun.mortnon.wj.service;

import fun.mortnon.wj.model.Webhook;
import fun.mortnon.wj.model.WjPage;

/**
 * 数据推送管理
 *
 * @author dongfangzan
 * @date 27.7.21 11:00 上午
 */
public interface WjDataService {
    /**
     * 获取 Webhook 列表
     *
     * @param surveyId 问卷 ID
     * @return
     */
    WjPage<Webhook> listWebhook(Long surveyId);

    /**
     * 获取 Webhook 详情
     *
     * @param surveyId  问题 ID
     * @param webHookId webhook ID
     * @return
     */
    Webhook getWebhookDetail(Long surveyId, Long webHookId);

    /**
     * 新建 Webhook
     *
     * @param surveyId 问题 ID
     * @param url      推送 url
     * @param isActive 是否启用
     * @return
     */
    Webhook createWebhook(Long surveyId, String url, Boolean isActive);

    /**
     * 修改 Webhook
     *
     * @param surveyId  问题 ID
     * @param webhookId webhook ID
     * @param url       推送 url
     * @param isActive  是否启用
     */
    Webhook modifyWebhook(Long surveyId, Long webhookId, String url, Boolean isActive);

    /**
     * 删除 Webhook
     *
     * @param surveyId  问题 ID
     * @param webhookId webhook ID
     * @return
     */
    boolean deleteWebhook(Long surveyId, Long webhookId);
}
