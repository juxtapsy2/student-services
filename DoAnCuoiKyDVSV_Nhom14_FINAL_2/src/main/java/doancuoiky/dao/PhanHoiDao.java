package doancuoiky.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.mail.util.*;

import doancuoiky.model.Login;
import doancuoiky.model.NguoiDung;
import doancuoiky.model.PhanHoi;
import doancuoiky.model.YeuCau;
import doancuoiky.utils.JDBCUtils;

public class PhanHoiDao {

	public boolean insertPhanHoi(String maPH, String maYC, String maND,
            String tieuDePH, String noiDungPH, Timestamp ngayPH)
				throws ClassNotFoundException {
							Class.forName("com.mysql.jdbc.Driver");
							
							try (Connection connection = JDBCUtils.getConnection();
							// Step 2: Create a statement using connection object
							PreparedStatement preparedStatement = connection.prepareStatement(
							"INSERT INTO PHANHOI (MaPH, MaYC, MaNguoiDung, TieuDePH, NoiDungPH, NgayPH) " +
							"VALUES (?, ?, ?, ?, ?, ?)")) {
							preparedStatement.setString(1, maPH);
							preparedStatement.setString(2, maYC);
							preparedStatement.setString(3, maND);
							preparedStatement.setString(4, tieuDePH);
							preparedStatement.setString(5, noiDungPH);
							preparedStatement.setTimestamp(6, ngayPH);
							
							System.out.println(preparedStatement);
							int rowsAffected = preparedStatement.executeUpdate();
							return rowsAffected > 0;
							} 
							catch (SQLException e) {
							JDBCUtils.printSQLException(e);
							return false;
							}

				}

	public List<PhanHoi> getPhanHoiByMaND(String maND) throws ClassNotFoundException {
        List<PhanHoi> listPH = null;

        
        Class.forName("com.mysql.jdbc.Driver");

        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection
                     .prepareStatement("SELECT * FROM PHANHOI WHERE MaNguoiDung = ?")) {

            preparedStatement.setString(1, maND);

            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                PhanHoi ph = new PhanHoi();
                ph.setMaPH(rs.getString("MaPH"));
                ph.setMaYC(rs.getString("MaYC"));
                ph.setMaND(maND);
                ph.setTieuDePH(rs.getNString("TieuDePH"));
                ph.setNoiDungPH(rs.getNString("NoiDungPH"));
                ph.setNgayPH(rs.getTimestamp("NgayPH"));
                listPH.add(ph);
            }

        } catch (SQLException e) {
            JDBCUtils.printSQLException(e);
        }

        return listPH;
    }

}