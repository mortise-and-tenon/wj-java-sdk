package fun.mortnon.wj.service;

import fun.mortnon.wj.model.AccessToken;

/**
 * @author dongfangzan
 * @date 27.7.21 5:22 下午
 */
public interface WjStorageConfig {

    /**
     * 获取token
     *
     * @return accessToken
     */
    AccessToken getAccessToken();

    /**
     * 设置accessToken
     *
     * @param accessToken token
     */
    void setAccessToken(AccessToken accessToken);

    /**
     * 强制过期token
     */
    void expireAccessToken();

    /**
     * 获取token过期时间
     *
     * @return token过期时间
     */
    long getAccessTokenExpiresTime();
}
