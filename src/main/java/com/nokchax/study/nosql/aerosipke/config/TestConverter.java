package com.nokchax.study.nosql.aerosipke.config;

import com.nokchax.study.nosql.aerosipke.domain.TestDomain;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.ReadingConverter;
import org.springframework.data.convert.WritingConverter;

import java.util.Map;

public class TestConverter {
    @WritingConverter
    public enum UserDataToMapConverter implements Converter<TestDomain, Map<String, Object>> {
        INSTANCE;

        @Override
        public Map<String, Object> convert(TestDomain source) {
            Map<String, Object> map = Map.of(
                    "id", source.getId(),
                    "value", source.getValue()
            );
            return map;
        }
    }

    @ReadingConverter
    public enum MapToUserDataToConverter implements Converter<Map<String, Object>, TestDomain> {
        INSTANCE;

        @Override
        public TestDomain convert(Map<String, Object> source) {
            long id = (long) source.getOrDefault("id", 0);
            String value = (String) source.getOrDefault("value", null);
            return new TestDomain(id, value);
        }
    }
}
