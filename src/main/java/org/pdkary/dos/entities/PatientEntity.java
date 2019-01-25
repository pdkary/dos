package org.pdkary.dos.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long idPatients;
	
	private String firstName;
	private String lastName;
	private int age;
	private String info;
	
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public Long getId() {
		return idPatients;
	}
	public void setId(Long idPatients) {
		this.idPatients = idPatients;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
