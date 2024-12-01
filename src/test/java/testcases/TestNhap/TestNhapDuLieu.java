package testcases.TestNhap;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.relevantcodes.extentreports.LogStatus;
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
//    @DataProvider(name = "testData")
//    public Object[][] readCsvData() throws IOException, CsvException {
//        // Đọc file CSV
//        CSVReader csvReader = new CSVReader(new FileReader("src/test/resources/datatest.csv"));
//        List<String[]> allRows = csvReader.readAll();
//        Object[][] data = new Object[allRows.size() - 1][3];  // Bỏ qua dòng tiêu đề
//        for (int i = 1; i < allRows.size(); i++) {
//            data[i-1][0] = allRows.get(i)[0];  // name
//            data[i-1][1] = allRows.get(i)[1];  // customerGroup
//            data[i-1][2] = allRows.get(i)[2];  // phoneNumber
//        }
//        return data;
//    }
//    @Test(priority = 1, description = "Nhập dữ liệu thành công", dataProvider = "testData")
//    public void NhapDuLieuThanhCong(String name, String customerGroup, String phoneNumber) {
//        try {
//            test.log(LogStatus.INFO, "Bắt đầu test nhập dữ liệu");
//            test.log(LogStatus.INFO, "Dữ liệu: " +
//                    "Name: " + name +
//                    ", Customer Group: " + customerGroup +
//                    ", Phone Number: " + phoneNumber);
//
//            testPage = new TestPage(driver);
//            navigateToURL(PropertiesFile.getPropValue("url"));
//            test.log(LogStatus.INFO, "Điều hướng đến: " + PropertiesFile.getPropValue("url"));
//
//            testPage.boQuaTatCa(name, customerGroup, phoneNumber);
//            test.log(LogStatus.INFO, "Nhập dữ liệu thành công");
//            test.log(LogStatus.PASS, "Test nhập dữ liệu thành công");
//        } catch (Exception e) {
//            // Nếu có lỗi, log và fail test
//            test.log(LogStatus.FAIL, "Test nhập dữ liệu thất bại");
//            test.log(LogStatus.FAIL, "Chi tiết lỗi: " + e.getMessage());
//
//            throw e;
//        }
//    }

//    @Test(priority = 1, description = "Nhập dữ liệu thành công")
//    public void NhapDuLieuThanhCong(){
//        String testName = "Nhập dữ liệu thành công";
//        try {
//            logTestStart(testName, "Trinh Minh Duc","Modern Trade","0368424724");
//
//            testPage = new TestPage(driver);
//            String url = PropertiesFile.getPropValue("url");
//            navigateToURL(url);
//            logTestNavigation(url);
//
//            testPage.themThanhCong();
//            testPage.thayThongBaoAlertThemThanhCong();
//
//            logTestSuccess(testName);
//        }catch (Exception e){
//            logTestFailure(testName,e);
//            throw e;
//        }
//    }

    @Test(priority = 2, description = "Nhập dữ liệu để trống tên combo box")
    public void NhapDuLieuFailCBBKhachHang() {
        String testName = "Nhập dữ liệu để trống tên combo box";
        try {
            logTestStart(testName, "Trinh Minh Duc","","0368424724");

            testPage = new TestPage(driver);
            String url = PropertiesFile.getPropValue("url");
            navigateToURL(url);
            logTestNavigation(url);

            testPage.boQuaTatCa("Trinh Minh Duc","","0368424724");
            testPage.thayThongBaoFailCBB("nhóm khách hàng không được để trống");

            logTestSuccess(testName);
        }catch (Exception e){
            logTestFailure(testName,e);
            throw e;
        }
    }

    @Test(priority = 3, description = "Nhập dữ liệu tên khách hàng sai format")
    public void NhapDuLieuFailTenKhachHangFormat() {

        String testName = "Nhập dữ liệu tên khách hàng sai format";
        try {
            logTestStart(testName, "123","Modern Trade","0368424724");

            testPage = new TestPage(driver);
            String url = PropertiesFile.getPropValue("url");
            navigateToURL(url);
            logTestNavigation(url);

            testPage.boQuaTatCa("123","Modern Trade","0368424724");
            testPage.thayThongBaoFailTenKhachHangFormat("tên khách hàng chỉ chứa chữ cái, khoảng trắng và dấu");

            logTestSuccess(testName);
        }catch (Exception e){
            logTestFailure(testName,e);
            throw e;
        }
    }
    @Test(priority = 4, description = "Nhập dữ liệu tên khách hàng sai size min")
    public void NhapDuLieuFailTenKhachHangSizeMin() {
        String testName = "Nhập dữ liệu tên khách hàng sai size min";
        try {
            logTestStart(testName, "a", "Modern Trade", "0368424724");

            testPage = new TestPage(driver);
            String url = PropertiesFile.getPropValue("url");
            navigateToURL(url);
            logTestNavigation(url);

            testPage.boQuaTatCa("a", "Modern Trade", "0368424724");
            testPage.thayThongBaoFailTenKhachHangSize("tên khách hàng phải từ 3-50 ký tự");

            logTestSuccess(testName);
        }catch (Exception e){
            logTestFailure(testName,e);
            throw e;
        }
    }
    @Test(priority = 5, description = "Nhập dữ liệu tên khách hàng sai size max")
    public void NhapDuLieuFailTenKhachHangSizeMax() {
        String testName = "Nhập dữ liệu tên khách hàng sai size max";
        try {
            logTestStart(testName, "abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc", "Modern Trade", "0368424724");

            testPage = new TestPage(driver);
            String url = PropertiesFile.getPropValue("url");
            navigateToURL(url);
            logTestNavigation(url);

            testPage.boQuaTatCa("abcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabcabc", "Modern Trade", "0368424724");
            testPage.thayThongBaoFailTenKhachHangSize("tên khách hàng phải từ 3-50 ký tự");

            logTestSuccess(testName);
        }catch (Exception e){
            logTestFailure(testName,e);
            throw e;
        }
    }
    @Test(priority = 6, description = "Nhập dữ liệu để trống số điện thoại")
    public void NhapDuLieuFailSoDienThoaiTrong() {
        String testName = "Nhập dữ liệu tên khách hàng sai size max";
        try {
            logTestStart(testName, "Trinh Minh Duc", "Modern Trade", "");

            testPage = new TestPage(driver);
            String url = PropertiesFile.getPropValue("url");
            navigateToURL(url);
            logTestNavigation(url);

            testPage.boQuaTatCa("Trinh Minh Duc", "Modern Trade", "");
            testPage.thayThongBaoFailSoDienThoaiTrong("số điện thoại không được để trống");
            testPage.thayThongBaoFailSoDienThoaiFormat("số điện thoại phải bắt đầu bằng 0 và có độ dài 10-12 ký tự");

            logTestSuccess(testName);
        }catch (Exception e){
            logTestFailure(testName,e);
            throw e;
        }
    }


}