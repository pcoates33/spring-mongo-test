# spring-mongo-test
Couple of tests for decimal places in json strings

MongoRepoTest::simpleTest shows how to add a sepcific formatter to the Document.toJson() call by specifying JsonWriterSettings

JacksonWriterTest::serializeUsingMapperWithExactBigDecimalTest shows how to set up a Jackson ObjectMapper that will read and write decimals as is.

i.e.  {"fieldName": 1.00} will still be {"fieldName": 1.00} after it has been read into a JsonNode and then written by the object mapper.
