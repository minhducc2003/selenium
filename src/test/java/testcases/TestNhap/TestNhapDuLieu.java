package testcases.TestNhap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import helpers.PropertiesFile;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.TestPage;
import testcases.BaseTest;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class TestNhapDuLieu extends BaseTest {
    private TestPage testPage;
    @DataProvider(name = "testData")
    public Object[][] readCsvData() throws IOException, CsvException {
        // Đọc file CSV
        CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/datatest.csv"));
        List<String[]> allRows = csvReader.readAll();
        Object[][] data = new Object[allRows.size()][3];  // Vì có 3 cột: name, customerGroup, phoneNumber
        for (int i = 0; i < allRows.size(); i++) {
            data[i][0] = allRows.get(i)[0];  // name
            data[i][1] = allRows.get(i)[1];  // customerGroup
            data[i][2] = allRows.get(i)[2];  // phoneNumber
        }
        return data;
    }
    @Test(priority = 1, description = "Nhập dữ liệu thành công", dataProvider = "testData")
    public void NhapDuLieuThanhCong(String name, String customerGroup, String phoneNumber){
        testPage = new TestPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        testPage.boQuaTatCa(name, customerGroup, phoneNumber);
        // Thực hiện các kiểm tra sau khi nhập dữ liệu
        // testPage.thayThongBaoThanhCong("Thông báo thành công");
    }

//    @Test(priority = 1, description = "Nhập dữ liệu thành công")
//    public void NhapDuLieuThanhCong(){
//        testPage = new TestPage(driver);
//        navigateToURL(PropertiesFile.getPropValue("url"));
//        testPage.themThanhCong();
//    }
//
//    @Test(priority = 2, description = "Nhập dữ liệu để trống tên combo box")
//    public void NhapDuLieuFailCBBKhachHang() {
//        testPage = new TestPage(driver);
//        navigateToURL(PropertiesFile.getPropValue("url"));
//        testPage.boQuaTatCa("Trinh Minh Duc", "", "0368424724");
//
//        testPage.thayThongBaoFailCBB("nhóm khách hàng không được để trống");
//    }
//
//    @Test(priority = 3, description = "Nhập dữ liệu tên khách hàng sai format")
//    public void NhapDuLieuFailTenKhachHangFormat() {
//        testPage = new TestPage(driver);
//        navigateToURL(PropertiesFile.getPropValue("url"));
//        testPage.boQuaTatCa("123", "Modern Trade", "0368424724");
//
//        // Kiểm tra từng thông báo lỗi
//        testPage.thayThongBaoFailTenKhachHangFormat("tên khách hàng chỉ chứa chữ cái, khoảng trắng và dấu");
//    }
//    @Test(priority = 4, description = "Nhập dữ liệu tên khách hàng sai size min")
//    public void NhapDuLieuFailTenKhachHangSizeMin() {
//        testPage = new TestPage(driver);
//        navigateToURL(PropertiesFile.getPropValue("url"));
//        testPage.boQuaTatCa("a", "Modern Trade", "0368424724");
//
//        // Kiểm tra từng thông báo lỗi
//        testPage.thayThongBaoFailTenKhachHangSize("tên khách hàng phải từ 3-50 ký tự");
//    }
//    @Test(priority = 5, description = "Nhập dữ liệu tên khách hàng sai size max")
//    public void NhapDuLieuFailTenKhachHangSizeMax() {
//        testPage = new TestPage(driver);
//        navigateToURL(PropertiesFile.getPropValue("url"));
//        testPage.boQuaTatCa("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc", "Modern Trade", "0368424724");
//
//        // Kiểm tra từng thông báo lỗi
//        testPage.thayThongBaoFailTenKhachHangSize("tên khách hàng phải từ 3-50 ký tự");
//    }
//    @Test(priority = 6, description = "Nhập dữ liệu để trống số điện thoại")
//    public void NhapDuLieuFailSoDienThoaiTrong() {
//        testPage = new TestPage(driver);
//        navigateToURL(PropertiesFile.getPropValue("url"));
//        testPage.boQuaTatCa("Trinh Minh Duc", "Modern Trade", "");
//
//        // Kiểm tra từng thông báo lỗi
//        testPage.thayThongBaoFailSoDienThoaiTrong("số điện thoại không được để trống");
//        testPage.thayThongBaoFailSoDienThoaiFormat("số điện thoại phải bắt đầu bằng 0 và có độ dài 10-12 ký tự");
//    }


}