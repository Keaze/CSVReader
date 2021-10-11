package mapper.data;

import java.util.StringJoiner;

public class MappingPair {
    public final String name;
    public final String value;

    public MappingPair(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public static MappingPair of(String name, String value) {
        return new MappingPair(name, value);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MappingPair.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .add("value='" + value + "'")
                .toString();
    }
}
