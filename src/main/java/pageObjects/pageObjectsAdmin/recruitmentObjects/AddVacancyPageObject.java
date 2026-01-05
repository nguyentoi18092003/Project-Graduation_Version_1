package pageObjects.pageObjectsAdmin.recruitmentObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuRecruitmentPageObject;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class AddVacancyPageObject extends SubMenuRecruitmentPageObject {
    WebDriver driver;

    public AddVacancyPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public Map<String, String> VacancyFromDB(String vacancyName) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String sql = """
                SELECT 
                    v.name AS vacancy_name,
                    j.job_title AS job_title_name,
                    v.description,
                    v.no_of_positions,
                    CASE WHEN v.status = 1 THEN 'Active' ELSE 'Inactive' END AS status_name,
                    CASE WHEN v.published_in_feed = 1 THEN 'Published' ELSE 'Hidden' END AS publish_status,
                    CONCAT(e.emp_firstname, ' ', IFNULL(e.emp_middle_name,''), ' ', e.emp_lastname) AS hiring_manager
                FROM ohrm_job_vacancy v
                JOIN ohrm_job_title j ON v.job_title_code = j.id
                JOIN hs_hr_employee e ON v.hiring_manager_id = e.emp_number
                WHERE v.name = ?;
            """;

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, vacancyName);
            rs = pstm.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    records.put(metaData.getColumnLabel(i), rs.getString(i));
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
}
