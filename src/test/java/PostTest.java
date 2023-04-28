import Objects.User;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;

// https://www.eliasnogueira.com/the-best-way-to-add-a-request-body-to-a-post-request-using-rest-assured/
class PostTest {

    @Test
    void postUsingObjectMapping() {

        User user = new User("Elias", "Principal Engineer");

        Response r = given().
                contentType(ContentType.JSON).
                body(user).
                when().
                post(" https://reqres.in/api/users");
        r.then().statusCode(HttpStatus.SC_CREATED);

        r.body().prettyPrint();

        JsonPath bdy = r.jsonPath();
        System.out.println(bdy.get("name").toString());

        assertTrue(bdy.get("name").toString().equals(user.getName()));
    }
}