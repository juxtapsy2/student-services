package doancuoiky.model;

import java.sql.Date;
import java.sql.Timestamp;

public class ThongBao {

	    private String maTB;
	    private String maNguoiGui;
	    private String maNguoiNhan;
	    private String tieuDe;
	    private String noiDung;
	    private Date ngayGui;
	public ThongBao() {
		// TODO Auto-generated constructor stub
	}
	
    public String getMaTB() {
        return maTB;
    }

    public void setMaTB(String maTB) {
        this.maTB = maTB;
    }

    public String getMaNguoiGui() {
        return maNguoiGui;
    }

    public void setMaNguoiGui(String maNguoiGui) {
        this.maNguoiGui = maNguoiGui;
    }

    public String getMaNguoiNhan() {
        return maNguoiNhan;
    }

    public void setMaNguoiNhan(String maNguoiNhan) {
        this.maNguoiNhan = maNguoiNhan;
    }

    public String getTieuDe() {
        return tieuDe;
    }

    public void setTieuDe(String tieuDe) {
        this.tieuDe = tieuDe;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public Date getNgayGui() {
        return ngayGui;
    }

    public void setNgayGui(Date ngayGui) {
        this.ngayGui = ngayGui;
    }

}
