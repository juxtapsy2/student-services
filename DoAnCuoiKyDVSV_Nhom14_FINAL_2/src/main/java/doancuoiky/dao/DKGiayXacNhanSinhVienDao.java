package doancuoiky.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import doancuoiky.model.DKGiayXacNhanSinhVien;
import doancuoiky.model.Login;
import doancuoiky.model.NguoiDung;
import doancuoiky.utils.JDBCUtils;
public class DKGiayXacNhanSinhVienDao {
       DichVuDao dvdao = new DichVuDao();
	   public boolean insertDKGiayXacNhanSinhVien(String maDV, String iDGiayXacNhan, String maNguoiDung,
               String namHoc, String hocKy, int soLuong,
               Timestamp ngayDangKy, Timestamp thoiGianNhan, String tinhTrang, String tenDichVu)
				throws ClassNotFoundException {
							Class.forName("com.mysql.jdbc.Driver");
							
							try (Connection connection = JDBCUtils.getConnection();
							// Step 2: Create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO DKGiayXacNhanSinhVien (MaDV, IDGiayXacNhan, MaNguoiDung, NamHoc, HocKy, SoLuong, NgayDangKy, ThoiGianNhan, TinhTrang, TenDichVu) " +
							"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
							preparedStatement.setString(1, maDV);
							preparedStatement.setString(2, iDGiayXacNhan);
							preparedStatement.setString(3, maNguoiDung);
							preparedStatement.setString(4, namHoc);
							preparedStatement.setString(5, hocKy);
							preparedStatement.setInt(6, soLuong);
							preparedStatement.setTimestamp(7, ngayDangKy);
							preparedStatement.setTimestamp(8, thoiGianNhan);
							preparedStatement.setString(9, tinhTrang);
							preparedStatement.setString(10, dvdao.getTenDVByMaDV(maDV));
							
							System.out.println(preparedStatement);
							int rowsAffected = preparedStatement.executeUpdate();
							
							// Trả về true nếu có ít nhất một dòng được chèn
							return rowsAffected > 0;
							} catch (SQLException e) {
							JDBCUtils.printSQLException(e);
							// Trả về false nếu có lỗi xảy ra
							return false;
				}
}
	   public List<DKGiayXacNhanSinhVien> getDKGiayXacNhanSinhVienListByMaNguoiDung(String maNguoiDung) throws ClassNotFoundException {
	        List<DKGiayXacNhanSinhVien> giayXacNhanList = new ArrayList<>();

	        try {
	            Class.forName("com.mysql.jdbc.Driver");

	            try (Connection connection = JDBCUtils.getConnection();
	                 PreparedStatement preparedStatement = connection.prepareStatement(
	                         "SELECT * FROM DKGiayXacNhanSinhVien WHERE MaNguoiDung = ?")) {
	                preparedStatement.setString(1, maNguoiDung);

	                System.out.println(preparedStatement);
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        DKGiayXacNhanSinhVien giayXacNhan = new DKGiayXacNhanSinhVien();
	                        giayXacNhan.setMaDV(resultSet.getString("MaDV"));
	                        giayXacNhan.setIDGiayXacNhan(resultSet.getString("IDGiayXacNhan"));
	                        giayXacNhan.setMaNguoiDung(resultSet.getString("MaNguoiDung"));
	                        giayXacNhan.setNamHoc(resultSet.getString("NamHoc"));
	                        giayXacNhan.setHocKy(resultSet.getString("HocKy"));
	                        giayXacNhan.setStt(resultSet.getInt("STT"));
	                        giayXacNhan.setSoLuong(resultSet.getInt("SoLuong"));
	                        giayXacNhan.setNgayDangKy(resultSet.getDate("NgayDangKy"));
	                        giayXacNhan.setThoiGianNhan(resultSet.getDate("ThoiGianNhan"));
	                        giayXacNhan.setTinhTrang(resultSet.getString("TinhTrang"));
	                        giayXacNhan.setTenDichVu(resultSet.getString("TenDichVu")); // Thêm dòng này

	                        giayXacNhanList.add(giayXacNhan);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            JDBCUtils.printSQLException(e);
	        }

	        return giayXacNhanList;
	    }
	   public List<DKGiayXacNhanSinhVien> getDKGiayXacNhanSinhVienByTinhTrang(String tinhTrang) throws ClassNotFoundException {
		    List<DKGiayXacNhanSinhVien> giayXacNhanList = new ArrayList<>();

		    try {
		        Class.forName("com.mysql.jdbc.Driver");

		        try (Connection connection = JDBCUtils.getConnection();
		             PreparedStatement preparedStatement = connection.prepareStatement(
		                     "SELECT * FROM DKGiayXacNhanSinhVien WHERE TinhTrang = ?")) {

		            preparedStatement.setString(1, tinhTrang);

		            System.out.println(preparedStatement);

		            try (ResultSet resultSet = preparedStatement.executeQuery()) {
		                while (resultSet.next()) {
		                    DKGiayXacNhanSinhVien giayXacNhan = new DKGiayXacNhanSinhVien();
		                    giayXacNhan.setMaDV(resultSet.getString("MaDV"));
		                    giayXacNhan.setIDGiayXacNhan(resultSet.getString("IDGiayXacNhan"));
		                    giayXacNhan.setMaNguoiDung(resultSet.getString("MaNguoiDung"));
		                    giayXacNhan.setNamHoc(resultSet.getString("NamHoc"));
		                    giayXacNhan.setHocKy(resultSet.getString("HocKy"));
		                    giayXacNhan.setStt(resultSet.getInt("STT"));
		                    giayXacNhan.setSoLuong(resultSet.getInt("SoLuong"));
		                    giayXacNhan.setNgayDangKy(resultSet.getDate("NgayDangKy"));
		                    giayXacNhan.setThoiGianNhan(resultSet.getDate("ThoiGianNhan"));
		                    giayXacNhan.setTinhTrang(resultSet.getString("TinhTrang"));
		                    giayXacNhan.setTenDichVu(resultSet.getString("TenDichVu")); // Thêm dòng này

		                    giayXacNhanList.add(giayXacNhan);
		                }
		            }
		        }
		    } catch (SQLException e) {
		        JDBCUtils.printSQLException(e);
		    }

		    return giayXacNhanList;
		}

	   public List<DKGiayXacNhanSinhVien> getDKGiayXacNhanSinhVienAll() throws ClassNotFoundException {
	        List<DKGiayXacNhanSinhVien> giayXacNhanList = new ArrayList<>();

	        try {
	            Class.forName("com.mysql.jdbc.Driver");

	            try (Connection connection = JDBCUtils.getConnection();
	                 PreparedStatement preparedStatement = connection.prepareStatement(
	                         "SELECT * FROM DKGiayXacNhanSinhVien")) {

	                System.out.println(preparedStatement);
	                try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                    while (resultSet.next()) {
	                        DKGiayXacNhanSinhVien giayXacNhan = new DKGiayXacNhanSinhVien();
	                        giayXacNhan.setMaDV(resultSet.getString("MaDV"));
	                        giayXacNhan.setIDGiayXacNhan(resultSet.getString("IDGiayXacNhan"));
	                        giayXacNhan.setMaNguoiDung(resultSet.getString("MaNguoiDung"));
	                        giayXacNhan.setNamHoc(resultSet.getString("NamHoc"));
	                        giayXacNhan.setHocKy(resultSet.getString("HocKy"));
	                        giayXacNhan.setStt(resultSet.getInt("STT"));
	                        giayXacNhan.setSoLuong(resultSet.getInt("SoLuong"));
	                        giayXacNhan.setNgayDangKy(resultSet.getDate("NgayDangKy"));
	                        giayXacNhan.setThoiGianNhan(resultSet.getDate("ThoiGianNhan"));
	                        giayXacNhan.setTinhTrang(resultSet.getString("TinhTrang"));
	                        giayXacNhan.setTenDichVu(resultSet.getString("TenDichVu")); // Thêm dòng này

	                        giayXacNhanList.add(giayXacNhan);
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            JDBCUtils.printSQLException(e);
	        }

	        return giayXacNhanList;
	    }
	   public boolean deleteDKGiayXacNhanSinhVienByID(String idGiayXacNhan) throws ClassNotFoundException {
		    try {
		        Class.forName("com.mysql.jdbc.Driver");

		        try (Connection connection = JDBCUtils.getConnection();
		             PreparedStatement preparedStatement = connection.prepareStatement(
		                     "DELETE FROM DKGiayXacNhanSinhVien WHERE IDGiayXacNhan = ?")) {

		            preparedStatement.setString(1, idGiayXacNhan);

		            System.out.println(preparedStatement);

		            int rowsAffected = preparedStatement.executeUpdate();

		            // Trả về true nếu có ít nhất một dòng bị ảnh hưởng (đã xóa thành công)
		            return rowsAffected > 0;
		        }
		    } catch (SQLException e) {
		        JDBCUtils.printSQLException(e);
		        // Trả về false nếu có lỗi xảy ra
		        return false;
		    }
		}
	   public DKGiayXacNhanSinhVien getDKGiayXacNhanSinhVienByID(String id) throws ClassNotFoundException {
		    DKGiayXacNhanSinhVien giayXacNhan = null;

		    try {
		        Class.forName("com.mysql.jdbc.Driver");

		        try (Connection connection = JDBCUtils.getConnection();
		             PreparedStatement preparedStatement = connection.prepareStatement(
		                     "SELECT * FROM DKGiayXacNhanSinhVien WHERE IDGiayXacNhan = ?")) {

		            preparedStatement.setString(1, id);

		            try (ResultSet resultSet = preparedStatement.executeQuery()) {
		                if (resultSet.next()) {
		                    giayXacNhan = new DKGiayXacNhanSinhVien();
		                    giayXacNhan.setMaDV(resultSet.getString("MaDV"));
		                    giayXacNhan.setIDGiayXacNhan(resultSet.getString("IDGiayXacNhan"));
		                    giayXacNhan.setMaNguoiDung(resultSet.getString("MaNguoiDung"));
		                    giayXacNhan.setNamHoc(resultSet.getString("NamHoc"));
		                    giayXacNhan.setHocKy(resultSet.getString("HocKy"));
		                    giayXacNhan.setStt(resultSet.getInt("STT"));
		                    giayXacNhan.setSoLuong(resultSet.getInt("SoLuong"));
		                    giayXacNhan.setNgayDangKy(resultSet.getDate("NgayDangKy"));
		                    giayXacNhan.setThoiGianNhan(resultSet.getDate("ThoiGianNhan"));
		                    giayXacNhan.setTinhTrang(resultSet.getString("TinhTrang"));
		                    giayXacNhan.setTenDichVu(resultSet.getString("TenDichVu")); // Thêm dòng này
		                }
		            }
		        }
		    } catch (SQLException e) {
		        JDBCUtils.printSQLException(e);
		    }

		    return giayXacNhan;
		}
	   public boolean updateDKGiayXacNhanSinhVien(String idGiay,   Timestamp  thoiGianNhan, String tinhTrang) {
		    try (Connection connection = JDBCUtils.getConnection();
		         PreparedStatement preparedStatement = connection.prepareStatement(
		                 "UPDATE DKGiayXacNhanSinhVien SET ThoiGianNhan = ?, TinhTrang = ? WHERE IDGiayXacNhan = ?")) {

		        preparedStatement.setDate(1, new java.sql.Date(thoiGianNhan.getTime()));
		        preparedStatement.setString(2, tinhTrang);
		        preparedStatement.setString(3, idGiay);

		        int rowsAffected = preparedStatement.executeUpdate();

		        return rowsAffected > 0;

		    } catch (SQLException exception) {
		        JDBCUtils.printSQLException(exception);
		        return false;
		    }
		}



}
