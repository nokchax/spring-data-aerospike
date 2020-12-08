package com.nokchax.study.nosql.aerosipke.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Getter
@Setter
@AllArgsConstructor
public class TestDomain {
    @Id
    private long id;
    private String value;
}
