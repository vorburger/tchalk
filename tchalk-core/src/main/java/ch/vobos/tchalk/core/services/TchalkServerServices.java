package ch.vobos.tchalk.core.services;

import ch.vobos.tchalk.core.domain.NewYo;
import ch.vobos.tchalk.core.domain.simpletypes.YoID;

public interface TchalkServerServices {

	YoID say(NewYo yo);
	
}
