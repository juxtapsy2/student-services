package doancuoiky.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import doancuoiky.dao.LoginDao;

/**
 * Servlet implementation class UpdatePasswordController
 */
@WebServlet("/views/SV/doimatkhau")
public class UpdatePasswordController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdatePasswordController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Lấy giá trị từ form
		 HttpSession session = request.getSession();
        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
      
		String manguoidung = (String) session.getAttribute("MaND");
        System.out.print(manguoidung);
        System.out.print(currentPassword);
        System.out.print(newPassword);
        LoginDao logindao = new LoginDao();
        try {
			boolean succ = logindao.updatePassword(manguoidung, currentPassword, newPassword);
			if(succ) {
				  request.setAttribute("thongbaomatkhau", "Đổi mật khẩu thành công !");
                  request.getRequestDispatcher("/views/SV/doimatkhau.jsp").forward(request, response);
                 // response.sendRedirect("Login.jsp");
			}else {
				  request.setAttribute("thongbaomatkhau", "Không thể đổi mật khẩu !");
                  request.getRequestDispatcher("/views/SV/doimatkhau.jsp").forward(request, response);
			}
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
