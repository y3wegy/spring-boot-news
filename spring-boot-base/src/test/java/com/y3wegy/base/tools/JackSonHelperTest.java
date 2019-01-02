package com.y3wegy.base.tools;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class JackSonHelperTest {

    private static final Logger logger = LoggerFactory.getLogger(JackSonHelperTest.class);
    @Test
    public void testSS() {
        try {
            JsonGenerator jsonGenerator = JackSonHelper.getObjectMapper().getFactory().createGenerator(System.out);
            String[] arr = {"a", "b", "c"};
            logger.info("jsonGenerator");
            String str = "hello world jackson!";
            //byte
            jsonGenerator.writeBinary(str.getBytes());
            //boolean
            jsonGenerator.writeBoolean(true);
            //null
            jsonGenerator.writeNull();
            //float
            jsonGenerator.writeNumber(2.2f);
            //char
            jsonGenerator.writeRaw("c");
            //String
            jsonGenerator.writeRaw(str, 5, 10);
            //String
            jsonGenerator.writeRawValue(str, 5, 5);
            //String
            jsonGenerator.writeString(str);
            //jsonGenerator.writeTree(JsonNodeFactory.instance.POJONode(str));
            logger.info("---------------------");

            //Object
            jsonGenerator.writeStartObject();//{
            jsonGenerator.writeObjectFieldStart("user");//user:{
            jsonGenerator.writeStringField("name", "jackson");//name:jackson
            jsonGenerator.writeBooleanField("sex", true);//sex:true
            jsonGenerator.writeNumberField("age", 22);//age:22
            jsonGenerator.writeEndObject();//}

            jsonGenerator.writeArrayFieldStart("infos");//infos:[
            jsonGenerator.writeNumber(22);//22
            jsonGenerator.writeString("this is array");//this is array
            jsonGenerator.writeEndArray();//]

            jsonGenerator.writeEndObject();//}

            System.out.println("\r\n");

            User securityUser = new User();
            securityUser.setId("234");
            securityUser.setPassword("12344");
            //complex Object
            jsonGenerator.writeStartObject();//{
            jsonGenerator.writeObjectField("user", securityUser);//user:{bean}
            jsonGenerator.writeObjectField("infos", arr);//infos:[array]
            jsonGenerator.writeEndObject();//}
            jsonGenerator.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    class User{
        private String id;
        @JsonIgnore
        private String password;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}