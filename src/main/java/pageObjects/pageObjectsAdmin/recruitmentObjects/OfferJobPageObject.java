package pageObjects.pageObjectsAdmin.recruitmentObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuRecruitmentPageObject;
import pageUIs.pageUIsAdmin.recruitmentUIs.AddCandidatePageUI;
import pageUIs.pageUIsAdmin.recruitmentUIs.OfferJobPageUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class OfferJobPageObject extends SubMenuRecruitmentPageObject {
    WebDriver driver;

    public OfferJobPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public String getApplicationStatus() {
        waitForElementVisible(driver, OfferJobPageUI.APPLICATION_STATUS);
        String text = getElementText(driver, OfferJobPageUI.APPLICATION_STATUS);
        return text.replace("Status:", "").trim();
    }
    public Map<String, String> OfferJobCandidateFromDB(String email) {
        Map<String, String> records = new HashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql ="""
                    SELECT 
                        CONCAT(TRIM(c.first_name), ' ', TRIM(c.middle_name), ' ', TRIM(c.last_name)) AS full_name,
                        cv.status, v.name AS vacancy_name
                    FROM ohrm_job_candidate c
                    JOIN ohrm_job_candidate_vacancy cv ON c.id = cv.candidate_id
                    JOIN ohrm_job_vacancy v ON v.id = cv.vacancy_id
                    WHERE LOWER(TRIM(c.email)) = LOWER(TRIM(?))
                    ORDER BY c.id DESC LIMIT 1
                """;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, email);
            rs = pstm.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            if (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnLabel(i);
                    String columnValue = rs.getString(i);
                    records.put(columnName, columnValue);
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
