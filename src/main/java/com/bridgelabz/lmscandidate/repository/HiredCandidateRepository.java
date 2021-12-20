package com.bridgelabz.lmscandidate.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import com.bridgelabz.lmscandidate.model.HiredCandidate;

/**
 * purpose to do all CRUD operation using mongoRepository
 * 
 * @author Sanjay
 * @version 1.0
 * @since 12/17/2021
 */
@Repository
public interface HiredCandidateRepository extends MongoRepository<HiredCandidate, Long> {

}
