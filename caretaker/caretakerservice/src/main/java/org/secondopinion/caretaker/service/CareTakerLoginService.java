package org.secondopinion.caretaker.service;

import org.secondopinion.caretaker.dto.Caretaker;

public interface CareTakerLoginService {
	public Caretaker login(String userName, String password);
}
