package doancuoiky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doancuoiky.model.Login;
import doancuoiky.model.Lop;
import doancuoiky.model.NguoiDung;
import doancuoiky.model.TinhTrang;
import doancuoiky.utils.JDBCUtils;
public class LopDao {
	public Lop getLopByMaLop(String maLop) throws ClassNotFoundException {
	    Lop lop = null;

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Tạo một PreparedStatement với câu truy vấn sử dụng tham số
	         PreparedStatement preparedStatement = connection
	                 .prepareStatement("SELECT * FROM LOP WHERE MaLop = ?")) {

	        // Đặt giá trị tham số cho câu truy vấn
	        preparedStatement.setString(1, maLop);

	        System.out.println(preparedStatement);
	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            // Nếu có kết quả từ câu truy vấn, tạo đối tượng Lop từ dữ liệu
	            lop = new Lop();
	            lop.setMaLop(rs.getString("MaLop"));
	            lop.setMaNganh(rs.getString("MaNganh"));
	            lop.setTenLop(rs.getNString("TenLop"));
	            lop.setNamNhapHoc(rs.getInt("NamNhapHoc"));
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return lop;
	}
	public List<Lop> getAllLops() throws ClassNotFoundException {
	    List<Lop> lops = new ArrayList<>();

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Tạo một PreparedStatement với câu truy vấn không có điều kiện WHERE
	         PreparedStatement preparedStatement = connection
	                 .prepareStatement("SELECT * FROM LOP")) {

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            // Đối với mỗi hàng trong kết quả, tạo đối tượng Lop và thêm vào danh sách
	            Lop lop = new Lop();
	            lop.setMaLop(rs.getString("MaLop"));
	            lop.setMaNganh(rs.getString("MaNganh"));
	            lop.setTenLop(rs.getNString("TenLop"));
	            lop.setNamNhapHoc(rs.getInt("NamNhapHoc"));
	            
	            lops.add(lop);
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return lops;
	}



}
