package pages;


import helpers.Keyword;
import helpers.PropertiesFile;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;


public class TestPage extends Keyword {

    public TestPage(WebDriver driver1) {
        this.driver = driver1;
    }

    private By TXT_TEN_KHACH_HANG = By.id("customerName");
    private By TXT_SO_DIEN_THOAI = By.id("phoneNumber");
    private By CBB_KHACH_HANG = By.xpath("//select[@id='customerGroup']");
    private By LABEL_THANH_CONG = By.xpath("/html/body/div/div/p");
    private By LABEL_TRUNG_SO_DIEN_THOAI = By.xpath("/html/body/div/div/ul/li");

    private By LABEL_FAIL_CBB_KHACH_HANG = By.xpath("/html/body/div/div/ul/li[1]");
    private By LABEL_FAIL_TEN_KHACH_HANG_TRONG = By.xpath("/html/body/div/div/ul/li[1]");
    private By LABEL_FAIL_TEN_KHACH_HANG_FORMAT = By.xpath("/html/body/div/div/ul/li");
    private By LABEL_FAIL_TEN_KHACH_HANG_SIZE = By.xpath("/html/body/div/div/ul/li");
    private By LABEL_FAIL_SO_DIEN_THOAI_TRONG = By.xpath("/html/body/div/div/ul/li[1]");
    private By LABEL_FAIL_SO_DIEN_THOAI_FORMAT = By.xpath("/html/body/div/div/ul/li[2]");

    private By BTN_THEM = By.xpath("/html/body/div/form/button");

    @Step("Them thanh cong")
    public TestPage themThanhCong(){
        setText(TXT_TEN_KHACH_HANG, PropertiesFile.getPropValue("ten"));
        selectOptionByText(CBB_KHACH_HANG, "Modern Trade");
        setText(TXT_SO_DIEN_THOAI, PropertiesFile.getPropValue("soDienThoai"));
        clickElement(BTN_THEM);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        verifyElementText(LABEL_THANH_CONG,"Thêm mới khách hàng thành công");
        return this;
    }
    @Step("Bỏ qua tất cả")
    public TestPage boQuaTatCa(String name, String cbb, String sdt) {
        setText(TXT_TEN_KHACH_HANG, name);

        // Nếu cbb là rỗng, không cần chọn option
        if (!cbb.isEmpty()) {
            selectOptionByText(CBB_KHACH_HANG, cbb);
        } else {
            System.out.println("Combo box để trống, không chọn giá trị nào.");
        }

        setText(TXT_SO_DIEN_THOAI, sdt);
        clickElement(BTN_THEM);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this;
    }
    @Step("THÔNG BÁO ALERT THÊM THÀNH CÔNG")
    public TestPage thayThongBaoAlertThemThanhCong(){
        verifyElementText(LABEL_THANH_CONG,"Thêm mới khách hàng thành công");
        return this;
    }
    @Step("THÔNG BÁO ALERT TRÙNG SỐ ĐIỆN THOẠI")
    public TestPage thayThongBaoAlertTrungSoDienThoai(){
        verifyElementText(LABEL_TRUNG_SO_DIEN_THOAI,"Số điện thoại đã tồn tại");
        return this;
    }
    @Step("THÔNG BÁO BẠN CHƯA NHẬP THÔNG TIN")
//    public TestPage thayThongBaoFailALl(String thongBao){
//        verifyElementText(LABEL_FAIL_CBB_KHACH_HANG,thongBao);
//        verifyElementText(LABEL_FAIL_TEN_KHACH_HANG_TRONG,thongBao);
//        verifyElementText(LABEL_FAIL_TEN_KHACH_HANG_FORMAT,thongBao);
//        verifyElementText(LABEL_FAIL_TEN_KHACH_HANG_SIZE,thongBao);
//        verifyElementText(LABEL_FAIL_SO_DIEN_THOAI_TRONG,thongBao);
//        verifyElementText(LABEL_FAIL_SO_DIEN_THOAI_FORMAT,thongBao);
//        return this;
//    }
    public TestPage thayThongBaoFailCBB(String thongBao){
        verifyElementText(LABEL_FAIL_CBB_KHACH_HANG,thongBao);
        return this;
    }
    public TestPage thayThongBaoFailTenKhachHangTrong(String thongBao){
        verifyElementText(LABEL_FAIL_TEN_KHACH_HANG_TRONG,thongBao);
        return this;
    }
    public TestPage thayThongBaoFailTenKhachHangFormat(String thongBao){
        verifyElementText(LABEL_FAIL_TEN_KHACH_HANG_FORMAT,thongBao);
        return this;
    }
    public TestPage thayThongBaoFailTenKhachHangSize(String thongBao){
        verifyElementText(LABEL_FAIL_TEN_KHACH_HANG_SIZE,thongBao);
        return this;
    }
    public TestPage thayThongBaoFailSoDienThoaiTrong(String thongBao){
        verifyElementText(LABEL_FAIL_SO_DIEN_THOAI_TRONG,thongBao);
        return this;
    }
    public TestPage thayThongBaoFailSoDienThoaiFormat(String thongBao){
        verifyElementText(LABEL_FAIL_SO_DIEN_THOAI_FORMAT,thongBao);
        return this;
    }


}