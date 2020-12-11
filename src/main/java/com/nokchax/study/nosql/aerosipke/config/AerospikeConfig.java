package com.nokchax.study.nosql.aerosipke.config;

import com.aerospike.client.Host;
import com.aerospike.client.policy.ClientPolicy;
import com.nokchax.study.nosql.aerosipke.domain.TestRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.aerospike.config.AbstractAerospikeDataConfiguration;
import org.springframework.data.aerospike.repository.config.EnableAerospikeRepositories;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Configuration
@EnableAerospikeRepositories(basePackageClasses = TestRepository.class)
public class AerospikeConfig extends AbstractAerospikeDataConfiguration {
    @Value("${aerospike.hosts}")
    private List<String> hosts;
    @Value("${aerospike.port}")
    private int port;
    @Value("${aerospike.namespace}")
    private String namespace;

    @Override
    protected Collection<Host> getHosts() {
        return hosts.stream()
                .map(host -> new Host(host, port))
                .collect(Collectors.toSet());
    }

    @Override
    protected String nameSpace() {
        return namespace;
    }

    @Override
    protected ClientPolicy getClientPolicy() {
        ClientPolicy clientPolicy = super.getClientPolicy();

        clientPolicy.writePolicyDefault.expiration = 2;
        log.info("Set ttl : " + clientPolicy.writePolicyDefault.expiration);

        return clientPolicy;
    }
}
