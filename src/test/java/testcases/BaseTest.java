package testcases;

import common.BaseSetup;
import helpers.Keyword;
import helpers.PropertiesFile;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class BaseTest extends Keyword {
    // Khai báo ExtentReports
    protected static ExtentReports extent;
    protected static ExtentTest test;

    @BeforeSuite
    public void setUpReport() {
        // Đảm bảo properties được load
        PropertiesFile.setPropertiesFile();

        // Tạo thư mục reports nếu chưa tồn tại
        File reportDir = new File("src/test/resources/reports");
        if (!reportDir.exists()) {
            reportDir.mkdirs();
        }

        // Khởi tạo ExtentReports
        String reportName = "AutomationReport_" +
                new SimpleDateFormat("yyyy-MM-dd_HHmmss").format(new Date()) + ".html";
        extent = new ExtentReports("src/test/resources/reports/" + reportName, true);

        // Thêm thông tin hệ thống
        extent.addSystemInfo("Host Name", "LocalHost")
                .addSystemInfo("Environment", "QA")
                .addSystemInfo("Browser", getSafeProperty("browser"))
                .addSystemInfo("URL", getSafeProperty("url"));
    }

    @BeforeMethod
    public void setup(java.lang.reflect.Method method) {
        // Chuẩn bị driver
        driver = new BaseSetup().setupDriver(getSafeProperty("browser"));
        wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutWaitForPageLoaded));

        // Bắt đầu test trong ExtentReports
        test = extent.startTest(method.getName(), "Chi tiết test case: " + method.getName());
    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        // Ghi log kết quả test
        switch (result.getStatus()) {
            case ITestResult.FAILURE -> {
                test.log(LogStatus.FAIL, "Test Case Failed: " + result.getName());
                test.log(LogStatus.FAIL, "Error: " + result.getThrowable());
            }
            case ITestResult.SKIP -> test.log(LogStatus.SKIP, "Test Case Skipped: " + result.getName());
            case ITestResult.SUCCESS -> test.log(LogStatus.PASS, "Test Case Passed: " + result.getName());
        }

        // Kết thúc test
        extent.endTest(test);

        // Đóng trình duyệt
        driver.quit();
    }

    @AfterSuite
    public void endReport() {
        // Lưu và đóng báo cáo
        extent.flush();
        extent.close();
    }

    // Các hàm tiện ích cho logging
    public void logTestStart(String testName, String name, String customerGroup, String phoneNumber) {
        test.log(LogStatus.INFO, "Bắt đầu test: " + testName);
        test.log(LogStatus.INFO, String.format("Dữ liệu: Name: %s, Customer Group: %s, Phone Number: %s",
                name, customerGroup, phoneNumber));
    }

    public void logTestNavigation(String url) {
        test.log(LogStatus.INFO, "Điều hướng đến: " + url);
    }

    public void logTestSuccess(String testName) {
        test.log(LogStatus.PASS, "Test " + testName + " thành công");
    }

    public void logTestFailure(String testName, Exception e) {
        test.log(LogStatus.FAIL, "Test " + testName + " thất bại");
        test.log(LogStatus.FAIL, "Chi tiết lỗi: " + e.getMessage());
    }

    // Hàm tiện ích để lấy giá trị an toàn từ properties
    private String getSafeProperty(String key) {
        String value = PropertiesFile.getPropValue(key);
        return value != null ? value : "Not Specified";
    }
}
