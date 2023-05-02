package com.nikesh.cms.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Entity
public class Customer {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@NotNull(message = "Firstname is Required Field")
	@NotEmpty(message = "Firstname is Required Field")
	private String firstName;
	
	@NotNull(message = "Lastame is Required Field")
	@NotEmpty(message = "Lastame is Required Field")
	private String lastName;
	
	private String nickName;
	
	@NotNull(message="Sex is Required Field")
	private char sex;
	
	@NotNull(message = "Age is Required Field")
	private Number age;
	
	@NotNull(message = "Qualification is Required Field")
	@NotEmpty(message = "Qualification is Required Field")
	private String qualification;
	
	@Size(min=1,max=250,message="Permanent Address cannot empty and should be max 250 characters")
	private String permanentAddress;
	
	@Size(min=1,max=250,message="Communication Address cannot empty and should be max 250 characters")
	private String communicationAddress;
	
	@NotNull(message="State pin is Required Field")
	@NotEmpty(message = "State pin is Required Field")
	private String statePin;
	
	@Size(min=1,max=250,message="Notes cannot empty and should be max 250 characters")
	private String notes;
	
	public Customer() {
	}

	public Customer(Long id,String firstName, String lastName, String nickName, char sex, Number age, String qualification,
			String permanentAddress, String communicationAddress, String statePin, String notes) {
		super();
		this.id=id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.nickName = nickName;
		this.sex = sex;
		this.age = age;
		this.qualification = qualification;
		this.permanentAddress = permanentAddress;
		this.communicationAddress = communicationAddress;
		this.statePin = statePin;
		this.notes = notes;
	}
	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public char getSex() {
		return sex;
	}

	public Number getAge() {
		return age;
	}

	public String getQualification() {
		return qualification;
	}

	public String getPermanentAddress() {
		return permanentAddress;
	}

	public String getCommunicationAddress() {
		return communicationAddress;
	}

	public String getStatePin() {
		return statePin;
	}

	public String getNotes() {
		return notes;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public void setSex(char sex) {
		this.sex = sex;
	}

	public void setAge(Number age) {
		this.age = age;
	}

	public void setQualification(String qualification) {
		this.qualification = qualification;
	}

	public void setPermanentAddress(String permanentAddress) {
		this.permanentAddress = permanentAddress;
	}

	public void setCommunicationAddress(String communicationAddress) {
		this.communicationAddress = communicationAddress;
	}

	public void setStatePin(String statePin) {
		this.statePin = statePin;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	
	
	

	

}
