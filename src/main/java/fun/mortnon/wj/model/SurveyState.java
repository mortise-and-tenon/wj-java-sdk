package fun.mortnon.wj.model;

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
    STOP_RECYCLE(3, "暂停回收")

    ;


    SurveyState(int state, String desc) {
        this.state = state;
        this.desc = desc;
    }

    private int state;

    private String desc;
}
