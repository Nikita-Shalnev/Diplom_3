package webpages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PasswordRecoveryPage {
    private final WebDriver driver;

    // Локаторы
    private final By emailField = By.cssSelector("input[name='name']");
    private final By recoverBtn = By.cssSelector("form button");
    private final By loginLink = By.cssSelector("a[href='/login']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Ввести email: {email}")
    public void typeEmail(String email) {
        var field = driver.findElement(emailField);
        field.clear();
        field.sendKeys(email);
    }

    @Step("Нажать кнопку «Восстановить»")
    public void clickRecoverBtn() {
        driver.findElement(recoverBtn).click();
    }

    @Step("Перейти по ссылке «Войти»")
    public void clickLoginLink() {
        driver.findElement(loginLink).click();
    }
}