package doancuoiky.web;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.owasp.encoder.Encode;
import doancuoiky.dao.ThongBaoDao;
import doancuoiky.model.NguoiDung;
import doancuoiky.model.ThongBao;

/**
 * Servlet implementation class AddThongBaoController
 */
@WebServlet("/views/CTSV/themthongbao")
public class AddThongBaoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	 private static final String PREFIX = "TB";
	    private static final int TOKEN_LENGTH = 7; // 9
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddThongBaoController() {
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
		  // Đọc dữ liệu từ form
		String maThongBao = generateRandomToken() ;
		String maNguoiNhan = Encode.forHtml(request.getParameter("MaNguoiNhan"));
	    String tieuDe = Encode.forHtml(request.getParameter("TieuDe")); // 100 ki tu 
	    String noiDung = Encode.forHtml(request.getParameter("NoiDung"));
	    try {
	        maNguoiNhan = truncateIfNecessary(maNguoiNhan, 10);
	        tieuDe = truncateIfNecessary(tieuDe, 100);
	        noiDung = truncateIfNecessary(noiDung, 2000);
	    } catch (IllegalArgumentException e) {
	        // Thông báo lỗi cho người dùng và yêu cầu nhập lại
	        response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
	        return;
	    }
        if (!isValidInput(maNguoiNhan)) {
            // Xử lý khi MaNguoiNhan không hợp lệ (ví dụ: gửi mã lỗi và dừng xử lý)
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "MaNguoiNhan không hợp lệ.");
            return;
        }

        // Kiểm tra hợp lệ của TieuDe
        if (!isValidInput(tieuDe)) {
            // Xử lý khi TieuDe không hợp lệ (ví dụ: gửi mã lỗi và dừng xử lý)
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "TieuDe không hợp lệ.");
            return;
        }

        // Kiểm tra hợp lệ của NoiDung
        if (!isValidInput(noiDung)) { // 
            // Xử lý khi NoiDung không hợp lệ (ví dụ: gửi mã lỗi và dừng xử lý)
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "NoiDung không hợp lệ.");
            return;
        }
        Date ngayguoi = new java.sql.Date(System.currentTimeMillis());
        
        HttpSession session = request.getSession();
       
		NguoiDung nguoidung1 = (NguoiDung)session.getAttribute("NguoiDung");
        String maNguoigui = nguoidung1.getMaNguoiDung();
        
        
        ThongBao thongbao =  new ThongBao();
        ThongBaoDao thongbaodao = new ThongBaoDao();
        thongbao.setMaNguoiGui(maNguoigui);
        thongbao.setMaNguoiNhan(maNguoiNhan);
        thongbao.setNoiDung(noiDung);
        thongbao.setTieuDe(tieuDe);
        thongbao.setNgayGui(ngayguoi);
        thongbao.setMaTB(maThongBao);
        try {
            thongbaodao.addNewNotification(thongbao);
            // Đặt thông báo thành công vào request attribute
            request.setAttribute("thongBao", "Thông báo đã được gửi thành công!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Đặt thông báo không thành công vào request attribute
            request.setAttribute("thongBao", "Có lỗi xảy ra, không thể gửi thông báo.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
        System.out.print("Trang thai them thong bao");
        
        // Thực hiện xử lý dữ liệu (ví dụ: lưu vào cơ sở dữ liệu)

        // Trả về kết quả (có thể chuyển hướng đến trang khác)
        response.getWriter().println("Thông báo đã được gửi thành công!" + maNguoigui);
    
	}
	public static String generateRandomToken() {
		
        SecureRandom random = new SecureRandom();
        StringBuilder token = new StringBuilder(PREFIX);

        for (int i = 0; i < TOKEN_LENGTH; i++) {
            char randomChar = (char) (random.nextInt(26) + 'A');
            token.append(randomChar);
        }

        return token.toString();
    }
	private String truncateIfNecessary(String input, int maxLength) {
	    if (input.length() > maxLength) {
	        // Thông báo lỗi cho người dùng và yêu cầu nhập lại
	        throw new IllegalArgumentException("Độ dài đầu vào vượt quá giới hạn cho phép.");
	    }
	    return input;
	}
	private boolean isValidInput(String input) {
	    return input.matches("[a-zA-Z0-9\\s]+");
	}
}
