package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author dongfangzan
 * @date 27.7.21 11:16 上午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class WjAccessTokenResponse extends WjBaseResponse{

    /** 请求id */
    @JsonProperty("request_id")
    private String requestId;

    /** Token */
    private AccessToken data;
}
