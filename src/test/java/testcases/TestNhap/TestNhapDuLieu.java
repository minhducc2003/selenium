package testcases.TestNhap;

import helpers.PropertiesFile;
import org.testng.annotations.Test;
import pages.TestPage;
import testcases.BaseTest;

public class TestNhapDuLieu extends BaseTest {
    private TestPage testPage;

    @Test(priority = 1, description = "Nhập dữ liệu thành công")
    public void NhapDuLieuThanhCong(){
        testPage = new TestPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        testPage.themThanhCong();
    }

    @Test(priority = 2, description = "Nhập dữ liệu để trống tên combo box")
    public void NhapDuLieuFailCBBKhachHang() {
        testPage = new TestPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        testPage.boQuaTatCa("Trinh Minh Duc", "", "0368424724");

        testPage.thayThongBaoFailCBB("nhóm khách hàng không được để trống");
    }

    @Test(priority = 3, description = "Nhập dữ liệu tên khách hàng sai format")
    public void NhapDuLieuFailTenKhachHangFormat() {
        testPage = new TestPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        testPage.boQuaTatCa("123", "Modern Trade", "0368424724");

        // Kiểm tra từng thông báo lỗi
        testPage.thayThongBaoFailTenKhachHangFormat("tên khách hàng chỉ chứa chữ cái, khoảng trắng và dấu");
    }
    @Test(priority = 4, description = "Nhập dữ liệu tên khách hàng sai size min")
    public void NhapDuLieuFailTenKhachHangSizeMin() {
        testPage = new TestPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        testPage.boQuaTatCa("a", "Modern Trade", "0368424724");

        // Kiểm tra từng thông báo lỗi
        testPage.thayThongBaoFailTenKhachHangSize("tên khách hàng phải từ 3-50 ký tự");
    }
    @Test(priority = 5, description = "Nhập dữ liệu tên khách hàng sai size max")
    public void NhapDuLieuFailTenKhachHangSizeMax() {
        testPage = new TestPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        testPage.boQuaTatCa("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc", "Modern Trade", "0368424724");

        // Kiểm tra từng thông báo lỗi
        testPage.thayThongBaoFailTenKhachHangSize("tên khách hàng phải từ 3-50 ký tự");
    }
    @Test(priority = 6, description = "Nhập dữ liệu để trống số điện thoại")
    public void NhapDuLieuFailSoDienThoaiTrong() {
        testPage = new TestPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        testPage.boQuaTatCa("Trinh Minh Duc", "Modern Trade", "");

        // Kiểm tra từng thông báo lỗi
        testPage.thayThongBaoFailSoDienThoaiTrong("số điện thoại không được để trống");
        testPage.thayThongBaoFailSoDienThoaiFormat("số điện thoại phải bắt đầu bằng 0 và có độ dài 10-12 ký tự");
    }


}