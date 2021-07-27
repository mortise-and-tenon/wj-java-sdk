package fun.mortnon.wj.model.utils;

import fun.mortnon.wj.constants.WjApiConstants;
import fun.mortnon.wj.exception.WjException;
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
        long time = System.currentTimeMillis();
        Map<String, String> params = requestContent.getParam();
        String url = buildGetUrlWithParams(WjApiConstants.DOMAIN_NAME + requestContent.getUrl(), params);

        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);

        CloseableHttpResponse response = null;
        try {
            httpGet.setHeader("Content-Type", ContentType.APPLICATION_JSON.getMimeType());

            response = closeableHttpClient.execute(httpGet);

            HttpEntity httpEntity = response.getEntity();

            AssertUtils.nonNull(httpEntity, ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.nonNull(response, ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.nonNull(response.getStatusLine(), ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.isEquals(response.getStatusLine().getStatusCode(), 200, ErrorCode.RemoteServerError, "远端服务器异常");

            String result = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);

            log.info("调用地址：「{}」正常，返回结果：「{}」", url, result);

            requestContent.setResult(result);

            return handler.get();
        } catch (WjException e) {
            log.error("调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            throw new WjException(ErrorCode.RemoteServerError, null, e.getMessage());
        } finally {
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
    private static String buildGetUrlWithParams(String url, Map<String, String> params) {
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
