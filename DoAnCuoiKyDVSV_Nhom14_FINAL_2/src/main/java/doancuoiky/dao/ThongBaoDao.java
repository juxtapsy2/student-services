package doancuoiky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doancuoiky.model.ThongBao;
import doancuoiky.utils.JDBCUtils;

public class ThongBaoDao {

	public ThongBaoDao() {
		// TODO Auto-generated constructor stub
	}
	public ThongBao getThongBaoByMaTB(String maTB) throws ClassNotFoundException {
	    ThongBao thongBao = null;

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Tạo một PreparedStatement với câu truy vấn có điều kiện WHERE theo MaTB
	         PreparedStatement preparedStatement = connection
	                 .prepareStatement("SELECT * FROM THONGBAO WHERE MaTB = ?")) {

	        // Thiết lập tham số cho MaTB
	        preparedStatement.setString(1, maTB);

	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            // Nếu có kết quả, tạo đối tượng ThongBao
	            thongBao = new ThongBao();
	            thongBao.setMaTB(rs.getString("MaTB"));
	            thongBao.setMaNguoiGui(rs.getString("MaNguoiGui"));
	            thongBao.setMaNguoiNhan(rs.getString("MaNguoiNhan"));
	            thongBao.setTieuDe(rs.getString("TieuDe"));
	            thongBao.setNoiDung(rs.getString("NoiDung"));
	            thongBao.setNgayGui(rs.getDate("NgayGui"));
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return thongBao;
	}
	
	public List<ThongBao> getAllThongBaos() throws ClassNotFoundException {
	    List<ThongBao> thongBaos = new ArrayList<>();

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Tạo một PreparedStatement với câu truy vấn không có điều kiện WHERE
	         PreparedStatement preparedStatement = connection
	                 .prepareStatement("SELECT * FROM THONGBAO")) {

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            // Đối với mỗi hàng trong kết quả, tạo đối tượng ThongBao và thêm vào danh sách
	            ThongBao thongBao = new ThongBao();
	            thongBao.setMaTB(rs.getString("MaTB"));
	            thongBao.setMaNguoiGui(rs.getString("MaNguoiGui"));
	            thongBao.setMaNguoiNhan(rs.getString("MaNguoiNhan"));
	            thongBao.setTieuDe(rs.getString("TieuDe"));
	            thongBao.setNoiDung(rs.getString("NoiDung"));
	            thongBao.setNgayGui(rs.getDate("NgayGui"));

	            thongBaos.add(thongBao);
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return thongBaos;
	}
	public List<ThongBao> getThongBaosByMaNguoiNhan(String maNguoiNhan) throws ClassNotFoundException {
	    List<ThongBao> thongBaos = new ArrayList<>();

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Tạo một PreparedStatement với câu truy vấn có điều kiện WHERE theo MaNguoiNhan
	         PreparedStatement preparedStatement = connection
	                 .prepareStatement("SELECT * FROM THONGBAO WHERE MaNguoiNhan = ?")) {

	        preparedStatement.setString(1, maNguoiNhan);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            // Đối với mỗi hàng trong kết quả, tạo đối tượng ThongBao và thêm vào danh sách
	            ThongBao thongBao = new ThongBao();
	            thongBao.setMaTB(rs.getString("MaTB"));
	            thongBao.setMaNguoiGui(rs.getString("MaNguoiGui"));
	            thongBao.setMaNguoiNhan(rs.getString("MaNguoiNhan"));
	            thongBao.setTieuDe(rs.getString("TieuDe"));
	            thongBao.setNoiDung(rs.getString("NoiDung"));
	            thongBao.setNgayGui(rs.getDate("NgayGui"));

	            thongBaos.add(thongBao);
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return thongBaos;
	}
	public List<ThongBao> getThongBaosByMaNguoiGui(String maNguoiGui) throws ClassNotFoundException {
	    List<ThongBao> thongBaos = new ArrayList<>();

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Tạo một PreparedStatement với câu truy vấn có điều kiện WHERE theo MaNguoiNhan
	         PreparedStatement preparedStatement = connection
	                 .prepareStatement("SELECT * FROM THONGBAO WHERE MaNguoiGui = ?")) {

	        preparedStatement.setString(1, maNguoiGui);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            // Đối với mỗi hàng trong kết quả, tạo đối tượng ThongBao và thêm vào danh sách
	            ThongBao thongBao = new ThongBao();
	            thongBao.setMaTB(rs.getString("MaTB"));
	            thongBao.setMaNguoiGui(rs.getString("MaNguoiGui"));
	            thongBao.setMaNguoiNhan(rs.getString("MaNguoiNhan"));
	            thongBao.setTieuDe(rs.getString("TieuDe"));
	            thongBao.setNoiDung(rs.getString("NoiDung"));
	            thongBao.setNgayGui(rs.getDate("NgayGui"));

	            thongBaos.add(thongBao);
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return thongBaos;
	}
	public boolean addNewNotification(ThongBao thongBao) throws ClassNotFoundException {
	    boolean notificationAdded = false;

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Step 2: Create a statement using connection object
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "INSERT INTO THONGBAO (MaTB, MaNguoiGui, MaNguoiNhan, TieuDe, NoiDung, NgayGui) " +
	                         "VALUES (?, ?, ?, ?, ?, ?)")) {

	        preparedStatement.setString(1, thongBao.getMaTB());
	        preparedStatement.setString(2, thongBao.getMaNguoiGui());
	        preparedStatement.setString(3, thongBao.getMaNguoiNhan());
	        preparedStatement.setString(4, thongBao.getTieuDe());
	        preparedStatement.setString(5, thongBao.getNoiDung());
	        preparedStatement.setDate(6, thongBao.getNgayGui());

	        int rowsAffected = preparedStatement.executeUpdate();

	        // Kiểm tra xem có bản ghi nào đã được thêm vào không
	        if (rowsAffected > 0) {
	            System.out.println("Thông báo đã được thêm vào thành công.");
	            notificationAdded = true;
	        } else {
	            System.out.println("Không có thông báo nào được thêm vào.");
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return notificationAdded;
	}


}
