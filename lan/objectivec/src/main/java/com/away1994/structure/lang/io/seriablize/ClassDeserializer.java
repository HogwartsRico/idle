package com.away1994.structure.lang.io.seriablize;

import com.away1994.structure.lang.symbols.impl.ClassImpl;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

public class ClassDeserializer extends JsonDeserializer<ClassImpl> {
    @Override
    public ClassImpl deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        String value = jsonParser.getText();

        ClassImpl clazz = new ClassImpl();
        clazz.setCachedIdentify(value);
        return clazz;
    }
}
