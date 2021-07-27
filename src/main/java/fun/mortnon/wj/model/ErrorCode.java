package fun.mortnon.wj.model;

import fun.mortnon.wj.model.utils.StringUtils;

import java.util.Arrays;

/**
 * 错误码枚举
 *
 * @author dongfangzan
 * @date 27.7.21 10:28 上午
 */
public enum ErrorCode {

    /** 请求成功 */
    OK("OK", "请求成功"),

    /** 请求无权限 */
    PermissionDenied("PermissionDenied", "请求无权限"),

    /** 系统错误*/
    InternalServerError("InternalServerError", "系统错误"),

    /** 外部调用错误 */
    RemoteServerError("RemoteServerError", "外部调用错误"),

    /** 接口不存在 */
    NoRoute("NoRoute", "接口不存在"),

    /** 参数错误 */
    InvalidArgument("InvalidArgument", "参数错误"),

    /** 资源不存在 */
    NotFound("NotFound", "资源不存在"),

    /** 资源已存在 */
    AlreadyExists("AlreadyExists", "资源已存在"),

    /** 资源已耗尽 */
    ResourceExhausted("ResourceExhausted", "资源已耗尽")
    ;

    /** 错误码 */
    private final String code;

    /** 错误描述 */
    private final String desc;

    ErrorCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public String getDesc() {
        return desc;
    }

    /**
     * 根据错误码返回错误枚举
     *
     * @param code 错误码
     * @return     错误枚举，若果返回空，则表示未知错误码
     */
    public ErrorCode getByCode(String code) {
        if (StringUtils.isBlank(code)) {
            return null;
        }

        return Arrays.stream(ErrorCode.values())
                .filter(errorCode -> code.equals(errorCode.getCode()))
                .findAny().orElse(null);
    }
}
