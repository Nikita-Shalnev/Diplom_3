package webpages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By nameField = By.cssSelector("input[name='name']");
    private final By emailField = By.xpath("//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By registerBtn = By.cssSelector("form button");
    private final By loginLink = By.cssSelector("a[href='/login']");
    private final By errorMessage = By.xpath("//form//fieldset[3]//div[.//input[@type='password']]//p");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввести имя: {name}")
    public void typeName(String name) {
        wait.until(ExpectedConditions.elementToBeClickable(nameField)).clear();
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Ввести email: {email}")
    public void typeEmail(String email) {
        wait.until(ExpectedConditions.elementToBeClickable(emailField)).clear();
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль")
    public void typePassword(String password) {
        wait.until(ExpectedConditions.elementToBeClickable(passwordField)).clear();
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Заполнить все поля регистрации")
    public void fillForm(String name, String email, String password) {
        typeName(name);
        typeEmail(email);
        typePassword(password);
    }

    @Step("Нажать кнопку «Зарегистрироваться»")
    public void clickRegisterBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(registerBtn)).click();
    }

    @Step("Перейти по ссылке «Войти»")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }

    @Step("Проверить отображение ошибки пароля")
    public boolean isPasswordErrorVisible() {
        return driver.findElement(errorMessage).isDisplayed();
    }

    @Step("Получить текст ошибки пароля")
    public String getPasswordErrorText() {
        return driver.findElement(errorMessage).getText();
    }

    public By getErrorMessageLocator() {
        return errorMessage;
    }
}