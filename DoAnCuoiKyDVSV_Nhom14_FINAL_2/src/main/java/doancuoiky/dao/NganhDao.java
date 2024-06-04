package doancuoiky.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doancuoiky.model.Nganh;
import doancuoiky.utils.JDBCUtils;
public class NganhDao {

	public NganhDao() {
		// TODO Auto-generated constructor stub
	}
	public Nganh getNganhByMaLop(String maLop) throws ClassNotFoundException {
	    Nganh nganh = null;

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Tạo một PreparedStatement với câu truy vấn sử dụng tham số
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "SELECT NGANH.* FROM LOP JOIN NGANH ON LOP.MaNganh = NGANH.MaNganh WHERE LOP.MaLop = ?")) {

	        // Đặt giá trị tham số cho câu truy vấn
	        preparedStatement.setString(1, maLop);

	        System.out.println(preparedStatement);
	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            // Nếu có kết quả từ câu truy vấn, tạo đối tượng Nganh từ dữ liệu
	            nganh = new Nganh();
	            nganh.setMaNganh(rs.getString("MaNganh"));
	            nganh.setTenNganh(rs.getNString("TenNganh"));
	            nganh.setMaKhoa(rs.getString("MaKhoa"));
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return nganh;
	}

}
