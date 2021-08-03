package fun.mortnon.wj.model.utils;

import fun.mortnon.wj.exception.WjException;
import fun.mortnon.wj.model.Error;
import fun.mortnon.wj.model.ErrorCode;

import java.util.Collection;
import java.util.Objects;

/**
 * 问卷断言工具
 *
 * @author dongfangzan
 * @date 27.7.21 12:51 下午
 */
public class AssertUtils {

    /**
     * 断言表达式是否为空
     *
     * @param expression 表达式
     * @param errorCode  错误码
     * @param error      二级错误码
     * @param message    错误消息
     */
    public static void isTrue(boolean expression, ErrorCode errorCode, Error error, String message) {
        if (!expression) {
            throwException(errorCode, error, message);
        }
    }

    /**
     * 断言字符串非空
     *
     * @param src       字符串
     * @param errorCode 错误码
     * @param message   错误消息
     */
    public static void notBlank(String src, ErrorCode errorCode, String message) {
        isTrue(Objects.nonNull(src) && src.length() > 0, errorCode, message);
    }

    /**
     * 断言集合非空
     *
     * @param collection 集合
     * @param errorCode  错误码
     * @param message    错误消息
     */
    public static void notEmpty(Collection<?> collection, ErrorCode errorCode, String message) {
        isTrue(Objects.nonNull(collection) && collection.size() > 0, errorCode, message);
    }

    /**
     * 断言表达式是否为空
     *
     * @param expression 表达式
     * @param errorCode  错误码
     * @param message    错误消息
     */
    public static void isTrue(boolean expression, ErrorCode errorCode, String message) {
        if (!expression) {
            throwException(errorCode, null, message);
        }
    }

    /**
     * 断言对象不为空
     *
     * @param obj       对象
     * @param errorCode 错误码
     * @param message   消息
     */
    public static void nonNull(Object obj, ErrorCode errorCode, String message) {
        isTrue(Objects.nonNull(obj), errorCode, null, message);
    }

    /**
     * 断言两个对象是否相等
     *
     * @param obj1      对象1
     * @param obj2      对象2
     * @param errorCode 错误码
     * @param message   错误消息
     */
    public static void isEquals(Object obj1, Object obj2, ErrorCode errorCode, String message) {
        if (Objects.isNull(obj1) || Objects.isNull(obj2)) {
            isTrue(false, errorCode, null, message);
        }

        isTrue(obj1.equals(obj2), errorCode, null, message);
    }

    /**
     * 断言两个对象是否相等
     *
     * @param obj1      对象1
     * @param obj2      对象2
     * @param errorCode 错误码
     * @param message   错误消息
     */
    public static void isEquals(Object obj1, Object obj2, ErrorCode errorCode, Error error, String message) {
        if (Objects.isNull(obj1) || Objects.isNull(obj2)) {
            isTrue(false, errorCode, error, message);
        }

        isTrue(obj1.equals(obj2), errorCode, error, message);
    }


    /**
     * 抛出异常
     *
     * @param errorCode 错误码
     * @param error     二级错误码
     * @param message   错误消息
     */
    public static void throwException(ErrorCode errorCode, Error error, String message) {
        throw new WjException(errorCode, error, message);
    }
}
