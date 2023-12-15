import Model.Comment;
import Model.Post;
import Services.PostService;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;

import static io.restassured.RestAssured.expect;

public class PostTest {
    PostService postServiceObj = new PostService();
    SoftAssert softAssert = new SoftAssert();

    static ResponseSpecification res_Spec;

    @BeforeClass
    public void specifications() {
        res_Spec = expect().statusCode(200);
    }
//Get all post test
    @Test
    public void getAllPosts() {
        Response response = postServiceObj.getAllPosts().then()
                .log().all().spec(res_Spec).extract().response();
        Post[] postObj = response.as(Post[].class);

        for (int i = 0; i < Arrays.asList(postObj).size(); i++) {
            softAssert.assertNotNull(String.valueOf(postObj[i].userId));
            softAssert.assertEquals(postObj[i].id, i + 1);
            softAssert.assertNotNull(postObj[i].title);
            softAssert.assertNotNull(postObj[i].body);
        }
        softAssert.assertAll();
    }
//Positive test to create post
    @Test
    public void createPost() {
        Response response = postServiceObj.createPostByUserId(1).then()
                .log().all().extract().response();
        Post postObj = response.as(Post.class);

        softAssert.assertEquals(response.statusCode(), 201, "Status code should be 201: CREATED");
        softAssert.assertEquals(postObj.userId, 1);
        softAssert.assertNotNull(String.valueOf(postObj.id));
        softAssert.assertEquals(postObj.title, "my_first_post");
        softAssert.assertEquals(postObj.body, "have a nice day :)");
        softAssert.assertAll();
    }
    //Negative test to create post
    @Test
    public void createPostNegativeTest() {
        Response response = postServiceObj.createPostByUserId(1).then()
                .log().all().extract().response();
        Post postObj = response.as(Post.class);

        softAssert.assertEquals(response.statusCode(), 201, "Status code should be 201: CREATED");
        softAssert.assertEquals(postObj.userId, 1);
        softAssert.assertNotNull(String.valueOf(postObj.id));
        softAssert.assertEquals(postObj.title, "MyPost");
        softAssert.assertEquals(postObj.body, "Sunday day :)");
        softAssert.assertAll();
    }
    // Positive Test to get post by ID

    @Test
    public void getPostByPostId() {
        Response response = postServiceObj.getPostById(99).then()
                .log().all().spec(res_Spec).extract().response();
        Post postObj = response.as(Post.class);

        softAssert.assertNotNull(String.valueOf(postObj.userId));
        softAssert.assertEquals(postObj.id, 99);
        softAssert.assertNotNull(postObj.title);
        softAssert.assertNotNull(postObj.body);
        softAssert.assertAll();
    }
    //Negative test to get post By ID
    @Test
    public void getPostByPostIdNegative () {
        Response response = postServiceObj.getPostById(405).then()
                .log().all().spec(res_Spec).extract().response();
        Post postObj = response.as(Post.class);

        softAssert.assertNotNull(String.valueOf(postObj.userId));
        softAssert.assertEquals(postObj.id, 405);
        softAssert.assertNotNull(postObj.title);
        softAssert.assertNotNull(postObj.body);
        softAssert.assertAll();
    }
    // Positive Test
    @Test
    public void updatePostByPostId() {
        Response response = postServiceObj.updatePostById(99).then()
                .log().all().spec(res_Spec).extract().response();
        Post postObj = response.as(Post.class);

        softAssert.assertNotNull(String.valueOf(postObj.userId));
        softAssert.assertEquals(postObj.id, 99);
        softAssert.assertEquals(postObj.title, "message");
        softAssert.assertEquals(postObj.body, "Hello World!!!");
        softAssert.assertAll();
    }
// delete post
    @Test
    public void deletePostByPostId() {
        postServiceObj.deletePostById(1)
                .then().spec(res_Spec);
    }
// positive test get post by user ID
    @Test
    public void getPostsByUserId() {
        Response response = postServiceObj.filterPostByUserId(9).then()
                .log().all().spec(res_Spec).extract().response();
        Post[] postObj = response.as(Post[].class);

        for (int i = 0; i < Arrays.asList(postObj).size(); i++) {
            softAssert.assertEquals(postObj[i].userId, 9);
            softAssert.assertNotNull(String.valueOf(postObj[i].id));
            softAssert.assertNotNull(postObj[i].title);
            softAssert.assertNotNull(postObj[i].body);
        }
        softAssert.assertAll();
    }
    // positive test
    @Test
    public void getAllCommentsOfPost() {
        Response response = postServiceObj.getAllCommentsOfPostById(1).then()
                .log().all().spec(res_Spec).extract().response();
        Comment[] commentObj = response.as(Comment[].class);

        for (int i = 0; i < Arrays.asList(commentObj).size(); i++) {
            softAssert.assertEquals(commentObj[0].postId, 1);
            softAssert.assertNotNull(String.valueOf(commentObj[0].id));
            softAssert.assertNotNull(commentObj[0].name);
            softAssert.assertNotNull(commentObj[0].email);
            softAssert.assertNotNull(commentObj[0].body);
        }
        softAssert.assertAll();
    }

}
