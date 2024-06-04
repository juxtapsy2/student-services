package doancuoiky.web;
//Cross-site Scripting (XSS) là một kxy thuật tấn công mà người tấn công chèn mã javaScript 
//hoặc HTML độc hại vào một trang web hoặc ứng dụng web. Kẻ tấn công có thể lấy thông tin đăng nhập,
// thực hiện các hành động vượt quá chức năng cho phép thậm chí kiểm soát hoàn toàn trang web.
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List; 

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doancuoiky.dao.NguoiDungDao;
import doancuoiky.dao.TinhTrangDao;
import doancuoiky.model.NguoiDung;
import doancuoiky.model.TinhTrang;
// ImPORT  OWASP Encoder: OWASP Encoder là một thư viện Java được sử dụng để mã hóa dữ liệu trước
//khi hiển thị trên trang web, nhằm ngăn chặn tấn công XSS.
//Thư viện cung cấp các phương thức để mã hóa các ký tự đặc biệt trong HTML, JavaScript, URL, v.v.
//Reflected XSS là hình thức tấn công được sử dụng nhiều nhất. Đây là nơi mã script độc hại 
//đến từ HTTP request. Từ đó, hacker đánh cắp dữ liệu của người dùng, chiếm quyền truy cập
//và hoạt động của họ trên website thông 
//qua việc chia sẻ URL chứa mã độc. Hình thức này thường nhắm đến ít nạn nhân.
import org.owasp.encoder.Encode;
/**
 * Servlet implementation class AddNguoiDungController
 */
@WebServlet("/views/QTVHT/themnguoidung")
public class AddNguoiDungController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddNguoiDungController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String maNguoiDung = Encode.forHtml(request.getParameter("maNguoiDung"));
		if (maNguoiDung == null || maNguoiDung.isEmpty() || maNguoiDung.length() > 10 || !maNguoiDung.matches("[A-Za-z0-9]+")) {
		    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mã người dùng không hợp lệ");
		    return;
		}
        String hoTen = request.getParameter("hoTen");
        String ngaySinhStr = request.getParameter("ngaySinh");
		Date ngaySinh = null;
		if (ngaySinhStr != null && ngaySinhStr.length() <= 10) {
		    try {
		        // Parse the date string into a Date object
		        ngaySinh = Date.valueOf(ngaySinhStr);
		    } catch (IllegalArgumentException e) {     
		        e.printStackTrace(); 
		        return;
		    }
		} 
		
		String gioiTinh = request.getParameter("gioiTinh");
		if (gioiTinh == null || gioiTinh.isEmpty() || (!gioiTinh.equals("Nam") && !gioiTinh.equals("Nu") && !gioiTinh.equals("Khác"))) {
		    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Giới tính không hợp lệ");
		    return;
		}
	    String danToc = request.getParameter("danToc");
	    if (danToc == null || danToc.isEmpty()) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dân tộc không được để trống");
	        return;
	    }   
	    if (!danToc.matches("[a-zA-Z]+")) {     
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dân tộc không hợp lệ");
	        return;
	    }
	    String cmnd = Encode.forHtml(request.getParameter("cmnd"));
	    if (cmnd == null || cmnd.isEmpty() || !cmnd.matches("\\d{12}")) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Số CMND không hợp lệ");
	        return;
	    }
	    String tonGiao = Encode.forHtml(request.getParameter("tonGiao"));
	    if (tonGiao == null || tonGiao.isEmpty() || !tonGiao.matches("[a-zA-Z]+")) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Tôn giáo không hợp lệ");
	        return;
	    }
	    String queQuan = Encode.forHtml(request.getParameter("queQuan"));
	    if (!queQuan.matches("^[a-zA-Z\\s-]+(-[a-zA-Z\\s-]+)?,[a-zA-Z\\s-]+$")) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Quê quán không đúng định dạng");
	        return;
	    }
	    String maLop = Encode.forHtml(request.getParameter("maLop"));
	    if (maLop == null || maLop.isEmpty() || !maLop.matches("[A-Za-z0-9]+")) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mã lớp không hợp lệ");
	        return;
	    }

	    String vaiTro = Encode.forHtml(request.getParameter("vaiTro"));
	    List<String> validRoles = Arrays.asList("SV", "CTSV", "QTVHT");

	 if (!validRoles.contains(vaiTro)) {
	     response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Vai trò không hợp lệ");
	     return;
	 }
	    String maTinhTrang = Encode.forHtml(request.getParameter("maTinhTrang"));
	    TinhTrangDao tinhTrangDao = new TinhTrangDao();
	    List<TinhTrang> tinhTrangList = null;
	    try {
	        tinhTrangList = tinhTrangDao.getAllTinhTrang();
	    } catch (Exception e) {
	        // Xử lý ngoại lệ nếu có
	        e.printStackTrace();
	        // Hoặc có thể trả về lỗi cho client
	        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Lỗi khi lấy danh sách mã tình trạng từ cơ sở dữ liệu");
	        return;
	    }

	    // Tiếp tục xử lý validation và thêm mã tình trạng vào danh sách hợp lệ
	    List<String> validMaTinhTrangList = new ArrayList<>();
	    for (TinhTrang tinhTrang : tinhTrangList) {
	        validMaTinhTrangList.add(tinhTrang.getMaTinhTrang());
	    }
	    if (!validMaTinhTrangList.contains(maTinhTrang)) {
	        // Xử lý lỗi khi mã tình trạng không hợp lệ
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Mã tình trạng không hợp lệ");
	        return;
	    }
	    String diaChi = Encode.forHtml(request.getParameter("diaChi"));
	    if (diaChi.matches(".*[^a-zA-Z0-9\\s,].*")) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Địa chỉ không được chứa các ký tự đặc biệt ngoài dấu phẩy (,)");
	        return;
	    }
	    String sdt =Encode.forHtml(request.getParameter("sdt"));
	    if (!sdt.matches("\\d{10}")) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Số điện thoại phải có 10 chữ số");
	        return;
	    }
	    String email = Encode.forHtml(request.getParameter("email"));
	    if (email == null || !email.matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Email không hợp lệ");
	        return;
	    }
	        // Create a new NguoiDung object and set its properties
	        NguoiDung nguoiDung = new NguoiDung();
	        nguoiDung.setMaNguoiDung(maNguoiDung);
	        nguoiDung.setHoTen(hoTen);
	        nguoiDung.setNgaySinh(ngaySinh);
	        nguoiDung.setGioiTinh(gioiTinh);
	        nguoiDung.setDanToc(danToc);
	        nguoiDung.setCmnd(cmnd);
	        nguoiDung.setTonGiao(tonGiao);
	        nguoiDung.setQueQuan(queQuan);
	        nguoiDung.setMaLop(maLop);
	        nguoiDung.setVaiTro(vaiTro);
	        nguoiDung.setMaTinhTrang(maTinhTrang);
	        nguoiDung.setDiaChi(diaChi);
	        nguoiDung.setSdt(sdt);
	        nguoiDung.setEmail(email);

	         NguoiDungDao nguoidungdao = new NguoiDungDao();
	         try {
				nguoidungdao.addNewUser(nguoiDung);
				 response.sendRedirect("quanlysinhvien.jsp");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	      
		
	}

}
