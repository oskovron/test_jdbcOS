package tests.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.*;


public class MyTestApi {
    //revert me too
    String baseURL = "https://reqres.in/api/";

    @Test
    public void firstTestApi(){

        String users = "users?page=2";
        when()
                .get(baseURL + users)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .assertThat()
                .body("page", equalTo(2));
    }
}
