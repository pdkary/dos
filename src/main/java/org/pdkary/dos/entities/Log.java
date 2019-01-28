package org.pdkary.dos.entities;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Entity
@Data
@ToString(exclude="patient")
@NoArgsConstructor
public class Log {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	public Date date;
	public int intHour;
	public int intValue;
	
	@ManyToOne
	@JoinColumn(name="patient_id")
	public @NonNull Patient patient;
}
