package com.nokchax.study.nosql.aerosipke.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestTest {
    @Autowired
    private TestRepository testRepository;

    @Test
    public void diTest() {
        assertThat(testRepository).isNotNull();
    }

}