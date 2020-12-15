package com.nokchax.study.nosql.aerosipke.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@Document(collection = "domain", expiration = 1)
@AllArgsConstructor
public class TestDomain {
    @Id
    private long id;
    private String value;
}
