package doancuoiky.web;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import doancuoiky.dao.NguoiDungDao;
import doancuoiky.model.NguoiDung;


/**
 * Servlet implementation class ImportServletSV
 */
@WebServlet("/views/QTVHT/ImportServletSV")
@MultipartConfig
public class ImportServletSV extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ImportServletSV() {
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
		Part filePart = request.getPart("file");

        InputStream fileContent = filePart.getInputStream();
        HSSFWorkbook workbook = new HSSFWorkbook(fileContent);
        HSSFSheet sheet = workbook.getSheetAt(0);

        Iterator<Row> iterator = sheet.iterator();

        NguoiDungDao nguoidungdao = new NguoiDungDao();
        int columnIndex = 0; 
        while (iterator.hasNext()) {
            Row currentRow = iterator.next();
            Iterator<Cell> cellIterator = currentRow.iterator();
            
            Cell cell1 = currentRow.getCell(columnIndex);
            String MaNguoiDung = cell1.getStringCellValue();
            
            
            Cell cell2 = currentRow.getCell(++columnIndex);
            String HoTen = cell2.getStringCellValue();
            
            
            Cell cell3 = currentRow.getCell(++columnIndex);
            java.util.Date tngaySinh = (java.util.Date) cell3.getDateCellValue();

         // Convert to java.sql.Date
         java.sql.Date NgaySinh = new java.sql.Date(tngaySinh.getTime());
			
			/*
			 * Cell cell3 = currentRow.getCell(++columnIndex); String ngaySinhStr =
			 * cell3.getStringCellValue(); SimpleDateFormat sdf = new
			 * SimpleDateFormat("yyyy-MM-dd"); Date ngaySinh = null; try { ngaySinh = (Date)
			 * sdf.parse(ngaySinhStr); } catch (ParseException e) { e.printStackTrace(); }
			 */
			 
     //       ++columnIndex;
            
			
			
			 Cell cell4 = currentRow.getCell(++columnIndex); String GioiTinh =
			cell4.getStringCellValue();
			 
			  Cell cell5 = currentRow.getCell(++columnIndex); String DanToc =
			  cell5.getStringCellValue();
			  
			
			 Cell cell6 = currentRow.getCell(++columnIndex); String CMND =
			 cell6.getStringCellValue();
			 
			 
			 
			 Cell cell7 = currentRow.getCell(++columnIndex); String TonGiao =
			 cell7.getStringCellValue();
			
			 Cell cell8 = currentRow.getCell(++columnIndex); String DiaChi =
			 cell8.getStringCellValue();
			 
			 
			 Cell cell9 = currentRow.getCell(++columnIndex); String SDT =
			 cell9.getStringCellValue();
			 
			 
			 Cell cell10 = currentRow.getCell(++columnIndex); String Email =
			 cell10.getStringCellValue();
			 
			 Cell cell11 = currentRow.getCell(++columnIndex); String QueQuan =
			 cell11.getStringCellValue();
			 
			 
			 Cell cell12 = currentRow.getCell(++columnIndex); String MaLop =
			 cell12.getStringCellValue();
			 
			 
			Cell cell13 = currentRow.getCell(++columnIndex); String VaiTro =
			 cell13.getStringCellValue();
			 
			 Cell cell14 = currentRow.getCell(++columnIndex); String MaTinhTrang =
			 cell14.getStringCellValue();
			 
           
            
            
            
            
            NguoiDung nguoiDung = new NguoiDung();
            nguoiDung.setMaNguoiDung(MaNguoiDung);
            nguoiDung.setHoTen(HoTen);
            nguoiDung.setNgaySinh(NgaySinh);
			
			  nguoiDung.setGioiTinh(GioiTinh);
			  nguoiDung.setDanToc(DanToc);
			 nguoiDung.setSdt(SDT);
				
				  nguoiDung.setCmnd(CMND); nguoiDung.setTonGiao(TonGiao);
				 nguoiDung.setDiaChi(DiaChi); nguoiDung.setSdt(null);
				 nguoiDung.setEmail(Email); nguoiDung.setQueQuan(QueQuan);
				 nguoiDung.setMaLop(MaLop); nguoiDung.setVaiTro(VaiTro);
				 nguoiDung.setMaTinhTrang(MaTinhTrang);
				 
			 
            try {nguoidungdao.addNewUser(nguoiDung); } catch
			  (ClassNotFoundException e) { // TODO Auto-generated catch block
			  e.printStackTrace(); }
            columnIndex = 0;
        }

        response.sendRedirect("quanlysinhvien.jsp");
	}

}
