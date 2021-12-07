package com.bridgelabz.lmscandidate.service;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bridgelabz.lmscandidate.dto.HiredCandidateDTO;
import com.bridgelabz.lmscandidate.exception.HireCandidateException;
import com.bridgelabz.lmscandidate.model.HiredCandidate;
import com.bridgelabz.lmscandidate.repository.HiredCandidateRepository;
import com.bridgelabz.lmscandidate.util.JwtToken;

@Service
public class HiredCandidateService implements IHiredCandidateService {
	

	
	@Autowired
	private HiredCandidateRepository hiredCandidateRepository;
	@Autowired
	JwtToken jwtToken;


	@Override
	public List<HiredCandidate> getCandidate() {
		return hiredCandidateRepository.findAll();
	}

	@Override
	public HiredCandidate getCandidateById(long candidateId) {
		return hiredCandidateRepository.findById(candidateId)
				.orElseThrow(() -> new HireCandidateException("User with id " + candidateId + " does not exist..!"));
	}

	@Override
	public HiredCandidate saveCandidate(@Valid HiredCandidateDTO hiredCandidateDTO) {
		HiredCandidate hiredCandidate = new HiredCandidate();
		
		hiredCandidate.setFirstName(hiredCandidateDTO.getFirstName());
		hiredCandidate.setLastName(hiredCandidateDTO.getLastName());
		hiredCandidate.setMiddleName(hiredCandidateDTO.getMiddleName());
		hiredCandidate.setEmail(hiredCandidateDTO.getEmail());
		hiredCandidate.setMobileNumber(hiredCandidateDTO.getMobileNumber());
		hiredCandidate.setHiredCity(hiredCandidateDTO.getHiredCity());
		hiredCandidate.setHiredDate(hiredCandidateDTO.getHiredDate());
		hiredCandidate.setDegree(hiredCandidateDTO.getDegree());
		hiredCandidate.setHiredLab(hiredCandidateDTO.getHiredLab());
		hiredCandidate.setAttitudeRemark(hiredCandidateDTO.getAttitudeRemark());
		hiredCandidate.setCommunicationRemark(hiredCandidateDTO.getCommunicationRemark());
		hiredCandidate.setKnowledgeRemark(hiredCandidateDTO.getKnowledgeRemark());
		hiredCandidate.setOnboardingStatus(hiredCandidateDTO.getOnboardingStatus());
		hiredCandidate.setStatus(hiredCandidateDTO.getStatus());
		hiredCandidate.setCreatorUser(hiredCandidateDTO.getCreatorUser());
		hiredCandidate.setJoinDate(hiredCandidateDTO.getJoinDate());
		hiredCandidate.setLocation(hiredCandidateDTO.getLocation());
		hiredCandidate.setAggPer(hiredCandidateDTO.getAggPer());
		hiredCandidate.setCurrentPinCode(hiredCandidateDTO.getCurrentPinCode());
		hiredCandidate.setPermanentPincode(hiredCandidateDTO.getPermanentPincode());
		
		
		return hiredCandidateRepository.save(hiredCandidate);
	}

	@Override
	public HiredCandidate updateUser(long candidateId, @Valid HiredCandidateDTO hiredCandidateDTO) {
		Optional<HiredCandidate> isPresent = hiredCandidateRepository.findById(candidateId);
		if(isPresent.isPresent()) {
			this.saveCandidate(hiredCandidateDTO);
			return isPresent.get();		
		}
		else
			throw	new HireCandidateException("User with id " + candidateId + " does not exist..!");
		
	}

	@Override
	public void deleteUser(long candidateId) {
		HiredCandidate hiredCandidate = this.getCandidateById(candidateId);
		hiredCandidateRepository.delete(hiredCandidate);
		
	}

	@Override
	public String deleteAllCandidateData() {
		hiredCandidateRepository.deleteAll();
		return null;
	}

	@Override
	public HiredCandidate candidateProfile(String token) {
		Long id = jwtToken.decodeToken(token);
		Optional<HiredCandidate> isPresent = hiredCandidateRepository.findById(id);
		if(isPresent.isPresent()) {
			return isPresent.get();
		}
		throw new HireCandidateException("User with id " + id + " does not exist..!");
	}
	

}
