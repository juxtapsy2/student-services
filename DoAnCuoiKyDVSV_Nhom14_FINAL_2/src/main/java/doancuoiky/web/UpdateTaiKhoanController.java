package doancuoiky.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doancuoiky.dao.LoginDao;
import doancuoiky.model.Login;

/**
 * Servlet implementation class UpdateTaiKhoanController
 */
@WebServlet("/views/QTVHT/updatetaikhoan")
public class UpdateTaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTaiKhoanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

            String id = request.getParameter("sid");
            LoginDao logindao = new LoginDao();
            Login login = logindao.selectLoginsByMaNguoiDung(id).get(0);
            request.setAttribute("updatetaikhoan", login);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/views/QTVHT/capnhattaikhoan.jsp");
	        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
       
		
		
		
     	  System.out.println("Dang update");
     	 String maND = request.getParameter("MaND");
         String username = request.getParameter("username");
         String password = request.getParameter("password");
         String role = request.getParameter("role");

         
         
  
         LoginDao logindao = new LoginDao();
          
         try {
			logindao.updateLoginRecord(maND, username, password, role);
			 response.sendRedirect("quanlytaikhoan.jsp");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       

    
         
	}

}
