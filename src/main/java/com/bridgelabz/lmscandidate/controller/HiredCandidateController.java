package com.bridgelabz.lmscandidate.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.lmscandidate.dto.HiredCandidateDTO;
import com.bridgelabz.lmscandidate.dto.ResponseDTO;
import com.bridgelabz.lmscandidate.model.HiredCandidate;
import com.bridgelabz.lmscandidate.repository.HiredCandidateRepository;
import com.bridgelabz.lmscandidate.service.IHiredCandidateService;
import com.bridgelabz.lmscandidate.util.JwtToken;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/hiredcandidate")
@Slf4j
public class HiredCandidateController {
	
	@Autowired
	private IHiredCandidateService iCandidateService;
	@Autowired
	private JwtToken jwtToken;

	
	@RequestMapping(value = { "", "/", "/get" })
	public ResponseEntity<ResponseDTO> getCandidateData() {
		List<HiredCandidate> candidateList = iCandidateService.getCandidate();
		ResponseDTO response = new ResponseDTO("Get call success", candidateList);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	 /**
     * Take user id to get user details from database database
     *
     * @param long userId
     * @return response is user data
     */
	@GetMapping("/get/{candidateId}")
	public ResponseEntity<ResponseDTO> getCandidateData(@PathVariable("candidateId") long candidateId ) {
		HiredCandidate hiredCandidate = iCandidateService.getCandidateById(candidateId);
		ResponseDTO response = new ResponseDTO("Get call success for id",  hiredCandidate);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}
	
	 /**
     * Take user details to register in database
     *
     * @param userDTO
     * @return UserResponse as JWTToken
     */
	@PostMapping("/save")
	public ResponseEntity<ResponseDTO> saveCandidateData(@RequestParam String token, @RequestBody HiredCandidateDTO hiredCandidateDTO) {
		HiredCandidate  hiredCandidate = iCandidateService.saveCandidate(token,hiredCandidateDTO);
		log.debug("HiredCandidate DTO: " +hiredCandidateDTO.toString());
		ResponseDTO response = new ResponseDTO("HireCandidate Data saved Successfully",
				jwtToken.createToken( hiredCandidate.getId()));
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	 /**
     * Take user details to update in database
     *
     * @param long userId and UserDTO body 
     * @return UserResponse as JWTToken
     */
	@PutMapping("/update")
	public ResponseEntity<ResponseDTO> updateHiredCandidateData(@RequestHeader String token,
			@Valid @RequestBody HiredCandidateDTO hiredCandidateDTO) {
		HiredCandidate  hiredCandidate = iCandidateService.updateUser(token, hiredCandidateDTO);
		ResponseDTO response = new ResponseDTO("Updated Contact Data ", jwtToken.createToken( hiredCandidate.getId()));
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	 /**
     * Take user details to register in database
     *
     * @param long userId
     * @return id is deleted 
     */
	@DeleteMapping("/delete/{candidateId}")
	public ResponseEntity<ResponseDTO> deleteCandidateData(@PathVariable("candidateId") long candidateId) {
		iCandidateService.deleteUser(candidateId);
		ResponseDTO response = new ResponseDTO("Delete call success for id ", "deleted id:" + candidateId);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	 /**
     * Delete all user details in database
     *
     * @return Delete all user details.
     */
	@DeleteMapping("/deleteall")
	public ResponseEntity<ResponseDTO> deleteAllHiredCandidateData() {
		String message = iCandidateService.deleteAllCandidateData();
		ResponseDTO respDTO = new ResponseDTO("Deleteall:", message);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	@GetMapping("/profile")
	public ResponseEntity<ResponseDTO> getCandidateProfile(@RequestParam String token) {
		HiredCandidate hiredCandidate = iCandidateService.candidateProfile(token);
		ResponseDTO response = new ResponseDTO(hiredCandidate.getFirstName() + " Profile ", hiredCandidate);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	@PostMapping("/takecandidatelist")
	public ResponseEntity<ResponseDTO> importHiredCandidate(@RequestParam("file") MultipartFile filePath) {
		String message = iCandidateService.saveCandidateDetails(filePath);
		ResponseDTO response = new ResponseDTO(" Updated ", message);
		return new ResponseEntity<ResponseDTO>(response,
				HttpStatus.OK);
	}
}
