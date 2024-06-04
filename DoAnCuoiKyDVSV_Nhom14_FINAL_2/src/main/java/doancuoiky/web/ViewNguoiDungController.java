package doancuoiky.web;

import java.io.IOException;

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
 * Servlet implementation class ViewNguoiDungController
 */
@WebServlet("/views/QTVHT/viewnguoidung")
public class ViewNguoiDungController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewNguoiDungController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  String id = request.getParameter("sid");
		 System.out.print(id);
		 System.out.print("Trang thai xoa");
		 if (id != null && !id.isEmpty()) {
		        NguoiDungDao nguoidungdao = new NguoiDungDao();
		        NguoiDung nguoidung = null;
		        try {
		            nguoidung = nguoidungdao.getNguoiDungByMaND(id);
		            // Kiểm tra xem người dùng có tồn tại không
		            if (nguoidung != null) {
		                request.setAttribute("xemnguoidung", nguoidung);
		                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/QTVHT/xemnguoidung.jsp");
		                dispatcher.forward(request, response);
		            } else {
		                // Nếu không tìm thấy người dùng, có thể xử lý tại đây, ví dụ:
		                response.getWriter().println("Không tìm thấy thông tin người dùng với id: " + id);
		            }
		        } catch (ClassNotFoundException e) {
		            e.printStackTrace();
		            // Xử lý ngoại lệ ClassNotFoundException
		        }
		    } else {
		        // Nếu id là null hoặc trống, có thể xử lý tại đây, ví dụ:
		        response.getWriter().println("ID không hợp lệ");
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
