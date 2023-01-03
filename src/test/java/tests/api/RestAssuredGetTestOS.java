package tests.api;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

public class RestAssuredGetTestOS {
    String baseURL = "https://reqres.in/api/";
    String user2 = "users/2";
    @Test
    public void getTest()
    {
        // Specify the base URL to the RESTful web service
        RestAssured.baseURI = baseURL;

        // Get the RequestSpecification of the request that you want to sent
        // to the server. The server is specified by the BaseURI that we have
        // specified in the above step.
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.log().all();

        // Make a request to the server by specifying the method Type and the method URL.
        // This will return the Response from the server. Store the response in a variable.
        Response response = httpRequest.request(Method.GET, user2);
//        Response response  = httpRequest.get(user2);

        // Now let us print the body of the message to see what response
        // we have received from the server
        String responseBody = response.getBody().asString(); // or response.asList();
        System.out.println("Response Body is =>  " + responseBody);

        System.out.println("Response headers are =>  " + response.getHeaders().asList());

        String contentType = response.header("content-type");
        System.out.println(contentType);

    }

}
