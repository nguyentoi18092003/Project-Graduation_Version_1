package pageObjects.pageObjectsAdmin.recruitmentObjects;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuRecruitmentPageObject;
import pageUIs.pageUIsAdmin.pimUIs.EmployeeListPageUI;
import pageUIs.pageUIsAdmin.recruitmentUIs.VacancyListPageUI;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import utilities.MySQLConnUtils;

import java.sql.*;

public class VacancyListPageObject extends SubMenuRecruitmentPageObject {
    WebDriver driver;

    public VacancyListPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void clickToAddButtton() {
        waitForElementClickable(driver, VacancyListPageUI.ADD_BUTTON);
        clickToElement(driver,VacancyListPageUI.ADD_BUTTON);
    }

    public boolean isVacancyDeletedFromDB(String vacancyName) {
        boolean isDeleted = false;
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            String sql = """
                SELECT COUNT(*) AS total
                FROM ohrm_job_vacancy
                WHERE name = ?;
            """;
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, vacancyName);
            rs = pstm.executeQuery();

            if (rs.next()) {
                int count = rs.getInt("total");
                isDeleted = (count == 0); // true nếu không còn record nào
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
        return isDeleted;
    }

}
