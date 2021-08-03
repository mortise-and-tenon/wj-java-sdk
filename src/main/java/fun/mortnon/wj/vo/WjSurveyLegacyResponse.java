package fun.mortnon.wj.vo;

import fun.mortnon.wj.model.SurveyDetailLegacy;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 问卷详情
 *
 * @author dongfangzan
 * @date 31.7.21 5:50 下午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class WjSurveyLegacyResponse extends WjBaseResponse{
    private static final long serialVersionUID = -5292836689715146601L;

    /** 问卷详情 */
    private SurveyDetailLegacy data;
}
