package doancuoiky.dao;

import doancuoiky.model.Khoa;
import doancuoiky.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class KhoaDao {

	public KhoaDao() {
		// TODO Auto-generated constructor stub
	}
	public Khoa getKhoaByMaNganh(String maNganh) throws ClassNotFoundException {
	    Khoa khoa = null;

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Tạo một PreparedStatement với câu truy vấn sử dụng tham số
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "SELECT KHOA.* FROM NGANH JOIN KHOA ON NGANH.MaKhoa = KHOA.MaKhoa WHERE NGANH.MaNganh = ?")) {

	        // Đặt giá trị tham số cho câu truy vấn
	        preparedStatement.setString(1, maNganh);

	        System.out.println(preparedStatement);
	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            // Nếu có kết quả từ câu truy vấn, tạo đối tượng Khoa từ dữ liệu
	            khoa = new Khoa();
	            khoa.setMaKhoa(rs.getString("MaKhoa"));
	            khoa.setTenKhoa(rs.getNString("TenKhoa"));
	            khoa.setViTri(rs.getNString("ViTri"));
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return khoa;
	}


}
