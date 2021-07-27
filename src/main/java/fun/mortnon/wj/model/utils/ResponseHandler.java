package fun.mortnon.wj.model.utils;

/**
 * @author dongfangzan
 * @date 27.7.21 11:26 上午
 */
public interface ResponseHandler<T> {

    /**
     * 处理返回结果
     *
     * @param resp 腾讯问卷的返回值
     * @return     处理返回结果
     */
    T handle(String resp);
}
