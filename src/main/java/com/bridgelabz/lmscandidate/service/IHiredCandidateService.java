package com.bridgelabz.lmscandidate.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.bridgelabz.lmscandidate.dto.HiredCandidateDTO;
import com.bridgelabz.lmscandidate.model.HiredCandidate;

@Service
public interface IHiredCandidateService {

	List<HiredCandidate> getCandidate();

	HiredCandidate getCandidateById(long candidateId);

	HiredCandidate saveCandidate(@Valid HiredCandidateDTO hiredCandidateDTO);

	HiredCandidate updateUser(long candidateId, @Valid HiredCandidateDTO hiredCandidateDTO);

	void deleteUser(long candidateId);

	String deleteAllCandidateData();

	HiredCandidate candidateProfile(String token);

}
