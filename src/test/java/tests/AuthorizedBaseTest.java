package tests;

import api.UserApiClient;
import data.UserData;
import data.UserDataFactory;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;

import static org.apache.http.HttpStatus.SC_OK;
import static org.junit.Assert.assertEquals;

public class AuthorizedBaseTest extends BaseTest {
    protected UserData testUser;
    private String authToken;

    @Before
    public void initAuthorizedUser() {
        testUser = UserDataFactory.createValidUser();

        Response createResponse = UserApiClient.createUser(testUser);
        assertEquals(SC_OK, createResponse.statusCode());

        Response loginResponse = UserApiClient.loginUser(testUser);
        assertEquals(SC_OK, loginResponse.statusCode());
        authToken = loginResponse.path("accessToken");

        mainPage.clickSignInBtn();
        authPage.typeEmail(testUser.getEmail());
        authPage.typePassword(testUser.getPassword());
        authPage.clickSubmitBtn();
        authPage.waitForMainPageAfterAuth();
    }

    @After
    public void cleanupUser() {
        if (authToken != null && !authToken.isEmpty()) {
            UserApiClient.deleteUser(authToken);
        }
    }
}