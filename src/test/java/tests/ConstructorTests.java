package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTests extends BaseTest {

    @Test
    @DisplayName("Переход в раздел «Булки»")
    @Description("Проверка, что при клике на вкладку «Булки» открывается соответствующий раздел")
    public void bunsSectionOpens() {
        mainPage.navigateToSauces();  // сначала уходим с булок
        mainPage.navigateToBuns();
        assertTrue(mainPage.isBunsSectionVisible());
    }

    @Test
    @DisplayName("Переход в раздел «Соусы»")
    @Description("Проверка, что при клике на вкладку «Соусы» открывается соответствующий раздел")
    public void saucesSectionOpens() {
        mainPage.navigateToSauces();
        assertTrue(mainPage.isSaucesSectionVisible());
    }

    @Test
    @DisplayName("Переход в раздел «Начинки»")
    @Description("Проверка, что при клике на вкладку «Начинки» открывается соответствующий раздел")
    public void fillingsSectionOpens() {
        mainPage.navigateToFillings();
        assertTrue(mainPage.isFillingsSectionVisible());
    }
}