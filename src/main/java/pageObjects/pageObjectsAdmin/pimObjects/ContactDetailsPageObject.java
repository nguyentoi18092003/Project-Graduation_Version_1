package pageObjects.pageObjectsAdmin.pimObjects;

import org.openqa.selenium.WebDriver;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class ContactDetailsPageObject extends EmployeeListMenuPageObject {
    WebDriver driver;

    public ContactDetailsPageObject (WebDriver driver){
        super(driver);
        this.driver=driver;

    }
    public Map<String, String> contactDetailsFromDB(String employeeId) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String paramSql = "select e.emp_street1, e.emp_street2, e.city_code,e.provin_code, e.emp_zipcode,c.name, e.emp_hm_telephone, e.emp_mobile, e.emp_work_telephone, e.emp_work_email, e.emp_oth_email from hs_hr_employee as e join hs_hr_country as c on e.coun_code =c.cou_code where e.employee_id=?; ";
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
