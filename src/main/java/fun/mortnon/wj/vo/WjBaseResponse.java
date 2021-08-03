package fun.mortnon.wj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import fun.mortnon.wj.model.Error;
import fun.mortnon.wj.model.ErrorCode;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 问卷响应体
 *
 * @author dongfangzan
 * @date 27.7.21 10:26 上午
 */
@Data
@Accessors(chain = true)
public class WjBaseResponse<T> implements Serializable {

    private static final long serialVersionUID = 6861752766183024694L;

    /** 错误码 */
    protected ErrorCode code;

    /** 错误信息 */
    protected Error error;

    /** 请求id */
    @JsonProperty("request_id")
    private String requestId;

    /** 数据 */
    private T data;
}
