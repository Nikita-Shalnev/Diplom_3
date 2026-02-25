package tests;

import api.UserApiClient;
import config.AppConfig;
import data.UserData;
import data.UserDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AuthTests extends BaseTest {
    private UserData testUser;

    @Before
    public void prepareUser() {
        testUser = UserDataFactory.createValidUser();
        UserApiClient.createUser(testUser);
    }

    @Test
    @DisplayName("Вход через кнопку «Войти в аккаунт» на главной")
    @Description("Пользователь вводит корректные данные и входит в систему через главную страницу")
    public void loginViaMainPageBtn() {
        mainPage.clickSignInBtn();
        authPage.typeEmail(testUser.getEmail());
        authPage.typePassword(testUser.getPassword());
        authPage.clickSubmitBtn();
        authPage.waitForMainPageAfterAuth();
        assertEquals(AppConfig.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку «Личный кабинет»")
    @Description("Пользователь входит в систему через кнопку в шапке сайта")
    public void loginViaAccountBtn() {
        mainPage.clickAccountBtn();
        authPage.typeEmail(testUser.getEmail());
        authPage.typePassword(testUser.getPassword());
        authPage.clickSubmitBtn();
        authPage.waitForMainPageAfterAuth();
        assertEquals(AppConfig.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через форму регистрации")
    @Description("Пользователь переходит на страницу регистрации и входит через ссылку «Войти»")
    public void loginViaRegistrationForm() {
        mainPage.clickAccountBtn();
        authPage.goToRegistration();
        registrationPage.clickLoginLink();
        authPage.typeEmail(testUser.getEmail());
        authPage.typePassword(testUser.getPassword());
        authPage.clickSubmitBtn();
        authPage.waitForMainPageAfterAuth();
        assertEquals(AppConfig.BASE_URL, driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через форму восстановления пароля")
    @Description("Пользователь переходит на страницу восстановления пароля и входит через ссылку «Войти»")
    public void loginViaPasswordRecoveryForm() {
        mainPage.clickAccountBtn();
        authPage.goToPasswordRecovery();
        recoveryPage.clickLoginLink();
        authPage.typeEmail(testUser.getEmail());
        authPage.typePassword(testUser.getPassword());
        authPage.clickSubmitBtn();
        authPage.waitForMainPageAfterAuth();
        assertEquals(AppConfig.BASE_URL, driver.getCurrentUrl());
    }
}