package tests;

import api.UserApiClient;
import config.AppConfig;
import config.ErrorMessages;
import data.UserData;
import data.UserDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.apache.http.HttpStatus.SC_OK;

public class RegistrationTests extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Пользователь с валидными данными успешно регистрируется и может войти")
    public void validUserCanRegister() {
        UserData newUser = UserDataFactory.createValidUser();

        mainPage.clickSignInBtn();
        authPage.goToRegistration();
        registrationPage.waitForPageLoad();

        registrationPage.typeName(newUser.getName());
        registrationPage.typeEmail(newUser.getEmail());
        registrationPage.typePassword(newUser.getPassword());
        registrationPage.clickRegisterBtn();

        authPage.waitForPageLoad();

        authPage.typeEmail(newUser.getEmail());
        authPage.typePassword(newUser.getPassword());
        authPage.clickSubmitBtn();

        mainPage.waitForPageLoad();

        String currentUrl = driver.getCurrentUrl();
        assertTrue("Не удалось войти после регистрации",
                currentUrl.equals(AppConfig.BASE_URL + "/") || currentUrl.equals(AppConfig.BASE_URL));

        Response loginResponse = UserApiClient.loginUser(newUser);
        if (loginResponse.statusCode() == SC_OK) {
            String token = loginResponse.path("accessToken");
            UserApiClient.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Ошибка при коротком пароле")
    @Description("Пользователь с паролем менее 6 символов видит сообщение об ошибке")
    public void shortPasswordShowsError() {
        UserData invalidUser = UserDataFactory.createUserWithShortPassword();

        mainPage.clickSignInBtn();
        authPage.goToRegistration();
        registrationPage.waitForPageLoad();

        registrationPage.typeName(invalidUser.getName());
        registrationPage.typeEmail(invalidUser.getEmail());
        registrationPage.typePassword(invalidUser.getPassword());

        System.out.println("Нажимаем кнопку регистрации с паролем: " + invalidUser.getPassword());
        registrationPage.clickRegisterBtn();

        // Ждем появления ошибки
        System.out.println("Ожидаем появление ошибки...");
        registrationPage.waitForErrorMessage();

        System.out.println("Проверяем видимость ошибки");
        assertTrue("Сообщение об ошибке не отображается",
                registrationPage.isPasswordErrorVisible());

        String errorText = registrationPage.getPasswordErrorText();
        System.out.println("Текст ошибки: " + errorText);

        assertEquals("Текст ошибки не совпадает",
                ErrorMessages.INVALID_PASSWORD,
                errorText);

        System.out.println("Проверяем URL: " + driver.getCurrentUrl());
        assertTrue("Должны остаться на странице регистрации",
                driver.getCurrentUrl().contains("/register"));
    }
}