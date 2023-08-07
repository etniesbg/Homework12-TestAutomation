package ui.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

public class HomePage extends BasePage {
    private static final String URL = "/home";
    private static final By userPanelLocator = By.cssSelector(".userpanel-header");
    private static final By itemsMenuLocator = By.id("tabs_objects");
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void navigate(){
        navigate(URL);
    }

    /**
     * Navigating to Items page from main menu
     */
    public void clickItemsMenu(){
        click(itemsMenuLocator, "Items tab from main menu");
    }

    public String getLoggedUser(){
        return getText(userPanelLocator);
    }
}
