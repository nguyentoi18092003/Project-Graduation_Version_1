package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;
import pageUIs.pageUIsAdmin.pimUIs.AddEmployeePageUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.*;

public class AddEmployeePageObject extends SubMenuAdminPageObject {
    WebDriver driver;

    public AddEmployeePageObject(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
    public void clickToSaveButton() {
        waitForSpinnerIconInvisible();
        waitForElementClickable(driver,AddEmployeePageUI.SAVE_BUTTON);
        clickToElementByJS(driver,AddEmployeePageUI.SAVE_BUTTON);

    }
    public void clickToggle(){
        waitForSpinnerIconInvisible();
        waitForElementClickable(driver,AddEmployeePageUI.TOGGLE);
        clickToElement(driver,AddEmployeePageUI.TOGGLE);
    }
    public Map<String, String> employeeFromDB(String employeeId) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String paramSql = "select emp_firstname, emp_middle_name, emp_lastname from hs_hr_employee where employee_id=?; ";
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
}
