package testcases.DangNhap;

import org.testng.annotations.Test;
import pages.HomePage;
import pages.DangNhapPage;
import testcases.BaseTest;
import helpers.PropertiesFile;

public class DangNhapWebBanHang extends BaseTest {

    private HomePage homePage;
    private DangNhapPage dangnhapPage ;

    @Test
    public void DangNhapThanhCong(){
        homePage=new HomePage(driver);
        dangnhapPage =new DangNhapPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        homePage.moDangNhap();
        dangnhapPage.dangNhapThanhCong();
        homePage.dongDangNhap();

    }

    @Test
    public void DangNhapThanhCong_email21kytudung()
    {
        homePage=new HomePage(driver);
        dangnhapPage =new DangNhapPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        homePage.moDangNhap();
        dangnhapPage.dangNhapVoi("11111111111@gmail.com","1111111111");
        dangnhapPage.thayThongBao2("so mot");
        homePage.dongDangNhap();
    }

    @Test
    public void DangNhapThanhCong_email50kytudung()
    {
        homePage=new HomePage(driver);
        dangnhapPage =new DangNhapPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        homePage.moDangNhap();
        dangnhapPage.dangNhapVoi("222222222222222222222222222222222222222@gmail.com","22222222222222222222");
        dangnhapPage.thayThongBao3("so hai");
        homePage.dongDangNhap();
    }

    @Test
    public void DangNhapThanhCong_email51kytu()
    {
        homePage=new HomePage(driver);
        dangnhapPage =new DangNhapPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        homePage.moDangNhap();
        dangnhapPage.dangNhapVoi("77777777777777777777777777777777777777777@gmail.com","777777777777777777777");
        dangnhapPage.thayThongBao("so bay");
        homePage.dongDangNhap();
    }


    @Test
    public void DangNhapKhongThanhCong_saiemail()
    {
        homePage=new HomePage(driver);
        dangnhapPage =new DangNhapPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        homePage.moDangNhap();
        dangnhapPage.dangNhapVoi("nguyenthi@gmail.com","linh0910");
        dangnhapPage.thayThongBaoFail("Thông tin đăng nhập không hợp lệ.");
    }
    @Test
    public void DangNhapKhongThanhCong_saipass1()
    {
        homePage=new HomePage(driver);
        dangnhapPage =new DangNhapPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        homePage.moDangNhap();
        dangnhapPage.dangNhapVoi("nguyenthilink09102000@gmail.com","0910");
        dangnhapPage.thayThongBaoFail("Thông tin đăng nhập không hợp lệ.");
    }

    @Test
    public void DangNhapKhongThanhCong_passnhapkytudacbiet()
    {
        homePage=new HomePage(driver);
        dangnhapPage =new DangNhapPage(driver);
        navigateToURL(PropertiesFile.getPropValue("url"));
        homePage.moDangNhap();
        dangnhapPage.dangNhapVoi("nguyenthilink09102000@gmail.com","!@#$%");
        dangnhapPage.thayThongBaoFail("Thông tin đăng nhập không hợp lệ.");
    }


}
