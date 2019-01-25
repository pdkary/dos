package org.pdkary.dos.entities;

import java.sql.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class LogEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idLogs;
	
	private Date date;
	
	private Long Patients_idPatients;
	
	private int int7;
	private int int7h;
	private int int8;
	private int int8h;
	private int int9;
	private int int9h;
	private int int10;
	private int int10h;
	private int int11;
	private int int11h;
	private int int12;
	private int int12h;
	private int int1;
	private int int1h;
	private int int2;
	private int int2h;
	private int int3;
	private int int3h;
	private int int4;
	private int int4h;
	private int int5;
	private int int5h;
	private int int6;
	private int int6h;

	
	
}
