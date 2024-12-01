package testcases;

import common.BaseSetup;
import helpers.Keyword;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import helpers.PropertiesFile;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest extends Keyword {

    @BeforeMethod
    public void setup() {
        PropertiesFile.setPropertiesFile();
        driver = new BaseSetup().setupDriver(PropertiesFile.getPropValue("browser"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutWaitForPageLoaded)); // correct constructor for WebDriverWait
        Date date = new Date();
        String LogDate = new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(date);
        System.setProperty("logFilename", LogDate);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
