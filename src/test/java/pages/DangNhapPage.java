package pages;

import helpers.Keyword;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import helpers.PropertiesFile;

public class DangNhapPage extends Keyword {


    public DangNhapPage(WebDriver driver1) {
        this.driver = driver1;
    }

    // các element trên trang web

    private By TXT_EMAIL = By.id("customer_email");

    private By TXT_PASSWORD = By.id("customer_password");

    private By BTN_DANG_NHAP = By.xpath("/html/body/section[2]/div/div/div[2]/div/div[1]/div[1]/form/div[2]/div/button");

    private By LABEL_TB_TK_KHONG_HOP_LE = By.xpath("//*[@id=\"customer_login\"]/div[1]");

    private By LABEL_TEN_KH = By.xpath("/html/body/section[2]/div/div/div[1]/div/p/span");

    private By LABEL_TEN_KH1 = By.xpath("/html/body/section[2]/div/div/div[1]/div/p/span");

    private By LABEL_TEN_KH2 = By.xpath("/html/body/section[2]/div/div/div[1]/div/p/span");

    private By LABEL_TEN_KH3 = By.xpath("/html/body/section[2]/div/div/div[1]/div/p/span");



    @Step("Đăng nhập thành công")
    public DangNhapPage dangNhapThanhCong() {
        setText(TXT_EMAIL, PropertiesFile.getPropValue("email"));
        setText(TXT_PASSWORD, PropertiesFile.getPropValue("password"));
        clickElement(BTN_DANG_NHAP);
        verifyElementText(LABEL_TEN_KH,"Nguyễn Linh đƠN ẢO");

        return this;
    }


    @Step("đăng nhập không thành công")
    public DangNhapPage dangNhapVoi(String email, String password) {
        // Nhập email
        setText(TXT_EMAIL, email);

        // Nhập mật khẩu
        setText(TXT_PASSWORD, password);

        // Chờ 3 giây trước khi nhấn nút đăng nhập
        try {
            Thread.sleep(3000);  // Tạm dừng 3 giây
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Nhấn nút đăng nhập
        clickElement(BTN_DANG_NHAP);

        return this;
    }


    @Step("Thông báo khi đăng nhập vs email 21 ký tự")
    public DangNhapPage thayThongBao2(String thongBao) {
        verifyElementText(LABEL_TEN_KH2,thongBao);// ví dụ kia là element text thông báo false
        return this;
    }

    @Step("Thông báo khi đăng nhập vs email 50 ký tự")
    public DangNhapPage thayThongBao3(String thongBao) {
        verifyElementText(LABEL_TEN_KH3,thongBao);// ví dụ kia là element text thông báo false
        return this;
    }

    @Step("Thông báo khi đăng nhập vs email 51 ký tự")
    public DangNhapPage thayThongBao(String thongBao) {
        verifyElementText(LABEL_TEN_KH1,thongBao);// ví dụ kia là element text thông báo false
        return this;
    }
    @Step("Thông báo khi đăng nhập sai Email")
    public DangNhapPage thayThongBaoFail(String thongBao) {
       verifyElementText(LABEL_TB_TK_KHONG_HOP_LE,thongBao);// ví dụ kia là element text thông báo false
        return this;
    }

}
