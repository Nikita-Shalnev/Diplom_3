package webpages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegistrationPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By nameField = By.cssSelector("input[name='name']");
    private final By emailField = By.xpath(".//label[text()='Email']/following-sibling::input");
    private final By passwordField = By.cssSelector("input[type='password']");
    private final By registerBtn = By.xpath(".//button[text()='Зарегистрироваться']");
    private final By loginLink = By.cssSelector("a[href='/login']");

    // Упрощаем локатор ошибки - ищем по тексту
    private final By errorMessage = By.xpath(".//p[contains(text(),'Некорректный пароль')]");
    private final By registerHeader = By.xpath(".//h2[text()='Регистрация']");

    public RegistrationPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввести имя: {name}")
    public void typeName(String name) {
        WebElement nameElement = wait.until(ExpectedConditions.elementToBeClickable(nameField));
        nameElement.clear();
        nameElement.sendKeys(name);
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
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (org.openqa.selenium.NoSuchElementException e) {
            return false;
        }
    }

    @Step("Получить текст ошибки пароля")
    public String getPasswordErrorText() {
        return driver.findElement(errorMessage).getText();
    }

    @Step("Ожидание появления сообщения об ошибке")
    public void waitForErrorMessage() {
        try {
            Thread.sleep(2000); // Ждем появления ошибки
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
        } catch (Exception e) {
            // Если не нашли по тексту, пробуем найти по классу
            By fallbackLocator = By.xpath(".//div[contains(@class, 'input__error')]");
            wait.until(ExpectedConditions.visibilityOfElementLocated(fallbackLocator));
        }
    }

    @Step("Ожидание загрузки страницы регистрации")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(registerHeader));
    }
}