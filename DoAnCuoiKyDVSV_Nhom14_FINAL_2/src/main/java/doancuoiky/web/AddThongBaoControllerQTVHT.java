package doancuoiky.web;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Date;
import java.sql.Timestamp;

import javax.servlet.ServletException;
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
 * Servlet implementation class AddThongBaoControllerQTVHT
 */
@WebServlet("/views/QTVHT/themthongbao")
public class AddThongBaoControllerQTVHT extends HttpServlet {

	private static final long serialVersionUID = 1L;
	 private static final String PREFIX = "TB";
	private static final int TOKEN_LENGTH = 7; // 9
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddThongBaoControllerQTVHT() {
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
		String maThongBao = generateRandomToken();
        String maNguoiNhan = Encode.forHtml(request.getParameter("MaNguoiNhan"));
        String tieuDe = Encode.forHtml(request.getParameter("TieuDe"));
        String noiDung = Encode.forHtml(request.getParameter("NoiDung"));
        
        try {
            maNguoiNhan = truncateIfNecessary(maNguoiNhan, 10);
            tieuDe = truncateIfNecessary(tieuDe, 100);
            noiDung = truncateIfNecessary(noiDung, 3000); // Fix here: Change maxLength to 3000
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return;
        }

        if (!isValidInput(maNguoiNhan) || !isValidInput(tieuDe) || !isValidInput(noiDung)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Dữ liệu không hợp lệ.");
            return;
        }

        Date ngayguoi = new java.sql.Date(System.currentTimeMillis());
        
        HttpSession session = request.getSession();
        NguoiDung nguoidung1 = (NguoiDung) session.getAttribute("NguoiDung");
        String maNguoigui = nguoidung1.getMaNguoiDung();
        
        ThongBao thongbao = new ThongBao();
        ThongBaoDao thongbaodao = new ThongBaoDao();
        thongbao.setMaNguoiGui(maNguoigui);
        thongbao.setMaNguoiNhan(maNguoiNhan);
        thongbao.setNoiDung(noiDung);
        thongbao.setTieuDe(tieuDe);
        thongbao.setNgayGui(ngayguoi);
        thongbao.setMaTB(maThongBao);
        
        try {
            thongbaodao.addNewNotification(thongbao);
            request.setAttribute("thongBao", "Thông báo đã được gửi thành công!");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            request.setAttribute("thongBao", "Có lỗi xảy ra, không thể gửi thông báo.");
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
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
            throw new IllegalArgumentException("Độ dài đầu vào vượt quá giới hạn cho phép.");
        }
        return input;
    }

    private boolean isValidInput(String input) {
        return input.matches("[a-zA-Z0-9\\s]+");
    }
}