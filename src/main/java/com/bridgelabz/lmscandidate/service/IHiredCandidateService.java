package com.bridgelabz.lmscandidate.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.lmscandidate.dto.HiredCandidateDTO;
import com.bridgelabz.lmscandidate.model.HiredCandidate;

@Service
public interface IHiredCandidateService {

	List<HiredCandidate> getCandidate(String token);

	HiredCandidate getCandidateById(String token, long candidateId);

	HiredCandidate saveCandidate(String token, HiredCandidateDTO hiredCandidateDTO);

	HiredCandidate updateUser(String token,long id, @Valid HiredCandidateDTO hiredCandidateDTO);

	void deleteUser(String token, long candidateId);

	String deleteAllCandidateData(String token);

	HiredCandidate candidateProfile(String token, long id);

	String saveCandidateDetails(String token, MultipartFile filePath);

}
