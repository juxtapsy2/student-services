package doancuoiky.model;

import java.io.Serializable;
import java.util.Date;

public class Login implements Serializable {

	 private static final long serialVersionUID = 1L;
	    private int ID;
	    private String MaNguoiDung;
	    private String TenDangNhap;
	    private String MatKhau;
	    private String VaiTro;
	    private int LuotTruyCap;
	    private Date TruyCapGanNhat;

	    public Login() {
	        // Constructor mặc định
	    }

	    // Getters và setters cho các thuộc tính

	    public int getID() {
	        return ID;
	    }

	    public void setID(int ID) {
	        this.ID = ID;
	    }

	    public String getMaNguoiDung() {
	        return MaNguoiDung;
	    }

	    public void setMaNguoiDung(String maNguoiDung) {
	        MaNguoiDung = maNguoiDung;
	    }

	    public String getTenDangNhap() {
	        return TenDangNhap;
	    }

	    public void setTenDangNhap(String tenDangNhap) {
	        TenDangNhap = tenDangNhap;
	    }

	    public String getMatKhau() {
	        return MatKhau;
	    }

	    public void setMatKhau(String matKhau) {
	        MatKhau = matKhau;
	    }

	    public String getVaiTro() {
	        return VaiTro;
	    }

	    public void setVaiTro(String vaiTro) {
	        VaiTro = vaiTro;
	    }

	    public int getLuotTruyCap() {
	        return LuotTruyCap;
	    }

	    public void setLuotTruyCap(int luotTruyCap) {
	        LuotTruyCap = luotTruyCap;
	    }

	    public Date getTruyCapGanNhat() {
	        return TruyCapGanNhat;
	    }

		public void setTruyCapGanNhat(java.util.Date truyCapGanNhat2) {
		    
			 TruyCapGanNhat = truyCapGanNhat2;
		}
         
	  

}
