package pageObjects.pageObjectsClient.MyInfoObjectsC;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.sidebarObjects.SidebarPageObject;
import pageUIs.pageUIsAdmin.pimUIs.EmployeeListMenuPageUI;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class PersonalDetailPageObjectC extends SidebarPageObject {
    WebDriver driver;
    public PersonalDetailPageObjectC(WebDriver driver){
        super(driver);
        this.driver=driver;
    }
    public void clickToLinkByName(String linkName){
        waitForElementClickable(driver, EmployeeListMenuPageUI.DYNAAMIC_LINK_TEXT_BY_NAME,linkName);
        clickToElement(driver,EmployeeListMenuPageUI.DYNAAMIC_LINK_TEXT_BY_NAME,linkName);
    }

    public List<String> getListActualLabel() {
        return getListElementText(driver, BaseElementUI.DYNAMIC_NAME_LABEL);
    }

    public List<String> getListActualPlaceholder() {
        List<String> list = getListElementAttribute(driver, BaseElementUI.TEXTBOX_PLACEHOLDER, "placeholder");
        list.remove("Search");
        return list;
    }

    public String getActualPlaceholder() {
        return getElementAttribute(driver, BaseElementUI.TEXTAREA_PLACEHOLDER, "placeholder");
    }

    public List<String> getListListErrorMessageColer() {
        List<String> list = getListElementCssValue(driver, BaseElementUI.ERROR_MESSAGE, "color");
        return list;
    }

    public void clickButtonAdd() {
        this.clickToElement(driver, BaseElementUI.ADD_BUTTON);
    }

    public void isErrorMessageDisplay(String errorMessage) {
        this.waitForElementVisible(driver, BaseElementUI.DYNAMIC_ERROR_MESSAGE, errorMessage);
        this.isElementDisplayed(driver, BaseElementUI.DYNAMIC_ERROR_MESSAGE, errorMessage);
    }

    // Dang chưa sưa cai gi ca
    public List<Map<String, String>> getDefaultValueFromDB(String employeeId) {
        List<Map<String, String>> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String paramSql = "SELECT * FROM `hs_hr_employee` WHERE employee_id = ?;";
            pstm = conn.prepareStatement(paramSql);
            pstm.setString(1, employeeId);
            rs = pstm.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData(); // Lấy thông tin cột
            int columnCount = metaData.getColumnCount(); // Lấy số lượng cột

            while (rs.next()) {
                LinkedHashMap<String, String> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i); // Lấy tên cột
                    if (columnName.equals("emp_firstname") || columnName.equals("emp_lastname") || columnName.equals("emp_middle_name") || columnName.equals("employee_id")) {
                        String columnValue = rs.getString(i); // Lấy giá trị của cột
                        row.put(columnName, columnValue);
                    }
                }
                records.add(row); // Thêm bản ghi vào danh sách
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return records;
    }

    public List<String> getListValueDroplistFromDB() {
        List<String> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String paramSql = "SELECT c.name FROM `hs_hr_country` as c order by c.name;";
            pstm = conn.prepareStatement(paramSql);
            //pstm.setString(1, employeeId);
            rs = pstm.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData(); // Lấy thông tin cột
            int columnCount = metaData.getColumnCount(); // Lấy số lượng cột

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnValue = rs.getString(i); // Lấy giá trị của cột
                    columnValue = columnValue.substring(0, 1).toUpperCase() + columnValue.substring(1, columnValue.length() - 1).toLowerCase();
                    records.add(columnValue);
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return records;
    }

    public List<String> getListActualValueDropList() {
        List<String> valueInDropList;
        valueInDropList = getListElementText(driver, BaseElementUI.DYNAMIC_VALUE_IN_DROPLIST);
        return valueInDropList;
    }

    public void clickToNationalDropList(String label) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
        clickToElement(driver, BaseElementUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
    }

    public void clickLabel(String label) {
        waitForElementClickable(driver, BaseElementUI.DYNAMIC_LABEL, label);
        clickToElement(driver, BaseElementUI.DYNAMIC_LABEL, label);
    }
    public void selectDayInCalender(String day){
        waitForElementClickable(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER, day);
        clickToElement(driver,BaseElementUI.DYNAMIC_DAY_IN_CALENDER, day);
    }


}
