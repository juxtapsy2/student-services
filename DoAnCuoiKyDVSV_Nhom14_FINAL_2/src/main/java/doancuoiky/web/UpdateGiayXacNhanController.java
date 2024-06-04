package doancuoiky.web;

import java.io.IOException;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doancuoiky.dao.DKGiayXacNhanSinhVienDao;
import doancuoiky.dao.DichVuDao;
import doancuoiky.dao.NguoiDungDao;
import doancuoiky.model.DKGiayXacNhanSinhVien;
import doancuoiky.model.DichVu;
import doancuoiky.model.NguoiDung;

/**
 * Servlet implementation class UpdateGiayXacNhanController
 */
@WebServlet("/views/CTSV/updategiayxacnhan")
public class UpdateGiayXacNhanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateGiayXacNhanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		    String id = request.getParameter("gxnid");
	        DKGiayXacNhanSinhVienDao gxnsvdao = new DKGiayXacNhanSinhVienDao();
	        
	        DKGiayXacNhanSinhVien gxnsv = new DKGiayXacNhanSinhVien();
			try {
				gxnsv = gxnsvdao.getDKGiayXacNhanSinhVienByID(id);
				 request.setAttribute("updategiayxacnhan", gxnsv);
			        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/CTSV/capnhatgiayxacnhan.jsp");
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
        String maNguoiDung = request.getParameter("MaNguoiDung");
        String IDGIAY = request.getParameter("IDGiay");
        String stt = request.getParameter("STT");
        String soLuong = request.getParameter("SoLuong");
        String ngayDangKyStr = request.getParameter("NgayDangKy");
        String thoiGianNhanStr = request.getParameter("ThoiGianNhan");
        String tinhTrang = request.getParameter("TinhTrang");

        // Chuyển đổi chuỗi ngày thành đối tượng Date
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
      
       
        Timestamp thoiGianNhan = null;

        try {
        	  thoiGianNhan = new Timestamp(dateFormat.parse(thoiGianNhanStr).getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        DKGiayXacNhanSinhVienDao gxndao = new DKGiayXacNhanSinhVienDao();
        DKGiayXacNhanSinhVien gxn = new  DKGiayXacNhanSinhVien();
       
        gxndao.updateDKGiayXacNhanSinhVien(IDGIAY, thoiGianNhan, tinhTrang);
        response.sendRedirect("quanlygiayxacnhan.jsp");
	}

}
