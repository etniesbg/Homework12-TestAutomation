package ui.pages;

import io.qameta.allure.Feature;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ui.core.BasePage;

@Feature("Login feature")
public class LoginPage extends BasePage {
    private static final String URL = "/";
    private static final By emailFieldLocator = By.id("loginusername");
    private static final By passwordFieldLocator = By.id("loginpassword");
    private static final By loginButtonLocator = By.id("loginsubmit");
    private static final By companyNameLocator = By.xpath("//div[@id='wellcome']/h2");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Step("Navigate to Login page")
    public void navigate(){
        navigate(URL);
    }

    public void login(String email, String password){
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
    }

    public String getCompanyName(){
        return getText(companyNameLocator);
    }

    @Step("Enter password {password}")
    private void enterPassword(String password){
        type(passwordFieldLocator, password);
    }

    @Step("Enter email {email}")
    private void enterEmail(String email){
        type(emailFieldLocator, email);

    }

    @Step("Clicking Login button")
    private void clickLoginButton(){
        click(loginButtonLocator, "Login button");

    }
}
