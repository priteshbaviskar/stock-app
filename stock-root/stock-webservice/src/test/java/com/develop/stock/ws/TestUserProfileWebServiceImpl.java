//package com.develop.stock.ws;
//
//import com.develop.giclee.utilities.common.UserStatusCode;
//import com.develop.giclee.utilities.dao.UserAuthDAO;
//import com.develop.giclee.utilities.dao.UserDAO;
//import com.develop.giclee.utilities.json.ResponseJson;
//import com.develop.giclee.utilities.model.User;
//import com.develop.giclee.utilities.model.UserAuthentication;
//import com.google.common.collect.Lists;
//import com.google.gson.Gson;
//import com.google.gson.reflect.TypeToken;
//import org.glassfish.jersey.server.ResourceConfig;
//import org.glassfish.jersey.test.JerseyTest;
//import org.joda.time.DateTime;
//import org.json.JSONException;
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.MockitoAnnotations;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import javax.ws.rs.client.Entity;
//import javax.ws.rs.core.Application;
//import javax.ws.rs.core.Response;
//import java.lang.reflect.Type;
//import java.util.List;
//
//import static org.junit.Assert.*;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath*:TestApplication-context.xml"})
//public class TestUserProfileWebServiceImpl extends JerseyTest {
//
//    User user1, user2;
//
//    @Autowired
//    private UserAuthDAO userAuthenticationDAO;
//
//    @Autowired
//    private UserDAO userDAO;
//
//    @Before
//    public void setup() {
//
//        MockitoAnnotations.initMocks(this);
//
//        user1 = new User();
//        user2 = new User();
//
//        user1.setUserID(1);
//        user1.setEmailID("paplu@gmail.com");
//        user1.setFirstName("Amar");
//        user1.setPassword("aailaUiim@1");
//        user1.setStatusCodeID(10001); //active
//        user1.setCreatedBy(1);
//        user1.setLastModifiedBy(1);
//
//        user2.setUserID(2);
//        user2.setEmailID("taplu@gmail.com");
//        user2.setFirstName("Prem");
//        user2.setPassword("aailaUiim#2");
//        user2.setStatusCodeID(10001); //active
//        user2.setCreatedBy(1);
//        user2.setLastModifiedBy(1);
//
//        //create users in h2 db. Not the most ideal way to create. :|
//        target("/users/register").request().post(Entity.json(user1), String.class);
//        target("/users/register").request().post(Entity.json(user2), String.class);
//    }
//
//
//    @Test
//    public void testRegister_Success() {
//        User user3 = new User();
//        user3.setUserID(3);
//        user3.setEmailID("nahanekatime@gmail.com");
//        user3.setFirstName("Ram Gopal");
//        user3.setLastName("Bajaj");
//        user3.setPassword("Khaan#kaT1me");
//        user3.setStatusCodeID(10001); //active
//
//        Response response = target("/users/register").request().post(Entity.json(user3), Response.class);
//        String jsonOutput = response.readEntity(String.class);
//        System.out.println(jsonOutput);
//        assertEquals(201, response.getStatus());
//
//    }
//
//    @Test
//    public void testRegister_ValidationFailedWeakPassword() {
//        User user3 = new User();
//        user3.setUserID(3);
//        user3.setEmailID("nahanekatime@gmail.com");
//        user3.setFirstName("Ram Gopal");
//        user3.setLastName("Bajaj");
//        user3.setPassword("cham pak");
//        user3.setStatusCodeID(10001); //active
//
//        Response response = target("/users/register").request().post(Entity.json(user3), Response.class);
//        assertEquals(400, response.getStatus());
//        String actualJson = response.readEntity(String.class);
//        System.out.println(actualJson);
//
//    }
//
//    @Test
//    public void testRegister_ValidationFailedEmailAddress() {
//        User user3 = new User();
//        user3.setUserID(3);
//        user3.setEmailID("nahan_eka_time%gmail.com");
//        user3.setFirstName("Ram Gopal");
//        user3.setLastName("Bajaj");
//        user3.setPassword("Champak123#");
//        user3.setStatusCodeID(10001); //active
//
//        Response response = target("/users/register").request().post(Entity.json(user3), Response.class);
//        assertEquals(400, response.getStatus());
//        String actualJson = response.readEntity(String.class);
//        System.out.println(actualJson);
//
//    }
//
//
//    @Test
//    public void testRegister_Fail() {
//
//        Response response = target("/users/register").request().post(Entity.json(user1), Response.class);
//        assertEquals(400, response.getStatus());
//    }
//
//    @Test
//    public void testGetAllUsers_Success() throws JSONException {
//
//        Gson gson = new Gson();
//        List<User> users = Lists.newArrayList(user1, user2);
//
//        Type listOfTestObject = new TypeToken<List<User>>() {
//        }.getType();
//        String expectedJson = gson.toJson(users, listOfTestObject);
//        System.out.println(expectedJson);
//        String actualJson = target("/users").request().get(String.class);
//
//        assertTrue(actualJson.contains("paplu"));
//        assertTrue(actualJson.contains("taplu"));
//
//        //JSONAssert.assertEquals(expectedJson, actualJson, false);
//    }
//
//    @Test
//    public void testUpdateUser_Success() {
//
//        user1.setLastName("Gogo");
//        user1.setFirstName("Crime Master");
//
//        Response response = target("/users/updateUserName").request().put(Entity.json(user1), Response.class);
//        assertEquals(200, response.getStatus());
//        Response responseGetUser = target("/users/getUser/paplu@gmail.com").request().get(Response.class);
//
//        String json = responseGetUser.readEntity(String.class);
//        System.out.println(json);
//        assertTrue(json.contains("lastName"));
//        assertTrue(json.contains("Gogo"));
//        assertTrue(json.contains("Crime Master"));
//        assertTrue(!json.contains("Amar"));
//
//    }
//
//    @Test
//    public void testUpdateUser_SuccessFirstNameNULL() {
//
//        user1.setLastName("Gogo");
//        user1.setFirstName(null);
//
//        Response response = target("/users/updateUserName").request().put(Entity.json(user1), Response.class);
//        assertEquals(200, response.getStatus());
//        Response responseGetUser = target("/users/getUser/paplu@gmail.com").request().get(Response.class);
//
//        String json = responseGetUser.readEntity(String.class);
//        System.out.println(json);
//        assertTrue(json.contains("lastName"));
//        assertTrue(json.contains("Gogo"));
//    }
//
//    @Test
//    public void testUpdateUser_IncorrectJsonFields() {
//
//        user1.setPhoneNumber("9960413489");
//        Response response = target("/users/updateUserName").request().put(Entity.json(user1), Response.class);
//        assertEquals(200, response.getStatus());
//        Response responseGetUser = target("/users/getUser/1").request().get(Response.class);
//
//        String json = responseGetUser.readEntity(String.class);
//        assertTrue(!json.contains("phoneNumer"));
//        assertTrue(!json.contains("9960413489"));
//
//    }
//
//
//    @Test
//    public void testUpdateUser_UserDoesNotExist() {
//
//        User user = new User();
//        user.setFirstName("Bakasur");
//        Response response = target("/users/updateUserName").request().put(Entity.json(user), Response.class);
//
//        assertEquals(404, response.getStatus());
//
//    }
//
//    @Test
//    public void testUpdateUser_PhoneNumberSuccess() {
//
//        user1.setPhoneNumber("9960413489");
//        Response response = target("/users/updatePhoneNumber").request().put(Entity.json(user1), Response.class);
//        assertEquals(200, response.getStatus());
//        Response responseGetUser = target("/users/getUser/paplu@gmail.com").request().get(Response.class);
//
//        String json = responseGetUser.readEntity(String.class);
//        assertEquals(200, responseGetUser.getStatus());
//        assertTrue(json.contains("phoneNumber"));
//        assertTrue(json.contains("9960413489"));
//
//    }
//
//    @Test
//    public void testUpdateUser_PhoneNumberFail() {
//
//        User user = new User();
//        user.setPhoneNumber("9960512348");
//        Response response = target("/users/updatePhoneNumber").request().put(Entity.json(user), Response.class);
//        assertEquals(404, response.getStatus());
//    }
//
//    @Test
//    public void testUpdateUser_PhoneNumberInvalidPhoneNumber() {
//
//        User user = new User();
//        user.setPhoneNumber("55427907");
//        Response response = target("/users/updatePhoneNumber").request().put(Entity.json(user), Response.class);
//        assertEquals(400, response.getStatus());
//    }
//
//    @Test
//    public void testDeleteUser_Success() {
//        Response response = target("/users/deleteUser").request().put(Entity.json(user1), Response.class);
//        assertEquals(200, response.getStatus());
//        Response responseGetUser = target("/users/getUser/paplu@gmail.com").request().get(Response.class);
//        String json = responseGetUser.readEntity(String.class);
//        assertTrue(json.contains("10003"));
//    }
//
//    @Test
//    public void testDeleteUser_UserNotFound() {
//        user1.setEmailID("lajwanti@gmail.com");
//        Response response = target("/users/deleteUser").request().put(Entity.json(user1), Response.class);
//        assertEquals(404, response.getStatus());
//    }
//
//    @Test
//    public void testDeleteByIdUser_UserNotFound() {
//        Response response = target("/users/deleteById/234").request().delete();
//        assertEquals(404, response.getStatus());
//
//    }
//
//    @Test
//    public void testUpdatePassword_Success() {
//        String origPassword = user1.getPassword();
//        user1.setPassword("Kuttekidoom123#");
//        Response response = target("/users/updatePassword").request().put(Entity.json(user1), Response.class);
//        assertEquals(200, response.getStatus());
//        assertTrue(!response.readEntity(String.class).contains(origPassword));
//    }
//
//    @Test
//    public void testUpdatePassword_InvalidPassword() {
//        String origPassword = user1.getPassword();
//        user1.setPassword("lala llaal#");
//        Response response = target("/users/updatePassword").request().put(Entity.json(user1), Response.class);
//        String actualJson = response.readEntity(String.class);
//        assertEquals(400, response.getStatus());
//        assertTrue(!actualJson.contains(origPassword));
//
//    }
//
//    @Test
//    public void testUpdateUserName_UserValidationException() {
//        user1.setPhoneNumber("lalalal");
//        Response response = target("/users/updateUserName").request().put(Entity.json(user1), Response.class);
//        assertEquals(400, response.getStatus());
//        String json = response.readEntity(String.class);
//
//
//    }
//
//    @Test
//    public void testUpdateUserPhoneNumber_UserValidationException() {
//        user1.setPhoneNumber("lalalal");
//        Response response = target("/users/updatePhoneNumber").request().put(Entity.json(user1), Response.class);
//        assertEquals(400, response.getStatus());
//        String json = response.readEntity(String.class);
//
//    }
//
//    @Test
//    public void testUpdateUserPassword_UserValidationException() {
//        user1.setPhoneNumber("lalalal");
//        Response response = target("/users/updatePassword").request().put(Entity.json(user1), Response.class);
//        assertEquals(400, response.getStatus());
//        String json = response.readEntity(String.class);
//
//
//    }
//
//    @Test
//    public void testDeleteUser_UserValidationException() {
//        user1.setPhoneNumber("lalalal");
//        Response response = target("/users/deleteUser").request().put(Entity.json(user1), Response.class);
//        assertEquals(400, response.getStatus());
//        String json = response.readEntity(String.class);
//
//
//    }
//
//    @Test
//    public void testUpdateUserPassword_UserNotFound() {
//        user1.setEmailID("blahblah@talegaon.com");
//        Response response = target("/users/updatePassword").request().put(Entity.json(user1), Response.class);
//        assertEquals(404, response.getStatus());
//    }
//
//    //	@Test
//    //	public void testUserLogin_Success() {
//    //
//    //		User user3 = new User();
//    //		user3.setUserID(3);
//    //		user3.setEmailID("pritesh@gmail.com");
//    //		user3.setFirstName("Ram Gopal");
//    //		user3.setLastName("Bajaj");
//    //		user3.setPassword("Champak123#");
//    //		user3.setStatusCodeID(10001); //active
//    //		user3.setCreatedBy(1);
//    //		user3.setLastModifiedBy(1);
//    //
//    //		target("/users/createUser").request().post(Entity.json(user3), Response.class);
//    //
//    //		Response response = target("/users/login").request().put(Entity.json(user3), Response.class);
//    //		assertEquals(200, response.getStatus());
//    //		ResponseJson authToken = response.readEntity(ResponseJson.class);
//    //		assertTrue(!new Gson().toJson(authToken).contains("null"));
//    //	}
//
//    @Test
//    public void testUserLogin_UserInactive() {
//        user1.setStatusCodeID(UserStatusCode.PENDING.getStatusCode());
//        Response response = target("/users/login").request().put(Entity.json(user1), Response.class);
//        assertEquals(401, response.getStatus());
//        ResponseJson authToken = response.readEntity(ResponseJson.class);
//        assertTrue(!new Gson().toJson(authToken).contains("null"));
//    }
//
//    @Test
//    public void testUserLogin_UserDeleted() {
//        user1.setStatusCodeID(UserStatusCode.DELETED.getStatusCode());
//        Response response = target("/users/login").request().put(Entity.json(user1), Response.class);
//        assertEquals(401, response.getStatus());
//        ResponseJson authToken = response.readEntity(ResponseJson.class);
//        assertTrue(!new Gson().toJson(authToken).contains("null"));
//    }
//
//
//    @Test
//    public void testUserLogin_Fail() throws JSONException {
//        user1.setPassword("champak");
//        String expectedJson = "{\r\n  \"responseMessage\" : \"User login failed. Go home!\",\r\n  \"isSuccess\" : false,\r\n  \"object\" : { }\r\n}";
//        Response response = target("/users/login").request().put(Entity.json(user1), Response.class);
//        assertEquals(401, response.getStatus());
//        String authToken = response.readEntity(String.class);
//
//
//    }
//
//    @Test
//    public void testVerificationURL_Success() {
//
//        UserAuthentication userAuth = userAuthenticationDAO.findByUserID(1);
//        String verificationToken = userAuth.getVerificationToken();
//        String url = "/users/verify/";
//        Response response = target(url + verificationToken).request().get();
//        String json = response.readEntity(String.class);
//        System.out.println(json);
////        assertTrue(json.contains("verification was a success"));
//        userAuth = userAuthenticationDAO.findByUserID(1);
//        assertTrue(10001==userAuth.getUser().getStatusCodeID());
//    }
//
//    @Test
//    public void testVerificationURL_TokenExpired() {
//
//        UserAuthentication userAuth = userAuthenticationDAO.findByUserID(1);
//        userAuth.setVerificationTokenGenerationTime(new DateTime().minusDays(10).toDate());
//        userAuthenticationDAO.update(userAuth);
//
//        String verificationToken = userAuth.getVerificationToken();
//        String url = "/users/verify/";
//        Response response = target(url + verificationToken).request().get();
//        String json = response.readEntity(String.class);
//        System.out.println(json);
//
//
//        userAuth = userAuthenticationDAO.findById(1);
//        String newToken = userAuth.getVerificationToken();
//        assertNotNull(newToken);
//        assertNotEquals(newToken, verificationToken);
//        System.out.println("oldToken:: " + verificationToken + " newToken:: " + newToken);
//        assertTrue(10002==userAuth.getUser().getStatusCodeID());
//    }
//
//    @Test
//    public void testVerificationToken_UserAlreadyVerified() {
//
//        UserAuthentication userAuth = userAuthenticationDAO.findByUserID(1);
//        String verificationToken = userAuth.getVerificationToken();
//        String url = "/users/verify/";
//        Response response = target(url + verificationToken).request().get();
//        String json = response.readEntity(String.class);
//        System.out.println(json);
////        assertTrue(json.contains("verification was a success"));
//        userAuth = userAuthenticationDAO.findByUserID(1);
//        assertTrue(10001==userAuth.getUser().getStatusCodeID());
//
//        response = target(url + verificationToken).request().get();
//        json = response.readEntity(String.class);
//
//    }
//
//    @Test
//    public void testVerificationURL_Failure() {
//        String url = "/users/verify/";
//        Response response = target(url + "1234").request().get();
//        String json = response.readEntity(String.class);
//        System.out.println(json);
//        assertTrue(json.contains("2-02-029"));
//    }
//
//
//    @Test
//    public void testGetUserByEmailID_Success() {
//
//        Gson gson = new Gson();
//        String expectedJson = gson.toJson(user1);
//        System.out.println("Expected json " + expectedJson);
//        Response response = target("/users/getUser/paplu@gmail.com").request().get();
//        assertEquals(response.getStatus(), 200);
//        String actualJson = response.readEntity(String.class);
//        System.out.println("Actual Json " + actualJson);
//    }
//
//    @Test
//    public void testGetUserByEmailID_Fail() {
//
//        Gson gson = new Gson();
//        String expectedJson = gson.toJson(user1);
//        System.out.println("Expected json " + expectedJson);
//        Response response = target("/users/getUser/ghatotkach@gmail.com").request().get();
//        assertEquals(response.getStatus(), 404);
//        String actualJson = response.readEntity(String.class);
//        System.out.println("Actual Json " + actualJson);
//    }
//
//
//    @Override
//    protected Application configure() {
//        ResourceConfig rc = new ResourceConfig(UserProfileWebServiceImpl.class);
//        rc.property("contextConfigLocation", "classpath*:TestApplication-context.xml");
//        return rc;
//
//    }
//
//    public void setUserAuthenticationDAO(UserAuthDAO userAuthenticationDAO) {
//        this.userAuthenticationDAO = userAuthenticationDAO;
//    }
//
//
//    public void setUserDAO(UserDAO userDAO) {
//        this.userDAO = userDAO;
//    }
//
//
//}
