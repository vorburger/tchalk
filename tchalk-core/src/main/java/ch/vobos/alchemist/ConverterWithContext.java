package ch.vobos.alchemist;

import java.util.Optional;

@FunctionalInterface
@SuppressWarnings("null")
public interface ConverterWithContext<InT, OutT, ContextT> extends Converter<InT, OutT> {

    OutT convert(InT in, Optional<ContextT> context);

    @Override
    default OutT convert(InT in) {
	return convert(in, Optional.empty());
    }

}
