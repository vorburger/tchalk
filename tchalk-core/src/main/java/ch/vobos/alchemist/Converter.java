package ch.vobos.alchemist;


@FunctionalInterface
public interface Converter<InT, OutT> /* TODO Eclipse bug extends Function<InT, OutT> */{

    OutT convert(InT in);

    // @Override
    // default OutT apply(@Nullable InT in) {
    // Objects.requireNonNull(in);
    // return convert(in);
    // }

}
