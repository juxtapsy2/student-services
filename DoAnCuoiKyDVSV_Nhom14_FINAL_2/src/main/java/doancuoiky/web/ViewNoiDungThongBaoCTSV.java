package doancuoiky.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doancuoiky.dao.ThongBaoDao;
import doancuoiky.model.ThongBao;

/**
 * Servlet implementation class ViewNoiDungThongBaoCTSV
 */
@WebServlet("/views/CTSV/xemnoidungCTSV")
public class ViewNoiDungThongBaoCTSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewNoiDungThongBaoCTSV() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String id = request.getParameter("ndid");
		 System.out.print(id);
		 System.out.print("Trang thai xem noi dung");
		 
		 ThongBao thongbao = new ThongBao();
		 ThongBaoDao thongbaodao = new ThongBaoDao();
		 try {
			thongbao = thongbaodao.getThongBaoByMaTB(id);
			 System.out.print(thongbao.getNoiDung());
			   request.setAttribute("noidungthongbao", thongbao.getNoiDung());
               request.getRequestDispatcher("/views/CTSV/index.jsp").forward(request, response);
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
