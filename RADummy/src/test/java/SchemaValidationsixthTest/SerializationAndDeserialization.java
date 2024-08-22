package SchemaValidationsixthTest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.Test;



public class SerializationAndDeserialization {
    //Serialization: Converting POJO → JSON

    @Test
    void convertPOJO2JSON() throws JsonProcessingException {

        //Create Java Object using POJO CLass
        StudentPOJO_CreateData stupojo = new StudentPOJO_CreateData(); //POJO object
        stupojo.setName("King");
        stupojo.setLocation("London");
        stupojo.setPhone("2233223345");
        String[] courseArr = {"DS", "ML", "Rest"};
        stupojo.setCoursesArr(courseArr);


        //Serialization: Converting POJO → JSON
        ObjectMapper objMapper= new ObjectMapper();
        String jsondata= objMapper.writerWithDefaultPrettyPrinter().writeValueAsString(stupojo);
        System.out.println(jsondata);

    }

    //Deserialization: Converting JSON → POJO
    @Test
    void convertJSON2POJO() throws JsonProcessingException {

        //Converting JSON to String
        String jsondata= "{\n" +
                "  \"name\" : \"King\",\n" +
                "  \"location\" : \"London\",\n" +
                "  \"phone\" : \"2233223345\",\n" +
                "  \"coursesArr\" : [ \"DS\", \"ML\", \"Rest\" ]\n" +
                "}";

        //Converting JSON --> POJO

        ObjectMapper objMapper=new ObjectMapper();
        StudentPOJO_CreateData stuPOJO= objMapper.readValue(jsondata, StudentPOJO_CreateData.class ); //StudentPOJO_CreateData.class will return StudentPOJO_CreateData class object
        System.out.println(stuPOJO.getName());


    }
}