package csvreader;

import filehandler.FileLineReader;
import mapper.LineMapper;
import mapper.data.MappingList;
import objectbuilder.Builder;
import utils.Result;

import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Reader<T> {


    private final FileLineReader fileReader;
    private final LineMapper mapper;
    private final Builder<T> builder;
    private final Consumer<Result<List<T>, String>> consumer;

    public Reader(FileLineReader fileReader, LineMapper mapper, Builder<T> builder, Consumer<Result<List<T>, String>> consumer) {
        this.fileReader = fileReader;
        this.mapper = mapper;
        this.builder = builder;
        this.consumer = consumer;
    }

    public void read(String fileName) {
        final Result<List<MappingList>, String> mappings = Result.traverse(fileReader.readFile(fileName)
                .map(this::createMappings)).mapError(this::concatErrors);

        final Result<List<T>, String> res = Result.traverse(mappings.map(this::buildObjects)).mapError(this::concatErrors);

        consumer.accept(res);
    }

    private List<Result<T, String>> buildObjects(List<MappingList> x) {
        return x.stream()
                .map(builder::build)
                .collect(Collectors.toList());
    }

    private String concatErrors(List<String> xs) {
        return String.join("<>", xs);
    }

    private List<Result<MappingList, String>> createMappings(List<String> lines) {
        return mapper.parseLines(lines);
    }
}
