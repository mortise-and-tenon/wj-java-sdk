package fun.mortnon.wj.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @author qiansl
 * @version 1.0
 * @date 2022/3/8 3:33 下午
 * @description 登录授权一次code
 */
@Data
public class LoginCode implements Serializable {

    /**
     * 授权登录code
     */
    private String code;
}
