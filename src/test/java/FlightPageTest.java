import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class FlightPageTest {
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
    public void addDeleteFlight() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Рейсы")).click();
        long start_size = driver.findElements(By.cssSelector("p")).size();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("airlineId")).sendKeys("airline_1");
        driver.findElement(By.id("departure")).sendKeys("airp_1");
        driver.findElement(By.id("arrival")).sendKeys("airp_2");
        driver.findElement(By.id("date1")).sendKeys("11/24/2011");
        driver.findElement(By.id("date2")).sendKeys("11/24/2011");
        driver.findElement(By.id("cost")).sendKeys("1111666");
        driver.findElement(By.id("number")).sendKeys("1111666");
        driver.findElement(By.id("miles")).sendKeys("1111666");
        driver.findElement(By.id("add")).click();
        long after_add_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size + 1, after_add_size);
        driver.findElement(By.partialLinkText("1111666")).click();
        driver.findElement(By.id("delete")).click();
        long after_delete_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size, after_delete_size);
    }

    @Test
    public void updateFlight() {
        driver.get("http://localhost:8080/");
        driver.findElement(By.linkText("Рейсы")).click();
        long start_size = driver.findElements(By.cssSelector("p")).size();
        driver.findElement(By.id("add")).click();
        driver.findElement(By.id("airlineId")).sendKeys("airline_1");
        driver.findElement(By.id("departure")).sendKeys("airp_1");
        driver.findElement(By.id("arrival")).sendKeys("airp_2");
        driver.findElement(By.id("date1")).sendKeys("11/24/2011");
        driver.findElement(By.id("date2")).sendKeys("11/24/2011");
        driver.findElement(By.id("cost")).clear();
        driver.findElement(By.id("cost")).sendKeys("111177");
        driver.findElement(By.id("number")).clear();
        driver.findElement(By.id("number")).sendKeys("111177");
        driver.findElement(By.id("miles")).clear();
        driver.findElement(By.id("miles")).sendKeys("111177");
        driver.findElement(By.id("add")).click();
        long after_add_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size + 1, after_add_size);
        driver.findElement(By.partialLinkText("111177")).click();

        driver.findElement(By.id("update")).click();
        driver.findElement(By.id("airlineId")).sendKeys("airline_2");
        driver.findElement(By.id("departure")).sendKeys("airp_2");
        driver.findElement(By.id("arrival")).sendKeys("airp_1");
        driver.findElement(By.id("date1")).sendKeys("21/24/2011");
        driver.findElement(By.id("date2")).sendKeys("21/24/2011");
        driver.findElement(By.id("cost")).clear();
        driver.findElement(By.id("cost")).sendKeys("1111");
        driver.findElement(By.id("number")).clear();
        driver.findElement(By.id("number")).sendKeys("1111");
        driver.findElement(By.id("miles")).clear();
        driver.findElement(By.id("miles")).sendKeys("1111");
        driver.findElement(By.id("update")).click();

        driver.findElement(By.id("delete")).click();
        long after_delete_size = driver.findElements(By.cssSelector("p")).size();
        Assert.assertEquals(start_size, after_delete_size);
    }

}