package doancuoiky.model;

import java.sql.Date;
import java.sql.Timestamp;

public class YeuCau {
	private String maYC;
    private String maDV;
	private String maNguoiDung;
	private int stt;
	private String tieuDeYC;
	private String noiDungYC;
	private Timestamp ngayYC;
	private String trangThai;
	
	public YeuCau() {
		// TODO Auto-generated constructor stub
	}
	 // Getter and Setter methods
    public String getMaYC() {
        return maYC;
    }

    public void setMaYC(String maYC) {
        this.maYC = maYC;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getMaNguoiDung() {
        return maNguoiDung;
    }

    public void setMaNguoiDung(String maNguoiDung) {
        this.maNguoiDung = maNguoiDung;
    }
    
    public int getSTT() {
        return stt;
    }

    public void setSTT(int stt) {
        this.stt = stt;
    }
    
    public String getTieuDeYC() {
        return tieuDeYC;
    }

    public void setTieuDeYC(String tieuDeYC) {
        this.tieuDeYC = tieuDeYC;
    }
    
    public String getNoiDungYC() {
        return noiDungYC;
    }

    public void setNoiDungYC(String noiDungYC) {
        this.noiDungYC = noiDungYC;
    }
    
    public Timestamp getNgayYC() {
        return ngayYC;
    }

    public void setNgayYC(Timestamp ngayYC) {
        this.ngayYC = ngayYC;
    }
    
    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }
}