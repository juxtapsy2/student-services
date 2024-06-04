package doancuoiky.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doancuoiky.dao.DKGiayXacNhanSinhVienDao;
import doancuoiky.dao.DichVuDao;

/**
 * Servlet implementation class DeleteDichVuController
 */
@WebServlet("/views/CTSV/deletedichvu")
public class DeleteDichVuController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteDichVuController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("dvid");
		 System.out.print(id);
		 System.out.print("Trang thai xoa");
		 DichVuDao dvdao = new DichVuDao();
		 
		 dvdao.deleteDichVuByMaDV(id);
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
