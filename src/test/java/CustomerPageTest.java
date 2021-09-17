import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class CustomerPageTest {
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
    public void addDeleteCustomer() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Клиенты")).click();
        long start_size = driver.findElements(By.cssSelector("p")).size();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("name")).sendKeys("тестовый");
        driver.findElement(By.id("addr")).sendKeys("тестовый");
        driver.findElement(By.id("phone")).sendKeys("тестовый");
        driver.findElement(By.id("email")).sendKeys("тестовый");
        driver.findElement(By.id("add")).click();
        long after_add_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size + 1, after_add_size);
        driver.findElement(By.linkText("тестовый")).click();
        driver.findElement(By.id("delete")).click();
        long after_delete_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size, after_delete_size);
    }

    @Test
    public void updateCustomer() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Клиенты")).click();
        long start_size = driver.findElements(By.cssSelector("p")).size();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("name")).sendKeys("тестовый2");
        driver.findElement(By.id("addr")).sendKeys("тестовый2");
        driver.findElement(By.id("phone")).sendKeys("тестовый2");
        driver.findElement(By.id("email")).sendKeys("тестовый2");
        driver.findElement(By.id("add")).click();
        long after_add_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size + 1, after_add_size);
        driver.findElement(By.linkText("тестовый2")).click();
        driver.findElement(By.id("update")).click();
        driver.findElement(By.id("name")).clear();
        driver.findElement(By.id("name")).sendKeys("тестовый");
        driver.findElement(By.id("addr")).clear();
        driver.findElement(By.id("addr")).sendKeys("тестовый");
        driver.findElement(By.id("phone")).clear();
        driver.findElement(By.id("phone")).sendKeys("тестовый");
        driver.findElement(By.id("email")).clear();
        driver.findElement(By.id("email")).sendKeys("тестовый");
        driver.findElement(By.id("update")).click();
        driver.findElement(By.id("delete")).click();
        long after_delete_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size, after_delete_size);
    }

}