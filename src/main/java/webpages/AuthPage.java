package webpages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class AuthPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By emailField = By.cssSelector("input[name='name']");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By submitBtn = By.xpath(".//button[text()='Войти']");
    private final By registerLink = By.cssSelector("a[href='/register']");
    private final By forgotPwdLink = By.cssSelector("a[href*='forgot-password']");
    private final By orderBtn = By.xpath(".//button[text()='Оформить заказ']");
    private final By loginHeader = By.xpath(".//h2[text()='Вход']");

    public AuthPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввести email: {email}")
    public void typeEmail(String email) {
        WebElement emailElement = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        emailElement.clear();
        emailElement.sendKeys(email);
    }

    @Step("Ввести пароль")
    public void typePassword(String password) {
        WebElement passwordElement = wait.until(ExpectedConditions.elementToBeClickable(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }

    @Step("Нажать кнопку «Войти»")
    public void clickSubmitBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(submitBtn)).click();
    }

    @Step("Перейти к регистрации")
    public void goToRegistration() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }

    @Step("Перейти к восстановлению пароля")
    public void goToPasswordRecovery() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPwdLink)).click();
    }

    @Step("Ожидание загрузки страницы логина")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginHeader));
    }

    @Step("Ожидание загрузки главной страницы после авторизации")
    public void waitForMainPageAfterAuth() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(orderBtn));
    }
}