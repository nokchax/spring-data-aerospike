package com.nokchax.study.nosql.aerosipke.config;

import com.aerospike.client.Bin;
import com.aerospike.client.Key;
import com.nokchax.study.nosql.aerosipke.domain.TestDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.aerospike.convert.AerospikeReadData;
import org.springframework.data.aerospike.convert.AerospikeWriteData;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TestConverter {

    @WritingConverter
    @RequiredArgsConstructor
    public static class UserDataToMapConverter implements Converter<TestDomain, AerospikeWriteData> {
        private final String namespace = "test";
        private final String setName = "test";

        @Override
        public AerospikeWriteData convert(TestDomain source) {
            Key key = new Key(namespace, setName, source.getId());
            int expire = 2;

            Map<String, Object> map = Map.of(
                    "id", source.getId(),
                    "value", source.getValue()
            );

            Integer version = null; // not versionable document
            Collection<Bin> bins = List.of(
                    new Bin("id", source.getId()),
                    new Bin("value", source.getValue())
            );

            return new AerospikeWriteData(key, bins, expire, version);
        }
    }

    @ReadingConverter
    public enum MapToUserDataToConverter implements Converter<AerospikeReadData, TestDomain> {
        INSTANCE;

        @Override
        public TestDomain convert(AerospikeReadData source) {
            long id = (long) source.getValue("id");
            String value = (String) source.getValue("value");

            return new TestDomain(id, value);
        }
    }
}
