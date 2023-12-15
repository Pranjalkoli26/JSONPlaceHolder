
import Model.Albums;
import Model.Post;
import Model.Todo;
import Model.User;
import Services.UserService;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static io.restassured.RestAssured.expect;

public class UserTest {
    UserService userServiceObj = new UserService();
    SoftAssert softAssert = new SoftAssert();

    static ResponseSpecification res_Spec;

    @BeforeClass
    public void specifications() {
        res_Spec = expect().statusCode(200);
    }
//Get all user
    @Test
    public void getAllUsers() {
        Response response = userServiceObj.getAllUsers()
                .then().log().all().spec(res_Spec).extract().response();
        User[] user = response.as(User[].class);

        for (int i = 0; i < Arrays.asList(user).size(); i++) {
            softAssert.assertEquals(user[i].id, i + 1);
            softAssert.assertNotNull(user[i].name);
            softAssert.assertNotNull(user[i].username);
            softAssert.assertNotNull(user[i].email);
            softAssert.assertNotNull(user[i].address);
            softAssert.assertNotNull(user[i].phone);
            softAssert.assertNotNull(user[i].company);
        }
        softAssert.assertAll();
    }
// Get User
    @Test
    public void getUser() {
        Response response = userServiceObj.getUserById(1)
                .then().log().all().spec(res_Spec).extract().response();
        User user= response.as(User.class);

        softAssert.assertEquals(user.id, 1);
        softAssert.assertNotNull(user.name);
        softAssert.assertNotNull(user.username);
        softAssert.assertNotNull(user.email);
        softAssert.assertNotNull(user.address);
        softAssert.assertNotNull(user.phone);
        softAssert.assertNotNull(user.company);
        softAssert.assertAll();
    }
//Creating User
    @Test
    public void createUser() {
        Response response = userServiceObj.createUser()
                .then().log().all().extract().response();
        User user = response.as(User.class);

        softAssert.assertEquals(response.statusCode(), 201, "Status code should be 201: CREATED");
        softAssert.assertNotNull(String.valueOf(user.id));
        softAssert.assertEquals(user.name, "Pranjal Koli");
        softAssert.assertEquals(user.username, "Prankstar");
        softAssert.assertEquals(user.email, "Pranjal.koli@vodafone.com");
        softAssert.assertNotNull(user.address);
        softAssert.assertEquals(user.phone, "0836464747");
        softAssert.assertEquals(user.company.name, "ABCD");
        softAssert.assertAll();
    }
// Deletion of User
    @Test
    public void deleteUser() {
        userServiceObj.deleteUserById(1)
                .then().spec(res_Spec);
    }
//Updating User
    @Test
    public void updateUser() {
        Response response = userServiceObj.updateUserById(1)
                .then().log().all().spec(res_Spec).extract().response();
        User user = response.as(User.class);

        softAssert.assertEquals(user.id, 1);
        softAssert.assertEquals(user.name, "Om Shigvan");
        softAssert.assertEquals(user.username, "shigvanOm");
        softAssert.assertEquals(user.email, "om.shigvan@vodafone.com");
        softAssert.assertNotNull(user.address);
        softAssert.assertEquals(user.phone, "7083504552");
        softAssert.assertEquals(user.company.name, "_VOIS");
        softAssert.assertEquals(user.company.catchPhrase, "We move the world");
        softAssert.assertAll();
    }
// Get all User Posts
    @Test
    public void getAllUserPosts() {
        Response response = userServiceObj.getAllUserPosts(1)
                .then().log().all().spec(res_Spec).extract().response();
        Post[] post = response.as(Post[].class);

        for (int i = 0; i < Arrays.asList(post).size(); i++) {
            softAssert.assertEquals(post[i].userId, 1);
            softAssert.assertNotNull(String.valueOf(post[i].id));
            softAssert.assertNotNull(post[i].title);
            softAssert.assertNotNull(post[i].body);
        }
        softAssert.assertAll();
    }
// Get all User
    @Test
    public void getAllUserTodos() {
        Response response = userServiceObj.getAllUserTodos(1)
                .then().log().all().spec(res_Spec).extract().response();
        Todo[] todo= response.as(Todo[].class);

        for (int i = 0; i < Arrays.asList(todo).size(); i++) {
            softAssert.assertEquals(todo[i].userId, 1);
            softAssert.assertNotNull(String.valueOf(todo[i].id));
            softAssert.assertNotNull(todo[i].title);
            softAssert.assertNotNull(String.valueOf(todo[i].completed));
        }
        softAssert.assertAll();
    }
    // User By Albums

    @Test
    public void getAllUserAlbums() {
        Response response = userServiceObj.getAllUserAlbums(1)
                .then().log().all().spec(res_Spec).extract().response();
        Albums[] albums = response.as(Albums[].class);

        for (int i = 0; i < Arrays.asList(albums).size(); i++) {
            softAssert.assertEquals(albums[i].userId, 1);
            softAssert.assertNotNull(String.valueOf(albums[i].id));
            softAssert.assertNotNull(albums[i].title);
        }
        softAssert.assertAll();
    }

}