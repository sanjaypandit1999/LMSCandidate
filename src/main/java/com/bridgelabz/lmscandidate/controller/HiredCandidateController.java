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
import com.bridgelabz.lmscandidate.service.IHiredCandidateService;
import com.bridgelabz.lmscandidate.util.JwtToken;


@RestController
@RequestMapping("/hiredcandidate")
public class HiredCandidateController {
	
	 /**
     * purpose to get object from bean by using @Autowired
     *
     */
	@Autowired
	private IHiredCandidateService iCandidateService;
	@Autowired
	private JwtToken jwtToken;

	 /**
     * Take  candidate details from database database
     *
     * @param  token
     * @return response is all candidate data
     */
	@RequestMapping(value = { "", "/", "/get" })
	public ResponseEntity<ResponseDTO> getCandidateData(@RequestHeader String token) {
		List<HiredCandidate> candidateList = iCandidateService.getCandidate(token);
		ResponseDTO response = new ResponseDTO("Get call success", candidateList);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	 /**
     * Take user id to get candidate details from database database
     *
     * @param long userId and token
     * @return response is user data
     */
	@GetMapping("/get/{candidateId}")
	public ResponseEntity<ResponseDTO> getCandidateData(@RequestHeader String token, @PathVariable("candidateId") long candidateId ) {
		HiredCandidate hiredCandidate = iCandidateService.getCandidateById(token, candidateId);
		ResponseDTO response = new ResponseDTO("Get call success for id",  hiredCandidate);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}
	
	 /**
     * Take candidate details to save in database
     *
     * @param hiredCandidateDTO and token
     * @return UserResponse as JWTToken
     */
	@PostMapping("/saved")
	public ResponseEntity<ResponseDTO> saveCandidateData(@RequestHeader String token, @RequestBody HiredCandidateDTO hiredCandidateDTO) {
		HiredCandidate  hiredCandidate = iCandidateService.saveCandidate(token,hiredCandidateDTO);
		ResponseDTO response = new ResponseDTO("HireCandidate Data saved Successfully",
				jwtToken.createToken( hiredCandidate.getId()));
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	 /**
     * Take user details to update in database
     *
     * @param long candidateId, hiredCandidateDTO body and token
     * @return UserResponse as JWTToken
     */
	@PutMapping("/update/{id}")
	public ResponseEntity<ResponseDTO> updateHiredCandidateData(@RequestHeader String token,@PathVariable long id,
			@Valid @RequestBody HiredCandidateDTO hiredCandidateDTO) {
		HiredCandidate  hiredCandidate = iCandidateService.updateUser(token,id, hiredCandidateDTO);
		ResponseDTO response = new ResponseDTO("Updated Contact Data ", hiredCandidate);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	 /**
     * Take candidateId to delete candidate in database
     *
     * @param long userId and token
     * @return candidate profile
     */
	@DeleteMapping("/delete/{candidateId}")
	public ResponseEntity<ResponseDTO> deleteCandidateData(@RequestHeader String token, @PathVariable("candidateId") long candidateId) {
		iCandidateService.deleteUser(token,candidateId);
		ResponseDTO response = new ResponseDTO("Delete call success for id ", "deleted id:" + candidateId);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);

	}

	 /**
     * Delete all user details in database
     *
     * @return Delete all user details.
     */
	@DeleteMapping("/deleteall")
	public ResponseEntity<ResponseDTO> deleteAllHiredCandidateData(@RequestHeader String token) {
		String message = iCandidateService.deleteAllCandidateData(token);
		ResponseDTO respDTO = new ResponseDTO("Deleteall:", message);
		return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
	}

	 /**
     * Take user id to get candidate profile from database
     *
     * @param long userId and token
     * @return profile data
     */
	@GetMapping("/profile/{id}")
	public ResponseEntity<ResponseDTO> getCandidateProfile(@RequestHeader String token,@PathVariable long id) {
		HiredCandidate hiredCandidate = iCandidateService.candidateProfile(token,id);
		ResponseDTO response = new ResponseDTO(hiredCandidate.getFirstName() + " Profile ", hiredCandidate);
		return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
	}

	/**
     * Take xlsx file data drop in database
     *
     * @param file and token
     * @return store all data in database
     */
	@PostMapping("/takecandidatelist")
	public ResponseEntity<ResponseDTO> importHiredCandidate(@RequestHeader String token,@RequestParam("file") MultipartFile filePath) {
		String message = iCandidateService.saveCandidateDetails(token,filePath);
		ResponseDTO response = new ResponseDTO(" Updated ", message);
		return new ResponseEntity<ResponseDTO>(response,
				HttpStatus.OK);
	}
}
