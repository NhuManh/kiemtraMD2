import java.io.*;
import java.text.ParseException;
import java.util.*;

public class Main {
    private static final String FILE_PATH = "src/benh_an.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            while (true) {
                System.out.println("Menu:");
                System.out.println("1. Them moi benh an");
                System.out.println("2. Xoa benh an");
                System.out.println("3. Xem danh sach");
                System.out.println("4. Thoat");
                System.out.print("Chon chuc nang: ");
                int chon = scanner.nextInt();
                scanner.nextLine();  // consume newline

                switch (chon) {
                    case 1:
                        themMoiBenhAn(scanner);
                        break;
                    case 2:
                        xoaBenhAn(scanner);
                        break;
                    case 3:
                        xemDanhSach();
                        break;
                    case 4:
                        System.out.println("Thoat chuong trinh.");
                        return;
                    default:
                        System.out.println("Chuc nang khong hop le.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void themMoiBenhAn(Scanner scanner) throws IOException, ParseException {
        int soThuTu = laySoThuTuCuoi() + 1;
        System.out.print("Nhap ma benh an (BA-XXX): ");
        String maBenhAn =scanner.nextLine();
        if (daTonTai(maBenhAn)) {
            System.out.println("Bệnh án đã tồn tại.");
            return;
        }
        System.out.print("Nhap ma benh nhan (BN-XXX): ");
        String maBenhNhan = scanner.nextLine();
        System.out.print("Nhap ten benh nhan: ");
        String tenBenhNhan = scanner.nextLine();
        String ngayNhapVien = Validate.validateNgayThang(scanner,"Nhap ngay nhap vien (dd/MM/yyyy): ");
        String ngayRaVien = Validate.validateNgayThang(scanner,"Nhap ngay ra vien (dd/MM/yyyy): ");
        System.out.print("Nhap ly do nhap vien: ");
        String lyDoNhapVien = scanner.nextLine();

        System.out.print("Loai benh an (1: Thuong, 2: VIP): ");
        int loaiBenhAn = scanner.nextInt();
        scanner.nextLine();

        BenhAn benhAn;
        if (loaiBenhAn == 1) {
            System.out.print("Nhap phi nam vien: ");
            double phiNamVien = scanner.nextDouble();
            scanner.nextLine();
            benhAn = new BenhAnThuong(soThuTu, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, phiNamVien);
        } else if (loaiBenhAn == 2) {
            System.out.print("Nhap loai VIP (VIP I, VIP II, VIP III): ");
            String loaiVIP = scanner.nextLine();
            String thoiHanVIP = Validate.validateNgayThang(scanner,"Nhap thoi han VIP (dd/MM/yyyy): ");
            benhAn = new BenhAnVIP(soThuTu, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien, loaiVIP, thoiHanVIP);
        } else {
            System.out.println("Loai benh an khong hop le.");
            return;
        }

        ghiVaoFileCSV(benhAn);
    }

    private static void ghiVaoFileCSV(BenhAn benhAn) throws IOException {
        try (FileWriter writer = new FileWriter(FILE_PATH, true);
             BufferedWriter bw = new BufferedWriter(writer)) {
            bw.write(benhAn.toCSVFormat());
            bw.newLine();
        }
    }

    private static boolean daTonTai(String maBenhAn) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.contains(maBenhAn)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static int laySoThuTuCuoi() throws IOException {
        int maxSoThuTu = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                int soThuTu = Integer.parseInt(columns[0]);
                if (soThuTu > maxSoThuTu) {
                    maxSoThuTu = soThuTu;
                }
            }
        }
        return maxSoThuTu;
    }

    private static void xoaBenhAn(Scanner scanner) throws IOException {
        System.out.print("Nhap ma benh an can xoa: ");
        String maBenhAn = scanner.nextLine();

        if (daTonTai(maBenhAn)) {
            System.out.print("Ban co chac muon xoa? (Yes/No): ");
            String xacNhan = scanner.nextLine();
            if (xacNhan.equalsIgnoreCase("Yes")) {
                xoaBenhAnTuFile(maBenhAn);
                System.out.println("Da xoa benh an. Danh sach benh an sau khi xoa:");
                xemDanhSach();
            } else {
                System.out.println("Quay ve menu chinh.");
            }
        } else {
            System.out.println("Ma benh an khong ton tai.");
        }
    }

    private static void xoaBenhAnTuFile(String maBenhAn) throws IOException {
        File inputFile = new File(FILE_PATH);
        File tempFile = new File("src/benh_an.csv");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.contains(maBenhAn)) {
                    bw.write(line);
                    bw.newLine();
                }
            }
        }
        inputFile.delete();
        tempFile.renameTo(inputFile);
    }

    private static void xemDanhSach() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] columns = line.split(",");
                System.out.print(columns[0] +",");
                System.out.print(columns[1] +",");
                System.out.print(columns[2] +",");
                System.out.print(columns[3] +",");
                System.out.print(columns[4] +",");
                System.out.print(columns[5] +",");
                System.out.print(columns[6] +".");
                if (columns.length > 7) {
                    System.out.println(columns[7] +".");
                } else if (columns.length > 8) {
                    System.out.print(columns[7] +".");
                    //System.out.print(columns[8] +",");
                }
                System.out.println();
            }
        }
    }
}
