import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AirlinePageTest {
    private WebDriver driver;

    @BeforeTest
    public void setup() {
        System.setProperty("webdriver.chrome.driver","chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown() {
        driver.close();
    }

    @Test
    public void addDeleteAirline() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Авиалинии")).click();
        long start_size = driver.findElements(By.cssSelector("p")).size();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("name")).sendKeys("тестовая");
        driver.findElement(By.id("add")).click();
        long after_add_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size + 1, after_add_size);
        driver.findElement(By.linkText("тестовая")).click();
        driver.findElement(By.id("delete")).click();
        long after_delete_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size, after_delete_size);
    }

    @Test
    public void updateAirline() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Авиалинии")).click();
        driver.findElement(By.linkText("airline_1")).click();
        long start_size = driver.findElements(By.cssSelector("p")).size();
        driver.findElement(By.id("update")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("тестовая");
        driver.findElement(By.id("update")).click();
        driver.findElement(By.id("update")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("airline_1");
        driver.findElement(By.id("update")).click();
        long after_update_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size, after_update_size);
    }

}