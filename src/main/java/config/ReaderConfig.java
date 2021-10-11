package config;

import java.util.List;

public class ReaderConfig {


    public final String objectName;
    public final String separator;
    public final List<String> elements;

    public ReaderConfig(String objectName, String separator, List<String> elements) {
        this.objectName = objectName;
        this.separator = separator;
        this.elements = elements;
    }

}
