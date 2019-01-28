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
	public Long id;

	public String strFirstName;
	public String strLastName;
	public int intAge;
	public String strInfo;
	
	@OneToMany(mappedBy="patient",targetEntity=Log.class, cascade = CascadeType.ALL)
	public Set<Log> logs;
}
