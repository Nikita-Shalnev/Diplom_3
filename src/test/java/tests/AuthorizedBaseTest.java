package tests;

import api.UserApiClient;
import data.UserData;
import data.UserDataFactory;
import org.junit.After;
import org.junit.Before;

public class AuthorizedBaseTest extends BaseTest {
    protected UserData testUser;
    private String authToken;

    @Before
    public void initAuthorizedUser() {
        testUser = UserDataFactory.createValidUser();
        UserApiClient.createUser(testUser);
        authToken = UserApiClient.getAuthToken(testUser);

        mainPage.clickSignInBtn();
        authPage.typeEmail(testUser.getEmail());
        authPage.typePassword(testUser.getPassword());
        authPage.clickSubmitBtn();
        authPage.waitForMainPageAfterAuth();
    }

    @After
    public void cleanupUser() {
        UserApiClient.deleteUser(authToken);
    }
}