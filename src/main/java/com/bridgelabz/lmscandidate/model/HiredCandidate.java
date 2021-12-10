package com.bridgelabz.lmscandidate.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity(name = "hired_candidate")
@Table(name = "hired_candidate")
public class HiredCandidate {
		@Id
		@GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;
	
	    private String firstName;
	    private String middleName;
	    private String lastName;
	    private String email;
	    private long mobileNumber;
	    private String hiredCity;
	    private Date hiredDate;
	    private String degree;
	    private String hiredLab;
	    private String attitudeRemark;
	    private String communicationRemark;
	    private String knowledgeRemark;
	    private String aggregateRemark;
	    private String onboardingStatus;
	    private String status;
//	    private long creatorUser;
	    private Date joinDate;
	    private String location;
//	    private double aggPer;
//	    private long currentPinCode;
//	    private long permanentPincode;
}
