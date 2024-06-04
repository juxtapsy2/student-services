package doancuoiky.web;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import doancuoiky.dao.DKGiayXacNhanSinhVienDao;


@WebServlet("/DKGiayXacNhanSinhVienController")
public class DKGiayXacNhanSinhVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public DKGiayXacNhanSinhVienController() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Retrieve data from the request parameters
		HttpSession session = request.getSession();
        String maDV = request.getParameter("GiayXacNhan");
        String iDGiayXacNhan =  generateRandomID(10);
        String maNguoiDung = (String) session.getAttribute("MaND");
        String namHoc = "2023-2024";
        String hocKy = "1";
        int soLuong = Integer.parseInt(request.getParameter("SoLuong"));
        DKGiayXacNhanSinhVienDao dao = new DKGiayXacNhanSinhVienDao();
        // Lấy thời gian hiện tại
        LocalDateTime currentTime = LocalDateTime.now();

        // Cộng thêm 2 ngày
        LocalDateTime newTime = currentTime.plusDays(2);

        // Chuyển đổi LocalDateTime thành Timestamp
        Timestamp currentTimestamp = Timestamp.valueOf(currentTime);
        Timestamp newTimestamp = Timestamp.valueOf(newTime);
        try {
            // Call the insert method from the DAO
            boolean success = dao.insertDKGiayXacNhanSinhVien(maDV, iDGiayXacNhan, maNguoiDung,
                    namHoc, hocKy, soLuong, currentTimestamp,
                    newTimestamp, "Chua Nhan", "ADD");

            // Send a response back to the client
            if (success) {
                // Thông báo thành công và chuyển hướng trang sau 2 giây
                String successMessage = "Đăng ký thành công !";
                response.getWriter().write("<script>alert('" + successMessage + "'); setTimeout(function(){ window.location.href='/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/SV/dangkygiayxacnhan.jsp'; }, 0);</script>");
            } else {
                // Thông báo thất bại và chuyển hướng trang sau 2 giây
                String failureMessage = "Đăng ký thất bại";
                response.getWriter().write("<script>alert('" + failureMessage + "'); setTimeout(function(){ window.location.href='/DoAnCuoiKyDVSV_Nhom14/views/SV/dangkygiayxacnhan.jsp'; }, 0);</script>");
                response.sendRedirect("/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/SV/dangkygiayxacnhan.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write("<script>alert('Error: " + e.getMessage() + "'); setTimeout(function(){ window.location.href='/DoAnCuoiKyDVSV_Nhom14/views/SV/dangkygiayxacnhan.jsp'; }, 2000);</script>");
        }
     
	}
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateRandomID(int length) {
        if (length <= 0) {
            throw new IllegalArgumentException("Length must be greater than 0");
        }

        StringBuilder randomID = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            randomID.append(randomChar);
        }

        return randomID.toString();
    }

}
