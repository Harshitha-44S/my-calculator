import java.util.stream.Stream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SaastechXPathTest {
    private WebDriver driver;

    static Stream<String> browsers() {
        return Stream.of("chrome", "firefox", "edge");
    }

    @AfterEach
    void tearDown() {
        if (driver != null) driver.quit();
    }

    @ParameterizedTest(name = "Saastech XPath navigation in {0}")
    @MethodSource("browsers")
    void saastechNavigation(String browser) {
        driver = BrowserDrivers.create(browser);
        driver.get("https://saastech.com/");
        driver.findElement(By.xpath("//a[contains(text(),'Home')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'About Us')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Software')]")).click();
        driver.findElement(By.xpath("//a[contains(text(),'Hardware')]")).click();
    }
}
