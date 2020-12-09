package com.nokchax.study.nosql.aerosipke.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest // NoClassDefFoundError: org/springframework/jdbc/core/ConnectionCallback
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
    @DisplayName("데이터를 가지고 오는지")
    public void getTest() {
        Optional<TestDomain> testDomain = testRepository.findById(1L);

        assertThat(testDomain.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("데이터를 잘 저장하고 가져오는지")
    public void putTest() {
        TestDomain newTestDomain = new TestDomain(1L, "#1");
        testRepository.save(newTestDomain);
        Optional<TestDomain> testDomainFromAS = testRepository.findById(1L);

        assertThat(testDomainFromAS.isPresent()).isTrue();
        assertThat(testDomainFromAS.get().getValue()).isEqualTo("#1");

        testRepository.deleteAll();
    }

    @Test
    @DisplayName("데이터를 잘 업데이트 하는지")
    public void updateTest() {
        TestDomain newTestDomain = new TestDomain(1L, "#1");
        testRepository.save(newTestDomain);
        TestDomain testDomainFromAS = testRepository.findById(1L).get();

        assertThat(testDomainFromAS).isNotNull();
        assertThat(testDomainFromAS.getValue()).isEqualTo("#1");

        testDomainFromAS.setValue("@1");
        testRepository.save(testDomainFromAS);

        TestDomain updatedDomain = testRepository.findById(1L).get();
        assertThat(updatedDomain.getValue()).isEqualTo("@1");
    }

    @Test
    @DisplayName("Expire 가 잘 적용되는지")
    public void testExpiration() {
        // set expire
        // get
        // thread sleep
        // get
    }
}