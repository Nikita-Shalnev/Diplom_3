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
    private final By signInBtn = By.xpath(".//button[text()='Войти в аккаунт']");
    private final By bunsTab = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesTab = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsTab = By.xpath(".//span[text()='Начинки']/parent::div");
    private final By pageHeader = By.xpath(".//h1[contains(text(),'Соберите бургер')]");

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
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(bunsTab));
        scrollToElement(tab);
        clickElementSafely(tab);
        try {
            Thread.sleep(500); // Небольшая пауза для анимации
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Перейти в раздел «Соусы»")
    public void navigateToSauces() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(saucesTab));
        scrollToElement(tab);
        clickElementSafely(tab);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Перейти в раздел «Начинки»")
    public void navigateToFillings() {
        WebElement tab = wait.until(ExpectedConditions.elementToBeClickable(fillingsTab));
        scrollToElement(tab);
        clickElementSafely(tab);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @Step("Проверить, что активна вкладка «Булки»")
    public boolean isBunsTabActive() {
        WebElement tab = driver.findElement(bunsTab);
        String classValue = tab.getAttribute("class");
        System.out.println("Buns tab class: " + classValue);
        return classValue != null && classValue.contains("tab_tab_type_current");
    }

    @Step("Проверить, что активна вкладка «Соусы»")
    public boolean isSaucesTabActive() {
        WebElement tab = driver.findElement(saucesTab);
        String classValue = tab.getAttribute("class");
        System.out.println("Sauces tab class: " + classValue);
        return classValue != null && classValue.contains("tab_tab_type_current");
    }

    @Step("Проверить, что активна вкладка «Начинки»")
    public boolean isFillingsTabActive() {
        WebElement tab = driver.findElement(fillingsTab);
        String classValue = tab.getAttribute("class");
        System.out.println("Fillings tab class: " + classValue);
        return classValue != null && classValue.contains("tab_tab_type_current");
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitForPageLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(pageHeader));
    }

    private void scrollToElement(WebElement element) {
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center'});", element);
    }

    private void clickElementSafely(WebElement element) {
        try {
            element.click();
        } catch (org.openqa.selenium.ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
        }
    }
}