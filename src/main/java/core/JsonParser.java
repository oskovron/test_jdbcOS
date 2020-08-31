package core;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import model.DriverDTO;




public class JsonParser {
    public static void main(String[] args) throws Exception {
        FileInputStream file = new FileInputStream("src/main/resources/drivers.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        TypeFactory typeFactory = TypeFactory.defaultInstance();
        List<DriverDTO> drivers = objectMapper.readValue(file, typeFactory.constructCollectionType(ArrayList.class, DriverDTO.class));

        drivers.forEach(driver -> System.out.println(driver));

        System.out.println(drivers.get(0).getAge());
        System.out.println(drivers.get(1).getCar().getAge());

    }
}
