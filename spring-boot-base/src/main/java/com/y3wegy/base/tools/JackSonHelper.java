package com.y3wegy.base.tools;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.y3wegy.base.DateConstants;
import com.y3wegy.base.ServiceExeption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author y3wegy
 */
public class JackSonHelper {
    private static final Logger logger = LoggerFactory.getLogger(JackSonHelper.class);

    private static ObjectMapper objectMapper;
    private static JsonFactory jsonFactory;

    private JackSonHelper() {
    }

    /**
     * -------------------------------------------------------------
     *
     * @return
     * @author @date             @comment
     * y3wegy   02-Jan-19-002       init version
     * -------------------------------------------------------------
     */
    public static ObjectMapper getObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        LocalDateDeserializer localDateDeserializer = new LocalDateDeserializer(DateConstants.dateFormatter);
        LocalDateSerializer localDateSerializer = new LocalDateSerializer(DateConstants.dateFormatter);

        LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(DateConstants.dateTimeFormatter);
        LocalDateTimeDeserializer localDateTimeDeserializer = new LocalDateTimeDeserializer(DateConstants.dateTimeFormatter);

        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDate.class, localDateSerializer).addDeserializer(LocalDate.class, localDateDeserializer);
        javaTimeModule.addSerializer(LocalDateTime.class, localDateTimeSerializer).addDeserializer(LocalDateTime.class, localDateTimeDeserializer);
        objectMapper.registerModule(javaTimeModule);
        return objectMapper;
    }

    public static String obj2JsonStr(Object obj) throws ServiceExeption {
        StringWriter stringWriter = new StringWriter();
        JsonGenerator jsonGenerator = null;
        try {
            jsonGenerator = getObjectMapper().getFactory().createGenerator(stringWriter);
            jsonGenerator.writeObject(obj);
            return stringWriter.toString();
        } catch (IOException e) {
            logger.error("parse failed", e);
            throw new ServiceExeption("parse failed", e);
        } finally {
            if (jsonGenerator != null) {
                try {
                    jsonGenerator.close();
                } catch (IOException e) {
                    logger.error("close jsonGenerator failed", e);
                    throw new ServiceExeption("close jsonGenerator failed", e);
                }
            }
        }
    }

    public static <T> T jsonStr2Obj(String jsonStr, TypeReference<T> type) throws ServiceExeption {
        T readValue = null;
        try {
            readValue = getObjectMapper().readValue(jsonStr, type);
        } catch (IOException e) {
            logger.error("parse failed", e);
            throw new ServiceExeption("parse failed", e);
        }
        return readValue;
    }
}
