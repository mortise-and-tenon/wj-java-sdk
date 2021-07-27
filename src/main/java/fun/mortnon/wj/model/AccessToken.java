package fun.mortnon.wj.model;

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
    private String token;

    /** 过期时间 */
    private Long expiresIn;
}
