package pageObjects.pageObjectsAdmin.recruitmentObjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuRecruitmentPageObject;
import pageUIs.pageUIsAdmin.leaveUIs.LeaveListPageUI;
import pageUIs.pageUIsAdmin.recruitmentUIs.CandidateListPageUI;
import pageUIs.pageUIsAdmin.recruitmentUIs.VacancyListPageUI;
import pageUIs.pageUIsCommon.commonUIs.BaseElementUI;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.*;

public class CandidateListPageObject extends SubMenuRecruitmentPageObject {
    WebDriver driver;

    public CandidateListPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
    public void clickToAddButtton() {
        waitForElementClickable(driver, CandidateListPageUI.ADD_BUTTON);
        clickToElement(driver,CandidateListPageUI.ADD_BUTTON);
    }
    public void disableAutoFocus() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(
                "HTMLElement.prototype.scrollIntoView = function() {};"
        );
    }
    public void searchAndSelectInComboboxByLabel(String searchValue, String itemToSelect, String label) {
        enterToTextboxByLabel(searchValue,label);
        waitForElementVisible(driver,CandidateListPageUI.DROPDOWN_ITEM ,itemToSelect);
        clickToElement(driver, CandidateListPageUI.DROPDOWN_ITEM ,itemToSelect );
    }

    public String getValueInTextboxInTable(String columnName, String rowIndex){
        int columnIndex = getListElementSize(driver, CandidateListPageUI.DYNAMIC_INDEX_BY_COLUMN_NAME, columnName)+1;
        return getElementText(driver,CandidateListPageUI.DYNAMIC_TEXTBOX_BY_COLUMN_INDEX_ROW_INDEX, rowIndex, String.valueOf(columnIndex));
    }
    public void enterToCanlenderTextbox(String valueToSendKey, String placeholder) {
        waitForElementVisible(driver, CandidateListPageUI.DYNAMIC_CANLENDER_TEXTBOX_BY_PLACEHOLDER, placeholder);
        clearInputByJS(driver, CandidateListPageUI.DYNAMIC_CANLENDER_TEXTBOX_BY_PLACEHOLDER, placeholder);
        sendkeyToElement(driver, CandidateListPageUI.DYNAMIC_CANLENDER_TEXTBOX_BY_PLACEHOLDER, valueToSendKey, placeholder);
    }
    public void selectToDropdownByLabel(String label, String itemTextExpected ) {
        waitForElementClickable(driver, CandidateListPageUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, label);
        selectItemDropdown(driver, CandidateListPageUI.DYNAMIC_DROPDOWN_PARENT_BY_LABEL, CandidateListPageUI.DYNAMIC_DROPDOWN_CHILDREN_ITEM_BY_LABEL, itemTextExpected, label);
    }
    public int getTotalRecordOnUI() {
        String text = getElementText(driver, CandidateListPageUI.TOTAL_RECORD_FOUND_TEXT);
        if (text.equalsIgnoreCase("No Records Found")) {
            return 0;
        }
        return Integer.parseInt(text.replaceAll("[^0-9]", ""));
    }
    public List<Map<String, String>> getCandidatesByDateRange(String fromDate, String toDate) {
        List<Map<String, String>> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql =
                    "SELECT " +
                    "v.name AS vacancy, " +
                    "TRIM(CONCAT_WS(' ', c.first_name, c.middle_name, c.last_name)) AS candidate_name, " +
                    "TRIM(CONCAT_WS(' ', e.emp_firstname, e.emp_middle_name, e.emp_lastname)) AS hiring_manager, " +
                    "c.date_of_application, " +
                    "cv.status AS application_status " +
                    "FROM ohrm_job_candidate c " +
                    "JOIN ohrm_job_candidate_vacancy cv ON cv.candidate_id = c.id " +
                    "JOIN ohrm_job_vacancy v ON v.id = cv.vacancy_id " +
                    "JOIN hs_hr_employee e ON e.emp_number = v.hiring_manager_id " +
                    "WHERE c.date_of_application BETWEEN ? AND ? " +
                    "ORDER BY c.date_of_application DESC, " +
                    "TRIM(CONCAT_WS(' ', c.first_name, c.middle_name, c.last_name))";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, fromDate);
            pstm.setString(2, toDate);

            rs = pstm.executeQuery();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("vacancy", rs.getString("vacancy"));
                row.put("candidate_name", rs.getString("candidate_name"));
                row.put("hiring_manager", rs.getString("hiring_manager"));
                row.put("date_of_application", rs.getString("date_of_application"));
                row.put("application_status", rs.getString("application_status"));
                records.add(row);
            }

        } catch (Exception e) {
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

    public List<Map<String, String>> getCandidatesByName(String candidateName) {
        List<Map<String, String>> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql =
                    "SELECT " +
                    "v.name AS vacancy, " +
                    "TRIM(CONCAT_WS(' ', c.first_name, c.middle_name, c.last_name)) AS candidate_name, " +
                    "TRIM(CONCAT_WS(' ', e.emp_firstname, e.emp_middle_name, e.emp_lastname)) AS hiring_manager, " +
                    "c.date_of_application, " +
                    "cv.status AS application_status " +
                    "FROM ohrm_job_candidate c " +
                    "JOIN ohrm_job_candidate_vacancy cv ON cv.candidate_id = c.id " +
                    "JOIN ohrm_job_vacancy v ON v.id = cv.vacancy_id " +
                    "JOIN hs_hr_employee e ON e.emp_number = v.hiring_manager_id " +
                    "WHERE TRIM(CONCAT_WS(' ', c.first_name, c.middle_name, c.last_name)) = ? " +
                    "ORDER BY c.date_of_application DESC, " +
                    "TRIM(CONCAT_WS(' ', c.first_name, c.middle_name, c.last_name))";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, candidateName);

            rs = pstm.executeQuery();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("vacancy", rs.getString("vacancy"));
                row.put("candidate_name", rs.getString("candidate_name"));
                row.put("hiring_manager", rs.getString("hiring_manager"));
                row.put("date_of_application", rs.getString("date_of_application"));
                row.put("application_status", rs.getString("application_status"));
                records.add(row);
            }

        } catch (Exception e) {
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

    public List<Map<String, String>> getCandidateListByVacancy(String vacancy) {
        List<Map<String, String>> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql =
                    "SELECT " +
                            "v.name AS vacancy, " +
                            "TRIM(CONCAT_WS(' ', c.first_name, c.middle_name, c.last_name)) AS candidate_name, " +
                            "TRIM(CONCAT_WS(' ', e.emp_firstname, e.emp_middle_name, e.emp_lastname)) AS hiring_manager, " +
                            "c.date_of_application, " +
                            "cv.status AS application_status " +
                            "FROM ohrm_job_candidate c " +
                            "JOIN ohrm_job_candidate_vacancy cv ON cv.candidate_id = c.id " +
                            "JOIN ohrm_job_vacancy v ON v.id = cv.vacancy_id " +
                            "JOIN hs_hr_employee e ON e.emp_number = v.hiring_manager_id " +
                            "WHERE 1=1 ";

            if (!vacancy.equalsIgnoreCase("-- Select --")) {
                sql += " AND v.name LIKE ? ";
            }

            sql += " ORDER BY c.date_of_application DESC";

            pstm = conn.prepareStatement(sql);

            if (!vacancy.equalsIgnoreCase("-- Select --")) {
                pstm.setString(1, "%" + vacancy + "%");
            }

            rs = pstm.executeQuery();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("vacancy", rs.getString("vacancy"));
                row.put("candidate_name", rs.getString("candidate_name"));
                row.put("hiring_manager", rs.getString("hiring_manager"));
                row.put("date_of_application", rs.getString("date_of_application"));
                row.put("application_status", rs.getString("application_status"));
                records.add(row);
            }

        } catch (Exception e) {
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

    public List<Map<String, String>> getCandidateListByNameAndVacancy(String candidateName, String vacancy) {
        List<Map<String, String>> records = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();

            String sql =
                    "SELECT " +
                    "v.name AS vacancy, " +
                    "TRIM(CONCAT_WS(' ', c.first_name, c.middle_name, c.last_name)) AS candidate_name, " +
                    "TRIM(CONCAT_WS(' ', e.emp_firstname, e.emp_middle_name, e.emp_lastname)) AS hiring_manager, " +
                    "c.date_of_application, " +
                    "cv.status AS application_status " +
                    "FROM ohrm_job_candidate c " +
                    "JOIN ohrm_job_candidate_vacancy cv ON cv.candidate_id = c.id " +
                    "JOIN ohrm_job_vacancy v ON v.id = cv.vacancy_id " +
                    "JOIN hs_hr_employee e ON e.emp_number = v.hiring_manager_id " +
                    "WHERE TRIM(CONCAT_WS(' ', c.first_name, c.middle_name, c.last_name)) LIKE ? " +
                    "AND v.name LIKE ? " +
                    "ORDER BY c.date_of_application DESC, " +
                    "TRIM(CONCAT_WS(' ', c.first_name, c.middle_name, c.last_name))";

            pstm = conn.prepareStatement(sql);
            pstm.setString(1, "%" + candidateName + "%");
            pstm.setString(2, "%" + vacancy + "%");

            rs = pstm.executeQuery();

            while (rs.next()) {
                Map<String, String> row = new HashMap<>();
                row.put("vacancy", rs.getString("vacancy"));
                row.put("candidate_name", rs.getString("candidate_name"));
                row.put("hiring_manager", rs.getString("hiring_manager"));
                row.put("date_of_application", rs.getString("date_of_application"));
                row.put("application_status", rs.getString("application_status"));
                records.add(row);
            }

        } catch (Exception e) {
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

    public boolean isCandidateDeletedFromDB(String fullname) {
        try (Connection conn = MySQLConnUtils.getMySQLConnection()) {
            String sql =
                    "SELECT * FROM ohrm_job_candidate " +
                            "WHERE TRIM(CONCAT_WS(' ', first_name, middle_name, last_name)) = ?";

            PreparedStatement pstm = conn.prepareStatement(sql);
            pstm.setString(1, fullname);

            ResultSet rs = pstm.executeQuery();
            return !rs.next(); // true = đã xoá
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Map<String, Object> backupCandidate(String candidateName) {
        Map<String, Object> backup = new HashMap<>();

        try (Connection conn = MySQLConnUtils.getMySQLConnection()) {

            // Backup bảng ohrm_job_candidate
            String sqlCandidate =
                    "SELECT * FROM ohrm_job_candidate " +
                    "WHERE TRIM(CONCAT_WS(' ', first_name, middle_name, last_name)) = ?";

            PreparedStatement stm1 = conn.prepareStatement(sqlCandidate);
            stm1.setString(1, candidateName);
            ResultSet rs1 = stm1.executeQuery();

            Map<String, String> candidateData = new HashMap<>();
            if (rs1.next()) {
                ResultSetMetaData md = rs1.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    candidateData.put(md.getColumnName(i), rs1.getString(i));
                }
            }

            backup.put("candidate", candidateData);

            // Backup bảng ohrm_job_candidate_vacancy
            String sqlVacancy =
                    "SELECT * FROM ohrm_job_candidate_vacancy " +
                            "WHERE candidate_id = (SELECT id FROM ohrm_job_candidate " +
                            "WHERE TRIM(CONCAT_WS(' ', first_name, middle_name, last_name)) = ?)";

            PreparedStatement stm2 = conn.prepareStatement(sqlVacancy);
            stm2.setString(1, candidateName);

            ResultSet rs2 = stm2.executeQuery();

            List<Map<String, String>> vacancyList = new ArrayList<>();
            while (rs2.next()) {
                Map<String, String> row = new HashMap<>();
                ResultSetMetaData md = rs2.getMetaData();
                for (int i = 1; i <= md.getColumnCount(); i++) {
                    row.put(md.getColumnName(i), rs2.getString(i));
                }
                vacancyList.add(row);
            }

            backup.put("vacancies", vacancyList);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return backup;
    }


    public void rollbackCandidate(Map<String, Object> backup) {

        Map<String, String> candidate = (Map<String, String>) backup.get("candidate");
        List<Map<String, String>> vacancies = (List<Map<String, String>>) backup.get("vacancies");

        try (Connection conn = MySQLConnUtils.getMySQLConnection()) {

            // 1. Insert lại candidate
            String insertCandidate =
                    "INSERT INTO ohrm_job_candidate " +
                            "(id, first_name, middle_name, last_name, email, contact_number, date_of_application) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement stm1 = conn.prepareStatement(insertCandidate);
            stm1.setString(1, candidate.get("id"));
            stm1.setString(2, candidate.get("first_name"));
            stm1.setString(3, candidate.get("middle_name"));
            stm1.setString(4, candidate.get("last_name"));
            stm1.setString(5, candidate.get("email"));
            stm1.setString(6, candidate.get("contact_number"));
            stm1.setString(7, candidate.get("date_of_application"));
            stm1.executeUpdate();

            // 2. Insert lại các bản ghi vacancy
            for (Map<String, String> v : vacancies) {

                String insertVac =
                        "INSERT INTO ohrm_job_candidate_vacancy " +
                                "(id, candidate_id, vacancy_id, status, applied_date) " +
                                "VALUES (?, ?, ?, ?, ?)";

                PreparedStatement stm2 = conn.prepareStatement(insertVac);
                stm2.setString(1, v.get("id"));
                stm2.setString(2, v.get("candidate_id"));
                stm2.setString(3, v.get("vacancy_id"));
                stm2.setString(4, v.get("status"));
                stm2.setString(5, v.get("applied_date"));
                stm2.executeUpdate();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
