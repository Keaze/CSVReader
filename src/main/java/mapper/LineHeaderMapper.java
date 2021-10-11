package mapper;

import mapper.data.MappingList;
import utils.Result;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class LineHeaderMapper extends LineMapper {
    public LineHeaderMapper(String separator) {
        super(separator);
    }

    @Override
    public List<Result<MappingList, String>> parseLines(List<String> line) {
        if (line.isEmpty()) {
            return Collections.singletonList(Result.failure("List is empty"));
        }
        setUpElements(line.get(0));
        final List<String> subList = line.subList(1, line.size());
        return subList.stream().map(this::parseLine).collect(Collectors.toList());
    }

    private void setUpElements(String line) {
        elements = Arrays.stream(line.split(separator)).collect(Collectors.toList());
    }


}
