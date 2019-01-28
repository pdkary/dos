package org.pdkary.dos.repositories;

import java.util.List;

import org.pdkary.dos.entities.Patient;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<Patient,Long> {
	
	List<Patient> findAll();
	List<Patient> findByStrLastName(String lastName);
	List<Patient> findByStrFirstName(String firstName);
	List<Patient> findByStrFirstNameAndStrLastName(String firstName, String lastName);
	List<Patient> findByStrFirstNameAndStrLastNameAndIntAge(String firstName, String lastName, int age);
	List<Patient> findByIntAge(int Age);
}
