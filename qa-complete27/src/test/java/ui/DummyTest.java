package ui;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

@Tag("calculator")
public class DummyTest {

    @Test
    @Disabled("This is blocked because of https://pragmatcbg.atlassian.net/INV-12313")
    @Tag("ui")
    @DisplayName("Can sum positive numbers")
    public void canSumNumbers(){
        Assertions.assertEquals(4, 2+2);
    }

    @Test
    @DisplayName("Can navigate to Login page")
    public void canNavigateToLoginPage(){
        WebDriver driver = new FirefoxDriver();
        driver.get("https://st2016.inv.bg");
        WebElement heading = driver.findElement(By.tagName("h1"));
        Assertions.assertEquals("Вход в inv.bg", heading.getText());
        driver.quit();
    }
}
