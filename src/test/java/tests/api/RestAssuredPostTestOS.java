package tests.api;

import model.RegistrationFailureResponseDTO;
import model.RegistrationSuccessResponseDTO;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredPostTestOS extends BaseTest{
    @Test
    public void RegistrationSuccessful()
    {
        // Tutorial here:
        // https://www.toolsqa.com/rest-assured/post-request-using-rest-assured/
        RestAssured.baseURI ="https://reqres.in/api";
        RequestSpecification request = RestAssured.given();


        // JSONObject is a class that represents a Simple JSON.
        // We can add Key - Value pairs using the put method
        JSONObject requestParams = new JSONObject();
        requestParams.put("email",  "eve.holt@reqres.in");
        requestParams.put("password", "password1");

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.post("/register");

        //get access token
        String access_token = response.getBody().jsonPath().getString("token");
        System.out.println("Access token: " + access_token);


        int statusCode = response.getStatusCode();
        softAssert.assertEquals(statusCode, 200);
        int id = response.getBody().jsonPath().get("id");
        softAssert.assertEquals(id, 4);
        String token = response.jsonPath().get("token");
        softAssert.assertEquals(token, "QpwL5tke4Pnpja7X4");
    }

    @Test
    public void RegistrationUnsuccessful() {
        // Tutorial here:
        // https://www.toolsqa.com/rest-assured/post-request-using-rest-assured/
        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "sydney@fife");

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.post("/register");

        int statusCode = response.getStatusCode();
        softAssert.assertEquals(statusCode, 400);
        String error = response.jsonPath().get("error");
        softAssert.assertEquals(error, "Missing password");
    }

    @Test
    public void loginUnsuccessful() {
        // Tutorial here:
        // https://www.toolsqa.com/rest-assured/post-request-using-rest-assured/
        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "peter@klaven");

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.post("/login");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
        String error = response.jsonPath().get("error");
        Assert.assertEquals(error, "Missing password");
    }

    // Send a GET request to an Endpoint that expects POST
    @Test
    public void getInPOST(){
        RestAssured.baseURI ="https://reqres.in/api";
        RequestSpecification request = RestAssured.given();

        JSONObject requestParams = new JSONObject();
        requestParams.put("email",  "eve.holt@reqres.in");
        requestParams.put("password", "password1");

        // Add a header stating the Request body is a JSON
//        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.get("/register"); //get

        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);
        System.out.println("Responce body: " + response.body().toString());
    }

    //Deserialize json to Class with rest Assured
    // and
    //Deserialize JSON Response Body based on Response Status
    @Test
    public void deserializeJsonDTOSuccessRegister() {
        // Tutorial here:
        // https://www.toolsqa.com/rest-assured/post-request-using-rest-assured/
        RestAssured.baseURI = "https://reqres.in/api";
        RequestSpecification request = RestAssured.given();
        request.log().all();

        // JSONObject is a class that represents a Simple JSON.
        // We can add Key - Value pairs using the put method
        JSONObject requestParams = new JSONObject();
        requestParams.put("email", "eve.holt@reqres.in");
        requestParams.put("password", "password1");

        // Add a header stating the Request body is a JSON
        request.header("Content-Type", "application/json");

        // Add the Json to the body of the request
        request.body(requestParams.toJSONString());

        // Post the request and check the response
        Response response = request.post("/register");
        ResponseBody body = response.getBody();
        System.out.println(response.getBody().asString());

        //Deserialize JSON Response Body based on Response Status
        if (response.statusCode()==200) {
            // Deserialize the Response body into RegistrationSuccessResponse
            RegistrationSuccessResponseDTO responseBody = body.as(RegistrationSuccessResponseDTO.class);

            // Use the RegistrationSuccessResponse class instance to Assert the values of
            // Response.
            Assert.assertEquals(responseBody.id, 4);
            Assert.assertEquals( responseBody.token, "QpwL5tke4Pnpja7X4");
    }else if (response.statusCode()==400){
            // Deserialize the Response body into RegistrationSuccessResponse
            RegistrationFailureResponseDTO responseBody = body.as(RegistrationFailureResponseDTO.class);

            // Use the RegistrationSuccessResponse class instance to Assert the values of
            // Response.
            Assert.assertEquals(responseBody.error, "Missing password");
        }
    }
}
