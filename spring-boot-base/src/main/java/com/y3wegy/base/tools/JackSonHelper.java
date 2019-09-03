package com.y3wegy.base.tools;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.y3wegy.base.DateConstants;
import com.y3wegy.base.exception.ServiceException;

/**
 * @author y3wegy
 */
public class JackSonHelper {
    private static final Logger logger = LoggerFactory.getLogger(JackSonHelper.class);

    private static ObjectMapper objectMapper;
    private static JsonFactory jsonFactory;

    private JackSonHelper() {}

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

    /**
     * -------------------------------------------------------------
     *
     * @param obj
     * @param indentLength
     * @return
     * @throws ServiceException
     * @author @date        @comment
     * Chen, Rui   1/30/2019     init version
     * -------------------------------------------------------------
     * create json str with indent
     */
    public static String obj2JsonStr(Object obj, int indentLength) throws ServiceException {
        ObjectMapper objectMapper = getObjectMapper();
        DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter(String.format("%" + indentLength + "s", ""), DefaultIndenter.SYS_LF);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentObjectsWith(indenter);
        printer.indentArraysWith(indenter);
        objectMapper.setDefaultPrettyPrinter(printer);
        return obj2JsonStr(objectMapper, obj);
    }

    /**
     * -------------------------------------------------------------
     *
     * @param obj
     * @return
     * @throws ServiceException
     * @author @date        @comment
     * Chen, Rui   1/30/2019     init version
     * -------------------------------------------------------------
     */
    public static String obj2JsonStr(Object obj) throws ServiceException {
        return obj2JsonStr(getObjectMapper(), obj);
    }

    /**
     * -------------------------------------------------------------
     *
     * @param objectMapper
     * @param obj
     * @return
     * @throws ServiceException
     * @author @date        @comment
     * Chen, Rui   1/30/2019     init version
     * -------------------------------------------------------------
     */
    public static String obj2JsonStr(ObjectMapper objectMapper, Object obj) throws ServiceException {
        try {
            ObjectWriter objectWriter = objectMapper.writerWithDefaultPrettyPrinter();
            return objectWriter.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("parse failed", e);
            throw new ServiceException("parse failed", e);
        }
    }

    public static <T> T jsonStr2Obj(String jsonStr, TypeReference<T> type) throws ServiceException {
        T readValue = null;
        try {
            readValue = getObjectMapper().readValue(jsonStr, type);
        } catch (IOException e) {
            logger.error("parse failed", e);
            throw new ServiceException("parse failed", e);
        }
        return readValue;
    }
}
