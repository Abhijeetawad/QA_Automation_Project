package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class APITest {

    @Test
    public void getUserTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Response response = RestAssured.get("/users/1");

        System.out.println("Response: " + response.getBody().asString());
        assertEquals(response.getStatusCode(), 200); // Validate status code
    }
}
