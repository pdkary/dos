package org.pdkary.dos.repositories;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.pdkary.dos.entities.LogEntity;
import org.springframework.data.repository.CrudRepository;

public interface LogRepository extends CrudRepository<LogEntity, Long> {

	List<LogEntity> findByIdLogsAndDate(Long idLogs,Date date);
	Optional<LogEntity> findByIdLogs(Long idLogs);
}
