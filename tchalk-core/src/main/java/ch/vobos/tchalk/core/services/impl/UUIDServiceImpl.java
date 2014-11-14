package ch.vobos.tchalk.core.services.impl;

import java.util.UUID;

import ch.vobos.tchalk.core.services.UUIDService;

public class UUIDServiceImpl implements UUIDService {

	@Override
	@SuppressWarnings("null")
	public String newUUID() {
		return UUID.randomUUID().toString();
	}

}
