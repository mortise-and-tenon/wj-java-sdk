package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 企业管理员
 *
 * @author dongfangzan
 * @date 31.7.21 7:00 下午
 */
@Data
@Accessors(chain = true)
public class OrgUser implements Serializable {
    private static final long serialVersionUID = -2098956139701155616L;

    /** 用户id */
    @JsonProperty("user_id")
    private Long userId;

    /** 管理员昵称 */
    private String name;

    /** 管理员头像 */
    private String avatar;

    /** 管理员角色 0:企业创建者;2:企业管理员;3:问卷管理员 */
    private Long role;


}
