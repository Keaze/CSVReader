package filehandler;

import utils.Result;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileLineReader {
    public Result<List<String>, String> readFile(String fileName) {
        if (fileName == null) {
            return Result.failure("Filename is null");
        }

        Path path = Paths.get(fileName);
        try {
            return Result.success(Files.readAllLines(path));
        } catch (IOException e) {
            return Result.failure(e.getMessage());
        }

    }
}
