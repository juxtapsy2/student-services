package doancuoiky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import doancuoiky.model.DichVu;
import doancuoiky.model.Login;
import doancuoiky.utils.JDBCUtils;
public class DichVuDao 
{

	 public String getTenDVByMaDV(String maDV) throws ClassNotFoundException {
	        String tenDV = null;

	        Class.forName("com.mysql.jdbc.Driver");

	        try (Connection connection = JDBCUtils.getConnection();
	             // Step 2: Create a statement using connection object
	             PreparedStatement preparedStatement = connection
	                     .prepareStatement("SELECT TenDV FROM DICHVU WHERE MaDV = ?")) {
	            preparedStatement.setString(1, maDV);

	            System.out.println(preparedStatement);
	            ResultSet rs = preparedStatement.executeQuery();

	            if (rs.next()) {
	                // Nếu có kết quả từ câu truy vấn, lấy giá trị TenDV
	                tenDV = rs.getNString("TenDV");
	            }

	        } catch (SQLException e) {
	            JDBCUtils.printSQLException(e);
	        }

	        return tenDV;
	    }
	 public List<DichVu> selectAllDichVu() {
		    List<DichVu> dichVus = new ArrayList<>();

		    try (Connection connection = JDBCUtils.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DICHVU");
		         ResultSet rs = preparedStatement.executeQuery()) {

		        while (rs.next()) {
		            String maDV = rs.getString("MaDV");
		            String tenDV = rs.getString("TenDV");
		            String moTa = rs.getString("MoTa");
		            String namHoc = rs.getString("NamHoc");
		            String hocKy = rs.getString("HocKy");

		            DichVu dichVu = new DichVu();
		            dichVu.setMaDV(maDV);
		            dichVu.setTenDV(tenDV);
		            dichVu.setMoTa(moTa);
		            dichVu.setNamHoc(namHoc);
		            dichVu.setHocKy(hocKy);

		            dichVus.add(dichVu);
		        }

		    } catch (SQLException exception) {
		        JDBCUtils.printSQLException(exception);
		    }

		    return dichVus;
		}
	 public DichVu selectDichVuByMaDV(String maDV) {
		    DichVu dichVu = null;

		    try (Connection connection = JDBCUtils.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM DICHVU WHERE MaDV = ?");
		    ) {
		        preparedStatement.setString(1, maDV);
		        ResultSet rs = preparedStatement.executeQuery();

		        if (rs.next()) {
		            String tenDV = rs.getString("TenDV");
		            String moTa = rs.getString("MoTa");
		            String namHoc = rs.getString("NamHoc");
		            String hocKy = rs.getString("HocKy");

		            dichVu = new DichVu();
		            dichVu.setMaDV(maDV);
		            dichVu.setTenDV(tenDV);
		            dichVu.setMoTa(moTa);
		            dichVu.setNamHoc(namHoc);
		            dichVu.setHocKy(hocKy);
		        }

		    } catch (SQLException exception) {
		        JDBCUtils.printSQLException(exception);
		    }

		    return dichVu;
		}

	 public void deleteDichVuByMaDV(String maDV) {
		    try (Connection connection = JDBCUtils.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM DICHVU WHERE MaDV = ?")) {

		        preparedStatement.setString(1, maDV);

		        int rowsAffected = preparedStatement.executeUpdate();

		        if (rowsAffected > 0) {
		            System.out.println("Dịch vụ với MaDV " + maDV + " đã được xóa thành công.");
		        } else {
		            System.out.println("Không tìm thấy dịch vụ với MaDV " + maDV + ".");
		        }

		    } catch (SQLException exception) {
		        JDBCUtils.printSQLException(exception);
		    }
		}
	 public boolean insertDichVu(DichVu newDichVu) {
		    try (Connection connection = JDBCUtils.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(
		                 "INSERT INTO DICHVU (MaDV, TenDV, MoTa, NamHoc, HocKy) VALUES (?, ?, ?, ?, ?)")) {

		        preparedStatement.setString(1, newDichVu.getMaDV());
		        preparedStatement.setString(2, newDichVu.getTenDV());
		        preparedStatement.setString(3, newDichVu.getMoTa());
		        preparedStatement.setString(4, newDichVu.getNamHoc());
		        preparedStatement.setString(5, newDichVu.getHocKy());

		        int rowsAffected = preparedStatement.executeUpdate();

		        return rowsAffected > 0;

		    } catch (SQLException exception) {
		        JDBCUtils.printSQLException(exception);
		        return false;
		    }
		}

	 public boolean updateDichVu(DichVu updatedDichVu) {
		    try (Connection connection = JDBCUtils.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(
		                 "UPDATE DICHVU SET TenDV = ?, MoTa = ?, NamHoc = ?, HocKy = ? WHERE MaDV = ?")) {

		        preparedStatement.setString(1, updatedDichVu.getTenDV());
		        preparedStatement.setString(2, updatedDichVu.getMoTa());
		        preparedStatement.setString(3, updatedDichVu.getNamHoc());
		        preparedStatement.setString(4, updatedDichVu.getHocKy());
		        preparedStatement.setString(5, updatedDichVu.getMaDV());

		        int rowsAffected = preparedStatement.executeUpdate();

		        return rowsAffected > 0;

		    } catch (SQLException exception) {
		        JDBCUtils.printSQLException(exception);
		        return false;
		    }
		}

}
