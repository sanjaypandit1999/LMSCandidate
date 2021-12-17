package com.bridgelabz.lmscandidate.model;



import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;
@Document(collection = "database_sequences")
@Getter
@Setter
@Component
public class DataBaseSequence {

    @Id
    private String id;

    private long seq;

}
