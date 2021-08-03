package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

/**
 * @author dongfangzan
 * @date 31.7.21 4:38 下午
 */
public enum SurveyType {
    /** 普通问卷 */
    NORMAL(0, "普通问卷"),

    /** 测评问卷 */
    ACCESS(1, "测评问卷")
    ;


    SurveyType(int type, String desc) {
        this.type = type;
        this.desc = desc;
    }

    /**
     * 根据类型码获取枚举
     *
     * @param type 类型码
     * @return     枚举
     */
    @JsonCreator
    public static SurveyType getByType(int type) {
        return Arrays.stream(SurveyType.values()).filter(surveyType -> type == surveyType.type)
                .findAny().orElse(null);
    }

    /** 类型码 */
    @Getter
    private final int type;

    /** 描述字段 */
    @Getter
    private final String desc;
}
