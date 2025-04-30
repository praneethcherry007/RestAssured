import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class basicClass {

    @Test
    public void getPostsShouldReturn200() {
        // RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        Response response = given().baseUri("https://jsonplaceholder.typicode.com")
                .when()
                .get("/posts/1");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());
    }

    @Test
    public void registrationTest() {

        //basicClass body = new basicClass("eve.holt@reqres.in", "pistol");


        String workspaceName =
                given()
                        .baseUri("https://reqres.in/")
                        .header("x-api-key", "reqres-free-v1")
                        .header("Content-Type", "application/json")
                        .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}")
                        .when()
                        .post("api/register")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .extract()
                        .response()
                        .body()
                        .prettyPrint();
        System.out.println("post response" + workspaceName);
    }

    @Test
    public void assertTest() {

        Response response =
                given()
                        .baseUri("https://reqres.in/")
                        .header("x-api-key", "reqres-free-v1")
                        .header("Content-Type", "application/json")
                        .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\"}")
                        .when()
                        .get("api/users?page=2")
                        .then().assertThat().statusCode(200)
                        .extract().response();

        JsonPath jsonPath = new JsonPath(response.asString());
        String firstName = jsonPath.getString("data[1].first_name");
        assertThat(firstName,equalTo("Lindsay"));
        Assert.assertEquals(firstName,"Lindsay");
        System.out.println(firstName);

        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Body:\n" + response.getBody().asPrettyString());

    }
}
