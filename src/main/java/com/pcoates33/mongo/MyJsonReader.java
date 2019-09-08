package com.pcoates33.mongo;

import org.bson.json.JsonReader;

public class MyJsonReader extends JsonReader {
    /**
     * Constructs a new instance with the given JSON string.
     *
     * @param json A string representation of a JSON.
     */
    public MyJsonReader(String json) {
        super(json);
    }
}
