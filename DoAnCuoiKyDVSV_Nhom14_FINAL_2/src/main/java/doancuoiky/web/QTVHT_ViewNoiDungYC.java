package doancuoiky.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doancuoiky.dao.LoginDao;
import doancuoiky.dao.YeuCauDao;
import doancuoiky.model.Login;
import doancuoiky.model.YeuCau;
/**
 * Servlet implementation class ViewTaiKhoanController
 */
@WebServlet("/views/QTVHT/QTVHT_viewnoidungyc")
public class QTVHT_ViewNoiDungYC extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public QTVHT_ViewNoiDungYC() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("sid");
		String ycID = request.getParameter("ycid");
		System.out.print(id);
		System.out.print(ycID);
		YeuCauDao ycDao = new YeuCauDao();
		YeuCau yc = new YeuCau();
		try {
			yc = ycDao.getYeuCauCuThe(id, ycID);
			System.out.print(yc.getNoiDungYC());
			request.setAttribute("xemnoidungyc", yc.getNoiDungYC());
            request.getRequestDispatcher("/views/QTVHT/phanhoi.jsp").forward(request, response);
            response.sendRedirect("viewnoidungyc.jsp");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
