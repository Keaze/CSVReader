package mapper;

import mapper.data.MappingList;
import mapper.data.MappingPair;
import utils.Result;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;

public abstract class LineMapper {
    protected final String separator;
    protected List<String> elements;

    protected LineMapper(String separator) {
        this.separator = separator;
    }

    public abstract List<Result<MappingList, String>> parseLines(List<String> line);

    protected Result<MappingList, String> parseLine(String line) {
        if (line == null) {
            return Result.failure("Line is empty");
        }

        final Result<ArrayDeque<String>, String> splitResult = splitLines(line);
        if (!splitResult.successful()) {
            return splitResult.map(x -> MappingList.empty());
        }
        final ArrayDeque<String> values = splitResult.getOrElse(new ArrayDeque<>());
        final ArrayDeque<String> names = new ArrayDeque<>(elements);

        if (values.size() != names.size()) {
            return Result.failure(String.format("Invalid Config or CSV: %s Elements and %s Values found", names.size(), values.size()));
        }
        return createMappings(values, names);
    }

    protected Result<MappingList, String> createMappings(ArrayDeque<String> values, ArrayDeque<String> names) {
        try {
            return Result.success(IntStream.range(0, values.size()).mapToObj(x -> MappingPair.of(names.pop(), values.pop())).reduce(MappingList.empty(), MappingList::add, MappingList::combine));
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }

    protected Result<ArrayDeque<String>, String> splitLines(String line) {
        try {
            final String[] split = line.split(separator);
            return Result.success(new ArrayDeque<>(Arrays.asList(split)));
        } catch (Exception e) {
            return Result.failure(e.getMessage());
        }
    }
}
