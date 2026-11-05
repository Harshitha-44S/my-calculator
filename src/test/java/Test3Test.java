import java.time.Duration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Test3Test {
    private WebDriver driver;

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void test3() {
        // This site can keep loading background content, so wait for the controls we use.
        driver = BrowserDrivers.create("chrome", PageLoadStrategy.NONE);
        driver.get("https://www.letskodeit.com/practice");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.elementToBeClickable(By.id("bmwradio")));

        driver.findElement(By.id("bmwradio")).click();
        assertTrue(driver.findElement(By.id("bmwradio")).isSelected());
        assertFalse(driver.findElement(By.id("benzradio")).isSelected());
        assertFalse(driver.findElement(By.id("hondaradio")).isSelected());

        driver.findElement(By.id("bmwcheck")).click();
        assertTrue(driver.findElement(By.id("bmwcheck")).isSelected());
        assertFalse(driver.findElement(By.id("benzcheck")).isSelected());
        assertFalse(driver.findElement(By.id("hondacheck")).isSelected());

        Select cars = new Select(driver.findElement(By.id("carselect")));
        cars.selectByVisibleText("Benz");
        assertEquals("Benz", cars.getFirstSelectedOption().getText());
    }
}
