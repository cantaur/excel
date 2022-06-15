package evening.bread.excel.common.utils;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MapperUtils {

    private static ModelMapper modelMapper;

    /**
     * Model mapper property setting are specified in the following block.
     * Default property matching strategy is set to Strict see {@link MatchingStrategies}
     * Custom mappings are added using {@link ModelMapper#addMappings(PropertyMap)}
     */
    static {
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    private MapperUtils() {
        throw new AssertionError();
    }

    public static <T> T convert(Class<T> clazz, Object object) {
        return modelMapper.map(object, clazz);
    }

    public static <T> T convert(Type clazz, Object object) {
        return modelMapper.map(object, clazz);
    }

    public static <T> T convert(Type clazz, Object... objects){
        return convertArray(clazz, objects);
    }

    public static <T> T convert(Class<T> clazz, Object... objects){
        return convertArray(clazz, objects);
    }

    private static <T> T convertArray(Type clazz, Object... objects){
        Map<String,Object> resultMap = new HashMap<>();

        Arrays.stream(objects).forEach(object-> {
            Map<String,Object> map = JacksonUtils.objectToMap(object);
            map.forEach((key,value)->{
                if(Objects.nonNull(value)) resultMap.put(key,value);
            });
        });

        return modelMapper.map(resultMap, clazz);
    }


}