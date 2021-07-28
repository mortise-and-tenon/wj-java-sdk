package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author dongfangzan
 * @date 27.7.21 11:18 上午
 */
@Data
@Accessors(chain = true)
public class AccessToken {

    /** token */
    @JsonProperty("access_token")
    private String token;

    /** 过期时间 */
    @JsonProperty("expires_in")
    private Long expiresIn;
}
