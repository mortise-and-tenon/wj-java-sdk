package fun.mortnon.wj.service;

import fun.mortnon.wj.model.*;

/**
 * 问卷管理
 *
 * @author dongfangzan
 * @date 27.7.21 10:59 上午
 */
public interface WjManageService {

    /**
     * 获取用户问卷列表
     * @link {https://wj.qq.com/docs/openapi/survey/list_survey/}
     *
     * @param userId  用户ID或者企业ID，企业授权可以使用该参数，企业ID可以从 https://wj.qq.com 右上角头像-企业管理中获取
     * @param openId  用户唯一标识，第三方应用授权可以使用该参数，从网页授权的回调中获得
     * @param page    当前页码
     * @param perPage 每页显示条数
     * @return        用户问卷列表
     */
    WjPage<Survey> listSurvey(Long userId, String openId, Integer page, Integer perPage);

    /**
     * 获取问卷详情
     * @link {https://wj.qq.com/docs/openapi/survey/get_survey}
     *
     * 废弃原因：该接口存在大量历史遗留问题，存量可以继续使用，后续不再更新
     *
     * @param surveyId 问卷ID，示例值：292192
     * @return         问卷详情
     */
    @Deprecated
    SurveyDetailLegacy getSurveyDetailLegacy(Long surveyId);

    /**
     * 获取问卷的回答列表
     * @link {https://wj.qq.com/docs/openapi/survey/list_answer/}
     *
     * @param surveyId     问卷ID
     * @param perPage      每页显示条数
     * @param lastAnswerId 下页开始ID
     * @return             问卷的回答列表
     */
    WjPage<Answer> listAnswer(Long surveyId, Integer perPage, Long lastAnswerId);

    /**
     * 获取回答详情
     * @link {https://wj.qq.com/docs/openapi/survey/get_answer}
     *
     * @param surveyId 问卷ID
     * @param answerId 答案ID
     * @return         回答详情
     */
    Answer getAnswer(Long surveyId, Long answerId);
}
