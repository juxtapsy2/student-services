package doancuoiky.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.mail.util.*;

import doancuoiky.model.Login;
import doancuoiky.model.NguoiDung;
import doancuoiky.model.YeuCau;
import doancuoiky.utils.JDBCUtils;

public class YeuCauDao {

	public boolean insertYeuCau(String maYC, String maDV, String maND,
            String tieuDeYC, String noiDungYC, Timestamp ngayYC, String trangThai)
				throws ClassNotFoundException {
							Class.forName("com.mysql.jdbc.Driver");
							
							try (Connection connection = JDBCUtils.getConnection();
							// Step 2: Create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO YEUCAU (MaYC, MaDV, MaNguoiDung, TieuDeYC, NoiDungYC, NgayYC, TrangThai) " +
							"VALUES (?, ?, ?, ?, ?, ?, ?)")) {
							preparedStatement.setString(1, maYC);
							preparedStatement.setString(2, maDV);
							preparedStatement.setString(3, maND);
							preparedStatement.setString(4, tieuDeYC);
							preparedStatement.setString(5, noiDungYC);
							preparedStatement.setTimestamp(6, ngayYC);
							preparedStatement.setString(7, trangThai);
							
							System.out.println(preparedStatement);
							int rowsAffected = preparedStatement.executeUpdate();
							return rowsAffected > 0;
							} 
							catch (SQLException e) {
							JDBCUtils.printSQLException(e);
							return false;
							}

				}
	
	@SuppressWarnings("null")
	public List<YeuCau> getAllYeuCau() throws ClassNotFoundException {
        List<YeuCau> listYC = new ArrayList<YeuCau>();
        
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM YEUCAU")) {

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                YeuCau yc = new YeuCau();
                yc.setMaYC(rs.getString("MaYC"));
                yc.setMaDV(rs.getString("MaDV"));
                yc.setMaNguoiDung(rs.getString("MaNguoiDung"));
                yc.setSTT(rs.getInt("STT"));
                yc.setTieuDeYC(rs.getNString("TieuDeYC"));
                yc.setNoiDungYC(rs.getNString("NoiDungYC"));
                yc.setNgayYC(rs.getTimestamp("NgayYC"));
                yc.setTrangThai(rs.getNString("TrangThai")); 
                listYC.add(yc);
            }

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }

        return listYC;
    }
	
	/*
	 * @SuppressWarnings("null") public List<YeuCau> getYeuCauByMaND(String maND)
	 * throws ClassNotFoundException { List<YeuCau> listYC = new
	 * ArrayList<YeuCau>();
	 * 
	 * 
	 * Class.forName("com.mysql.jdbc.Driver");
	 * 
	 * try (Connection connection = JDBCUtils.getConnection(); PreparedStatement
	 * preparedStatement = connection
	 * .prepareStatement("SELECT * FROM YEUCAU WHERE MaNguoiDung = ?")) {
	 * 
	 * preparedStatement.setString(1, maND);
	 * 
	 * System.out.println(preparedStatement); ResultSet rs =
	 * preparedStatement.executeQuery();
	 * 
	 * if (rs.next()) { YeuCau yc = new YeuCau(); yc.setMaYC(rs.getString("MaYC"));
	 * yc.setMaDV(rs.getString("MaDV")); yc.setMaNguoiDung(maND);
	 * yc.setSTT(rs.getInt("STT")); yc.setTieuDeYC(rs.getNString("TieuDeYC"));
	 * yc.setNoiDungYC(rs.getNString("NoiDungYC"));
	 * yc.setNgayYC(rs.getTimestamp("NgayYC"));
	 * yc.setTrangThai(rs.getNString("TrangThai")); listYC.add(yc); }
	 * 
	 * } catch (SQLException e) { JDBCUtils.printSQLException(e); }
	 * 
	 * return listYC; }
	 */
	public List<YeuCau> getAllYeuCauByMaNguoiDung(String maNguoiDung) throws ClassNotFoundException {
	    List<YeuCau> listYC = new ArrayList<>();

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM YEUCAU WHERE MaNguoiDung = ?")) {

	        preparedStatement.setString(1, maNguoiDung);

	        System.out.println(preparedStatement);
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            YeuCau yc = new YeuCau();
	            yc.setMaYC(rs.getString("MaYC"));
	            yc.setMaDV(rs.getString("MaDV"));
	            yc.setMaNguoiDung(rs.getString("MaNguoiDung"));
	            yc.setSTT(rs.getInt("STT"));
	            yc.setTieuDeYC(rs.getNString("TieuDeYC"));
	            yc.setNoiDungYC(rs.getNString("NoiDungYC"));
	            yc.setNgayYC(rs.getTimestamp("NgayYC"));
	            yc.setTrangThai(rs.getNString("TrangThai"));
	            listYC.add(yc);
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return listYC;
	}

	public List<String> getAllMaNguoiDungFromYeuCau() throws ClassNotFoundException {
	    List<String> maNguoiDungs = new ArrayList<>();

	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Tạo một PreparedStatement với câu truy vấn không có điều kiện WHERE
	         PreparedStatement preparedStatement = connection
	                 .prepareStatement("SELECT MaNguoiDung FROM YEUCAU")) {

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            // Đối với mỗi hàng trong kết quả, thêm MaNguoiDung vào danh sách
	            String maNguoiDung = rs.getString("MaNguoiDung");
	            maNguoiDungs.add(maNguoiDung);
	        }

	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	    }

	    return maNguoiDungs;
	}

	public YeuCau getYeuCauCuThe(String maND, String maYC) throws ClassNotFoundException {
        YeuCau yc = new YeuCau();

        
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM YEUCAU WHERE MaNguoiDung = ? AND MaYC = ?")) {

            preparedStatement.setString(1, maND);
            preparedStatement.setString(2, maYC);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                yc = new YeuCau();
                yc.setMaYC(rs.getString("MaYC"));
                yc.setMaDV(rs.getString("MaDV"));
                yc.setMaNguoiDung(maND);
                yc.setSTT(rs.getInt("STT"));
                yc.setTieuDeYC(rs.getNString("TieuDeYC"));
                yc.setNoiDungYC(rs.getNString("NoiDungYC"));
                yc.setNgayYC(rs.getTimestamp("NgayYC"));
                yc.setTrangThai(rs.getNString("TrangThai")); 
            }

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }

        return yc;
    }
	public boolean updateTrangThaiYeuCau(String maYC, String trangThaiMoi) throws ClassNotFoundException {
	    Class.forName("com.mysql.jdbc.Driver");

	    try (Connection connection = JDBCUtils.getConnection();
	         // Step 2: Create a statement using connection object
	         PreparedStatement preparedStatement = connection.prepareStatement(
	                 "UPDATE YEUCAU SET TrangThai = ? WHERE MaYC = ?")) {
	        preparedStatement.setString(1, trangThaiMoi);
	        preparedStatement.setString(2, maYC);

	        System.out.println(preparedStatement);
	        int rowsAffected = preparedStatement.executeUpdate();
	        return rowsAffected > 0;
	    } catch (SQLException e) {
	        JDBCUtils.printSQLException(e);
	        return false;
	    }
	}


}