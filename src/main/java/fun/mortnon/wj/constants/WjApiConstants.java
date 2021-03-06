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
    // 企业
    /** 获取企业信息 */
    public final static String GET_ORG = "/api/orgs/%s";

    /** 获取企业管理员列表 */
    public final static String LIST_ORG_USERS = "/api/orgs/%s/users";

    // 分组
    /** 获取分组列表 */
    public final static String LIST_GROUP = "/api/contacts/teams/%s/groups";

    /** 新建分组 */
    public final static String CREATE_GROUP = "/api/contacts/teams/%s/groups";

    /** 修改分组 */
    public final static String UPDATE_GROUP = "/api/contacts/teams/%s/groups/%s";

    /** 删除分组 */
    public final static String BATCH_DELETE_GROUP = "/api/contacts/teams/%s/groups/batch_delete";

    // 成员
    /** 按团队获取成员列表 */
    public final static String LIST_TEAM_USER = "/api/contacts/teams/%s/users";

    /** 按分组获取成员列表 */
    public final static String LIST_GROUP_USER = "/api/contacts/teams/%s/groups/%s/users";

    /** 添加分组成员 */
    public final static String CREATE_USER = "/api/contacts/teams/%s/groups/%s/users/%s";

    /** 修改分组成员 */
    public final static String UPDATE_USER = "/api/contacts/teams/%s/users/%s";

    /** 删除分组成员*/
    public final static String DELETE_USER = "/api/contacts/teams/%s/groups/%s/users/%s";

    //数据推送管理
    /**
     * 获取 Webhook 列表
     */
    public final static String LIST_WEBHOOKS = "/api/surveys/%s/webhooks";

    /**
     * 获取 Webhook 详情
     */
    public final static String GET_WEBHOOK = "/api/surveys/%s/webhooks/%s";

    /**
     * 创建 Webhook
     */
    public final static String CREATE_WEBHOOK = LIST_WEBHOOKS;

    /**
     * 修改 Webhook
     */
    public final static String MODIFY_WEBHOOK = GET_WEBHOOK;

    /**
     * 删除 Webhook
     */
    public final static String DELETE_WEBHOOK = GET_WEBHOOK;

    // 第三方登录
    // 相关接口
    /** 注册用户 */
    public final static String REGISTER_USER = "/api/sso/users";

    /** 获取一次性登陆码 */
    public final static String GET_LOGIN_CODE = "/api/sso/code";

    /** 获取用户信息 */
    public final static String GET_USER = "/api/sso/users/%s";
}
