package org.pdkary.dos.repositories;

import java.util.List;

import org.pdkary.dos.entities.PatientEntity;
import org.springframework.data.repository.CrudRepository;

public interface PatientRepository extends CrudRepository<PatientEntity,Long> {
	
	List<PatientEntity> findByLastName(String lastName);
	List<PatientEntity> findByFirstName(String firstName);
	List<PatientEntity> findByAge(int Age);
}
