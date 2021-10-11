package csvreader;

import config.ReaderConfig;
import filehandler.FileLineReader;
import mapper.LineConfigMapper;
import mapper.LineHeaderMapper;
import objectbuilder.ObjectBuilder;
import resolver.ClassResolver;
import utils.Result;

import java.util.List;
import java.util.function.Consumer;

public class Readers {
    private Readers() {
    }

    public static Reader<Object> getObjectConfigReader(ReaderConfig cfg, ClassResolver resolver, Consumer<Result<List<Object>, String>> consumer) {
        return new Reader<>(new FileLineReader(), new LineConfigMapper(cfg.separator, cfg.elements), new ObjectBuilder(resolver.getClass(cfg.objectName)),
                consumer);

    }

    public static Reader<Object> getObjectHeaderReader(ReaderConfig cfg, ClassResolver resolver, Consumer<Result<List<Object>, String>> consumer) {
        return new Reader<>(new FileLineReader(), new LineHeaderMapper(cfg.separator), new ObjectBuilder(resolver.getClass(cfg.objectName)),
                consumer);

    }
}
