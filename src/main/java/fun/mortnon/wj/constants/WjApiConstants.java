package fun.mortnon.wj.constants;

/**
 * 腾讯问卷api
 *
 * @author dongfangzan
 * @date 27.7.21 10:17 上午
 */
public class WjApiConstants {

    /** 基础域名 */
    public final static String DOMAIN_NAME = "https://open.wj.qq.com";

    // 授权管理部分
    /** 获取access_token */
    public final static String OAUTH_ACCESS_TOKEN = "/api/oauth2/access_token";

    /** 用户同意授权，获取code */
    public final static String OAUTH_AUTHORIZE = "/connect/oauth2/authorize";

    // 问卷管理
    /** 获取用户问卷列表 */
    public final static String SURVEYS = "/api/surveys";

    /** 获取问卷详情 */
    public final static String SURVEY_LEGACY = "/api/surveys/%s/legacy";

    /** 获取回答列表 */
    public final static String SURVEY_ANSWERS = "/api/surveys/%s/answers";

    /** 获取回答详情 */
    public final static String SURVEY_ANSWERS_LEGACY = "/api/surveys/%s/answers/%s";

    // 团队通讯录管理
    /** 获取企业信息 */
    public final static String GET_ORG = "/api/orgs/%s";

    /** 获取企业管理员列表 */
    public final static String LIST_ORG_USERS = "/api/orgs/%s/users";

    /** 获取分组列表 */
    public final static String LIST_GROUP = "/api/contacts/teams/%s/groups";

}
