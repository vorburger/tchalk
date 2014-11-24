package ch.vobos.alchemist;

import org.eclipse.jdt.annotation.NonNull;

public interface ConverterRegistry {

	<@NonNull InT, @NonNull OutT> void register(Converter<InT, OutT> converter, Class<InT> inType, Class<OutT> outType);
	
}
