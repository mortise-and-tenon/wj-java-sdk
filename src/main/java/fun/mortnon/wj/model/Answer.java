package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 回答
 *
 * @author dongfangzan
 * @date 31.7.21 5:54 下午
 */
@Data
@Accessors(chain = true)
public class Answer implements Serializable {
    private static final long serialVersionUID = -5600942339014230363L;

    /** 答案ID */
    @JsonProperty("answer_id")
    private Long answerId;

    /** 用户标识 */
    private Long qq;

    /** 自定义信息（默认为空） */
    @JsonProperty("openid")
    private String openId;

    /** 用户在腾讯问卷的微信openid（默认为空）*/
    @JsonProperty("weixin_openid")
    private String weixinOpenId;

    /** 用户开始回答的时间 */
    @JsonProperty("started_at")
    private String startedAt;

    /** 用户提交答案的时间 */
    @JsonProperty("ended_at")
    private String endedAt;

    /** 用户回答的分数（默认null）（暂不提供） */
    private Long score;

    /** 企业微信用户信息（特定使用）*/

    /** 智慧校园用户信息（特定使用）*/

    /** 微校用户信息（特定使用）*/

    /** 用户的回答详情 */
    private List<AnswerDetail> answer;
}
