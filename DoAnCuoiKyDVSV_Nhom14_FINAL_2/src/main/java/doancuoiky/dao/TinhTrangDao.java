package doancuoiky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doancuoiky.model.Login;
import doancuoiky.model.NguoiDung;
import doancuoiky.model.TinhTrang;
import doancuoiky.utils.JDBCUtils;

public class TinhTrangDao {

	public TinhTrang getTinhTrangByMaTinhTrang(String maTinhTrang) throws ClassNotFoundException {
        TinhTrang tinhTrang = null;

    
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = JDBCUtils.getConnection();
             // Tạo một PreparedStatement với câu truy vấn sử dụng tham số
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM TINHTRANG WHERE MaTinhTrang = ?")) {

            // Đặt giá trị tham số cho câu truy vấn
            preparedStatement.setString(1, maTinhTrang);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                // Nếu có kết quả từ câu truy vấn, tạo đối tượng TinhTrang từ dữ liệu
                tinhTrang = new TinhTrang();
                tinhTrang.setMaTinhTrang(rs.getString("MaTinhTrang"));
                tinhTrang.setTenTinhTrang(rs.getNString("TenTinhTrang"));
                tinhTrang.setMoTa(rs.getString("MoTa"));
            }

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }

        return tinhTrang;
    }
	public List<TinhTrang> getAllTinhTrang() throws ClassNotFoundException {
	    List<TinhTrang> tinhTrangList = new ArrayList<>();

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Tạo một PreparedStatement với câu truy vấn không có tham số
	         PreparedStatement preparedStatement = connection
	                 .prepareStatement("SELECT * FROM TINHTRANG")) {

	        System.out.println(preparedStatement);
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            // Đối với mỗi bản ghi, tạo đối tượng TinhTrang và thêm vào danh sách
	            TinhTrang tinhTrang = new TinhTrang();
	            tinhTrang.setMaTinhTrang(rs.getString("MaTinhTrang"));
	            tinhTrang.setTenTinhTrang(rs.getNString("TenTinhTrang"));
	            tinhTrang.setMoTa(rs.getString("MoTa"));

	            tinhTrangList.add(tinhTrang);
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return tinhTrangList;
	}


}
