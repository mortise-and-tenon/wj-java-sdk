package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;

import java.util.Arrays;

/**
 * 问卷状态枚举
 *
 * @author dongfangzan
 * @date 31.7.21 4:05 下午
 */
public enum SurveyState {
    /** 回收中 */
    RECYCLING(2, "回收中"),

    /** 暂停回收 */
    STOP_RECYCLE(3, "暂停回收");


    SurveyState(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    /**
     * 根据状态码获取枚举
     *
     * @param state 状态码
     * @return      枚举
     */
    @JsonCreator
    public static SurveyState getByState(int state) {
        return Arrays.stream(SurveyState.values()).filter(surveyState -> state == surveyState.state)
                .findAny().orElse(null);
    }

    /** 状态码 */
    @Getter
    private final int state;

    /** 描述字段 */
    @Getter
    private final String desc;
}
