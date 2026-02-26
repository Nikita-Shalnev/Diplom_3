package webpages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PasswordRecoveryPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By emailField = By.cssSelector("input[name='name']");
    private final By recoverBtn = By.xpath(".//button[text()='Восстановить']");
    private final By loginLink = By.cssSelector("a[href='/login']");

    public PasswordRecoveryPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Ввести email: {email}")
    public void typeEmail(String email) {
        var field = wait.until(ExpectedConditions.elementToBeClickable(emailField));
        field.clear();
        field.sendKeys(email);
    }

    @Step("Нажать кнопку «Восстановить»")
    public void clickRecoverBtn() {
        wait.until(ExpectedConditions.elementToBeClickable(recoverBtn)).click();
    }

    @Step("Перейти по ссылке «Войти»")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
}