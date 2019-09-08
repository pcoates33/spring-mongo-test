package com.pcoates33.mongo;

import org.springframework.core.convert.converter.Converter;

public class MyDoubleConverter2 implements Converter<Double, String> {
    @Override
    public String convert(Double source) {
        return (source == null) ? null : source.toString();
    }
}
