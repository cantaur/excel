package evening.bread.excel.common.utils;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class JacksonUtils {

    public static final ObjectMapper objectMapper;

    /**
     * Model mapper property setting are specified in the following block.
     * Default property matching strategy is set to Strict see {@link MatchingStrategies}
     * Custom mappings are added using {@link ModelMapper#addMappings(PropertyMap)}
     */
    static {
        objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,Boolean.FALSE);
    }

    /**
     * Object를 Map으로 변경 필터 기능 추가
     * @param source
     * @param filters
     * @return
     */
    public static Map<String, Object> objectToMap(final Object source, final String[] filters){
        if(source !=  null){
            Map<String, Object> sourceMap = objectMapper.convertValue(source, Map.class);
            return sourceMap.entrySet().stream().filter(x-> Arrays.asList(filters).contains(x.getKey())).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));

        }
        return new HashMap<>();
    }

    public static Map<String, Object> objectToMap(final Object source){
        try {
            return objectMapper.convertValue(source, Map.class);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static String objectToJson(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Object jsonToGenericObject(String jsonString, Class genericClazz, Type... parameterClasses) {
        try {
            Class[] classes = Arrays.stream(parameterClasses)
                    .map(type -> (Class)type)
                    .toArray(Class[]::new);

            return objectMapper.readValue(jsonString, objectMapper.getTypeFactory()
                    .constructParametricType(genericClazz, classes));
        } catch (IOException e) {
            throw new NullPointerException();
        }
    }

    public static <T> T jsonToObject(String jsonString, Class<T> clazz) {
        try {
            return objectMapper.readValue(jsonString, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }
}