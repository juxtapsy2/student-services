package doancuoiky.model;

public class Khoa {

	 private String maKhoa;
	    private String tenKhoa;
	    private String viTri;

	    // Constructors
	    public Khoa() {
	        // Default constructor
	    }

	    public Khoa(String maKhoa, String tenKhoa, String viTri) {
	        this.maKhoa = maKhoa;
	        this.tenKhoa = tenKhoa;
	        this.viTri = viTri;
	    }

	    // Getters and Setters
	    public String getMaKhoa() {
	        return maKhoa;
	    }

	    public void setMaKhoa(String maKhoa) {
	        this.maKhoa = maKhoa;
	    }

	    public String getTenKhoa() {
	        return tenKhoa;
	    }

	    public void setTenKhoa(String tenKhoa) {
	        this.tenKhoa = tenKhoa;
	    }

	    public String getViTri() {
	        return viTri;
	    }

	    public void setViTri(String viTri) {
	        this.viTri = viTri;
	    }


}
