package tests;

import config.AppConfig;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import webpages.*;
import utils.BrowserSelector;

import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    protected MainPage mainPage;
    protected AuthPage authPage;
    protected RegistrationPage registrationPage;
    protected PasswordRecoveryPage recoveryPage;

    @Before
    public void initTest() {
        driver = BrowserSelector.createDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        mainPage = new MainPage(driver);
        authPage = new AuthPage(driver);
        registrationPage = new RegistrationPage(driver);
        recoveryPage = new PasswordRecoveryPage(driver);

        driver.get(AppConfig.BASE_URL);
    }

    @After
    public void cleanup() {
        if (driver != null) {
            driver.quit();
        }
    }
}