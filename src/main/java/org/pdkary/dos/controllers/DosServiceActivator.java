package org.pdkary.dos.controllers;

import org.pdkary.dos.dto.PatientInputDto;
import org.pdkary.dos.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class DosServiceActivator {
	
	@Autowired
	private PatientRepository patientRepository;
	
	public DosServiceActivator() {}
	
	public boolean callPersist(PatientInputDto dto) {
		return true;
	}
	
}
