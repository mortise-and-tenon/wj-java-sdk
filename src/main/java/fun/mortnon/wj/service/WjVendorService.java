package fun.mortnon.wj.service;

/**
 * 第三方登录
 *
 * @author dongfangzan
 * @date 27.7.21 11:00 上午
 */
public interface WjVendorService {

    /**
     * 功能：使用该接口可以为使用者创建一个账号，用于单点登录进入腾讯问卷系统。
     * 使用场景：你的 APP 或平台（称为腾讯问卷的第三方）已经有一套用户系统，你的用户需要使用腾讯问卷进行问卷编辑、投放及填答，最终你的 APP 或平台能够追溯编辑者、回答者的身份。使用该接口注册用户后，再通过获取一次性登录码和单点登录接口进行静默登录，用户即可以无缝对接到腾讯问卷中。
     * 请求方式： POST
     * 请求地址：/api/sso/users
     * @link {https://wj.qq.com/docs/openapi/sso/create_user}
     *
     * @param openId   第三方用户标识，用于辨别来自该第三方应用/平台的用户,长度不超过128个字符,在问卷系统中视 appid+openid 确定用户
     * @param nickname 用户昵称长度不超过64个字符
     * @param avatar   用户头像长度不超过255个字符
     * @return         用户id
     */
    Long createUser(String openId, String nickname, String avatar);


    /**
     * 功能：为第三方注册的用户创建一个临时验证码，用于统一入口 —— 带第三方登录态的跳转
     * 请求方式： POST
     * 请求地址：/api/sso/user/{user_id}/code
     * @link {https://wj.qq.com/docs/openapi/sso/get_user}
     *
     * @param userId      用户id
     * @param sceneType   场景值 user:作为问卷编辑者登录;respondent:作为问卷回答者登录
     * @param allowDomain 腾讯问卷也没说清楚这是啥
     * @return            一次性登录码，1分钟内有效
     */
    String getLoginCode(Long userId, String sceneType, String allowDomain);


    /**
     * 功能：获取第三方用户在问卷中对应的用户
     * 请求方式： POST
     * 请求地址：/api/tp/users/{openid}
     * @link {https://wj.qq.com/docs/openapi/sso/get_user}
     *
     * @param openId 第三方用户标识，用于辨别来自该第三方应用/平台的用户
     * @return       问卷系统用户ID
     */
    Long getUser(String openId);
}
