import java.text.ParseException;

public class BenhAnVIP extends BenhAn {
    private String loaiVIP;
    private String thoiHanVIP;

    public BenhAnVIP(int soThuTu, String maBenhAn, String maBenhNhan, String tenBenhNhan,
                     String ngayNhapVien, String ngayRaVien, String lyDoNhapVien, String loaiVIP, String thoiHanVIP) throws ParseException {
        super(soThuTu, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien);
        this.loaiVIP = loaiVIP;
        this.thoiHanVIP = thoiHanVIP;
    }

    @Override
    public String toCSVFormat() {
        return super.toString() + "," + loaiVIP + "," + thoiHanVIP;
    }
}
