import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/** Optional checks for a separate practice site; run explicitly when that site is reachable. */
class PracticeSiteChecks {
    private WebDriver driver;

    static Stream<String> browsers() {
        return Stream.of("chrome", "firefox", "edge");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @ParameterizedTest(name = "Practice page controls in {0}")
    @MethodSource("browsers")
    void test3(String browser) {
        driver = BrowserDrivers.create(browser);
        driver.get("https://www.letskodeit.com/practice");

        driver.findElement(By.id("bmwradio")).click();
        assertTrue(driver.findElement(By.id("bmwradio")).isSelected());
        assertFalse(driver.findElement(By.id("benzradio")).isSelected());
        assertFalse(driver.findElement(By.id("hondaradio")).isSelected());

        driver.findElement(By.id("bmwcheck")).click();
        assertTrue(driver.findElement(By.id("bmwcheck")).isSelected());

        Select cars = new Select(driver.findElement(By.id("carselect")));
        cars.selectByVisibleText("Benz");
        assertEquals("Benz", cars.getFirstSelectedOption().getText());
    }
}
