package fun.mortnon.wj.model;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 问卷摘要数据
 *
 * @author dongfangzan
 * @date 28.7.21 10:20 上午
 */
@Data
@Accessors(chain = true)
public class Survey {
    private Long id;

    private String hash;

    private String title;
}
