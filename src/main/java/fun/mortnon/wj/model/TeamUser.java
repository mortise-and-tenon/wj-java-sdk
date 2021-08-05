package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author dongfangzan
 * @date 5.8.21 11:35 上午
 */
@NoArgsConstructor
@Data
@Accessors(chain = true)
public class TeamUser implements Serializable {

    private static final long serialVersionUID = 6124440603550623539L;
    /** 用户头像*/
    @JsonProperty("avatar")
    private String avatar;

    /** 所属分组路径 */
    @JsonProperty("group_full_name")
    private String groupFullName;

    @JsonProperty("is_org_available")
    private Boolean isOrgAvailable;

    @JsonProperty("is_org_creator")
    private Boolean isOrgCreator;

    /** 用户姓名 */
    @JsonProperty("name")
    private String name;

    @JsonProperty("respon_id")
    private Integer responId;

    /** 用户权限角色 */
    @JsonProperty("role")
    private Integer role;

    /** 用户id */
    @JsonProperty("user_id")
    private Long userId;

    /** 分组id */
    @JsonProperty("group_id")
    private Integer groupId;
}
