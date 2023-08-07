package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

public class ClientPage extends BasePage {
    private static final String URL = "/";
    private static final By emailFieldLocator = By.id("loginusername");
    private static final By passwordFieldLocator = By.id("loginpassword");

    public ClientPage(WebDriver driver) {
        super(driver);
    }
}
