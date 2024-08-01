
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
public class Validate {
    public static boolean validateMaBenhAn(String code) {
        return false;
    }
    public static boolean validateMaBenhNhan(String code) {
        return false;
    }
    public static String validateNgayThang(Scanner sc,String prompt) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        String dateStr = "";
        while (true) {
            System.out.print(prompt);
            dateStr = sc.nextLine();
            try {
                dateFormat.parse(dateStr);
                break;
            } catch (ParseException e) {
                System.out.println("Ngày sinh không hợp lệ. Định dạng phải là dd/MM/yyyy. Vui lòng nhập lại.");
            }
        }
        return dateStr;
    }
}