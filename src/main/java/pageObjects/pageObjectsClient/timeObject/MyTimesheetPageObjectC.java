package pageObjects.pageObjectsClient.timeObject;

import org.openqa.selenium.WebDriver;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuAdminPageObject;
import pageObjects.pageObjectCommon.subMenuObjects.SubMenuTimePageObject;
import pageUIs.pageUIsClient.MyTimesheetUIC;
import utilities.MySQLConnUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class MyTimesheetPageObjectC extends SubMenuTimePageObject {
    WebDriver driver;

    public MyTimesheetPageObjectC(WebDriver driver){
        super(driver);
        this.driver=driver;

    }
    public int convertToPhut(String hour) {
        String[] p = hour.split(":");
        return Integer.parseInt(p[0]) * 60 + Integer.parseInt(p[1]);
    }


    public String sumTotalColume(ArrayList<String> listSumRow){
        int tong=0;
        for(String x:listSumRow){
            tong+=convertToPhut(x);
        }
        int gio = tong / 60;
        int phut = tong % 60;
        return String.format("%02d:%02d", gio, phut);
    }
    public String sumTimeOfRow(String value1,String value2,String value3,String value4,String value5,String value6,String value7){
        int tong=0;
        tong=convertToPhut(value1)+convertToPhut(value2)+convertToPhut(value3)+convertToPhut(value4)+convertToPhut(value5)+convertToPhut(value6)+convertToPhut(value7);
        int gio = tong / 60;
        int phut = tong % 60;
        return String.format("%02d:%02d", gio, phut);
    }
    public String sumTime(String hour1, String hour2){
        int tong=0;
            tong=convertToPhut(hour1)+convertToPhut(hour2);
        int gio = tong / 60;
        int phut = tong % 60;
        return String.format("%02d:%02d", gio, phut);
    }
    public boolean isStatusDisplay(String status){
        waitForElementVisible(driver, MyTimesheetUIC.STATUS,status);
        return isElementDisplayed(driver, MyTimesheetUIC.STATUS,status);
    }
    public String getCellValueInActionsTable(String columnName){
        int columnIndex = getListElementSize(driver, MyTimesheetUIC.DYNAMIC_INDEX_BY_COLUMN_NAME_IN_TABLE, columnName)+1;
        int lastRow=getListElementSize(driver,MyTimesheetUIC.ROW);
        String cellValue=getElementText(driver,MyTimesheetUIC.CELL_VALUE_IN_TABLE,String.valueOf(lastRow),String.valueOf(columnIndex));
        return cellValue;
    }
    public Map<String, String> PreCleanStep() {
        Map<String, String> result = new LinkedHashMap<>();
        Connection conn = null;
        PreparedStatement pstm = null;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            conn.setAutoCommit(false); // ✅ bắt đầu transaction

            // Danh sách các bảng cần xóa (theo thứ tự quan hệ)
            String[] tables = {
                    "ohrm_timesheet_item",
                    "ohrm_timesheet_action_log",
                    "ohrm_timesheet"
            };

            // Thực hiện xóa từng bảng
            for (String table : tables) {
                String sql = "DELETE FROM " + table;
                pstm = conn.prepareStatement(sql);
                int affected = pstm.executeUpdate();
                result.put(table, affected + " rows deleted");
                pstm.close(); // đóng PreparedStatement mỗi vòng lặp
            }

            conn.commit(); // ✅ commit transaction khi mọi thứ thành công

        } catch (SQLException e) {
            e.printStackTrace();
            if (conn != null) {
                try {
                    conn.rollback(); // ⚠️ rollback nếu có lỗi
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
            }
        } finally {
            try {
                if (pstm != null) pstm.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
};


