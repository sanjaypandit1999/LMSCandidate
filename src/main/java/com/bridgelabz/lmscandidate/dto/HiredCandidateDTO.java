package com.bridgelabz.lmscandidate.dto;

import java.util.Date;

import lombok.Data;

@Data
public class HiredCandidateDTO {
	
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
    private String creatorUser;
    private String joinDate;
    private String location;
    private double aggPer;
    private long currentPinCode;
    private long permanentPincode;
}
