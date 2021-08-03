package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 问卷详情
 *
 * @author dongfangzan
 * @date 31.7.21 3:51 下午
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class SurveyDetail extends Survey{
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
    private boolean loginCheck;

    /** 允许回答次数 */
    private Integer answerCount;

    /** 是否开启白名单 */
    @JsonProperty("whitelist_enable")
    private boolean whitelistEnable;

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


            /** 选项中的"其他"填空 */

            /** 是否隐藏 */
            private boolean hidden;

            /** 预填内容 */
            private boolean prefill;

            /** 是否随机 */
            private boolean random;

            /** 多选题一行显示的个数 */
            private Integer maxRow;

            /** 选项引用的题号 */

            /** 选项列表 */
            private List<Option> options;

            /** 测评题 */

            /** 题目id */
            private String id;

            /** 真实序号 */
            @JsonProperty("real_index")
            private Integer realIndex;

            /** 编号 */
            private Integer index;

            /** 多选题 最多可选 ，单行文本题、多行文本题 最大字数 */

            /** 文本验证规则，包括 `('number' */
            private String validate;

            /** 多行文本题行数 */

            /** 多行文本题列数 */

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

            /** 量表类型 */

            /** 是否反转，数值默认从小到大排 */

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

                /** 选择选项后显示题目 */

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
