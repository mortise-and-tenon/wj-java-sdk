package fun.mortnon.wj.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author Moon Wu
 * @date 2021/8/2
 */
@Data
@Accessors(chain = true)
public class ResponseResult {
    private String result;
}
