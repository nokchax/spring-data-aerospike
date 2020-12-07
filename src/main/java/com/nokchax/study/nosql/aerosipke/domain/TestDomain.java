package com.nokchax.study.nosql.aerosipke.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Getter
@AllArgsConstructor
public class TestDomain {
    @Id
    private long id;
    private String value;
}
