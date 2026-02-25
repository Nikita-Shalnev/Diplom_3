package webpages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Локаторы
    private final By accountBtn = By.cssSelector("a[href='/account']");
    private final By signInBtn = By.xpath("//button[.='Войти в аккаунт']");
    private final By bunsTab = By.xpath("//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath("//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath("//span[text()='Начинки']/parent::div");
    private final By bunsHeader = By.xpath("//h2[text()='Булки']");
    private final By saucesHeader = By.xpath("//h2[text()='Соусы']");
    private final By fillingsHeader = By.xpath("//h2[text()='Начинки']");
    private final By pageHeader = By.xpath("//*[contains(text(),'Соберите бургер')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажать кнопку «Личный кабинет»")
    public void clickAccountBtn() {
        driver.findElement(accountBtn).click();
    }

    @Step("Нажать кнопку «Войти в аккаунт»")
    public void clickSignInBtn() {
        driver.findElement(signInBtn).click();
    }

    @Step("Перейти в раздел «Булки»")
    public void navigateToBuns() {
        WebElement tab = driver.findElement(bunsTab);
        scrollToElement(tab);
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
    }

    @Step("Перейти в раздел «Соусы»")
    public void navigateToSauces() {
        WebElement tab = driver.findElement(saucesTab);
        scrollToElement(tab);
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
    }

    @Step("Перейти в раздел «Начинки»")
    public void navigateToFillings() {
        driver.findElement(fillingsTab).click();
    }

    @Step("Проверить отображение раздела «Булки»")
    public boolean isBunsSectionVisible() {
        return driver.findElement(bunsHeader).isDisplayed();
    }

    @Step("Проверить отображение раздела «Соусы»")
    public boolean isSaucesSectionVisible() {
        return driver.findElement(saucesHeader).isDisplayed();
    }

    @Step("Проверить отображение раздела «Начинки»")
    public boolean isFillingsSectionVisible() {
        return driver.findElement(fillingsHeader).isDisplayed();
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element);
    }
}