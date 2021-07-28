package fun.mortnon.wj.service;

import fun.mortnon.wj.model.AccessToken;
import fun.mortnon.wj.model.RequestContent;
import fun.mortnon.wj.model.WjAccessTokenResponse;
import fun.mortnon.wj.model.WjBaseResponse;

import java.util.Map;
import java.util.function.Supplier;

/**
 * 腾讯问卷基础服务
 *
 * @author dongfangzan
 * @date 27.7.21 10:59 上午
 */
public interface WjService {

    /**
     * get请求
     *
     * @param requestContent 请求上下文
     * @param handler        响应体处理器
     * @return               返回值
     */
    WjBaseResponse doGet(RequestContent requestContent, Supplier<WjBaseResponse> handler);


    /**
     * get请求，带token
     *
     * @param requestContent 请求上下文
     * @param handler        响应体处理器
     * @return               返回结果
     */
    WjBaseResponse doGetWithToken(RequestContent requestContent, Supplier<WjBaseResponse> handler);
//
//
//    /**
//     * post请求
//     *
//     * @param url    地址
//     * @param params 参数
//     * @return       返回值
//     */
//    String doPost(String url, Map<String, String> params);

    /**
     * 获取accessToken
     *
     * @param appId     appId，由腾讯问卷分配
     * @param secret    secret，由腾讯问卷分配
     * @param grantType 固定为 client_credential
     * @return          返回结果
     */
    AccessToken accessToken(String appId, String secret, String grantType);

    /**
     * 获取token
     *
     * @return token
     */
    String getAccessToken();

    /**
     * 获取问卷管理服务
     *
     * @return 问卷管理服务
     */
    WjManageService getWjManageService();
}
