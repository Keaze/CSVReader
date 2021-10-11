package mapper.data;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class MappingList {
    private final List<MappingPair> mappings;

    public MappingList(List<MappingPair> mappings, MappingPair pair) {
        this.mappings = new ArrayList<>(mappings);
        this.mappings.add(pair);
    }

    private MappingList() {
        this.mappings = new ArrayList<>();
    }

    private MappingList(List<MappingPair> mappings) {
        this.mappings = mappings;
    }

    public static MappingList of(MappingPair pair) {
        return new MappingList(new ArrayList<>(), pair);
    }

    public static MappingList empty() {
        return new MappingList();
    }

    public MappingList add(MappingPair par) {
        return new MappingList(mappings, par);
    }

    public MappingList combine(MappingList o) {
        final ArrayList<MappingPair> pairs = new ArrayList<>(mappings);
        pairs.addAll(o.mappings);
        return new MappingList(pairs);
    }

    public List<MappingPair> getMappings() {
        return mappings;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", MappingList.class.getSimpleName() + "[", "]")
                .add("mappings=" + mappings)
                .toString();
    }
}
