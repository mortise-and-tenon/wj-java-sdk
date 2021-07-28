package fun.mortnon.wj.model.utils;

import fun.mortnon.wj.constants.WjApiConstants;
import fun.mortnon.wj.exception.WjException;
import fun.mortnon.wj.model.AccessToken;
import fun.mortnon.wj.model.ErrorCode;
import fun.mortnon.wj.model.RequestContent;
import fun.mortnon.wj.model.WjBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * @author dongfangzan
 * @date 27.7.21 11:26 上午
 */
@Slf4j
public class HttpClientTemplate {

    /**
     * 执行get请求
     *
     * @param requestContent 请求上下文
     * @param handler        响应体处理器
     * @return               响应结果
     */
    public static WjBaseResponse doGet(RequestContent requestContent, Supplier<? extends WjBaseResponse> handler) {
        return doGetWithToken(requestContent, null, handler);
    }

    /**
     * 带token的get请求
     *
     * @param requestContent 请求上下文
     * @param token          token
     * @param handler        结果处理器
     * @return               返回结果
     */
    public static WjBaseResponse doGetWithToken(RequestContent requestContent, String token, Supplier<? extends  WjBaseResponse> handler) {
        long time = System.currentTimeMillis();
        Map<String, Object> params = requestContent.getParam();
        if (StringUtils.isNotBlank(token)) {
            // token 不为空，写token
            if (null == params || 0 == params.size()) {
                params = new HashMap<>(1);
            }

            params.put("access_token", token);
        }

        String url = buildGetUrlWithParams(requestContent.getUrl(), params);

        long timeClient = System.currentTimeMillis();
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

        log.info("创建httpClient耗时：{}ms", System.currentTimeMillis() - timeClient);
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            httpGet.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());

            long execTime = System.currentTimeMillis();
            response = closeableHttpClient.execute(httpGet);
            log.info("调用耗时：{}ms", System.currentTimeMillis() - execTime);

            HttpEntity httpEntity = response.getEntity();

            AssertUtils.nonNull(httpEntity, ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.nonNull(response, ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.nonNull(response.getStatusLine(), ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.isEquals(response.getStatusLine().getStatusCode(), 200, ErrorCode.RemoteServerError, "远端服务器异常");

            String result = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);

            log.info("调用地址：「{}」正常，返回结果：「{}」", url, result);

            requestContent.setResult(result);

            WjBaseResponse baseResponse = JacksonUtil.jsonToObject(result, WjBaseResponse.class);
            AssertUtils.nonNull(baseResponse, ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.isEquals(ErrorCode.OK, baseResponse.getCode(), baseResponse.getCode(), baseResponse.getError(), baseResponse.getCode().getDesc());

            long handleTime = System.currentTimeMillis();
            WjBaseResponse wjBaseResponse = handler.get();
            log.info("处理响应体耗时：{}ms", System.currentTimeMillis() - handleTime);
            return wjBaseResponse;
        } catch (WjException e) {
            log.error("调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            throw new WjException(ErrorCode.RemoteServerError, null, e.getMessage());
        } finally {
            long closeTime = System.currentTimeMillis();
            try {
                // 释放资源
                if (closeableHttpClient != null) {
                    closeableHttpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                log.error("调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            }
            log.info("关闭请求耗时：{}ms", System.currentTimeMillis() - closeTime);

            log.info("调用地址：「{}」完成，总耗时{}ms", url, System.currentTimeMillis() - time);
        }
    }

    public static WjBaseResponse doPost(RequestContent requestContent, Supplier<? extends WjBaseResponse> handler) {

        return handler.get();
    }

    /**
     * 使用参数拼接url
     *
     * @param url    url
     * @param params 参数
     * @return       拼接好的url
     */
    private static String buildGetUrlWithParams(String url, Map<String, Object> params) {
        if (Objects.isNull(params) || 0 == params.size()) {
            return url;
        }

        StringBuilder sb = new StringBuilder(url);
        sb.append("?");
        params.forEach((key, value) -> {
            sb.append(key);
            sb.append("=");
            sb.append(value);
            sb.append("&");
        });

        // 去掉最后一位&
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
