package org.pdkary.dos.controllers;
import org.pdkary.dos.dto.PatientInputDto;
import org.pdkary.dos.entities.PatientEntity;
import org.pdkary.dos.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DosRestController {
	
	@Autowired
	private static PatientRepository patientRepository;
	
	@PostMapping(path= "/input",consumes = "application/json")
	public void enterPatient(@RequestBody PatientInputDto dto) {
		PatientEntity patient = new PatientEntity();
		patient.setFirstName(dto.firstName);
		patient.setLastName(dto.lastName);
		patient.setAge(dto.age);
		patient.setInfo(dto.info);
		
		patientRepository.save(patient);
	}
	
	
}
