package com.pcoates33.mongo;

import org.bson.BsonDecimal128;
import org.bson.Document;
import org.bson.types.Decimal128;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
public class MongoRepo {

    @Autowired
    private MongoTemplate mongoTemplate;

    public static String MONGO_COLLECTION = "test-db";


    public void insert(String json) {
        Document document = Document.parse(json);
        mongoTemplate.save(document, MONGO_COLLECTION);
    }

    public Document fetch(String keyValue) {
        Query query = new Query();
        query.addCriteria(where("key").is(keyValue));

        return mongoTemplate.findOne(query, Document.class, MONGO_COLLECTION);

    }
}
