package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 问卷详情
 *
 * 废弃原因：腾讯问卷历史遗留问题导致的，后续可能不再使用
 *
 * @author dongfangzan
 * @date 31.7.21 3:51 下午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Deprecated
public class SurveyDetailLegacy extends Survey{
    private static final long serialVersionUID = -4234417084923328597L;

    // 基本内容
    /** 欢迎语 */
    private String prefix;

    /** 结束语 */
    private String suffix;

    /** 单页内容 */
    List<Page> pages;

    /** 页数 */
    @JsonProperty("page_count")
    private Integer pageCount;

    /** 问题数 */
    @JsonProperty("topic_count")
    private Integer topicCount;

    /** 回收开始时间 */
    private Long startTime;

    /** 回收结束时间 */
    private Long endTime;

    /** 创建时间 */
    private Long createTime;

    /** 更新时间 */
    private Long updateTime;

    /** 状态 */
    private SurveyState state;

    /** 使用的模板id，对应template_system表 */
    @JsonProperty("template_id")
    private Long templateId;

    // 基本设置
    /** 是否允许回到上一页 */
    private boolean prev;

    /** 是否显示题目序号 */
    private boolean titleIndex;

    /** 是否开启登陆验证 */
    @JsonProperty("login_check")
    private boolean loginCheck;

    private boolean login;

    /** 允许回答次数 */
    @JsonProperty("answer_count")
    private Integer answerCount;

    /** 是否开启白名单 */
    @JsonProperty("whitelist_enable")
    private boolean whitelistEnable;

    @JsonProperty("whitelist_type")
    private Integer whitelistType;

    /** 答题后跳转链接 */
    @JsonProperty("redirect_url")
    private String redirectUrl;

    /** 答题后推送数据的地址 */
    @JsonProperty("webhook_url")
    private String webhookUrl;

    @JsonProperty("survey_type")
    private SurveyType surveyType;

    /** 数组，题目随机顺序 */
    @JsonProperty("questions_random")
    private List<String> questionsRandom;

    /** 是否允许修改答案 */
    @JsonProperty("is_allow_update_answer")
    private boolean isAllowUpdateAnswer;

    /** 是否获取用户位置信息 */
    @JsonProperty("is_enabled_location")
    private boolean isEnabledLocation;

    /** 是否为测评问卷 */
    @JsonProperty("is_access_survey")
    private boolean isAccessSurvey;

    /** 文档未描述字段 */
    private String callback;

    /** 文档未描述字段 */
    @JsonProperty("show_stat")
    private boolean showStat;

    /** 文档未描述字段 */
    private boolean ip;

    /** 文档未描述字段 */
    private boolean pc;

    /** 文档未描述字段 */
    private String lang;

    /** 文档未描述字段 */
    private String purpose;

    private String url;

    @JsonProperty("thumb_url")
    private String thumbUrl;

    /** 文档未描述字段 */
    @JsonProperty("previewURL")
    private String previewUrl;

    private Object styles;

    private Reward reward;

    @JsonProperty("default_reward")
    private boolean defaultReward;

    @JsonProperty("is_enabled_luckymoney")
    private boolean isEnabledLuckymoney;

    @JsonProperty("is_enabled_common_luckymoney")
    private boolean isEnabledCommonLuckymoney;

    @JsonProperty("is_enabled_samples")
    private boolean isEnabledSamples;

    @JsonProperty("has_ongoing_groups")
    private boolean hasOngoingGroups;

    @JsonProperty("create_owner")
    private Long createOwner;

    @JsonProperty("survey_rules")
    private Object surveyRules;

    @JsonProperty("survey_dsl")
    private Object surveyDsl;

    @JsonProperty("hide_tencent_relevant_allowed")
    private boolean hideTencentRelevantAllowed;

    @JsonProperty("hide_tencent_relevant")
    private boolean hideTencentRelevant;

    @JsonProperty("custom_copyright")
    private String customCopyright;

    @JsonProperty("remark_name")
    private String remarkName;

    @JsonProperty("is_register")
    private boolean isRegister;

    @JsonProperty("is_enabled_data_cleaner")
    private boolean isEnabledDataCleaner;

    @JsonProperty("is_enabled_attendance")
    private boolean isEnabledAttendance;

    @JsonProperty("daily_attendance_count")
    private Integer dailyAttendanceCount;

    @JsonProperty("is_enabled_attendance_cert")
    private boolean isEnabledAttendanceCert;

    @JsonProperty("is_hide_advertisement")
    private boolean isHideAdvertisement;

    @JsonProperty("is_allow_get_healthy_status")
    private boolean isAllowGetHealthyStatus;

    @JsonProperty("is_redirect_url_verified")
    private boolean isRedirectUrlVerified;

    private Integer recycle;

    private Integer validCount;

    private String cache;

    private Skin skin;

    @JsonProperty("is_assess_survey")
    private Boolean isAssessSurvey;
    @JsonProperty("code")
    private Integer code;
    @JsonProperty("credit_level")
    private Integer creditLevel;
    @JsonProperty("timestamp")
    private Integer timestamp;
    @JsonProperty("is_sample_user")
    private Boolean isSampleUser;
    @JsonProperty("sample_reward_type")
    private Integer sampleRewardType;
    @JsonProperty("sample_reward_amount")
    private Integer sampleRewardAmount;
    @JsonProperty("admin_enable")
    private Boolean adminEnable;
    @JsonProperty("answer_alert")
    private AnswerAlertDTO answerAlert;
    @JsonProperty("answer_notice")
    private Boolean answerNotice;
    @JsonProperty("checkin_started_at")
    private String checkinStartedAt;
    @JsonProperty("checkin_expired_at")
    private String checkinExpiredAt;
    @JsonProperty("checkin_qrcode_uri")
    private String checkinQrcodeUri;
    @JsonProperty("is_checkin_approved_only")
    private Boolean isCheckinApprovedOnly;
    @JsonProperty("is_remind_before_checkin")
    private Boolean isRemindBeforeCheckin;
    @JsonProperty("is_allowed_enable_attendance_cert")
    private Boolean isAllowedEnableAttendanceCert;
    @JsonProperty("is_current_user_weiyun_binding")
    private Boolean isCurrentUserWeiyunBinding;
    @JsonProperty("is_enabled_weiyun")
    private Boolean isEnabledWeiyun;
    @JsonProperty("weiyun_user_id")
    private Integer weiyunUserId;
    @JsonProperty("survey_user")
    private SurveyUserDTO surveyUser;

    @NoArgsConstructor
    @Data
    public static class AnswerAlertDTO {
        @JsonProperty("survey_id")
        private Integer surveyId;
        @JsonProperty("status")
        private Integer status;
        @JsonProperty("type")
        private Integer type;
        @JsonProperty("counter")
        private Integer counter;
        @JsonProperty("limit")
        private Integer limit;
        @JsonProperty("operator_id")
        private Integer operatorId;
        @JsonProperty("update_time")
        private Integer updateTime;
        @JsonProperty("send_cnt")
        private Integer sendCnt;
    }

    @NoArgsConstructor
    @Data
    public static class SurveyUserDTO {
        @JsonProperty("level")
        private Integer level;
        @JsonProperty("level_str")
        private String levelStr;
        @JsonProperty("granted_query")
        private Object grantedQuery;
        @JsonProperty("granted_search")
        private Object grantedSearch;
    }

    @Data
    @Accessors(chain = true)
    public static class Skin {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("object_id")
        private Long objectId;
        @JsonProperty("object_type")
        private Integer objectType;
        @JsonProperty("name")
        private String name;
        @JsonProperty("theme_color")
        private String themeColor;
        @JsonProperty("scheme_color")
        private Integer schemeColor;
        @JsonProperty("support_color")
        private String supportColor;
        @JsonProperty("background_image")
        private String backgroundImage;
        @JsonProperty("preview_image")
        private String previewImage;
        @JsonProperty("is_show_logo_image")
        private Boolean isShowLogoImage;
        @JsonProperty("logo_image")
        private String logoImage;
        @JsonProperty("logo_position")
        private Integer logoPosition;
        @JsonProperty("theme_v1")
        private String themeV1;
        @JsonProperty("is_hidden")
        private Boolean isHidden;
        @JsonProperty("is_top")
        private Boolean isTop;
        @JsonProperty("position")
        private Integer position;
        @JsonProperty("creator")
        private Long creator;
        @JsonProperty("status")
        private Integer status;
        @JsonProperty("created_at")
        private String createdAt;
        @JsonProperty("updated_at")
        private String updatedAt;
    }

    @Data
    @Accessors(chain = true)
    public static class Reward {

        private String name;

        private String contact;

        private Long endDateTime;

        private String deliverMethod;

        private String lotteryMethod;

        private String winPrizeProbability;

        @JsonProperty("reward_type")
        private Integer rewardType;

        @JsonProperty("reward_cnt")
        private Integer rewardCount;

        @JsonProperty("reward_flag")
        private Integer rewardFlag;

        private Integer status;

        private List<RewardContent> list;

        @Data
        @Accessors(chain = true)
        public static class RewardContent {
            private Long id;

            private String name;

            private String value;

            private Integer count;
        }
    }

    @Data
    @Accessors(chain = true)
    public static class Page {
        /** 单页标识 */
        private String id;

        /** 序号 */
        private String index;

        /** 问题列表 */
        private List<Question> questions;

        @Data
        @Accessors(chain = true)
        public static class Question {
            /** 题目类型 */
            private String type;

            /** 题目标题 */
            private String title;

            /** 题目备注 */
            private String description;

            /** 是否必填 */
            private boolean required;

            /** 答题后跳转的题目 */
            @JsonProperty("goto")
            private String gotoQuestion;

            /** 选项中的"其他"填空 */
            @JsonProperty("blank_setting")
            private List<Object> blankSetting;

            /** 是否隐藏 */
            private boolean hidden;

            /** 预填内容 */
            private boolean prefill;

            /** 是否随机 */
            private boolean random;

            /** 多选题一行显示的个数 */
            private Integer maxRow;

            /** 选项引用的题号 */
            private Object refer;

            /** 选项列表 */
            private List<Option> options;

            /** 测评题 */
            private Object assess;

            /** 题目id */
            private String id;

            /** 真实序号 */
            @JsonProperty("real_index")
            private Integer realIndex;

            /** 编号 */
            private Integer index;

            /** 多选题 最多可选 ，单行文本题、多行文本题 最大字数 */
            private Object maxLength;

            /** 文本验证规则，包括 `('number' */
            private String validate;

            /** 多行文本题行数 */
            private Object rows;

            /** 多行文本题列数 */
            private String cols;

            /** 量表类型 */
            private String starShow;

            /** 废弃字段 */
            @Deprecated
            private String otherStr;

            /** 废弃字段 */
            @Deprecated
            private boolean isOther;

            /** 量表题起始值 */
            private Integer starBeginNum;

            /** 量表范围，2～10 */
            private Object starNum;

            /** 量表类型 */
            private Object starType;

            /** 是否反转，数值默认从小到大排 */
            private Object revertSort;

            /** 量表题起始文案 */
            private String starShowCustomStart;

            /** 量表题起末尾文案 */
            private String starShowCustomEnd;

            /** */
            private List<Integer> starOptions;

            /** 矩阵题子问题列表 */
            private List<SubTitle> subTitles;

            /** 联动题各级标题 */
            private List<String> levels;

            /** 联动题列表(嵌套) [选项ID,选项文本, 子选项列表] */
            private List<Object> groups;

            /** 允许上传文件类型 */
            @JsonProperty("file_type_limit")
            private List<String> fileTypeLimit;

            /** 允许上传文件大小(KB) */
            @JsonProperty("file_size_limit")
            private Long fileSizeLimit;

            @Data
            @Accessors(chain = true)
            public static class Option {
                /**	选项 ID */
                private String id;

                /** 选择选项后跳转题目 */
                @JsonProperty("goto")
                private String gotoQuestion;

                /** 选择选项后显示题目 */
                private List<Object> display;

                /** 题目设置选项随机的时候，固定当前选项位置 */
                private boolean noRandom;

                /** 选项文案 */
                private String text;

                /** 当题目为多选题的时候，设置当前选项为互斥选项 */
                private boolean exclusive;
            }

            @Data
            @Accessors(chain = true)
            public static class SubTitle {
                /** 矩阵题子问题 ID */
                private String id;

                /** 矩阵题子问题文本 */
                private String text;
            }
        }
    }
}
