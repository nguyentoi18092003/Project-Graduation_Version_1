package pageObjects.pageObjectsClient.timeObject;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class EditTimesheetPageObjectC extends SubMenuAdminPageObject {
    WebDriver driver;

    public EditTimesheetPageObjectC(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
    public Map<String, String> recordFromDB(String employeeId) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String paramSql = "SELECT * FROM `ohrm_timesheet` where employee_id=?; ";
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
