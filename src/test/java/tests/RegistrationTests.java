package tests;

import config.AppConfig;
import config.ErrorMessages;
import data.UserData;
import data.UserDataFactory;
import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTests extends BaseTest {

    @Test
    @DisplayName("Успешная регистрация нового пользователя")
    @Description("Пользователь с валидными данными успешно регистрируется и может войти")
    public void validUserCanRegister() {
        UserData newUser = UserDataFactory.createValidUser();

        mainPage.clickSignInBtn();
        authPage.goToRegistration();
        registrationPage.fillForm(newUser.getName(), newUser.getEmail(), newUser.getPassword());
        registrationPage.clickRegisterBtn();

        // Ждем загрузки страницы логина
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.urlContains("/login"));

        authPage.typeEmail(newUser.getEmail());
        authPage.typePassword(newUser.getPassword());
        authPage.clickSubmitBtn();

        // Ждем загрузки главной страницы
        mainPage.waitForPageLoad();
        assertTrue(driver.getCurrentUrl().startsWith(AppConfig.BASE_URL));
    }

    @Test
    @DisplayName("Ошибка при коротком пароле")
    @Description("Пользователь с паролем менее 6 символов видит сообщение об ошибке")
    public void shortPasswordShowsError() {
        UserData invalidUser = UserDataFactory.createUserWithShortPassword();

        mainPage.clickSignInBtn();
        authPage.goToRegistration();
        registrationPage.fillForm(invalidUser.getName(), invalidUser.getEmail(), invalidUser.getPassword());
        registrationPage.clickRegisterBtn();

        // Ждем появления ошибки
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.visibilityOf(
                driver.findElement(registrationPage.getErrorMessageLocator())));

        // Проверяем, что остались на странице регистрации
        assertTrue(driver.getCurrentUrl().contains("/register"));
        assertTrue(registrationPage.isPasswordErrorVisible());
        assertEquals(ErrorMessages.INVALID_PASSWORD, registrationPage.getPasswordErrorText());
    }
}