package com.pcoates33.mongo;

import com.mongodb.MongoClientURI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.convert.CustomConversions;
import org.springframework.data.convert.CustomConversions.StoreConversions;
import org.springframework.data.mapping.model.SimpleTypeHolder;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;

import java.util.Arrays;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public MongoTemplate mongoTemplate() {
        MongoClientURI uri = new MongoClientURI("mongodb://localhost/test-db");
        MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(uri);
        MongoTemplate mongoTemplate = new MongoTemplate((mongoDbFactory));

//        // try adding custom convertor
//        // These seem to get called when you save the bson document.
//        MappingMongoConverter mongoMapping = (MappingMongoConverter) mongoTemplate.getConverter();
//        mongoMapping.setCustomConversions(customConversions()); // tell mongodb to use the custom converters
//        mongoMapping.afterPropertiesSet();

        return mongoTemplate;
    }

    public CustomConversions customConversions() {
        StoreConversions storeConversions = StoreConversions.of(SimpleTypeHolder.DEFAULT, new MyDoubleConverter2());
        return new CustomConversions(storeConversions, Arrays.asList(new MyDoubleConverter()));
    }
}
