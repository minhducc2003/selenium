package helpers;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.List;


public class Keyword {

    public WebDriver driver;
    public WebDriverWait wait;
    public int timeoutWaitForPageLoaded = 30;

    @Step("Truy cập trang: {0}")
    public void navigateToURL(String url) {
        Log.info("Truy cap trang: " + url);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        driver.get(url);
        waitForPageLoaded();
    }

    @Step("Xóa text trong {0}")
    public void clearText(By element) {

        Log.info("[Clear text] của " + element.toString());
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).clear();



    }

    @Step("Nhập vào {0} giá trị {1}")
    public void setText(By element, String value) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        clearText(element);
        Log.info("[Set text] của " + element.toString());
        driver.findElement(element).sendKeys(value);

    }
    @Step("Nhập vào {0} giá trị {1} rồi Enter")
    public void setTextThenEnter(By element, String value) {

        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).clear();
        Log.info("Set text của " + element.toString() + " roi Enter");
        driver.findElement(element).sendKeys(value);
        driver.findElement(element).sendKeys(Keys.ENTER);
        waitForPageLoaded();

    }

    public void type(By element, String value) {

        Log.info("[Type] của " + element.toString());
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).sendKeys(value);
        waitForPageLoaded();

    }

    @Step("Click vào {0}")
    public void clickElement(By element) {

        Log.info("[Click] vào " + element.toString());
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        driver.findElement(element).click();
        waitForPageLoaded();

    }

    //Chọn Combobox
    public void selectOptionByText(By element, String text) {

        Log.info("[SelectOption] " + element.toString() + " by text " + text);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        Select select = new Select(driver.findElement(element));
        select.selectByVisibleText(text);
        waitForPageLoaded();
    }

    //Di chuột
    public void hoverTo(By element) {

        Log.info("[Hover] vào " + element.toString());
        WebElement ele = driver.findElement(element);
        Actions action = new Actions(driver);
        action.moveToElement(ele).perform();

    }

    //cuộn chuột
    public void hover() {
        JavascriptExecutor js = (JavascriptExecutor) driver;// cuộn chuột
        js.executeScript("window.scrollBy(0,700)");
    }

    public void hover2() {
        JavascriptExecutor js = (JavascriptExecutor) driver;// cuộn chuột
        js.executeScript("window.scrollBy(0,500)");
    }

    public boolean verifyUrl(String url) {
        System.out.println(driver.getCurrentUrl());
        System.out.println(url);

        return driver.getCurrentUrl().contains(url); //True/False
    }

    @Step("so sánh text của {0} với {1}")
    public void verifyElementText(By element, String textValue) {
        Log.info("[Verify Element text] của: " + element.toString() + " với value: " + textValue);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));

        // Đợi cho đến khi element hiển thị
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));

        // Lấy text từ element
        String elementText = driver.findElement(element).getText();

        // Chuyển cả actual và expected text về dạng chữ thường để so sánh
        Assert.assertEquals(elementText.toLowerCase(), textValue.toLowerCase(), "Text không bằng nhau");
    }

    @Step("so sánh text của {0} với {1}")
    public void verifyElement(By element, String textValue) {
        Log.info("[Verify Element text] của: " + element.toString() + " với value: " + textValue);
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        String elementText = driver.findElement(element).getAttribute("value"); //True/False
        Assert.assertEquals(elementText, textValue, "Không bằng nhau");

    }

    public void verifyAlertText(String textValue) {
        Log.info("[Verify alert text]  với value: " + textValue);
        Assert.assertEquals(driver.switchTo().alert().getText(), textValue, "Không bằng nhau");

    }

    public boolean verifyElementExist(By element) {
        //Tạo list lưu tất cả đối tượng WebElement
        List<WebElement> listElement = driver.findElements(element);

        int total = listElement.size();

        if (total > 0) {
            return true;
        }

        return false;
    }

    public boolean verifyPageLoaded(String pageLoadedText) {
        waitForPageLoaded();
        Boolean res = false;

        List<WebElement> elementList = driver.findElements(By.xpath("//*[contains(text(),'" + pageLoadedText + "')]"));
        if (elementList.size() > 0) {
            res = true;
            System.out.println("Page loaded (" + res + "): " + pageLoadedText);
        } else {
            res = false;
            System.out.println("Page loaded (" + res + "): " + pageLoadedText);
        }
        return res;
    }

    // Wait

    public void waitForPageLoaded() {
        // wait for jQuery to loaded
        ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                try {
                    return ((Long) ((JavascriptExecutor) driver).executeScript("return jQuery.active") == 0);
                } catch (Exception e) {
                    return true;
                }
            }
        };

        // wait for Javascript to loaded
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState")
                        .toString().equals("complete");
            }
        };

        try {
            wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutWaitForPageLoaded));
            wait.until(jQueryLoad);
            wait.until(jsLoad);
        } catch (Throwable error) {
            Assert.fail("Quá thời gian load trang.");
        }

    }

}
