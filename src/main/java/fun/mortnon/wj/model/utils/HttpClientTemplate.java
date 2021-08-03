package fun.mortnon.wj.model.utils;

import fun.mortnon.wj.exception.WjException;
import fun.mortnon.wj.model.ErrorCode;
import fun.mortnon.wj.model.RequestContent;
import fun.mortnon.wj.vo.WjBaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.stream.Collectors;

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
    public static <T> WjBaseResponse<T> doGet(RequestContent requestContent, Supplier<? extends WjBaseResponse<T>> handler) {
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
    public static <T> WjBaseResponse<T> doGetWithToken(RequestContent requestContent, String token, Supplier<? extends  WjBaseResponse<T>> handler) {
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
            String result = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);

            log.info("GET 调用地址：「{}」，返回结果：「{}」", url, result);

            AssertUtils.isEquals(response.getStatusLine().getStatusCode(), 200, ErrorCode.RemoteServerError, "远端服务器异常");
            requestContent.setResult(result);

            WjBaseResponse baseResponse = JacksonUtil.jsonToObject(result, WjBaseResponse.class);
            AssertUtils.nonNull(baseResponse, ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.isEquals(ErrorCode.OK, baseResponse.getCode(), baseResponse.getCode(), baseResponse.getError(), baseResponse.getCode().getDesc());

            long handleTime = System.currentTimeMillis();
            WjBaseResponse wjBaseResponse = handler.get();
            log.info("处理响应体耗时：{}ms", System.currentTimeMillis() - handleTime);
            return wjBaseResponse;
        } catch (WjException e) {
            log.error("GET 调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("GET 调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
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
                log.error("GET 调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            }
            log.info("关闭请求耗时：{}ms", System.currentTimeMillis() - closeTime);

            log.info("GET 调用地址：「{}」完成，总耗时{}ms", url, System.currentTimeMillis() - time);
        }
    }

    /**
     * post 请求
     *
     * @param requestContent
     * @param handler
     * @param <T>
     * @return
     */
    public static <T> WjBaseResponse<T> doPost(RequestContent requestContent, Supplier<? extends WjBaseResponse<T>> handler) {
        long time = System.currentTimeMillis();
        Map<String, Object> params = requestContent.getParam();

        String url = buildGetUrlWithParams(requestContent.getUrl(), params);

        long timeClient = System.currentTimeMillis();
        CloseableHttpClient closeableHttpClient = HttpClientBuilder.create().build();

        log.info("创建httpClient耗时：{}ms", System.currentTimeMillis() - timeClient);
        HttpPost httpPost = new HttpPost(url);

        Map<String, Object> formBody = requestContent.getFormBody();
        if (Objects.nonNull(formBody) && formBody.size() > 0) {
            List<BasicNameValuePair> list = formBody.entrySet().stream().filter(entry -> Objects.nonNull(entry.getValue()))
                    .map(entry -> new BasicNameValuePair(entry.getKey(), entry.getValue().toString()))
                    .collect(Collectors.toList());
            try {
                UrlEncodedFormEntity formEntity = new UrlEncodedFormEntity(list, "UTF-8");
                httpPost.setEntity(formEntity);
            } catch (UnsupportedEncodingException e) {
                // 不用做任何事情，不会出现异常
            }
        }
        CloseableHttpResponse response = null;
        try {
            long execTime = System.currentTimeMillis();
            response = closeableHttpClient.execute(httpPost);
            log.info("调用耗时：{}ms", System.currentTimeMillis() - execTime);

            HttpEntity httpEntity = response.getEntity();

            AssertUtils.nonNull(httpEntity, ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.nonNull(response, ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.nonNull(response.getStatusLine(), ErrorCode.RemoteServerError, "远端服务器未返回消息");

            String result = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);

            log.info("POST 调用地址：「{}」，返回结果：「{}」", url, result);

            AssertUtils.isEquals(response.getStatusLine().getStatusCode(), 200, ErrorCode.RemoteServerError, "远端服务器异常");


            requestContent.setResult(result);

            WjBaseResponse baseResponse = JacksonUtil.jsonToObject(result, WjBaseResponse.class);
            AssertUtils.nonNull(baseResponse, ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.isEquals(ErrorCode.OK, baseResponse.getCode(), baseResponse.getCode(), baseResponse.getError(), baseResponse.getCode().getDesc());

            long handleTime = System.currentTimeMillis();
            WjBaseResponse wjBaseResponse = handler.get();
            log.info("处理响应体耗时：{}ms", System.currentTimeMillis() - handleTime);
            return wjBaseResponse;
        } catch (WjException e) {
            log.error("POST 调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("POST 调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
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
                log.error("POST 调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            }
            log.info("关闭请求耗时：{}ms", System.currentTimeMillis() - closeTime);

            log.info("POST 调用地址：「{}」完成，总耗时{}ms", url, System.currentTimeMillis() - time);
        }
    }

    /**
     * 执行delete请求
     *
     * @param requestContent 请求上下文
     * @param handler        响应体处理器
     * @return               响应结果
     */
    public static <T> WjBaseResponse<T> doDelete(RequestContent requestContent, Supplier<? extends WjBaseResponse<T>> handler) {
        return doDeleteWithToken(requestContent, null, handler);
    }

    /**
     * 带token的delete请求
     *
     * @param requestContent 请求上下文
     * @param token          token
     * @param handler        结果处理器
     * @return               返回结果
     */
    public static <T> WjBaseResponse<T> doDeleteWithToken(RequestContent requestContent, String token, Supplier<? extends  WjBaseResponse<T>> handler) {
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
            String result = EntityUtils.toString(httpEntity, StandardCharsets.UTF_8);

            log.info("DELETE 调用地址：「{}」，返回结果：「{}」", url, result);

            AssertUtils.isEquals(response.getStatusLine().getStatusCode(), 200, ErrorCode.RemoteServerError, "远端服务器异常");
            requestContent.setResult(result);

            WjBaseResponse baseResponse = JacksonUtil.jsonToObject(result, WjBaseResponse.class);
            AssertUtils.nonNull(baseResponse, ErrorCode.RemoteServerError, "远端服务器未返回消息");
            AssertUtils.isEquals(ErrorCode.OK, baseResponse.getCode(), baseResponse.getCode(), baseResponse.getError(), baseResponse.getCode().getDesc());

            long handleTime = System.currentTimeMillis();
            WjBaseResponse wjBaseResponse = handler.get();
            log.info("处理响应体耗时：{}ms", System.currentTimeMillis() - handleTime);
            return wjBaseResponse;
        } catch (WjException e) {
            log.error("DELETE 调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            throw e;
        } catch (Exception e) {
            log.error("DELETE 调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
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
                log.error("DELETE 调用地址：「{}」发生错误，错误原因：「{}」", url, e.getMessage());
            }
            log.info("关闭请求耗时：{}ms", System.currentTimeMillis() - closeTime);

            log.info("DELETE 调用地址：「{}」完成，总耗时{}ms", url, System.currentTimeMillis() - time);
        }
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
            if (value instanceof List) {
                for (Object obj : (List) value) {
                    sb.append(key);
                    sb.append("[]=");
                    sb.append(obj);
                    sb.append("&");
                }
            } else {
                sb.append(key);
                sb.append("=");
                sb.append(value);
                sb.append("&");
            }
        });

        // 去掉最后一位&
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
