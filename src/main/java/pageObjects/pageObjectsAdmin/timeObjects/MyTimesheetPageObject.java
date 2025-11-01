package pageObjects.pageObjectsAdmin.timeObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuTimePageObject;

import java.util.ArrayList;

public class MyTimesheetPageObject extends SubMenuTimePageObject {
        WebDriver driver;

    public MyTimesheetPageObject(WebDriver driver){
            super(driver);
            this.driver=driver;

        }
    public int convertToPhut(String hour) {
        String[] p = hour.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }
    public String sumTimeOfRow(String value1,String value2,String value3,String value4,String value5,String value6,String value7){
        int tong=0;
        tong=convertToPhut(value1)+convertToPhut(value2)+convertToPhut(value3)+convertToPhut(value4)+convertToPhut(value5)+convertToPhut(value6)+convertToPhut(value7);
        int gio = tong / 60;
        int phut = tong % 60;
        return String.format("%02d:%02d", gio, phut);
    }
    public String sumTime(String hour1, String hour2){
        int tong=0;
        tong=convertToPhut(hour1)+convertToPhut(hour2);
        int gio = tong / 60;
        int phut = tong % 60;
        return String.format("%02d:%02d", gio, phut);
    }
}
