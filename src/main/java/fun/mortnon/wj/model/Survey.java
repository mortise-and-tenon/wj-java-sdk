package fun.mortnon.wj.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 问卷摘要数据
 *
 * @author dongfangzan
 * @date 28.7.21 10:20 上午
 */
@Data
@Accessors(chain = true)
public class Survey implements Serializable {
    private static final long serialVersionUID = 8940123464836843109L;

    /** 编号 */
    protected Long id;

    /** 哈希 */
    protected String hash;

    /** 标题 */
    protected String title;
}
