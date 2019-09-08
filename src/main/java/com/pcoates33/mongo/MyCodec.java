package com.pcoates33.mongo;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.Document;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.codecs.EncoderContext;

public class MyCodec extends DocumentCodec {

    @Override
    public void encode(BsonWriter writer, Document document, EncoderContext encoderContext) {
        super.encode(writer, document, encoderContext);
    }

    @Override
    public Document decode(BsonReader reader, DecoderContext decoderContext) {
        return super.decode(reader, decoderContext);
    }
}
