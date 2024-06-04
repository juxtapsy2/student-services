package doancuoiky.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doancuoiky.dao.*;
import doancuoiky.model.DichVu;
import doancuoiky.model.*;

/**
 * Servlet implementation class UpdateDichVu
 */
@WebServlet("/views/CTSV/updatedichvu")
public class UpdateDichVu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateDichVu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String id = request.getParameter("dvid");
        DichVuDao dvdao = new DichVuDao();
        DichVu dichvu = dvdao.selectDichVuByMaDV(id);
    
        request.setAttribute("updatedichvu", dichvu);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/CTSV/capnhatdichvu.jsp");
        dispatcher.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String maDV = request.getParameter("MaDV");
        String tenDV = request.getParameter("TenDV");
        String moTa = request.getParameter("MoTa");
        String namHoc = request.getParameter("NamHoc");
        String hocKy = request.getParameter("HocKy");
        System.out.print("Update dv" + maDV);
        DichVu dv = new DichVu();
        DichVuDao dvdao = new DichVuDao();
        dv.setMaDV(maDV);
        dv.setTenDV(tenDV);
        dv.setMoTa(moTa);
        dv.setNamHoc(namHoc);
        dv.setHocKy(hocKy);
        dvdao.updateDichVu(dv);
        response.sendRedirect("quanlydichvu.jsp");
        
	}

}
