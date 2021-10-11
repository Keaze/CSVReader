package data;

import resolver.ClassResolver;

public class NameResolver implements ClassResolver {
    @Override
    public Class<?> getClass(String name) {
        switch (name) {
            case "Person":
                return Person.class;
            case "Address":
                return Address.class;
            default:
                return null;
        }
    }
}
