import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty", "html:target/which-html-report", "json:target/target/which-report.json"}, tags = {"@smoke"})

public class RunTest {

    static WebDriver driver;

    @BeforeClass
    public static void start() {

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.addArguments("start-fullscreen");
        driver = new ChromeDriver(options);
        driver.get("about:blank");
        System.out.println("Checking the home page is shown......");
    }

    @AfterClass
    public static void stop() {

        driver.quit();
    }

}