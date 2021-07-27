package fun.mortnon.wj.exception;

import fun.mortnon.wj.model.Error;
import fun.mortnon.wj.model.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author dongfangzan
 * @date 27.7.21 12:46 下午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class WjException extends RuntimeException{

    /** 错误码 */
    private ErrorCode errorCode;

    /** 二级错误码 */
    private Error error;

    /** 错误消息 */
    private String message;
}
