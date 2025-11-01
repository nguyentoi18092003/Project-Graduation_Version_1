//package ptit.orangehrm.testAI;
//
//import utilities.ExcelConfig;
//import utilities.GeminiClient;
//
//import java.util.ArrayList;
//
//public class testai {
////    public static void main(String[] args) throws Exception {
//////        ExcelConfig excelConfig;
//////        excelConfig = ExcelConfig.getExcelData();
//////        excelConfig.switchToSheet("testai");
//////        String tmp="";
//////        for(int i=1;i<=37;i++){
//////            // Giả sử string này bạn đã lấy từ Excel
//////            String allTestacase = excelConfig.getCellData("Mota", i );
//////
//////            tmp=tmp+"Test case "+i+":"+allTestacase+";";
//////            String promt ="Đây là toàn bộ testcase của tôi, với mỗi testcase hãy cho tôi 1 data test mẫu ứng với mô tả của từng test case";
////////            System.out.println(tmp);
////            String result = GeminiClient.callGemini("hôm nay là thứ mấy");
////            System.out.println("Gemini raw response:" +"là "+ result+"\n");
////
////
////
////
////    }
//public static void main(String[] args) throws Exception {
//    String prompt = "Hãy cho tôi biết về đất nước việt nam!";
//    String result = utilities.GeminiClient.callGemini(prompt);
//    System.out.println("Gemini trả lời: " + result);
//}
//}
package ptit.orangehrm.testAI;

import utilities.ExcelConfig;
import utilities.GeminiClient;

public class testai {
    public static void main(String[] args) throws Exception {
        //String prompt = "Hãy cho tôi biết về đất nước Việt Nam! Tóm tắt ngắn gọn, ≤ 10 câu, dạng bullet";
//        String result = GeminiClient.callGemini(prompt);
//        System.out.println("Gemini trả lời: " + result);
        ExcelConfig excelConfig;
        excelConfig = ExcelConfig.getExcelData();
        excelConfig.switchToSheet("ClientDta");
        for(int i=1;i<=83;i++){
            String moTa = excelConfig.getCellData("Description", i );
            String yesNo=excelConfig.getCellData("Auto gen data test (Yes/No)", i );
            if(yesNo.equals("Yes")){
                String prompt = "Dựa vào mô tả của testcase, lấy cho tôi data test của case đó, chú ý chỉ cần trả về duy nhất data test không trả về nội dung thừa: Mô tả testcase như sau" +moTa;
                String result = GeminiClient.callGemini(prompt);
                System.out.println("Mô tả" +" "+ i+ ":" + moTa);
                System.out.println("Gemini trả lời: " + result);
            }



        }
    }
}
