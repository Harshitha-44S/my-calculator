import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SaastechTest {
    private WebDriver driver;

    static Stream<String> browsers() {
        return Stream.of("chrome", "firefox", "edge");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @ParameterizedTest(name = "Saastech CSS navigation in {0}")
    @MethodSource("browsers")
    void saastechNavigation(String browser) {
        driver = BrowserDrivers.create(browser);
        driver.get("https://saastech.com/");
        driver.manage().window().setSize(new org.openqa.selenium.Dimension(1814, 901));
        driver.findElement(By.cssSelector(".active > a")).click();
        driver.findElement(By.cssSelector("li:nth-child(2) > .animsition-link")).click();
        driver.findElement(By.cssSelector("li:nth-child(3) > .animsition-link")).click();
        driver.findElement(By.cssSelector("li:nth-child(4) > .animsition-link")).click();
    }
}
