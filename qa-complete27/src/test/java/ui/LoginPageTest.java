package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import ui.pages.HomePage;
import ui.pages.LoginPage;

import java.time.Duration;

@Tag("login")
public class LoginPageTest {
    private WebDriver driver;
    private static final String BASE_URL = "https://st2016.inv.bg";
    private static final long WAIT = 5;

    @BeforeEach
    public void beforeEachTest(TestInfo testInfo) {
        //This code will run before each test method
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(WAIT));
        driver.get(BASE_URL);
        driver.manage().window().maximize();
        System.out.println("Test: " + testInfo.getDisplayName()); //Printing display name of the current test
    }

    @AfterEach
    public void afterEachTest() {
        if (driver != null){
            driver.quit();
        }
    }

    @Test
    @Tag("positive")
    @DisplayName("Can login with valid credentials")
    public void canLoginWithValidCredentials() {
        //Check that user is at Login page
        WebElement heading = driver.findElement(By.xpath("//div[@id='wellcome']/h2"));
        Assertions.assertEquals("QA Ground", heading.getText());
        //Enter username/email
        System.out.println("Enter username:" + "karamfilovs@gmail.com");
        WebElement emailField = driver.findElement(By.id("loginusername"));
        emailField.sendKeys("karamfilovs@gmail.com");
        //Enter password
        System.out.println("Enter password:" + "111111");
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("111111");
        //Click Login button
        System.out.println("Click Login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        //Check that the user is logged in
        WebElement userPanel = driver.findElement(By.cssSelector(".selenium-button-userpanel"));
        Assertions.assertEquals("karamfilovs@gmail.com", userPanel.getText());
    }

    @Test
    @Tag("negative")
    @DisplayName("Cant login with invalid credentials")
    public void cantLoginWithInvalidCredentials() {
        //Check that user is at Login page
        WebElement heading = driver.findElement(By.xpath("//div[@id='wellcome']/h2"));
        Assertions.assertEquals("QA Ground", heading.getText());
        //Enter username/email
        System.out.println("Enter username:" + "karamfilovs@gmail.com");
        WebElement emailField = driver.findElement(By.id("loginusername"));
        emailField.sendKeys("karamfilovs@gmail.com");
        //Enter password
        System.out.println("Enter password:" + "1111112342");
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("1111112342");
        //Click Login button
        System.out.println("Click Login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        //Check the error message
        WebElement error = driver.findElement(By.id("error"));
        Assertions.assertEquals("Грешно потребителско име или парола. Моля, опитайте отново.", error.getText().trim());
    }

    @Test
    @Tag("negative")
    @DisplayName("Cant login with blank credentials")
    public void cantLoginWithBlankCredentials() {
        //Check that user is at Login page
        WebElement heading = driver.findElement(By.xpath("//div[@id='wellcome']/h2"));
        Assertions.assertEquals("QA Ground", heading.getText());
        //Click Login button
        System.out.println("Click Login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        //Check the error message
        WebElement error = driver.findElement(By.id("error"));
        Assertions.assertEquals("Моля, попълнете вашия email!", error.getText().trim());
    }


    @Test
    @Tag("positive")
    @DisplayName("Can login with with valid credentials and logout")
    public void canLoginAndLogout() {
        //Check that user is at Login page
        WebElement heading = driver.findElement(By.xpath("//div[@id='wellcome']/h2"));
        Assertions.assertEquals("QA Ground", heading.getText());
        //Enter username/email
        System.out.println("Enter username:" + "karamfilovs@gmail.com");
        WebElement emailField = driver.findElement(By.id("loginusername"));
        emailField.sendKeys("karamfilovs@gmail.com");
        //Enter password
        System.out.println("Enter password:" + "111111");
        WebElement passwordField = driver.findElement(By.id("loginpassword"));
        passwordField.sendKeys("111111");
        //Click Login button
        System.out.println("Click Login button");
        WebElement loginButton = driver.findElement(By.id("loginsubmit"));
        loginButton.click();
        //Check that the user is logged in
        WebElement userPanel = driver.findElement(By.cssSelector(".selenium-button-userpanel"));
        Assertions.assertEquals("karamfilovs@gmail.com", userPanel.getText());
        //Logout
        System.out.println("Click Logout");
        userPanel.click(); //Expand the 'dropdown'
        WebElement logoutLink = driver.findElement(By.cssSelector("a.selenium-button-logout"));
        logoutLink.click();
        //Check success message
        WebElement successMessage = driver.findElement(By.id("okmsg"));
        Assertions.assertEquals("Вие излязохте от акаунта си. ", successMessage.getText());
        //Check the redirect
        WebElement loginHeading = driver.findElement(By.xpath("//h1"));
        Assertions.assertEquals("Вход в inv.bg", loginHeading.getText());

    }

    @Test
    @Disabled
    @DisplayName("Can reset password")
    public void canResetPassword() {
        //Click forgotten pass link
        WebElement forgottenPassLink = driver.findElement(By.id("newpass2"));
        forgottenPassLink.click();
        //Enter valid account email
        WebElement emailField = driver.findElement(By.name("email"));
        emailField.sendKeys("karamfilovs@gmail.com");
        //Click send recover pass link button
        WebElement submitButton = driver.findElement(By.id("submit"));
        submitButton.click();
        //Check success message
        WebElement successMessage = driver.findElement(By.xpath("//div[@class='alert selenium-message alert-success sticky']"));
        Assertions.assertEquals("На e-mail адреса Ви беше изпратен линк, чрез който можете да смените паролата си.", successMessage.getText());
        //Check email inbox for new email and extract the link from the text
        //This will be implemented in the next lecture because it requires communication with external service
    }


    @Test
    @DisplayName("Can login using POM")
    public void canLoginWithValidCredentialsPOM(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login("karamfilovs@gmail.com", "111111");
        //Assert that login was successful
        HomePage homePage = new HomePage(driver);
        Assertions.assertEquals("karamfilovs@gmail.com", homePage.getLoggedUser());
    }
}
