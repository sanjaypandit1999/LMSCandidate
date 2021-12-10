package com.bridgelabz.lmscandidate.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.lmscandidate.dto.HiredCandidateDTO;
import com.bridgelabz.lmscandidate.dto.ResponseDTO;
import com.bridgelabz.lmscandidate.model.HiredCandidate;

@Service
public interface IHiredCandidateService {

	List<HiredCandidate> getCandidate();

	HiredCandidate getCandidateById(long candidateId);

	HiredCandidate saveCandidate(String token, HiredCandidateDTO hiredCandidateDTO);

	HiredCandidate updateUser(String token, @Valid HiredCandidateDTO hiredCandidateDTO);

	void deleteUser(long candidateId);

	String deleteAllCandidateData();

	HiredCandidate candidateProfile(String token);

	String saveCandidateDetails(MultipartFile filePath);

}
