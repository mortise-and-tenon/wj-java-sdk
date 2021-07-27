package fun.mortnon.wj.model.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dongfangzan
 * @date 29.4.21 2:39 下午
 */
@Slf4j
public class JacksonUtil {

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
    ;

    /**
     * 对象转换成json
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String objectToJson(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to Json error", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 对象转换成格式化的json
     *
     * @param obj
     * @param <T>
     * @return
     */
    public static <T> String objectToJsonPretty(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
        } catch (Exception e) {
            log.warn("Parse Object to Json error", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换成对象Class
     *
     * @param src
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String src, Class<T> clazz) {
        if (StringUtils.isBlank(src) || clazz == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(src, clazz);
        } catch (Exception e) {
            log.warn("Parse Json to Object error", e);
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换成对象TypeReference
     *
     * @param src
     * @param typeReference
     * @param <T>
     * @return
     */
    public static <T> T jsonToObject(String src, TypeReference<T> typeReference) {
        if (StringUtils.isBlank(src) || typeReference == null) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(src, typeReference);
        } catch (Exception e) {
            log.warn("Parse Json to Object error", e);
            e.printStackTrace();
            return null;
        }
    }

    public static Object jsonToObject(String src) {
        if (StringUtils.isBlank(src)) {
            return null;
        }
        try {
            return OBJECT_MAPPER.readValue(src, Object.class);
        } catch (Exception e) {
            log.warn("Parse Json to Object error", e);
            e.printStackTrace();
            return null;
        }
    }
}
