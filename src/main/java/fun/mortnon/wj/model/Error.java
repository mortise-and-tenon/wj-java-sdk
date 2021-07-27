package fun.mortnon.wj.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author dongfangzan
 * @date 27.7.21 10:41 上午
 */
@Data
@Accessors(chain = true)
public class Error {

    /** 内部错误类型 */
    private String type;
}
