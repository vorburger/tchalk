package ch.vobos.alchemist;

@FunctionalInterface
public interface Parser<T> extends Converter<String, T> {

    // @Override
    // T convert(String in);
    // without this you get "The target type of this expression must be a functional interface" compilation errors.
    // TODO Eclipse bug.. this should not be needed

}
