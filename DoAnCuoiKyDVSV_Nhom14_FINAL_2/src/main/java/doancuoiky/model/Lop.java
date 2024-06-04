package doancuoiky.model;

public class Lop {

	 private String maLop;
	    private String maNganh;
	    private String tenLop;
	    private int namNhapHoc;
	public Lop() {
		// TODO Auto-generated constructor stub
	}
	   // Getter methods
    public String getMaLop() {
        return maLop;
    }

    public String getMaNganh() {
        return maNganh;
    }

    public String getTenLop() {
        return tenLop;
    }

    public int getNamNhapHoc() {
        return namNhapHoc;
    }

    // Setter methods (if needed)
    public void setMaLop(String maLop) {
        this.maLop = maLop;
    }

    public void setMaNganh(String maNganh) {
        this.maNganh = maNganh;
    }

    public void setTenLop(String tenLop) {
        this.tenLop = tenLop;
    }

    public void setNamNhapHoc(int namNhapHoc) {
        this.namNhapHoc = namNhapHoc;
    }

}
