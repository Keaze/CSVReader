package objectbuilder;

import mapper.data.MappingList;
import utils.Result;

public interface Builder<R> {
    Result<R, String> build(MappingList mappings);
}
