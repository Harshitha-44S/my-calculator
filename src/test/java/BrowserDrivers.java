import java.time.Duration;
import java.util.Locale;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/** Creates consistently configured drivers for the supported desktop browsers. */
public final class BrowserDrivers {
    private BrowserDrivers() {}

    public static WebDriver create(String browserName) {
        return create(browserName, PageLoadStrategy.EAGER);
    }

    public static WebDriver create(String browserName, PageLoadStrategy pageLoadStrategy) {
        String browser = browserName.toLowerCase(Locale.ROOT);
        WebDriver driver = switch (browser) {
            case "chrome" -> new ChromeDriver(chromeOptions(pageLoadStrategy));
            case "firefox" -> new FirefoxDriver(firefoxOptions(pageLoadStrategy));
            case "edge" -> new EdgeDriver(edgeOptions(pageLoadStrategy));
            default -> throw new IllegalArgumentException("Unsupported browser: " + browserName);
        };
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(30));
        return driver;
    }

    private static ChromeOptions chromeOptions(PageLoadStrategy pageLoadStrategy) {
        ChromeOptions options = new ChromeOptions();
        options.setPageLoadStrategy(pageLoadStrategy);
        return options;
    }

    private static FirefoxOptions firefoxOptions(PageLoadStrategy pageLoadStrategy) {
        FirefoxOptions options = new FirefoxOptions();
        options.setPageLoadStrategy(pageLoadStrategy);
        return options;
    }

    private static EdgeOptions edgeOptions(PageLoadStrategy pageLoadStrategy) {
        EdgeOptions options = new EdgeOptions();
        options.setPageLoadStrategy(pageLoadStrategy);
        return options;
    }
}
