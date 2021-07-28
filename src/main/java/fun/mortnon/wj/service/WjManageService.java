package fun.mortnon.wj.service;

import fun.mortnon.wj.model.Survey;
import fun.mortnon.wj.model.WjPage;

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
}
