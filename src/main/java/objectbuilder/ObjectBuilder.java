package objectbuilder;

import mapper.data.MappingList;
import mapper.data.MappingPair;
import utils.Result;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectBuilder implements Builder<Object> {
    Class<?> target;

    public ObjectBuilder(Class<?> target) {
        this.target = target;
    }

    @Override
    public Result<Object, String> build(MappingList mappings) {
        try {
            final Object instance = target.getConstructor().newInstance();
            final Set<String> collect = Arrays.stream(target.getFields()).map(Field::getName).collect(Collectors.toSet());
            for (MappingPair mapping : mappings.getMappings()) {
                if (!collect.contains(mapping.name)) {
                    continue;
                }
                final Field field = target.getField(mapping.name);
                field.setAccessible(true);
                field.set(instance, mapping.value);
            }
            return Result.success(instance);

        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException e) {
            return Result.failure(e.getMessage());
        }
    }

}
