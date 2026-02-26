package tests;

import api.UserApiClient;
import config.AppConfig;
import data.UserData;
import data.UserDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.apache.http.HttpStatus.SC_OK;

public class AuthTests extends BaseTest {
    private UserData testUser;
    private String authToken;

    @Before
    public void prepareUser() {
        testUser = UserDataFactory.createValidUser();
        Response createResponse = UserApiClient.createUser(testUser);
        assertEquals(SC_OK, createResponse.statusCode());

        Response loginResponse = UserApiClient.loginUser(testUser);
        assertEquals(SC_OK, loginResponse.statusCode());
        authToken = loginResponse.path("accessToken");
    }

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной")
    @Description("Пользователь вводит корректные данные и входит в систему через главную страницу")
    public void loginViaMainPageBtn() {
        mainPage.clickSignInBtn();
        authPage.waitForPageLoad();
        authPage.typeEmail(testUser.getEmail());
        authPage.typePassword(testUser.getPassword());
        authPage.clickSubmitBtn();
        authPage.waitForMainPageAfterAuth();

        String currentUrl = driver.getCurrentUrl();
        assertTrue("Не удалось войти на главную страницу",
                currentUrl.equals(AppConfig.BASE_URL + "/") || currentUrl.equals(AppConfig.BASE_URL));
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Пользователь входит в систему через кнопку в шапке сайта")
    public void loginViaAccountBtn() {
        mainPage.clickAccountBtn();
        authPage.waitForPageLoad();
        authPage.typeEmail(testUser.getEmail());
        authPage.typePassword(testUser.getPassword());
        authPage.clickSubmitBtn();
        authPage.waitForMainPageAfterAuth();

        String currentUrl = driver.getCurrentUrl();
        assertTrue("Не удалось войти на главную страницу",
                currentUrl.equals(AppConfig.BASE_URL + "/") || currentUrl.equals(AppConfig.BASE_URL));
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Пользователь переходит на страницу регистрации и входит через ссылку «Войти»")
    public void loginViaRegistrationForm() {
        mainPage.clickAccountBtn();
        authPage.goToRegistration();
        registrationPage.waitForPageLoad();
        registrationPage.clickLoginLink();
        authPage.waitForPageLoad();
        authPage.typeEmail(testUser.getEmail());
        authPage.typePassword(testUser.getPassword());
        authPage.clickSubmitBtn();
        authPage.waitForMainPageAfterAuth();

        String currentUrl = driver.getCurrentUrl();
        assertTrue("Не удалось войти на главную страницу",
                currentUrl.equals(AppConfig.BASE_URL + "/") || currentUrl.equals(AppConfig.BASE_URL));
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    @Description("Пользователь переходит на страницу восстановления пароля и входит через ссылку «Войти»")
    public void loginViaPasswordRecoveryForm() {
        mainPage.clickAccountBtn();
        authPage.goToPasswordRecovery();
        recoveryPage.clickLoginLink();
        authPage.waitForPageLoad();
        authPage.typeEmail(testUser.getEmail());
        authPage.typePassword(testUser.getPassword());
        authPage.clickSubmitBtn();
        authPage.waitForMainPageAfterAuth();

        String currentUrl = driver.getCurrentUrl();
        assertTrue("Не удалось войти на главную страницу",
                currentUrl.equals(AppConfig.BASE_URL + "/") || currentUrl.equals(AppConfig.BASE_URL));
    }
}