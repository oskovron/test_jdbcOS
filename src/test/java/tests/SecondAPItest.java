package tests;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import io.restassured.http.Header;
import model.DriverDTO;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

public class SecondAPItest {
    String baseUrl = "https://reqres.in/api/";

    @Test
    public void test1() {
        String users = "users?page=2";

        when()
                .get(baseUrl + users)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .body("page", equalTo(2));
    }

    @Test
    public void test2(){
        String users = "users/2";
        when()
                .get(baseUrl + users)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .body("data.id" , equalTo(2));
    }

    @Test
    public void test3() throws Exception {
        String users = "users/2";

        FileInputStream file = new FileInputStream("src/main/resources/drivers.json");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

        TypeFactory typeFactory = TypeFactory.defaultInstance();
        List<DriverDTO> drivers = objectMapper.readValue(file, typeFactory.constructCollectionType(ArrayList.class, DriverDTO.class));

        DriverDTO driver1 = drivers.get(0);

        given().
                param("param1", "value1").
                param("content-type", "application/json").
        when().
                post(baseUrl+users, driver1)
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .assertThat()
                .body("data.id", equalTo(2));
    }

    @Test
    public void test4token() {
        String users = "users/2";

        Optional<Header> token = when().
                get(baseUrl + users)
                .then()
                .extract()
                .response()
                .getHeaders()
                .asList()
                .stream()
                .filter(currentHeader -> currentHeader.getName().equals("token"))
                .findFirst();

        //hz
        String tokenString;

        if(token.isPresent()) {
            tokenString = token.get().getValue();
        } else {
            tokenString = "";
        }
        //hz

        given()
                .header(new Header("token", tokenString))
                .when()
                .get()
                .then()
                .assertThat()
                .statusCode(200);

    }
}
