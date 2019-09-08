package com.pcoates33.mongo;

import org.bson.Document;
import org.bson.json.JsonWriterSettings;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Needs a mongo db running before the tests can run
 *
 * sudo docker run -d -p 27017:27017 -v data:/data/db  mongo
 */
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class MongoRepoTest {

    private static Logger log = LoggerFactory.getLogger(MongoRepoTest.class);

    private static JsonWriterSettings jsonWriterSettings = JsonWriterSettings
            .builder()
            .doubleConverter((value, writer) -> {
                writer.writeNumber(String.format("%.2f", value));
            })
            .build();

    @Autowired
    private MongoRepo mongoRepo;

    @Test
    public void simpleTest() {
        String keyValue = ""+System.currentTimeMillis();
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"key\":\"");
        json.append(keyValue);
        json.append("\"");
        json.append(",");
        json.append("\"num1\":1");
        json.append(",");
        json.append("\"num1dot00\":1.00");
        json.append(",");
        json.append("\"num1dot20\":1.20");
        json.append(",");
        json.append("\"num1dot23\":1.23");
        json.append("}");

        log.info("save {}", json.toString());
        mongoRepo.insert(json.toString());

        Document result = mongoRepo.fetch(keyValue);

        log.info("fetch : {}", result.toJson(jsonWriterSettings));
    }

    @Test
    public void extendedJsonTest() {
        String keyValue = ""+System.currentTimeMillis();
        StringBuilder json = new StringBuilder();
        json.append("{");
        json.append("\"key\":\"");
        json.append(keyValue);
        json.append("\"");
        json.append(",");
        json.append("\"num1\":1");
        json.append(",");
        json.append("\"num1dot00\":{\"$numberDecimal\":\"1.00\"}");
        json.append(",");
        json.append("\"num1dot20\":{\"$numberDecimal\":\"1.20\"}");
        json.append(",");
        json.append("\"num1dot23\":{\"$numberDecimal\":\"1.23\"}");
        json.append("}");

        log.info("save {}", json.toString());
        mongoRepo.insert(json.toString());

        Document result = mongoRepo.fetch(keyValue);

        log.info("fetch : {}", result.toJson());
    }
}
