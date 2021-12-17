package com.bridgelabz.lmscandidate.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.lmscandidate.model.HiredCandidate;
@Repository
public interface HiredCandidateRepository extends MongoRepository<HiredCandidate, Long> {

}
