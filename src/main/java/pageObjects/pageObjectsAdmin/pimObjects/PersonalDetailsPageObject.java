package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import pageUIs.pageUIsAdmin.pimUIs.PersonalDetailsPageUI;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.*;

public class PersonalDetailsPageObject extends EmployeeListMenuPageObject {
    WebDriver driver;

    public PersonalDetailsPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;

    }


    public void clickToRadioByValue(String gender) {
        clickToElementByJS(driver, PersonalDetailsPageUI.GENDER_RADIO, gender);
    }

    public void enterToDriverNumberTextbox(String numberDriver) {
        waitForElementVisible(driver,PersonalDetailsPageUI.DRIVER_NUMBER_TEXTBOX);
        clearInputByJS(driver, PersonalDetailsPageUI.DRIVER_NUMBER_TEXTBOX);
        sendkeyToElement(driver,PersonalDetailsPageUI.DRIVER_NUMBER_TEXTBOX,numberDriver);
    }
    public Map<String, String> personalDetailsFromDB(String employeeId) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String paramSql = "select e.emp_firstname ,e.emp_middle_name, e.emp_lastname,e.employee_id, e.emp_other_id, e.emp_dri_lice_exp_date,n.name,e.emp_marital_status, e.emp_birthday, e.emp_gender from hs_hr_employee as e join ohrm_nationality as n on e.nation_code =n.id where e.employee_id=?; ";
            pstm = conn.prepareStatement(paramSql);
            pstm.setString(1, employeeId);
            rs = pstm.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData(); // Lấy thông tin cột
            int columnCount = metaData.getColumnCount(); // Lấy số lượng cột

            while (rs.next()) {
               // LinkedHashMap<String, String> row = new LinkedHashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i); // Lấy tên cột
                    String columnValue = rs.getString(i); // Lấy giá trị của cột
                    records.put(columnName, columnValue);
                }
                 // Thêm bản ghi vào danh sách
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



}
