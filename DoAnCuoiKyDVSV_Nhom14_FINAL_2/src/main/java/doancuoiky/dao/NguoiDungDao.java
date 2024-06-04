package doancuoiky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doancuoiky.model.Login;
import doancuoiky.model.NguoiDung;
import doancuoiky.utils.JDBCUtils;

public class NguoiDungDao {

	public NguoiDung getNguoiDungByMaND(String maNguoiDung) throws ClassNotFoundException {
	    NguoiDung nguoiDung = null;

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Step 2: Create a statement using connection object
	         PreparedStatement preparedStatement = connection
	                 .prepareStatement("SELECT * FROM NguoiDung WHERE MaNguoiDung = ?")) {
	        preparedStatement.setString(1, maNguoiDung);

	        System.out.println(preparedStatement);
	        ResultSet rs = preparedStatement.executeQuery();

	        if (rs.next()) {
	            // Nếu có kết quả từ câu truy vấn, tạo đối tượng NguoiDung từ dữ liệu
	            nguoiDung = new NguoiDung();
	            nguoiDung.setMaNguoiDung(rs.getString("MaNguoiDung"));
	            nguoiDung.setHoTen(rs.getNString("HoTen"));
	            nguoiDung.setNgaySinh(rs.getDate("NgaySinh"));
	            nguoiDung.setGioiTinh(rs.getNString("GioiTinh"));
	            nguoiDung.setDanToc(rs.getNString("DanToc"));
	            nguoiDung.setCmnd(rs.getNString("CMND"));
	            
	            nguoiDung.setTonGiao(rs.getNString("TonGiao"));
	            nguoiDung.setDiaChi(rs.getNString("DiaChi"));
	            nguoiDung.setSdt(rs.getString("SDT"));
	            nguoiDung.setEmail(rs.getString("Email"));
	            nguoiDung.setQueQuan(rs.getNString("QueQuan"));
	            nguoiDung.setMaLop(rs.getString("MaLop"));
	            nguoiDung.setVaiTro(rs.getString("VaiTro"));
	            nguoiDung.setMaTinhTrang(rs.getString("MaTinhTrang"));
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return nguoiDung;
	}
	public List<NguoiDung> selectNguoiDungByVaiTro(String vaiTro) {
        List<NguoiDung> nguoiDungs = new ArrayList<>();

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NguoiDung WHERE VaiTro = ?");
        ) {
            preparedStatement.setString(1, vaiTro);

            try (ResultSet rs = preparedStatement.executeQuery()) {
                while (rs.next()) {
                    NguoiDung nguoiDung = new NguoiDung();
                    nguoiDung.setMaNguoiDung(rs.getString("MaNguoiDung"));
                    nguoiDung.setHoTen(rs.getString("HoTen"));
                    nguoiDung.setNgaySinh(rs.getDate("NgaySinh"));
                    nguoiDung.setGioiTinh(rs.getString("GioiTinh"));
                    nguoiDung.setDanToc(rs.getString("DanToc"));
                    nguoiDung.setCmnd(rs.getString("CMND"));
                    nguoiDung.setTonGiao(rs.getString("TonGiao"));
                    nguoiDung.setDiaChi(rs.getString("DiaChi"));
                    nguoiDung.setSdt(rs.getString("SDT"));
                    nguoiDung.setEmail(rs.getString("Email"));
                    nguoiDung.setQueQuan(rs.getString("QueQuan"));
                    nguoiDung.setMaLop(rs.getString("MaLop"));
                    nguoiDung.setVaiTro(rs.getString("VaiTro"));
                    nguoiDung.setMaTinhTrang(rs.getString("MaTinhTrang"));

                    nguoiDungs.add(nguoiDung);
                }
            }
        } catch (SQLException exception) {
            JDBCUtils.printSQLException(exception);
        }

        return nguoiDungs;
    }
	public List<NguoiDung> selectAllNguoiDung() {
	    List<NguoiDung> nguoiDungs = new ArrayList<>();

	    try (Connection connection = JDBCUtils.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM NguoiDung");
	    ) {

	        try (ResultSet rs = preparedStatement.executeQuery()) {
	            while (rs.next()) {
	                NguoiDung nguoiDung = new NguoiDung();
	                nguoiDung.setMaNguoiDung(rs.getString("MaNguoiDung"));
	                nguoiDung.setHoTen(rs.getString("HoTen"));
	                nguoiDung.setNgaySinh(rs.getDate("NgaySinh"));
	                nguoiDung.setGioiTinh(rs.getString("GioiTinh"));
	                nguoiDung.setDanToc(rs.getString("DanToc"));
	                nguoiDung.setCmnd(rs.getString("CMND"));
	                nguoiDung.setTonGiao(rs.getString("TonGiao"));
	                nguoiDung.setDiaChi(rs.getString("DiaChi"));
	                nguoiDung.setSdt(rs.getString("SDT"));
	                nguoiDung.setEmail(rs.getString("Email"));
	                nguoiDung.setQueQuan(rs.getString("QueQuan"));
	                nguoiDung.setMaLop(rs.getString("MaLop"));
	                nguoiDung.setVaiTro(rs.getString("VaiTro"));
	                nguoiDung.setMaTinhTrang(rs.getString("MaTinhTrang"));

	                nguoiDungs.add(nguoiDung);
	            }
	        }
	    } catch (SQLException exception) {
	        JDBCUtils.printSQLException(exception);
	    }

	    return nguoiDungs;
	}
	public boolean deleteNguoiDungByMaNguoiDung(String maNguoiDung) throws ClassNotFoundException {
	    boolean recordDeleted = false;

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Step 2: Create a statement using connection object
	         PreparedStatement preparedStatement = connection
	                 .prepareStatement("DELETE FROM NguoiDung WHERE MaNguoiDung = ?")) {
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
	public boolean addNewUser(NguoiDung nguoiDung) throws ClassNotFoundException {
	    boolean userAdded = false;

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Step 2: Create a statement using connection object
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "INSERT INTO NguoiDung (MaNguoiDung, HoTen, NgaySinh, GioiTinh, DanToc, CMND, TonGiao, DiaChi, SDT, Email, QueQuan, MaLop, VaiTro, MaTinhTrang) " +
	                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {

	        preparedStatement.setString(1, nguoiDung.getMaNguoiDung());
	        preparedStatement.setString(2, nguoiDung.getHoTen());
	        preparedStatement.setDate(3, nguoiDung.getNgaySinh());
	        preparedStatement.setString(4, nguoiDung.getGioiTinh());
	        preparedStatement.setString(5, nguoiDung.getDanToc());
	        preparedStatement.setString(6, nguoiDung.getCmnd());
	        preparedStatement.setString(7, nguoiDung.getTonGiao());
	        preparedStatement.setString(8, nguoiDung.getDiaChi());
	        preparedStatement.setString(9, nguoiDung.getSdt());
	        preparedStatement.setString(10, nguoiDung.getEmail());
	        preparedStatement.setString(11, nguoiDung.getQueQuan());
	        preparedStatement.setString(12, nguoiDung.getMaLop());
	        preparedStatement.setString(13, nguoiDung.getVaiTro());
	        preparedStatement.setString(14, nguoiDung.getMaTinhTrang());

	        int rowsAffected = preparedStatement.executeUpdate();

	        // Kiểm tra xem có bản ghi nào đã được thêm vào không
	        if (rowsAffected > 0) {
	            System.out.println("Người dùng đã được thêm vào thành công.");
	            userAdded = true;
	        } else {
	            System.out.println("Không có người dùng nào được thêm vào.");
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return userAdded;
	}
	public boolean updateUser(NguoiDung nguoiDung) throws ClassNotFoundException {
	    boolean userUpdated = false;

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Step 2: Create a statement using connection object
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "UPDATE NguoiDung SET HoTen=?, NgaySinh=?, GioiTinh=?, DanToc=?, CMND=?, TonGiao=?, DiaChi=?, SDT=?, Email=?, QueQuan=?, MaLop=?, VaiTro=?, MaTinhTrang=? WHERE MaNguoiDung=?")) {

	        preparedStatement.setString(1, nguoiDung.getHoTen());
	        preparedStatement.setDate(2, nguoiDung.getNgaySinh());
	        preparedStatement.setString(3, nguoiDung.getGioiTinh());
	        preparedStatement.setString(4, nguoiDung.getDanToc());
	        preparedStatement.setString(5, nguoiDung.getCmnd());
	        preparedStatement.setString(6, nguoiDung.getTonGiao());
	        preparedStatement.setString(7, nguoiDung.getDiaChi());
	        preparedStatement.setString(8, nguoiDung.getSdt());
	        preparedStatement.setString(9, nguoiDung.getEmail());
	        preparedStatement.setString(10, nguoiDung.getQueQuan());
	        preparedStatement.setString(11, nguoiDung.getMaLop());
	        preparedStatement.setString(12, nguoiDung.getVaiTro());
	        preparedStatement.setString(13, nguoiDung.getMaTinhTrang());
	        preparedStatement.setString(14, nguoiDung.getMaNguoiDung());

	        int rowsAffected = preparedStatement.executeUpdate();

	        // Kiểm tra xem có bản ghi nào đã được cập nhật không
	        if (rowsAffected > 0) {
	            System.out.println("Người dùng đã được cập nhật thành công.");
	            userUpdated = true;
	        } else {
	            System.out.println("Không có người dùng nào được cập nhật.");
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return userUpdated;
	}


}
