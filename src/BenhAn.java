import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BenhAn {
    protected int soThuTu;
    protected String maBenhAn;
    protected String maBenhNhan;
    protected String tenBenhNhan;
    protected Date ngayNhapVien;
    protected Date ngayRaVien;
    protected String lyDoNhapVien;

    public BenhAn(int soThuTu, String maBenhAn, String maBenhNhan, String tenBenhNhan,
                  String ngayNhapVien, String ngayRaVien, String lyDoNhapVien) throws ParseException {
        this.soThuTu = soThuTu;
        this.maBenhAn = maBenhAn;
        this.maBenhNhan = maBenhNhan;
        this.tenBenhNhan = tenBenhNhan;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        this.ngayNhapVien = sdf.parse(ngayNhapVien);
        this.ngayRaVien = sdf.parse(ngayRaVien);
        this.lyDoNhapVien = lyDoNhapVien;
    }

    public abstract String toCSVFormat();

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return String.format("%d,%s,%s,%s,%s,%s,%s",
                soThuTu,"BA-"+ maBenhAn,"BN-"+ maBenhNhan, tenBenhNhan,
                sdf.format(ngayNhapVien), sdf.format(ngayRaVien), lyDoNhapVien);
    }
}
