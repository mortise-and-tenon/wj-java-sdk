package fun.mortnon.wj.vo;

import fun.mortnon.wj.model.Survey;
import fun.mortnon.wj.model.WjPage;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author dongfangzan
 * @date 28.7.21 10:18 上午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class WjSurveyResponse extends WjBaseResponse{

    /** 数据 */
    private WjPage<Survey> data;
}
