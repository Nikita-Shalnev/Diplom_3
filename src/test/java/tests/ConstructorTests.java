package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class ConstructorTests extends BaseTest {

    @Test
    @DisplayName("Переход в раздел «Булки»")
    @Description("Проверка, что при клике на вкладку «Булки» она становится активной")
    public void bunsSectionOpens() {
        mainPage.navigateToFillings();
        mainPage.navigateToBuns();
        boolean isActive = mainPage.isBunsTabActive();
        System.out.println("Buns tab active after navigation: " + isActive);
        assertTrue("Вкладка 'Булки' не стала активной после клика", isActive);
    }

    @Test
    @DisplayName("Переход в раздел «Соусы»")
    @Description("Проверка, что при клике на вкладку «Соусы» она становится активной")
    public void saucesSectionOpens() {
        mainPage.navigateToBuns();
        mainPage.navigateToSauces();
        boolean isActive = mainPage.isSaucesTabActive();
        System.out.println("Sauces tab active after navigation: " + isActive);
        assertTrue("Вкладка 'Соусы' не стала активной после клика", isActive);
    }

    @Test
    @DisplayName("Переход в раздел «Начинки»")
    @Description("Проверка, что при клике на вкладку «Начинки» она становится активной")
    public void fillingsSectionOpens() {
        mainPage.navigateToBuns();
        mainPage.navigateToFillings();
        boolean isActive = mainPage.isFillingsTabActive();
        System.out.println("Fillings tab active after navigation: " + isActive);
        assertTrue("Вкладка 'Начинки' не стала активной после клика", isActive);
    }
}