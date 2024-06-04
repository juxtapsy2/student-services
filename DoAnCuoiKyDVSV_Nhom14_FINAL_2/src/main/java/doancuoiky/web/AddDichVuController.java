package doancuoiky.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doancuoiky.dao.DichVuDao;
import doancuoiky.model.DichVu;

/**
 * Servlet implementation class AddDichVuController
 */
@WebServlet("/views/CTSV/themdichvu")
public class AddDichVuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddDichVuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maDV = request.getParameter("MaDV");
        String tenDV = request.getParameter("TenDV");
        String moTa = request.getParameter("MoTa");
        String namHoc = request.getParameter("NamHoc");
        String hocKy = request.getParameter("HocKy");
        DichVu dv = new DichVu();
        DichVuDao dvdao = new DichVuDao();
        dv.setMaDV(maDV);
        dv.setTenDV(tenDV);
        dv.setMoTa(moTa);
        dv.setNamHoc(namHoc);
        dv.setHocKy(hocKy);
        dvdao.insertDichVu(dv);
        response.sendRedirect("quanlydichvu.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
