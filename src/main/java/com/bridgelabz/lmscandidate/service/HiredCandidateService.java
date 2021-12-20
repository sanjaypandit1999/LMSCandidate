package com.bridgelabz.lmscandidate.service;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bridgelabz.lmscandidate.dto.HiredCandidateDTO;
import com.bridgelabz.lmscandidate.exception.HireCandidateException;
import com.bridgelabz.lmscandidate.model.HiredCandidate;
import com.bridgelabz.lmscandidate.repository.HiredCandidateRepository;
import com.bridgelabz.lmscandidate.util.JwtToken;

/**
 * purpose to write business logic implements by IUserService
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Service
public class HiredCandidateService implements IHiredCandidateService {
	

	/**
	 * Inject object by using @Autowired annotation from bean
	 * 
	 */
	@Autowired
	private HiredCandidateRepository hiredCandidateRepository;
	@Autowired
	JwtToken jwtToken;
	
	@Autowired
	ModelMapper modelMapper;
	@Autowired 
	private SequenceGeneratorService sequenceGeneratorService;


	/**
	 * purpose to retrieve all data from database
	 * 
	 * @return all candidate data from DB
	 */
	@Override
	public List<HiredCandidate> getCandidate(String token) {
		return hiredCandidateRepository.findAll();
	}

	/**
	 * purpose to find individual user
	 * 
	 * @param long candidateId and token
	 * @return candidate data
	 */
	@Override
	public HiredCandidate getCandidateById(String token, long candidateId) {
		return hiredCandidateRepository.findById(candidateId)
				.orElseThrow(() -> new HireCandidateException("User with id " + candidateId + " does not exist..!"));
	}

	/**
	 * purpose to save candidate data in DB
	 * 
	 * @param hiredCandidateDTO body and token
	 * @return save candidate data
	 */
	@SuppressWarnings("static-access")
	@Override
	public HiredCandidate saveCandidate(String token, HiredCandidateDTO hiredCandidateDTO) {
		HiredCandidate hiredCandidate = new HiredCandidate();
		hiredCandidate.setId(sequenceGeneratorService.generateSequence(hiredCandidate.SEQUENCE_NAME));
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
		hiredCandidate.setJoinDate(hiredCandidateDTO.getJoinDate());
		hiredCandidate.setLocation(hiredCandidateDTO.getLocation());	
		return hiredCandidateRepository.save(hiredCandidate);
	}

	/**
	 * purpose to update  individual user
	 * 
	 * @param candidateId and body userDTO
	 * @return updated user data
	 */
	@Override
	public HiredCandidate updateUser(String token,long id, @Valid HiredCandidateDTO candidateDTO) {
		Optional<HiredCandidate> isUserPresent = hiredCandidateRepository.findById(id);
		if (isUserPresent.isPresent()) 
		{
			isUserPresent.get().setFirstName(candidateDTO.getFirstName());
			isUserPresent.get().setMiddleName(candidateDTO.getMiddleName());
			isUserPresent.get().setLastName(candidateDTO.getLastName());
			isUserPresent.get().setEmail(candidateDTO.getEmail());
			isUserPresent.get().setMobileNumber(candidateDTO.getMobileNumber());
			isUserPresent.get().setHiredCity(candidateDTO.getHiredCity());
			isUserPresent.get().setHiredDate(candidateDTO.getHiredDate());
			isUserPresent.get().setDegree(candidateDTO.getDegree());
			isUserPresent.get().setHiredLab(candidateDTO.getHiredLab());
			isUserPresent.get().setAttitudeRemark(candidateDTO.getAttitudeRemark());
			isUserPresent.get().setKnowledgeRemark(candidateDTO.getKnowledgeRemark());
			isUserPresent.get().setOnboardingStatus(candidateDTO.getOnboardingStatus());
			isUserPresent.get().setJoinDate(candidateDTO.getJoinDate());
			isUserPresent.get().setLocation(candidateDTO.getLocation());
		    return	hiredCandidateRepository.save(isUserPresent.get()); 
		}
		else
			throw	new HireCandidateException("User with id " + id + " does not exist..!");
		
	}

	/**
	 * purpose to delete individual user
	 * 
	 * @param candidateId and token
	 * @return delete user data
	 */
	@Override
	public void deleteUser(String token, long candidateId) {
		Optional<HiredCandidate> hiredCandidate = hiredCandidateRepository.findById(candidateId);
		if(hiredCandidate.isPresent()) {
		hiredCandidateRepository.deleteById(candidateId);
		}else
			throw	new HireCandidateException("User with id " + candidateId + " does not exist..!");

	}

	/**
	 * purpose to delete all user data in database
	 * 
	 * @param token
	 * @return Successfully deleted all the User
	 */
	@Override
	public String deleteAllCandidateData(String token) {
		hiredCandidateRepository.deleteAll();
		return "Delete all data";
	}

	 /**
     * purpose to view candidate profile
     *
     * @param long userId and token
     * @return show candidate profile 
     */
	@Override
	public HiredCandidate candidateProfile(String token, long id) {
		Optional<HiredCandidate> isPresent = hiredCandidateRepository.findById(id);
		if (isPresent.isPresent()) {
			return isPresent.get();
		}
		throw new HireCandidateException("User with id " + id + " does not exist..!");
	}

	/**
     * Take xlsx file data drop in database
     *
     * @param file and token
     * @return Successfully data drop in database
     */
	@SuppressWarnings("resource")
	@Override
	public String saveCandidateDetails(String token,MultipartFile filePath) {
		HiredCandidateDTO hiredCandidateDTO = new HiredCandidateDTO();
		ModelMapper modelMapper = new ModelMapper();
		boolean flag = true;
		try (InputStream fis = filePath.getInputStream()) {
			// Create Workbook instance holding reference to .xlsx file
			XSSFWorkbook workbook = new XSSFWorkbook(fis);
			// Get first/desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheet("data");
			// Iterate through each rows one by one
			Iterator<Row> rows = sheet.iterator();
			XSSFCell cell;
			while (rows.hasNext()) {
				Row row = rows.next();
				Iterator<Cell> cells = row.iterator();
				if (!flag) {
					while (cells.hasNext()) {
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setFirstName(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setMiddleName(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setLastName(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setEmail(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setMobileNumber((long) cell.getNumericCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setHiredCity(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setHiredDate(cell.getDateCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setDegree(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setHiredLab(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setAttitudeRemark(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setCommunicationRemark(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setKnowledgeRemark(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setAggregateRemark(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setOnboardingStatus(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setStatus(cell.getStringCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setJoinDate(cell.getDateCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setLocation(cell.getStringCellValue());

						HiredCandidate hiredCandidate = modelMapper.map(hiredCandidateDTO, HiredCandidate.class);
						hiredCandidateRepository.save(hiredCandidate);
					}
				}
				flag = false;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "Successfully data drop in database";
	}

}
