package doancuoiky.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import doancuoiky.dao.ThongKeKhoaDao;
import doancuoiky.dao.YeuCauDao;
import doancuoiky.model.ThongKeKhoa;

@WebServlet("/views/QTVHT/thongkeyeucau")
public class ThongKeController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final String OPTION_DEFAULT =  "Khoas";
    public ThongKeController() {
        super();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String option = request.getParameter("optionChoice");
        List<ThongKeKhoa> thongKeList = null;
        ThongKeKhoaDao thongKeKhoaDao = new ThongKeKhoaDao();
        YeuCauDao ycdao = new YeuCauDao();
        if(option == null || (!option.equals("Khoas") && !option.equals("Khoa"))) {
            option = OPTION_DEFAULT;
        }
        try {
            switch(option) {
                case OPTION_DEFAULT:
                    List<String> studentCodes = ycdao.getAllMaNguoiDungFromYeuCau();
                    thongKeList = thongKeKhoaDao.calculateStatistics(studentCodes);
                    break;
                case "Khoa":
                    thongKeList = thongKeKhoaDao.thongKeTheoKhoaYeuCau();
                    break;
            }
        } catch(ClassNotFoundException | SQLException e) {
        
        }

        request.setAttribute("thongKeList", thongKeList);
        request.getRequestDispatcher("thongke.jsp").forward(request, response);
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}