package doancuoiky.model;

import java.util.Date;

public class DKGiayXacNhanSinhVien {
	 private String maDV;
	    private String iDGiayXacNhan;
	    private String maNguoiDung;
	    private String namHoc;
	    private String hocKy;
	    private int stt;
	    private int soLuong;
	    private Date ngayDangKy;
	    private Date thoiGianNhan;
	    private String tinhTrang;
	    private String tenDichVu; // Thêm dòng này

	    public DKGiayXacNhanSinhVien() {
	        // TODO Auto-generated constructor stub
	    }

	    public String getMaDV() {
	        return maDV;
	    }

	    public void setMaDV(String maDV) {
	        this.maDV = maDV;
	    }

	    public String getIDGiayXacNhan() {
	        return iDGiayXacNhan;
	    }

	    public void setIDGiayXacNhan(String iDGiayXacNhan) {
	        this.iDGiayXacNhan = iDGiayXacNhan;
	    }

	    public String getMaNguoiDung() {
	        return maNguoiDung;
	    }

	    public void setMaNguoiDung(String maNguoiDung) {
	        this.maNguoiDung = maNguoiDung;
	    }

	    public String getNamHoc() {
	        return namHoc;
	    }

	    public void setNamHoc(String namHoc) {
	        this.namHoc = namHoc;
	    }

	    public String getHocKy() {
	        return hocKy;
	    }

	    public void setHocKy(String hocKy) {
	        this.hocKy = hocKy;
	    }

	    public int getStt() {
	        return stt;
	    }

	    public void setStt(int stt) {
	        this.stt = stt;
	    }

	    public int getSoLuong() {
	        return soLuong;
	    }

	    public void setSoLuong(int soLuong) {
	        this.soLuong = soLuong;
	    }

	    public Date getNgayDangKy() {
	        return ngayDangKy;
	    }

	    public void setNgayDangKy(Date ngayDangKy) {
	        this.ngayDangKy = ngayDangKy;
	    }

	    public Date getThoiGianNhan() {
	        return thoiGianNhan;
	    }

	    public void setThoiGianNhan(Date thoiGianNhan) {
	        this.thoiGianNhan = thoiGianNhan;
	    }

	    public String getTinhTrang() {
	        return tinhTrang;
	    }

	    public void setTinhTrang(String tinhTrang) {
	        this.tinhTrang = tinhTrang;
	    }

	    public String getTenDichVu() {
	        return tenDichVu;
	    }

	    public void setTenDichVu(String tenDichVu) {
	        this.tenDichVu = tenDichVu;
	    }
	
}
