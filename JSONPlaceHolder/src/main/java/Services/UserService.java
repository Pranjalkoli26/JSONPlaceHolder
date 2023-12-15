package Services;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
//API HANDLING
public class UserService {
    static String BASE_URL = "https://jsonplaceholder.typicode.com";
    static String BASE_PATH = "/users";
    RequestSpecification req_spec;

    public UserService() {
        this.req_spec = ((RequestSpecification)RestAssured.given().log().all()).baseUri(BASE_URL).basePath(BASE_PATH);
    }

    public Response getAllUsers() {
        return (Response)RestAssured.given().spec(this.req_spec).when().get();
    }

    public Response getUserById(int Id) {
        return (Response)RestAssured.given().spec(this.req_spec).when().get(String.valueOf(Id), new Object[0]);
    }

    public Response createUser() {
        return (Response)RestAssured.given().spec(this.req_spec).contentType(ContentType.JSON).body("{\n    \"name\": \"Om Shigvan\",\n    \"username\": \"shigvanOm\",\n    \"email\": \"om.shigvan@vodafone.com\",\n    \"address\": {\n        \"street\": \"Kulas Light\",\n        \"suite\": \"Apt. 556\",\n        \"city\": \"Gwenborough\",\n        \"zipcode\": \"92998-3874\",\n        \"geo\": {\n            \"lat\": \"-37.3159\",\n            \"lng\": \"81.1496\"\n        }\n    },\n    \"phone\": \"7083504552\",\n    \"website\": \"hildegard.org\",\n    \"company\": {\n        \"name\": \"_VOIS\",\n        \"catchPhrase\": \"We move the world\",\n        \"bs\": \"harness real-time e-markets\"\n    }\n}").when().post();
    }

    public Response deleteUserById(int Id) {
        return (Response)RestAssured.given().spec(this.req_spec).when().delete(String.valueOf(Id), new Object[0]);
    }

    public Response updateUserById(int Id) {
        return (Response)RestAssured.given().spec(this.req_spec).contentType(ContentType.JSON).body("{\n    \"id\": " + Id + ",\n    \"name\": \"Om Shigvan\",\n    \"username\": \"shigvanOm\",\n    \"email\": \"om.shigvan@vodafone.com\",\n    \"address\": {\n        \"street\": \"Kulas Light\",\n        \"suite\": \"Apt. 556\",\n        \"city\": \"Gwenborough\",\n        \"zipcode\": \"92998-3874\",\n        \"geo\": {\n            \"lat\": \"-37.3159\",\n            \"lng\": \"81.1496\"\n        }\n    },\n    \"phone\": \"7083504552\",\n    \"website\": \"hildegard.org\",\n    \"company\": {\n        \"name\": \"_VOIS\",\n        \"catchPhrase\": \"We move the world\",\n        \"bs\": \"harness real-time e-markets\"\n    }\n}").when().put(String.valueOf(Id), new Object[0]);
    }

    public Response getAllUserPosts(int Id) {
        return (Response)RestAssured.given().spec(this.req_spec).when().get("" + Id + "/posts", new Object[0]);
    }

    public Response getAllUserTodos(int Id) {
        return (Response)RestAssured.given().spec(this.req_spec).when().get("" + Id + "/todos", new Object[0]);
    }

    public Response getAllUserAlbums(int Id) {
        return (Response)RestAssured.given().spec(this.req_spec).when().get("" + Id + "/albums", new Object[0]);
    }
}