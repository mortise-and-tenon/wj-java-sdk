package fun.mortnon.wj.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 问卷分页数据
 *
 * @author dongfangzan
 * @date 28.7.21 10:19 上午
 */
@Data
@Accessors(chain = true)
public class WjPage<T> {

    /** 每页数量 */
    private int total;

    /** 结果数据 */
    private List<T> list;
}
