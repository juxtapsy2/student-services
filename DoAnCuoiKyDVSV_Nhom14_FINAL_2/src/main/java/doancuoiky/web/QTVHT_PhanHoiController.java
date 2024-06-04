package doancuoiky.web;

import java.io.IOException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.sql.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import doancuoiky.dao.YeuCauDao;
import doancuoiky.dao.LoginDao;
import doancuoiky.dao.PhanHoiDao;
import doancuoiky.dao.ThongBaoDao;
import doancuoiky.dao.ConstantData;
import doancuoiky.web.SendMail;
import doancuoiky.model.Login;
import doancuoiky.model.ThongBao;
import doancuoiky.model.YeuCau;


@WebServlet("/views/QTVHT/phanhoi")
public class QTVHT_PhanHoiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public QTVHT_PhanHoiController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/QTVHT/phanhoi.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Retrieve data from the request parameters
		HttpSession session = request.getSession();
        
		YeuCauDao ycDao = new YeuCauDao();
		YeuCau yc = new YeuCau();
        String maNguoiDung = request.getParameter("QTVHT_txtPhanHoiMaND");
        String maYC = request.getParameter("QTVHT_txtPhanHoiMaYC");
        try {
			yc = ycDao.getYeuCauCuThe(maNguoiDung, maYC);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			response.getWriter().write("<script>alert('Error: " + e.getMessage() + "'); setTimeout(function(){ window.location.href='/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/QTVHT/phanhoi.jsp'; }, 1000);</script>");
		}
        String maPH = "PH"+maYC+generateRandomID(2);
        String maNguoiPH = (String)session.getAttribute("MaND");
        String tieuDePH = request.getParameter("QTVHT_txtTieuDePH");
        String noiDungPH = request.getParameter("QTVHT_txtNoiDungPH");
        Timestamp ngayPH = new java.sql.Timestamp(System.currentTimeMillis());
        Date date = new Date(ngayPH.getTime());
        PhanHoiDao phDao = new PhanHoiDao();
        
        SendMail mailProcessor = new SendMail();
        ThongBaoDao thongbaoDao = new ThongBaoDao();
        ThongBao thongbao = new ThongBao();
        thongbao.setMaNguoiGui(maNguoiPH);
        thongbao.setMaTB("TB"+generateRandomID(6));
        thongbao.setMaNguoiNhan(maNguoiDung);
        thongbao.setNgayGui(date);
        thongbao.setTieuDe(tieuDePH);
        thongbao.setNoiDung(noiDungPH);    
        
        try {
            // Call the insert method from the DAO
            boolean success = phDao.insertPhanHoi(maPH, maYC, maNguoiPH,
                    tieuDePH, noiDungPH, ngayPH);
            ycDao.updateTrangThaiYeuCau(maYC, "Da phan hoi");
            // Send a response back to the client
            if (success) {
                String successMessage = "Reply sent!";
                /*for (addr : SVMailList) {
                	mailProcessor.send(addr, tieuDeYC, noiDungYC, addr.getUsername(), addr.getPassword());
                }*/
                //Thay doi trang thai yeu cau khi da phan hoi
              
                thongbaoDao.addNewNotification(thongbao);
                mailProcessor.send(ConstantData.sv01.getUsername(), tieuDePH, noiDungPH, ConstantData.qtv01.getUsername(), ConstantData.qtv01.getPassword());               
            	mailProcessor.send(ConstantData.sv02.getUsername(), tieuDePH, noiDungPH, ConstantData.qtv01.getUsername(), ConstantData.qtv01.getPassword());
                response.getWriter().write("<script>alert('" + successMessage + "'); setTimeout(function(){ window.location.href='/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/QTVHT/phanhoi.jsp'; }, 500);</script>");
                
            } else {
                String failureMessage = "Reply failed!";
                response.getWriter().write("<script>alert('" + failureMessage + "'); setTimeout(function(){ window.location.href='/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/QTVHT/phanhoi.jsp'; }, 1000);</script>");
                response.sendRedirect("/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/QTVHT/phanhoi.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write("<script>alert('Error: " + e.getMessage() + "'); setTimeout(function(){ window.location.href='/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/QTVHT/phanhoi.jsp'; }, 1000);</script>");
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