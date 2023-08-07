package ui;

import api.ItemAPI;
import api.LoginAPI;
import api.dto.Credentials;
import io.qameta.allure.Feature;
import io.qameta.allure.Issue;
import io.qameta.allure.Link;
import org.junit.jupiter.api.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import ui.pages.HomePage;
import ui.pages.ItemPage;
import ui.pages.LoginPage;

import java.time.Duration;

@Tag("item")
@Feature("ITEM")
public class ItemPageTest {
    private WebDriver driver;
    private String token;
    private static final String BASE_URL = "https://st2016.inv.bg";
    private static final long WAIT = 5;

    @BeforeEach
    @Disabled("Disabled because of bg see link bellow")
    @Issue("https://pragmaticbg.atlassiat.net")
    public void beforeEachTest(TestInfo testInfo) {
        //This code will run before each test method
        LoginAPI loginAPI = new LoginAPI("");
        Credentials credentials = new Credentials("karamfilovs@gmail.com", "111111", "st2016");
        token = loginAPI.obtainToken(credentials);
        ChromeOptions options = new ChromeOptions(); //Headless options
        options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
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
    @Tag("ui")
    @Disabled
    @Issue("https://bug.com")
    @DisplayName("Can delete existing item")
    public void canDeleteExistingItem(){

    }

    @Test
    @Link("https://pragmaticbg.atlassiat.net")
    @DisplayName("Can create item")
    public void canCreateItem(){
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login("karamfilovs@gmail.com", "111111");
        HomePage homePage = new HomePage(driver);
        homePage.clickItemsMenu();
        ItemPage itemPage = new ItemPage(driver);
        Assertions.assertEquals("Артикули",itemPage.pageHeading());
        //Create item via UI
        itemPage.createItem("QAComplete27");
        //Verify success message
        Assertions.assertEquals("Артикулът е добавен успешно.", itemPage.successMessage());
        //Delete item via API
        ItemAPI itemAPI = new ItemAPI(token);
        itemAPI.deleteAll();
        }


    @Test
    @Tag("u")
    @DisplayName("Correct message is displayed when no items exist")
    public void correctMessageIsDisplayedWhenNoItemsExist(){
        //Make sure system contains no items
        ItemAPI itemAPI = new ItemAPI(token);
        itemAPI.deleteAll();
        //Login UI
        LoginPage loginPage = new LoginPage(driver);
        loginPage.navigate();
        loginPage.login("karamfilovs@gmail.com", "111111");
        //Navigate to Item page
        HomePage homePage = new HomePage(driver);
        homePage.clickItemsMenu();
        //Check navigation was successful
        ItemPage itemPage = new ItemPage(driver);
        Assertions.assertEquals("Артикули", itemPage.pageHeading());
        //Check empty list message is displayed
        Assertions.assertEquals("Не са намерени артикули, отговарящи на зададените критерии.", itemPage.emptyListMessage());

    }

    @Test
    @DisplayName("Can search item")
    public void canSearchItem(){

    }
}
