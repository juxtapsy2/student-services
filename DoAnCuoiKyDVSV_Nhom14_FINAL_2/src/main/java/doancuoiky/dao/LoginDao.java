package doancuoiky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import doancuoiky.model.Login;
import doancuoiky.utils.JDBCUtils;
public class LoginDao {

	   public Login validate(Login userlogin) throws ClassNotFoundException {
	        Login loggedInUser = null;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = JDBCUtils.getConnection();
	            // Step 2: Create a statement using connection object
	            PreparedStatement preparedStatement = connection
	            .prepareStatement("SELECT * FROM dangnhap WHERE TenDangNhap = ? AND MatKhau = ?")) {
	            preparedStatement.setString(1, userlogin.getTenDangNhap());
	            preparedStatement.setString(2, userlogin.getMatKhau());

	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	                // Nếu có kết quả từ câu truy vấn, tạo đối tượng Login từ dữ liệu
	                loggedInUser = new Login();
	                loggedInUser.setID(rs.getInt("ID"));
	                loggedInUser.setMaNguoiDung(rs.getString("MaNguoiDung"));
	                loggedInUser.setTenDangNhap(rs.getString("TenDangNhap"));
	                loggedInUser.setMatKhau(rs.getString("MatKhau"));
	                loggedInUser.setVaiTro(rs.getString("VaiTro"));
	                loggedInUser.setLuotTruyCap(rs.getInt("LuotTruyCap"));
	                loggedInUser.setTruyCapGanNhat(rs.getDate("TruyCapGanNhat"));
	            }

	        } catch (SQLException e) {
	            JDBCUtils.printSQLException(e);
	        }

	        return loggedInUser;
	    }
	   public boolean deleteLoginRecordByMaNguoiDung(String maNguoiDung) throws ClassNotFoundException {
		    boolean recordDeleted = false;

		    Class.forName("com.mysql.jdbc.Driver");

		    try (Connection connection = JDBCUtils.getConnection();
		         // Step 2: Create a statement using connection object
		         PreparedStatement preparedStatement = connection
		                 .prepareStatement("DELETE FROM dangnhap WHERE MaNguoiDung = ?")) {
		        preparedStatement.setString(1, maNguoiDung);

		        int rowsAffected = preparedStatement.executeUpdate();

		        // Kiểm tra xem có bản ghi nào đã bị xóa không
		        if (rowsAffected > 0) {
		            System.out.println("Bản ghi đã được xóa thành công.");
		            recordDeleted = true;
		        } else {
		            System.out.println("Không có bản ghi nào được xóa.");
		        }

		    } catch (SQLException e) {
		        JDBCUtils.printSQLException(e);
		    }

		    return recordDeleted;
		}
	   public boolean addNewLoginRecord(String maNguoiDung, String tenDangNhap, String matKhau, String vaiTro) throws ClassNotFoundException {
	        boolean recordAdded = false;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = JDBCUtils.getConnection();
	             // Step 2: Create a statement using connection object
	             PreparedStatement preparedStatement = connection
	                     .prepareStatement("INSERT INTO DangNhap (MaNguoiDung, TenDangNhap, MatKhau, VaiTro, LuotTruyCap, TruyCapGanNhat) VALUES (?, ?, ?, ?, 0, NOW())")) {
	            preparedStatement.setString(1, maNguoiDung);
	            preparedStatement.setString(2, tenDangNhap);
	            preparedStatement.setString(3, matKhau);
	            preparedStatement.setString(4, vaiTro);

	            int rowsAffected = preparedStatement.executeUpdate();

	            // Kiểm tra xem có bản ghi nào đã được thêm vào không
	            if (rowsAffected > 0) {
	                System.out.println("Bản ghi đã được thêm vào thành công.");
	                recordAdded = true;
	            } else {
	                System.out.println("Không có bản ghi nào được thêm vào.");
	            }

	        } catch (SQLException e) {
	            JDBCUtils.printSQLException(e);
	        }

	        return recordAdded;
	    }
	   public boolean updateLoginRecordTruyCap(String maNguoiDung) throws ClassNotFoundException {
	        boolean recordUpdated = false;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = JDBCUtils.getConnection();
	             // Step 2: Create a statement using connection object
	             PreparedStatement preparedStatement = connection
	                     .prepareStatement("UPDATE DangNhap SET LuotTruyCap = LuotTruyCap + 1, TruyCapGanNhat = ? WHERE MaNguoiDung = ?")) {

	            // Sử dụng Timestamp để lấy thời điểm hiện tại
	            Timestamp timestamp = new Timestamp(new Date().getTime());

	            preparedStatement.setTimestamp(1, timestamp);
	            preparedStatement.setString(2, maNguoiDung);

	            int rowsAffected = preparedStatement.executeUpdate();

	            // Kiểm tra xem có bản ghi nào đã được cập nhật không
	            if (rowsAffected > 0) {
	                System.out.println("Bản ghi đã được cập nhật thành công.");
	                recordUpdated = true;
	            } else {
	                System.out.println("Không có bản ghi nào được cập nhật.");
	            }

	        } catch (SQLException e) {
	            JDBCUtils.printSQLException(e);
	        }

	        return recordUpdated;
	    }
	   public boolean updateLoginRecord(String maNguoiDung, String tenDangNhap, String matKhau, String vaiTro) throws ClassNotFoundException {
		    boolean recordUpdated = false;

		    Class.forName("com.mysql.jdbc.Driver");

		    try (Connection connection = JDBCUtils.getConnection();
		         // Step 2: Create a statement using connection object
		         PreparedStatement preparedStatement = connection.prepareStatement(
		                 "UPDATE DangNhap SET TenDangNhap = ?, MatKhau = ?, VaiTro = ? " +
		                         "WHERE MaNguoiDung = ?")) {

		        preparedStatement.setString(1, tenDangNhap);
		        preparedStatement.setString(2, matKhau);
		        preparedStatement.setString(3, vaiTro);
		        preparedStatement.setString(4, maNguoiDung);

		        int rowsAffected = preparedStatement.executeUpdate();

		        // Kiểm tra xem có bản ghi nào đã được cập nhật không
		        if (rowsAffected > 0) {
		            System.out.println("Bản ghi đã được cập nhật thành công.");
		            recordUpdated = true;
		        } else {
		            System.out.println("Không có bản ghi nào được cập nhật.");
		        }

		    } catch (SQLException e) {
		        JDBCUtils.printSQLException(e);
		    }

		    return recordUpdated;
		}

	   public List<Login> selectAllLogins() {
		    List<Login> logins = new ArrayList<>();

		    try (Connection connection = JDBCUtils.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DangNhap");
		         ResultSet rs = preparedStatement.executeQuery()) {

		        while (rs.next()) {
		            int id = rs.getInt("ID");
		            String maNguoiDung = rs.getString("MaNguoiDung");
		            String tenDangNhap = rs.getString("TenDangNhap");
		            String matKhau = rs.getString("MatKhau");
		            String vaiTro = rs.getString("VaiTro");
		            int luotTruyCap = rs.getInt("LuotTruyCap");
		            Date truyCapGanNhat = rs.getDate("TruyCapGanNhat");

		            Login login = new Login();
		            login.setID(id);
		            login.setMaNguoiDung(maNguoiDung);
		            login.setTenDangNhap(tenDangNhap);
		            login.setMatKhau(matKhau);
		            login.setVaiTro(vaiTro);
		            login.setLuotTruyCap(luotTruyCap);
		            login.setTruyCapGanNhat(truyCapGanNhat);

		            logins.add(login);
		        }

		    } catch (SQLException exception) {
		        JDBCUtils.printSQLException(exception);
		    }

		    return logins;
		}
	   public List<Login> selectLoginsByMaNguoiDung(String maNguoiDung) {
		    List<Login> logins = new ArrayList<>();

		    try (Connection connection = JDBCUtils.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DangNhap WHERE MaNguoiDung = ?");
		    ) {
		        preparedStatement.setString(1, maNguoiDung);

		        try (ResultSet rs = preparedStatement.executeQuery()) {
		            while (rs.next()) {
		                int id = rs.getInt("ID");
		                String tenDangNhap = rs.getString("TenDangNhap");
		                String matKhau = rs.getString("MatKhau");
		                String vaiTro = rs.getString("VaiTro");
		                int luotTruyCap = rs.getInt("LuotTruyCap");
		                Date truyCapGanNhat = rs.getDate("TruyCapGanNhat");

		                Login login = new Login();
		                login.setID(id);
		                login.setMaNguoiDung(maNguoiDung); // Cập nhật giá trị MaNguoiDung
		                login.setTenDangNhap(tenDangNhap);
		                login.setMatKhau(matKhau);
		                login.setVaiTro(vaiTro);
		                login.setLuotTruyCap(luotTruyCap);
		                login.setTruyCapGanNhat(truyCapGanNhat);

		                logins.add(login);
		            }
		        }
		    } catch (SQLException exception) {
		        JDBCUtils.printSQLException(exception);
		    }

		    return logins;
		}

	   public List<String> selectMaNguoiDungChuaCoTrongDangNhap() {
		    List<String> maNguoiDungList = new ArrayList<>();

		    try (Connection connection = JDBCUtils.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(
		                 "SELECT MaNguoiDung FROM NguoiDung " +
		                         "WHERE NOT EXISTS (SELECT 1 FROM DangNhap WHERE DangNhap.MaNguoiDung = NguoiDung.MaNguoiDung)"
		         );
		         ResultSet rs = preparedStatement.executeQuery()) {

		        while (rs.next()) {
		            String maNguoiDung = rs.getString("MaNguoiDung");
		            maNguoiDungList.add(maNguoiDung);
		        }

		    } catch (SQLException exception) {
		        JDBCUtils.printSQLException(exception);
		    }

		    return maNguoiDungList;
		}
	   public boolean updatePassword(String maNguoiDung, String matKhauHienTai, String matKhauMoi) throws ClassNotFoundException {
		    boolean passwordUpdated = false;

		    Class.forName("com.mysql.jdbc.Driver");

		    try (Connection connection = JDBCUtils.getConnection();
		         // Step 2: Create a statement using connection object
		         PreparedStatement preparedStatement = connection
		                 .prepareStatement("UPDATE DangNhap SET MatKhau = ? WHERE MaNguoiDung = ? AND MatKhau = ?")) {

		        preparedStatement.setString(1, matKhauMoi);
		        preparedStatement.setString(2, maNguoiDung);
		        preparedStatement.setString(3, matKhauHienTai);

		        int rowsAffected = preparedStatement.executeUpdate();

		        // Kiểm tra xem có bản ghi nào đã được cập nhật không
		        if (rowsAffected > 0) {
		            System.out.println("Mật khẩu đã được cập nhật thành công.");
		            passwordUpdated = true;
		        } else {
		            System.out.println("Mật khẩu hiện tại không đúng hoặc không có bản ghi nào được cập nhật.");
		        }

		    } catch (SQLException e) {
		        JDBCUtils.printSQLException(e);
		    }

		    return passwordUpdated;
		}


}
