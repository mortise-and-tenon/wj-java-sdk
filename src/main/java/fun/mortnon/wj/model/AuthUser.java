package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @author qiansl
 * @version 1.0
 * @date 2022/3/8 3:31 下午
 * @description 注册用户返回信息
 */
@Data
public class AuthUser implements Serializable {

    private static final long serialVersionUID = -6733814420610307658L;

    /** 用户id */
    @JsonProperty("user_id")
    private Long userId;

    /** 请求id */
    @JsonProperty("respondent_id")
    private Long respondentId;
}
