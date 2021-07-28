package fun.mortnon.wj.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author dongfangzan
 * @date 27.7.21 11:34 上午
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class RequestContent {

    /** 请求地址 */
    private String url;

    /** 请求参数 */
    private Map<String, Object> param;

    /** 结果 */
    private String result;
}
