package fun.mortnon.wj.service;

import fun.mortnon.wj.model.WjAccessTokenResponse;
import fun.mortnon.wj.model.WjBaseResponse;

import java.util.Map;

/**
 * 腾讯问卷基础服务
 *
 * @author dongfangzan
 * @date 27.7.21 10:59 上午
 */
public interface WjService {

//    /**
//     * get请求
//     *
//     * @param url    地址
//     * @param params 参数
//     * @return       返回值
//     */
//    String doGet(String url, Map<String, String> params);
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
    WjAccessTokenResponse accessToken(String appId, String secret, String grantType);
}