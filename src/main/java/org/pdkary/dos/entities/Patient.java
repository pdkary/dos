package org.pdkary.dos.entities;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString(exclude="logs")
public class Patient {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String strFirstName;
	private String strLastName;
	private int intAge;
	private String strInfo;
	
	@OneToMany(mappedBy="patient",targetEntity=Log.class, cascade = CascadeType.ALL)
	private Set<Log> logs;
}
