package pages;

import helpers.Keyword;
import org.openqa.selenium.By;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;

public class HomePage extends Keyword {


    public HomePage(WebDriver driver1) {
        this.driver = driver1;
    }

    // các element trên trang web

    private By TAT_QUANG_CAO = By.xpath("//div[@id='modal-campaign']//a[@class='ega-modal__close' and last()]");
    private By DI_CHUOT = By.xpath("/html/body/header/div/div[4]/div/div[1]");

    private By DANG_KY = By.xpath("//a[contains(text(),'Đăng ký')]");

    private By DANG_NHAP = By.xpath("//a[contains(text(),'Đăng nhập')]");

    private By DANG_XUAT = By.xpath("//a[contains(text(),'Đăng xuất')]");


    private By TRANG_CHU_BTN = By.xpath("//div[@class='ty-logo-container']");

    private By SEARCH_TXT = By.id("search_input");

    private By GIO_HANG_BTN = By.xpath("//div[contains(@class,'babi-cart')]");



    //các hành động ta thao tác trene trang web
    @Step("tìm kiếm thông tin sản phẩm: {0}")
    public HomePage timKiemSanPham(String tenSanPham) {
        setTextThenEnter(SEARCH_TXT, tenSanPham);
        return this;
    }

    @Step("điều hướng tới màn đăng nhập")
    public HomePage moDangNhap() {
        hoverTo(DI_CHUOT);// đây là biểu tượng người
        clickElement(DANG_NHAP);// đây là chữ đăng nhập
        // các hành động trong này là tập hợp sao cho đúng nghĩa với cái tên "moDangNhap"
        return this;
    }

    @Step("điều hướng tới màn đăng nhập")
    public HomePage dongDangNhap() {
        hoverTo(DI_CHUOT);// đây là biểu tượng người
        clickElement(DANG_XUAT);// đây là chữ đăng XUẤT
        // các hành động trong này là tập hợp sao cho đúng nghĩa với cái tên "moDangNhap"
        return this;
    }


    @Step("điều hướng tới màn đăng ký")
    public HomePage moDangKy() {
        hoverTo(DI_CHUOT);// đây là biểu tượng người
        clickElement(DANG_KY);// đây là chữ đăng ký
        // các hành động trong này là tập hợp sao cho đúng nghĩa với cái tên "moDangNhap"
        return this;
    }
}
