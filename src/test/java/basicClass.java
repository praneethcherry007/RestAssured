import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;



public class basicClass {

    @Test
    public void getPostsShouldReturn200() {
       RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response = given()
                .when()
                .get("/posts/1");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());
        }



}
