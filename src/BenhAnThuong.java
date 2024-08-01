import java.text.ParseException;

public class BenhAnThuong extends BenhAn {
    private double phiNamVien;

    public BenhAnThuong(int soThuTu, String maBenhAn, String maBenhNhan, String tenBenhNhan,
                        String ngayNhapVien, String ngayRaVien, String lyDoNhapVien, double phiNamVien) throws ParseException {
        super(soThuTu, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien);
        this.phiNamVien = phiNamVien;
    }

    @Override
    public String toCSVFormat() {
        return super.toString() + "," + phiNamVien;
    }
}
