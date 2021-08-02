package fun.mortnon.wj.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 用户回答详情
 *
 * @author dongfangzan
 * @date 31.7.21 6:01 下午
 */
@Data
@Accessors
public class AnswerDetail implements Serializable {
    private static final long serialVersionUID = 7825864239998780411L;

    /** 问卷页码 */
    private String id;

    /** 详细回答详情 */
    private List<AnswerQuestion> questions;

    @Data
    @Accessors(chain = true)
    public static class AnswerQuestion {
        /** 题目ID */
        private String id;

        /** 题目类型 */
        private String type;

        /** 文本输入（单行文本、多行文本、量表题等） */
        private String text;

        /** 选择输入（下拉、多选、排序等）*/
        private List<Option> options;

        /** 组合输入（矩阵多选、矩阵单选、矩阵量表等） */
        private List<Group> groups;

        /** 填空题 */
        private List<Blank> blanks;

        /** 联动题-题目ID */
        @JsonProperty("id_list")
        private List<String> idList;

        /** 联动题-ID对应内容 */
        @JsonProperty("text_list")
        private List<String> textList;

        /** 附件路径（暂不提供） */
        @JsonProperty("file_name_src")
        private Long fileNameSrc;

        /** 附件临时地址（暂不提供） */
        @JsonProperty("file_name_dst")
        private Long fileNameDst;

        @Data
        @Accessors(chain = true)
        public static class Option {
            /** 回答ID */
            private String id;

            /** 是否选中（默认1） */
            private Integer checked;

            /** 回答内容 */
            private String text;
        }

        @Data
        @Accessors(chain = true)
        public static class Group {
            /** 回答ID */
            private String id;

            /** 矩阵量表题 */
            private String text;

            /** 组合详情 */
            private List<Option> options;
        }

        @Data
        @Accessors(chain = true)
        public static class Blank {
            /** 填空题ID */
            private String id;

            /** 填空题内容 */
            private String value;
        }
    }
}
