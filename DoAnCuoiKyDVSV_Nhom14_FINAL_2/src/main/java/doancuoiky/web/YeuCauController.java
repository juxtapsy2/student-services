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
import doancuoiky.dao.ThongBaoDao;
import doancuoiky.dao.ConstantData;
import doancuoiky.web.SendMail;
import doancuoiky.model.Login;
import doancuoiky.model.ThongBao;
import doancuoiky.model.YeuCau;


@WebServlet("/views/SV/yeucau")
public class YeuCauController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public YeuCauController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/views/SV/yeucau.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Retrieve data from the request parameters
		HttpSession session = request.getSession();
        String maDV =request.getParameter("cmbDV") ;
        String maYC = "YC"+generateRandomID(6);
        String maNguoiDung = (String) session.getAttribute("MaND");
        String tieuDeYC = request.getParameter("txtTieuDeYC");
        String noiDungYC = request.getParameter("cmbDV")+ "\n" +request.getParameter("txtNoiDungYC");
        Timestamp ngayYC = new java.sql.Timestamp(System.currentTimeMillis());
        Date date = new Date(ngayYC.getTime());
        String trangThai = request.getParameter("TrangThai");
        if (trangThai == null) {trangThai = "Chưa duyệt";}
        YeuCauDao ycDao = new YeuCauDao();
        
        SendMail mailProcessor = new SendMail();       
        ThongBaoDao thongbaoDao = new ThongBaoDao();
        ThongBao thongbao = new ThongBao();
        ThongBao thongbao2 = new ThongBao();
        thongbao.setMaNguoiGui(maNguoiDung);
        thongbao2.setMaNguoiGui(maNguoiDung);
        thongbao.setMaTB("TB"+generateRandomID(6));
        thongbao2.setMaTB("TB"+generateRandomID(6));
        thongbao.setMaNguoiNhan("CTSV001");
        thongbao.setNgayGui(date);
        thongbao.setTieuDe(tieuDeYC);
        thongbao.setNoiDung(noiDungYC);
        thongbao2.setMaNguoiNhan("QTVHT001");
        thongbao2.setNgayGui(date);
        thongbao2.setTieuDe(tieuDeYC);
        thongbao2.setNoiDung(noiDungYC);
        
        try {
            // Call the insert method from the DAO
            boolean success = ycDao.insertYeuCau(maYC, maDV, maNguoiDung,
                    tieuDeYC, noiDungYC, ngayYC, trangThai);

            // Send a response back to the client
            if (success) {
                String successMessage = "Request sent!";
                /*for (addr : CTSVMailList) {
                	mailProcessor.send(addr, tieuDeYC, noiDungYC, trangThai, addr.getUsername(), addr.getPassword());
                }*/
                           
                thongbaoDao.addNewNotification(thongbao);                 
                thongbaoDao.addNewNotification(thongbao2);
                
            	mailProcessor.send(ConstantData.ctv01.getUsername(), tieuDeYC, noiDungYC, ConstantData.sv01.getUsername(), ConstantData.sv01.getPassword());
            	mailProcessor.send(ConstantData.qtv01.getUsername(), tieuDeYC, noiDungYC, ConstantData.sv01.getUsername(), ConstantData.sv01.getPassword());
            	mailProcessor.send(ConstantData.ctv01.getUsername(), tieuDeYC, noiDungYC, ConstantData.sv02.getUsername(), ConstantData.sv02.getPassword());
                mailProcessor.send(ConstantData.qtv01.getUsername(), tieuDeYC, noiDungYC, ConstantData.sv02.getUsername(), ConstantData.sv02.getPassword());
                response.getWriter().write("<script>alert('" + successMessage + "'); setTimeout(function(){ window.location.href='/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/SV/yeucau.jsp'; }, 500);</script>");
                
            } else {
                String failureMessage = "Request failed!";
                response.getWriter().write("<script>alert('" + failureMessage + "'); setTimeout(function(){ window.location.href='/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/SV/yeucau.jsp'; }, 500);</script>");
                response.sendRedirect("/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/SV/yeucau.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            response.getWriter().write("<script>alert('Error: " + e.getMessage() + "'); setTimeout(function(){ window.location.href='/DoAnCuoiKyDVSV_Nhom14_FINAL_2/views/SV/yeucau.jsp'; }, 500);</script>");
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