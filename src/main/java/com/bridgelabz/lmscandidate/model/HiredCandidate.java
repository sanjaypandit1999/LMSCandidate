package com.bridgelabz.lmscandidate.model;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

/**
 * purpose to all data store in mongoDB as a document
 * 
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Document(collection = "HiredCandidate")
@Data
public class HiredCandidate {
	  @Transient
	    public static final String SEQUENCE_NAME = "users_sequence";
	
		@Id
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
	    private Date joinDate;
	    private String location;

	    
	    
	    
	    
}
