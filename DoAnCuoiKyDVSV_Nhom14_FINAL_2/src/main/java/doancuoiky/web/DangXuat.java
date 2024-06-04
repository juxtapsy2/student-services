package doancuoiky.web;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class DangXuat
 */
/* @WebServlet("/views/SV/dangxuat") */
@WebServlet( urlPatterns = {"/views/SV/dangxuat", "/views/CTSV/dangxuat", "/views/QTVHT/dangxuat"})
public class DangXuat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DangXuat() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 try {
	            // Invalidate all sessions
	            Enumeration<String> sessionAttributeNames = request.getSession().getAttributeNames();
	            while (sessionAttributeNames.hasMoreElements()) {
	                String attributeName = sessionAttributeNames.nextElement();
	                request.getSession().removeAttribute(attributeName);
	            }

	            HttpSession session = request.getSession(false);
	            if (session != null) {
	                session.invalidate();
	            }

	            // Redirect to the login page
	            response.sendRedirect(request.getContextPath()+ "/views/dangnhap/dangnhap.jsp");
	        } catch (Exception e) {
	            // Handle exceptions
	            response.getWriter().println("Error deleting sessions: " + e.getMessage());
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
