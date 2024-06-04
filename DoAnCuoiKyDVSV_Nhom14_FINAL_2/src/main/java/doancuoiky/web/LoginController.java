package doancuoiky.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import doancuoiky.dao.DKGiayXacNhanSinhVienDao;
import doancuoiky.dao.LoginDao;
import doancuoiky.dao.NguoiDungDao;
import doancuoiky.dao.TinhTrangDao;
import doancuoiky.model.DKGiayXacNhanSinhVien;
import doancuoiky.model.Login;
import doancuoiky.model.NguoiDung;
import doancuoiky.model.TinhTrang;


import java.text.DateFormat;  
import java.text.SimpleDateFormat;  

@WebServlet("/dangnhap")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao logindao;
	private NguoiDungDao nguoidungdao;
	private TinhTrangDao tinhtrangdao;
	private DKGiayXacNhanSinhVienDao gxnsvdao;
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void init() {
        logindao = new LoginDao();
        nguoidungdao = new NguoiDungDao();
        tinhtrangdao = new TinhTrangDao();
        gxnsvdao = new DKGiayXacNhanSinhVienDao();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 authenticate(request, response);
	}
	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("txtName");
        String password = request.getParameter("txtPass");
        Login loginuser= new Login();
        NguoiDung nguoidung  = new NguoiDung();
        TinhTrang tinhtrang  = new TinhTrang();
       
        loginuser.setTenDangNhap(username);;
        loginuser.setMatKhau(password);
        System.out.println("Username: " + loginuser.getTenDangNhap());
        System.out.println("Password: " + loginuser.getMatKhau());
       
        try {
            if (logindao.validate(loginuser)!=null) 
            
            
            {
            	
            	    loginuser = logindao.validate(loginuser);
            	    nguoidung = nguoidungdao.getNguoiDungByMaND(loginuser.getMaNguoiDung());
            	    tinhtrang = tinhtrangdao.getTinhTrangByMaTinhTrang(nguoidung.getMaTinhTrang());
            	    List <DKGiayXacNhanSinhVien > giayxacnhanlist= gxnsvdao.getDKGiayXacNhanSinhVienListByMaNguoiDung(loginuser.getMaNguoiDung());
           
            	    HttpSession session = request.getSession();
            	    session.setAttribute("MaND", loginuser.getMaNguoiDung());
            	    session.setAttribute("PhanQuyen", loginuser.getVaiTro());
            	    session.setAttribute("TenNguoiDung", nguoidung.getHoTen()); 
            	    session.setAttribute("giayXacNhanList", giayxacnhanlist);
            	    if(tinhtrang !=null) {
            	    	 session.setAttribute("TenTinhTrang", tinhtrang.getTenTinhTrang()); 
            	    }
            	    session.setAttribute("NguoiDung", nguoidung);
            	    String vaiTro = loginuser.getVaiTro();
            	    String redirectPage;
                      
            	    switch (vaiTro) {
            	        case "SV":
            	            redirectPage = "views/SV/index.jsp";
            	            break;
            	        case "CTSV":
            	            redirectPage = "views/CTSV/index.jsp";
            	            break;
            	        case "QTVHT":
            	            redirectPage = "views/QTVHT/index.jsp";
            	            break;
            	        default:
            	            redirectPage = "views/SV/index.jsp";
            	    }
                   logindao.updateLoginRecordTruyCap(loginuser.getMaNguoiDung());
            	 	
               
            	response.sendRedirect(redirectPage);
            } else {
				/*
				 * HttpSession session = request.getSession(); // session.setAttribute("user",
				 * username);
				 */       
                  request.setAttribute("mess", "Tên đăng nhập hoặc mật khẩu sai !");
                  request.getRequestDispatcher("/views/dangnhap/dangnhap.jsp").forward(request, response);
                 // response.sendRedirect("Login.jsp");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

}
