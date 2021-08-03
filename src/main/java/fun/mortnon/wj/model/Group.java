package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 分组
 *
 * @author dongfangzan
 * @date 31.7.21 7:12 下午
 */
@Data
@Accessors(chain = true)
public class Group implements Serializable {
    private static final long serialVersionUID = 3947564684497700565L;

    /** 分组ID */
    private Long id;

    /** 企业ID */
    @JsonProperty("team_id")
    private Long teamId;

    /** 上级ID */
    @JsonProperty("parent_id")
    private Long parentId;

    private Long source;

    @JsonProperty("remoteId")
    private String remoteId;

    /** 分组名称 */
    private String name;

    @JsonProperty("full_name")
    private String fullName;

    private Long order;

    @JsonProperty("is_valid")
    private boolean isValid;

    /** 创建时间 */
    @JsonProperty("created_at")
    private String createdAt;

    /** 修改时间 */
    @JsonProperty("updated_at")
    private String updatedAt;

    /** 总条数 */
    private Long total;
}
