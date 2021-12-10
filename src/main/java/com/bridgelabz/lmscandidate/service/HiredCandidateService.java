package com.bridgelabz.lmscandidate.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
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

@Service
public class HiredCandidateService implements IHiredCandidateService {
	

	
	@Autowired
	private HiredCandidateRepository hiredCandidateRepository;
	@Autowired
	JwtToken jwtToken;
	
//	@Autowired
//	ModelMapper modelMapper;


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
	public HiredCandidate saveCandidate(String token, HiredCandidateDTO hiredCandidateDTO) {
		Long id = jwtToken.decodeToken(token);
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
//		hiredCandidate.setCreatorUser(id);
		hiredCandidate.setJoinDate(hiredCandidateDTO.getJoinDate());
		hiredCandidate.setLocation(hiredCandidateDTO.getLocation());
//		hiredCandidate.setAggPer(hiredCandidateDTO.getAggPer());
//		hiredCandidate.setCurrentPinCode(hiredCandidateDTO.getCurrentPinCode());
//		hiredCandidate.setPermanentPincode(hiredCandidateDTO.getPermanentPincode());
		
		
		return hiredCandidateRepository.save(hiredCandidate);
	}

	@Override
	public HiredCandidate updateUser(String token, @Valid HiredCandidateDTO hiredCandidateDTO) {
		Long id = jwtToken.decodeToken(token);
		Optional<HiredCandidate> isPresent = hiredCandidateRepository.findById(id);
		if(isPresent.isPresent()) {
			this.saveCandidate(token,hiredCandidateDTO);
			return isPresent.get();		
		}
		else
			throw	new HireCandidateException("User with id " + id + " does not exist..!");
		
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
		if (isPresent.isPresent()) {
			return isPresent.get();
		}
		throw new HireCandidateException("User with id " + id + " does not exist..!");
	}

	@SuppressWarnings("resource")
	@Override
	public String saveCandidateDetails(MultipartFile filePath) {
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
//						cell = (XSSFCell) cells.next();
//						hiredCandidateDTO.setCreatorUser((long)cell.getNumericCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setJoinDate(cell.getDateCellValue());
						cell = (XSSFCell) cells.next();
						hiredCandidateDTO.setLocation(cell.getStringCellValue());
//						cell = (XSSFCell) cells.next();
//						hiredCandidateDTO.setAggPer(cell.getNumericCellValue());
//						cell = (XSSFCell) cells.next();
//						hiredCandidateDTO.setCurrentPinCode((long) cell.getNumericCellValue());
//						cell = (XSSFCell) cells.next();
//						hiredCandidateDTO.setPermanentPincode((long) cell.getNumericCellValue());

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
