package fun.mortnon.wj.model.utils;

import java.util.Objects;

/**
 * @author dongfangzan
 * @date 27.7.21 10:35 上午
 */
public class StringUtils {

    /**
     * 判断字符串是否为空
     *
     * @param str 字符串
     * @return true-为空，false-不为空
     */
    public static boolean isBlank(String str) {
        return Objects.isNull(str) || "".equals(str);
    }

    /**
     * 判断字符串是否不为空
     *
     * @param str 字符串
     * @return true-不为空，false-为空
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
}
