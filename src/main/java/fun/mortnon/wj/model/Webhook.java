package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author Moon Wu
 * @date 2021/8/2
 */
@Data
@Accessors(chain = true)
public class Webhook {
    /**
     * 自增 ID
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long id;

    /**
     * 用户 ID
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("user_id")
    private Long UserId;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String object;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String options;

    /**
     * 进行数据推送的 Url，注意该地址需要能接收 POST 请求
     */
    private String url;

    /**
     * 是否启用（true：启用 false：关闭）
     */
    @JsonProperty("is_active")
    private boolean isActive;

    /**
     * 创建时间
     */
    @JsonProperty("created_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createdAt;

    /**
     * 修改时间
     */
    @JsonProperty("updated_at")
    @JsonFormat(shape = JsonFormat.Shape.STRING,pattern = "yyyy-MM-dd hh:mm:ss")
    private Date updatedAt;
}
