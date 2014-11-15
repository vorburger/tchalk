package ch.vobos.alchemist;

public interface ConverterRegistry {

	<InT, OutT> void register(Converter<InT, OutT> converter, Class<InT> inType, Class<OutT> outType);
	
}
