package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class DependentsPageObject extends EmployeeListMenuPageObject {
    WebDriver driver;

    public DependentsPageObject(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
    public Map<String, String> dependentsFromDB(String employeeId) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String paramSql = "select ed.ed_name, ed.ed_relationship_type, ed.ed_relationship, ed.ed_date_of_birth from hs_hr_emp_dependents as ed join hs_hr_employee as e on ed.emp_number =e.emp_number where e.employee_id=?; ";
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
