package org.pdkary.dos.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.pdkary.dos.entities.Log;
import org.pdkary.dos.entities.Patient;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<Log, Long> {

	List<Log> findByIdAndDate(Long id,Date date);
	Optional<Log> findById(Long id);
	List<Log> findByPatient(Patient patient);
	List<Log> findByPatientAndDate(Patient patient,Date date);
}
