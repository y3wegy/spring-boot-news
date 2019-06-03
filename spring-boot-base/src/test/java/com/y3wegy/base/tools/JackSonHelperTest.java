package com.y3wegy.base.tools;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.core.util.DefaultIndenter;
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.y3wegy.base.ServiceExeption;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

class JackSonHelperTest {

    private static final Logger logger = LoggerFactory.getLogger(JackSonHelperTest.class);

    @Test
    void testIndent() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        // Setup a pretty printer with an indenter (indenter has 4 spaces in this case)
        DefaultPrettyPrinter.Indenter indenter = new DefaultIndenter("    ", DefaultIndenter.SYS_LF);
        DefaultPrettyPrinter printer = new DefaultPrettyPrinter();
        printer.indentObjectsWith(indenter);
        printer.indentArraysWith(indenter);

        // Some object to serialize
        Map<String, Object> value = new HashMap<>();
        value.put("foo", Arrays.asList("a", "b", "c"));

        // Serialize it using the custom printer
        String json = mapper.writer(printer).writeValueAsString(value);

        // Print it
        System.out.println(json);
    }

    @Test
    void testEmptyStr() {
        logger.info(String.format("%" + 3 + "s", ""));
    }

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

    @Test
    void testParse() throws ServiceExeption {
        User user = new User();
        user.setId("1");
        user.setPassword("2");
        User child = new User();
        child.setId("2");
        child.setPassword("QQWQ");
        user.addChild(child);
        List<User> userList = new ArrayList<>(1);
        userList.add(user);

        String jsonStr = JackSonHelper.obj2JsonStr(userList);
        List<User> n = JackSonHelper.jsonStr2Obj(jsonStr, new TypeReference<List<User>>() {
        });
        logger.info(String.valueOf(n.size()));
    }

    static class User {
        private String id;
        @JsonIgnore
        private String password;

        final List<User> child = new ArrayList<>(1);

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

        public List<User> getChild() {
            return child;
        }

        public void addChild(User child) {
            this.child.add(child);
        }
    }
}
