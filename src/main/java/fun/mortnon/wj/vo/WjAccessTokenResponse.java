package fun.mortnon.wj.vo;

import fun.mortnon.wj.model.AccessToken;
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

    /** Token */
    private AccessToken data;
}
