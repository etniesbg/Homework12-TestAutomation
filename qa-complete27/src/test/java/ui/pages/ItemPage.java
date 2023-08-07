package ui.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

public class ItemPage extends BasePage {
    private static final String URL = "/objects";
    private static final By pageHeadingLocator = By.xpath("//div[@id='headline2']/h2");
    private static final By nameLocator = By.name("name");
    private static final By submitButtonLocator = By.name("do_submit");
    private static final By successMessageLocator = By.id("okmsg");
    private static final By emptyListMessageLocator = By.id("emptylist");
    private static final By createButtonLocator = By.cssSelector(".newbtn.selenium-add-item");

    public ItemPage(WebDriver driver) {
        super(driver);
    }

    public void navigate(){
        navigate();
    }

    public String pageHeading(){
        return getText(pageHeadingLocator);
    }

    public String successMessage(){
        return getText(successMessageLocator);
    }

    public String emptyListMessage(){
        return getText(emptyListMessageLocator);
    }

    public void clickCreateItemButton(){
        click(createButtonLocator, "Clicking Create Button");
    }

    public void enterName(String name){
        type(nameLocator, name);
    }

    public void save(){
        click(submitButtonLocator, "Save button");
    }

    public void createItem(String name){
        clickCreateItemButton();
        enterName(name);
        save();
    }
}
