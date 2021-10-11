package mapper;

import mapper.data.MappingList;
import utils.Result;

import java.util.List;
import java.util.stream.Collectors;

public class LineConfigMapper extends LineMapper {
    public LineConfigMapper(String separator, List<String> elements) {
        super(separator);
        this.elements = elements;
    }

    @Override
    public List<Result<MappingList, String>> parseLines(List<String> line) {
        return line.stream().map(this::parseLine).collect(Collectors.toList());
    }
}
