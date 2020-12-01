package com.nokchax.study.nosql.aerosipke.domain;

import org.springframework.data.aerospike.repository.AerospikeRepository;

public interface TestRepository extends AerospikeRepository<Test, Long> {
}
