package com.pcoates33.mongo;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class JacksonWriterTest {

    private static Logger log = LoggerFactory.getLogger(JacksonWriterTest.class);

    private String jsonForTest() {
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"num1\":1");
        json.append(",");
        json.append("\"num1dot00\":1.00");
        json.append(",");
        json.append("\"num1dot20\":1.20");
        json.append(",");
        json.append("\"num1dot23\":1.23");
        json.append("}");
        return json.toString();
    }

    private void readAndWriteJson(ObjectMapper mapper, String jsonIn) throws IOException {
        log.info("in : {}", jsonIn);
        JsonNode jsonNode = mapper.readTree(jsonIn);
        String jsonOut = mapper.writeValueAsString(jsonNode);
        log.info("out : {}", jsonOut);
    }

    @Test
    public void serializeUsingDefaultMapperTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        readAndWriteJson(mapper, jsonForTest());
    }

    @Test
    public void serializeUsingMapperWithBigDecimalTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN);
        readAndWriteJson(mapper, jsonForTest());
    }

    @Test
    public void serializeUsingMapperWithExactBigDecimalTest() throws IOException {
        ObjectMapper mapper = new ObjectMapper()
                .enable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN)
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));
        readAndWriteJson(mapper, jsonForTest());
    }


}
