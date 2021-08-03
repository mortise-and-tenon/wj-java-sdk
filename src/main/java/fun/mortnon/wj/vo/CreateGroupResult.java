package fun.mortnon.wj.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author dongfangzan
 * @date 3.8.21 2:46 下午
 */
@Data
@Accessors
public class CreateGroupResult implements Serializable {

    private static final long serialVersionUID = 1824768975665139164L;

    /** 分组ID */
    @JsonProperty("group_id")
    private Long groupId;

    /** 分组名称 */
    private String name;
}
