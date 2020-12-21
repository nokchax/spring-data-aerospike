package com.nokchax.study.nosql.aerosipke.domain;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class TestTestDomain {
    private static final long ID = 1L;
    @Autowired
    private TestRepository testRepository;

    @AfterEach
    public void clear() {
        testRepository.deleteAll();
    }

    @Test
    @DisplayName("Aerospike 가 잘 초기화 되는지")
    public void diTest() {
        assertThat(testRepository).isNotNull();
    }

    @Test
    @DisplayName("데이터를 가지고 오는지")
    public void getTest() {
        assertThat(testRepository.findById(ID).isEmpty()).isTrue();
    }

    @Test
    @DisplayName("데이터를 잘 저장하고 가져오는지")
    public void putTest() {
        saveNewEntity();

        TestDomain testDomainFromAS = testRepository.findById(ID)
                .orElseThrow();

        assertThat(testDomainFromAS).isNotNull();
        assertThat(testDomainFromAS.getValue()).isEqualTo("#1");
    }

    @Test
    @DisplayName("데이터를 잘 업데이트 하는지")
    public void updateTest() {
        saveNewEntity();
        TestDomain testDomainFromAS = testRepository.findById(ID)
                .orElseThrow();

        assertThat(testDomainFromAS).isNotNull();
        assertThat(testDomainFromAS.getValue()).isEqualTo("#1");

        testDomainFromAS.setValue("@1");
        testRepository.save(testDomainFromAS);

        TestDomain updatedDomain = testRepository.findById(1L)
                .orElseThrow();
        assertThat(updatedDomain.getValue()).isEqualTo("@1");
    }

    @Test
    @DisplayName("Expire 가 잘 적용되는지")
    public void testExpiration() throws InterruptedException {
        assertThatThrownBy(() -> testRepository.findById(1L).orElseThrow())
                .isInstanceOf(NoSuchElementException.class);

        saveNewEntity();

        Thread.sleep(2000);
        assertThat(testRepository.findById(1L).isEmpty()).isTrue();
    }

    private void saveNewEntity() {
        TestDomain newTestDomain = new TestDomain(1L, "#1");
        testRepository.save(newTestDomain);
    }
}