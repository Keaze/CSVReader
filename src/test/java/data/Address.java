package data;

import java.util.StringJoiner;

public class Address {
    public String location;
    public String street;
    public String city;

    @Override
    public String toString() {
        return new StringJoiner(", ", Address.class.getSimpleName() + "[", "]")
                .add("location='" + location + "'")
                .add("street='" + street + "'")
                .add("city='" + city + "'")
                .toString();
    }
}
