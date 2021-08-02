package fun.mortnon.wj.model;

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

    private int type;

    private String desc;
}
