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
 //Buffer Overflow là một lỗ hổng phổ biến trong lập trình máy tính,
//xảy ra khi một chương trình ghi dữ liệu ra ngoài phạm vi của một vùng nhớ đệm,
//có thể dẫn đến việc kiểm soát chương trình hoặc thực thi mã độc hại. 
//Cách giải quyết :
  //  	Kiểm tra và xử lý đầu vào an toàn.
//	Sử dụng hàm và phương pháp an toàn để làm việc với dữ liệu. StringBuffer StringBuilder
//	Giới hạn độ dài của dữ liệu để tránh vượt quá vùng nhớ đệm.

/**
 * Servlet implementation class ViewTaiKhoanController
 */
@WebServlet("/views/QTVHT/viewtaikhoan")
public class ViewTaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewTaiKhoanController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String id = request.getParameter("sid");
		 if (id != null && id.length() <= 50) { // Giả sử độ dài tối đa của id là 50 ký tự
		        System.out.println(id);
		        System.out.println("Trang thai xoa");
		       
		        LoginDao logindao = new LoginDao();
		        Login taikhoan = null;
		        
		        try {
		            // Thực hiện truy vấn dữ liệu từ cơ sở dữ liệu
		            taikhoan = logindao.selectLoginsByMaNguoiDung(id).get(0);
		            
		            if (taikhoan != null) {
		                // Nếu tìm thấy dữ liệu, chuyển tiếp đến trang hiển thị thông tin tài khoản
		                request.setAttribute("xemtaikhoan", taikhoan);
		                RequestDispatcher dispatcher = request.getRequestDispatcher("/views/QTVHT/xemtaikhoan.jsp");
		                dispatcher.forward(request, response);
		            } else {
		                // Nếu không tìm thấy dữ liệu, có thể xử lý thông báo tương ứng
		                response.getWriter().println("Không tìm thấy thông tin tài khoản với id: " + id);
		            }
		        } catch (Exception e) {
		            e.printStackTrace();
		            // Xử lý các ngoại lệ có thể xảy ra trong quá trình thực hiện truy vấn
		            response.getWriter().println("Đã xảy ra lỗi khi truy vấn dữ liệu");
		        }
		    } else {
		        // Nếu id là null hoặc vượt quá độ dài cho phép, có thể xử lý thông báo tương ứng
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
