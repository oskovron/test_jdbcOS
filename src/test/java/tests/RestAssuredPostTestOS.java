package tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredPostTestOS {
    @Test
    public void RegistrationSuccessful()
    {
        // Tutorial here:
        // https://www.toolsqa.com/rest-assured/post-request-using-rest-assured/
        RestAssured.baseURI ="https://reqres.in/api/register";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("Email",  "eve.holt@reqres.in");
        requestParams.put("Password", "password1");

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.post("/register");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        String id = response.jsonPath().get("id"); //dynamic changes on each post request
        Assert.assertEquals(id, id);
        String token = response.jsonPath().get("token");
//        Assert.assertEquals(token, "QpwL5tke4Pnpja7X4");
    }

    @Test
    public void RegistrationUnsuccessful() {
        // Tutorial here:
        // https://www.toolsqa.com/rest-assured/post-request-using-rest-assured/
        RestAssured.baseURI = "https://reqres.in/api/register";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("Email", "sydney@fife");

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.post("/register");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
        String error = response.jsonPath().get("error"); //dynamic changes on each post request
        Assert.assertEquals(error, "Missing password");
    }

    @Test
    public void loginUnsuccessful() {
        // Tutorial here:
        // https://www.toolsqa.com/rest-assured/post-request-using-rest-assured/
        RestAssured.baseURI = "https://reqres.in/api/login";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("Email", "peter@klaven");

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.post("/register");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
        String error = response.jsonPath().get("error"); //dynamic changes on each post request
        Assert.assertEquals(error, "Missing password");
    }
}
