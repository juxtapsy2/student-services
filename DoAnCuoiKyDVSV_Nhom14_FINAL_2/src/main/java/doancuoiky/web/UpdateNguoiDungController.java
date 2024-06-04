package doancuoiky.web;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doancuoiky.dao.LoginDao;
import doancuoiky.dao.NguoiDungDao;
import doancuoiky.model.Login;
import doancuoiky.model.NguoiDung;

/**
 * Servlet implementation class UpdateNguoiDungController
 */
@WebServlet("/views/QTVHT/updatenguoidung")
public class UpdateNguoiDungController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateNguoiDungController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id = request.getParameter("sid");
        NguoiDungDao nguoidungdao = new NguoiDungDao();
        NguoiDung nguoidung;
		try {
			nguoidung = nguoidungdao.getNguoiDungByMaND(id);
			 request.setAttribute("updatenguoidung", nguoidung);
		        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/QTVHT/capnhatnguoidung.jsp");
		        dispatcher.forward(request, response);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  // Lấy giá trị từ form
	    System.out.println("Dang update nguoidung ");
        String maNguoiDung = request.getParameter("maNguoiDung");
        String hoTen = request.getParameter("hoTen");
        String ngaySinhStr = request.getParameter("ngaySinh");
        // Parse the date string into a Date object (you may need to handle exceptions)
        Date ngaySinh = Date.valueOf(ngaySinhStr);
        String gioiTinh = request.getParameter("gioiTinh");
        String danToc = request.getParameter("danToc");
        String cmnd = request.getParameter("cmnd");
        String tonGiao = request.getParameter("tonGiao");
        String queQuan = request.getParameter("queQuan");
        String maLop = request.getParameter("maLop");
        String vaiTro = request.getParameter("vaiTro");
        String maTinhTrang = request.getParameter("maTinhTrang");
        String diaChi = request.getParameter("diaChi");
        String sdt = request.getParameter("sdt");
        String email = request.getParameter("email");
		/*
		 * System.out.println("Dang update nguoidung " + maNguoiDung);
		 * System.out.println("Ho Ten: " + hoTen); System.out.println("Ngay Sinh: " +
		 * ngaySinh); System.out.println("Gioi Tinh: " + gioiTinh);
		 * System.out.println("Dan Toc: " + danToc); System.out.println("CMND: " +
		 * cmnd); System.out.println("Ton Giao: " + tonGiao);
		 * System.out.println("Que Quan: " + queQuan); System.out.println("Ma Lop: " +
		 * maLop); System.out.println("Vai Tro: " + vaiTro);
		 * System.out.println("Ma Tinh Trang: " + maTinhTrang);
		 * System.out.println("Dia Chi: " + diaChi); System.out.println("SDT: " + sdt);
		 * System.out.println("Email: " + email);
		 */
        NguoiDungDao nguoidungdao = new NguoiDungDao();
        NguoiDung nguoiDung= new NguoiDung();
		
		
		 nguoiDung.setMaNguoiDung(maNguoiDung); nguoiDung.setHoTen(hoTen);
		 nguoiDung.setNgaySinh(ngaySinh); nguoiDung.setGioiTinh(gioiTinh);
		  nguoiDung.setDanToc(danToc); nguoiDung.setCmnd(cmnd);
		  nguoiDung.setTonGiao(tonGiao); nguoiDung.setQueQuan(queQuan);
		  nguoiDung.setMaLop(maLop); nguoiDung.setVaiTro(vaiTro);
		 nguoiDung.setMaTinhTrang(maTinhTrang); nguoiDung.setDiaChi(diaChi);
		  nguoiDung.setSdt(sdt); nguoiDung.setEmail(email);
		 System.out.println("MaNguoiDung: " + nguoiDung.getMaNguoiDung());
		 System.out.println("HoTen: " + nguoiDung.getHoTen());
		 System.out.println("NgaySinh: " + nguoiDung.getNgaySinh());
		 System.out.println("GioiTinh: " + nguoiDung.getGioiTinh());
		  System.out.println("DanToc: " + nguoiDung.getDanToc());
		  System.out.println("Cmnd: " + nguoiDung.getCmnd());
		  System.out.println("TonGiao: " + nguoiDung.getTonGiao());
		  System.out.println("QueQuan: " + nguoiDung.getQueQuan());
		  System.out.println("MaLop: " + nguoiDung.getMaLop());
		  System.out.println("VaiTro: " + nguoiDung.getVaiTro());
		  System.out.println("MaTinhTrang: " + nguoiDung.getMaTinhTrang());
		  System.out.println("DiaChi: " + nguoiDung.getDiaChi());
		  System.out.println("Sdt: " + nguoiDung.getSdt());
		  System.out.println("Email: " + nguoiDung.getEmail()); 
		try {
			nguoidungdao.updateUser(nguoiDung);
			 response.sendRedirect("quanlysinhvien.jsp");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
        
	}

}
