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

    /** 获取access_token */
    public final static String OAUTH_ACCESS_TOKEN = "/api/oauth2/access_token";

    /** 使用access_token获取企业下的问卷数据 */
    public final static String SURVEYS = "/api/surveys/%s";

    /** 用户同意授权，获取code */
    public final static String OAUTH_AUTHORIZE = "/connect/oauth2/authorize";

//    public final static

}
