package ch.vobos.tchalk.core.tests;

import java.util.function.Consumer;

import ch.vobos.tchalk.core.domain.Yo;
import ch.vobos.tchalk.core.services.TchalkClientServices;

public class TchalkClientServicesTestImpl implements TchalkClientServices {

	private final TestEventBus<Yo> testEventBus;

	public TchalkClientServicesTestImpl(TestEventBus<Yo> testEventBus) {
		this.testEventBus = testEventBus;
	}

	@Override
	public void registerReceiver(Consumer<Yo> callback) {
		testEventBus.addObserver(observer -> { callback.accept(observer); });
	}

}
