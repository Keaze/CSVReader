package main;

import config.ReaderConfig;
import csvreader.Readers;
import data.NameResolver;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        final List<String> elements = Arrays.asList("firstName", "lastName", "location", "street", "city");
        final String separator = ";";

        final ReaderConfig configPerson = new ReaderConfig("Person", separator, elements);
        final ReaderConfig configAddress = new ReaderConfig("Address", separator, elements);

        Readers.getObjectHeaderReader(configPerson, new NameResolver(), System.out::println).read("src\\test\\resources\\test.csv");
        Readers.getObjectHeaderReader(configAddress, new NameResolver(), System.out::println).read("src\\test\\resources\\test.csv");
    }
}
