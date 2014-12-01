package ch.vobos.alchemist;

@FunctionalInterface
public interface Converter<InT, OutT> /* TODO Eclipse bug extends Function<InT, OutT> */{

    // TODO Change return type to Optional IFF a Converter MAY refuse / not be able to convert?
    // That way could support chains, tried one after the other.. (but bad perf!). Better a separate interface for that, perhaps..
    OutT convert(InT in);

    // @Override
    // default OutT apply(@Nullable InT in) {
    // Objects.requireNonNull(in);
    // return convert(in);
    // }

}
