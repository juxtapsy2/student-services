package doancuoiky.model;

import java.sql.Date;
import java.sql.Timestamp;

public class PhanHoi {
	private String maPH;
    private String maYC;
    private String maND;
	private String tieuDePH;
	private String noiDungPH;
	private Timestamp ngayPH;
	
	public PhanHoi() {
		// TODO Auto-generated constructor stub
	}
	 // Getter and Setter methods
	public String getMaPH() {
        return maPH;
    }

    public void setMaPH(String maPH) {
        this.maPH = maPH;
    }
    public String getMaYC() {
        return maYC;
    }

    public void setMaYC(String maYC) {
        this.maYC = maYC;
    }
    
    public String getMaND() {
        return maND;
    }

    public void setMaND(String maND) {
        this.maND = maND;
    }
    
    public String getTieuDePH() {
        return tieuDePH;
    }

    public void setTieuDePH(String tieuDePH) {
        this.tieuDePH = tieuDePH;
    }
    
    public String getNoiDungPH() {
        return noiDungPH;
    }

    public void setNoiDungPH(String noiDungPH) {
        this.noiDungPH = noiDungPH;
    }
    
    public Timestamp getNgayPH() {
        return ngayPH;
    }

    public void setNgayPH(Timestamp ngayPH) {
        this.ngayPH = ngayPH;
    }
}