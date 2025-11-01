package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class EmergencyContactsPageObject extends EmployeeListMenuPageObject {
    WebDriver driver;

    public EmergencyContactsPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
    public Map<String, String> emergencyContactsFromDB(String employeeId) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String paramSql = "select e.eec_name, e.eec_relationship,e.eec_relationship,e.eec_home_no,e.eec_mobile_no,e.eec_office_no from hs_hr_emp_emergency_contacts e join hs_hr_employee em on e.emp_number=em.emp_number where em.employee_id=?; ";
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
