package com.bridgelabz.lmscandidate.dto;

import java.util.Date;

import lombok.Data;

/**
 * purpose to pass data with multiple attributes in one shot from client to server
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Data
public class HiredCandidateDTO {
	
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
