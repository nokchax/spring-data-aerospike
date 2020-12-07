package com.nokchax.study.nosql.aerosipke.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestTestDomain {
    @Autowired
    private TestRepository testRepository;

    @Test
    @DisplayName("Aerospike 가 잘 초기화 되는지")
    public void diTest() {
        assertThat(testRepository).isNotNull();
        System.out.println(testRepository);
    }

    @Test
    public void getTest() {
        Optional<TestDomain> testDomain = testRepository.findById(1L);

        assertThat(testDomain.isEmpty()).isTrue();
    }

    @Test
    public void putTest() {
        TestDomain newTestDomain = new TestDomain(1L, "#1");
        testRepository.save(newTestDomain);
        Optional<TestDomain> testDomainFromAS = testRepository.findById(1L);

        assertThat(testDomainFromAS.isPresent()).isTrue();
        assertThat(testDomainFromAS.get().getValue()).isEqualTo("#1");

        testRepository.deleteAll();
    }

}