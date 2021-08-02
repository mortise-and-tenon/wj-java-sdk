package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 企业信息
 *
 * @author dongfangzan
 * @date 31.7.21 6:42 下午
 */
@Data
@Accessors(chain = true)
public class Org implements Serializable {
    private static final long serialVersionUID = -7281322239536218620L;

    /** 企业id */
    private Long id;

    private String name;

    private String phone;

    @JsonProperty("current_user_role")
    private Integer currentUserRole;

    @JsonProperty("creator_id")
    private Long creatorId;

    @JsonProperty("creator_name")
    private String creatorName;

    @JsonProperty("creator_avatar_url")
    private String creatorAvatarUrl;

    /** 企业绑定的通讯录ID,用与管理通讯录 */
    @JsonProperty("team_id")
    private Long teamId;
}
