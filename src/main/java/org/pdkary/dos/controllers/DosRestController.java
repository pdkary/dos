package org.pdkary.dos.controllers;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.pdkary.dos.dto.CsvDto;
import org.pdkary.dos.dto.MultiLogDto;
import org.pdkary.dos.dto.PatientInputDto;
import org.pdkary.dos.dto.SingleLogDto;
import org.pdkary.dos.entities.Log;
import org.pdkary.dos.entities.Patient;
import org.pdkary.dos.repositories.LogRepository;
import org.pdkary.dos.repositories.PatientRepository;
import org.pdkary.dos.utils.DosStringUtils;
import org.pdkary.dos.utils.ExcelWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class DosRestController {

	@Autowired
	private PatientRepository patientRepository;

	@Autowired
	private LogRepository logRepository;

	@Autowired
	private ExcelWriter excelWriter;

	@GetMapping("/")
	public String home() {
		return "DOS Home page";
	}

	@PostMapping(path = "/input-patient", consumes = "application/json")
	public void enterPatient(@RequestBody PatientInputDto dto) {
		Patient patient = new Patient();
		patient.setStrFirstName(dto.firstName);
		patient.setStrLastName(dto.lastName);
		patient.setIntAge(dto.age);
		patient.setStrInfo(dto.info);
		patientRepository.save(patient);
	}

	@PostMapping(path = "/input-log", consumes = "application/json")
	public void enterLog(@RequestBody SingleLogDto dto) throws ParseException {
		Log log = new Log();
		log.setDate(DosStringUtils.toSqlDate(dto.date));
		log.setIntHour(DosStringUtils.toDosTime(dto.hour));
		log.setIntValue(dto.value);
		Optional<Patient> p = patientRepository.findById(dto.patientId);
		if (p.isPresent()) {
			log.setPatient(p.get());
			logRepository.save(log);
		}
	}

	@PostMapping(path = "/input-logs", consumes = "application/json")
	public void enterLogs(@RequestBody MultiLogDto dto) throws ParseException {
		for (int i = 0; i < dto.hours.length; i++) {
			Log log = new Log();
			log.setDate(DosStringUtils.toSqlDate(dto.date));
			log.setIntHour(DosStringUtils.toDosTime(dto.hours[i]));
			log.setIntValue(dto.values[i]);
			Optional<Patient> p = patientRepository.findById(dto.patientId);
			if (p.isPresent()) {
				log.setPatient(p.get());
				logRepository.save(log);
			}
		}

	}

	@GetMapping("/id")
	public String search(@RequestParam(required = false) String age, @RequestParam(required = false) String lastName,
			@RequestParam(required = false) String firstName) {
		List<Patient> patients = new ArrayList<Patient>();
		if (firstName != null && lastName != null && age != null) {
			patients = patientRepository.findByStrFirstNameAndStrLastNameAndIntAge(firstName, lastName,
					Integer.valueOf(age));
		} else if (firstName != null && lastName != null && age == null) {
			patients = patientRepository.findByStrFirstNameAndStrLastName(firstName, lastName);
		} else if (firstName != null && lastName == null && age == null) {
			patients = patientRepository.findByStrFirstName(firstName);
		} else if (firstName == null && lastName != null && age == null) {
			patients = patientRepository.findByStrLastName(lastName);
		} else if (firstName == null && lastName == null && age != null) {
			patients = patientRepository.findByIntAge(Integer.valueOf(age));
		}
		return patients.toString();
	}

	@GetMapping("/patient")
	public String search(@RequestParam(required = false) Long pId, @RequestParam(required = false) String lastName,
			@RequestParam(required = false) String firstName) {
		List<Patient> patients = new ArrayList<Patient>();
		if (pId != null) {
			Optional<Patient> p = patientRepository.findById(pId);
			if (p.isPresent()) {
				patients.add(p.get());
			}
		} else if (lastName != null && firstName != null) {
			patients = patientRepository.findByStrFirstNameAndStrLastName(firstName, lastName);
		} else if (lastName != null && firstName == null) {
			patients = patientRepository.findByStrLastName(lastName);
		} else if (lastName == null && firstName != null) {
			patients = patientRepository.findByStrFirstName(firstName);
		}
		return patients.toString();
	}

	@GetMapping("/logs")
	public String getLogs(@RequestParam Long pId) {
		String m = "";
		Optional<Patient> p = patientRepository.findById(pId);
		if (p.isPresent()) {
			List<Log> logs = logRepository.findByPatient(p.get());
			for (Log l : logs) {
				m += l.toString() + "\n";
			}
		}
		return m;
	}

	@GetMapping("/excel")
	public void getExcel(@RequestParam Long pId, @RequestParam String date, HttpServletResponse response)
			throws ParseException {
		response.setContentType("text/csv");
		Optional<Patient> patient = patientRepository.findById(pId);
		if (patient.isPresent()) {
			Date sqlDate = DosStringUtils.toSqlDate(date);
			CsvDto csvDto = excelWriter.getExcelDto(patient.get(), sqlDate);
			try (FileInputStream inputStream = new FileInputStream(csvDto.filePath)) {
				excelWriter.WriteSingleDay(patient.get(), sqlDate);
				IOUtils.copy(inputStream, response.getOutputStream());
				response.flushBuffer();
				response.setHeader("Content-disposition", "attachment; filename=" + csvDto.fileName);
			} catch (IOException e) {
				log.error("An Error occured while generating excel file", e);
			}
		}
	}
}