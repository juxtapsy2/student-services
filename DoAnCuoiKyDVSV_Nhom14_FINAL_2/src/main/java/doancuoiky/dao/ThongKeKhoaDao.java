package doancuoiky.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import doancuoiky.model.ThongKeKhoa;
import doancuoiky.utils.JDBCUtils;

public class ThongKeKhoaDao {
	YeuCauDao ycdao = new YeuCauDao();
	public ThongKeKhoaDao() {
		// TODO Auto-generated constructor stub
	}
	 public  List<ThongKeKhoa> calculateStatistics(List<String> studentCodes) {
	        Map<String, Integer> courseCounts = new HashMap<>();

	        // Iterate through the student codes
	        for (String studentCode : studentCodes) {
	            // Extract the course name (first two characters)
	            String courseName = studentCode.substring(0, 2);

	            // Update the count for the course
	            courseCounts.put(courseName, courseCounts.getOrDefault(courseName, 0) + 1);
	        }

	        // Create ThongKe objects from the map entries
	        List<ThongKeKhoa> thongKeList = new ArrayList<>();
	        for (Map.Entry<String, Integer> entry : courseCounts.entrySet()) {
	            ThongKeKhoa thongKe = new ThongKeKhoa(entry.getKey(), entry.getValue());
	            thongKe.setKhoaType("Khóa");
	            thongKeList.add(thongKe);
	        }

	        return thongKeList;
	    }
	 public List<ThongKeKhoa> thongKeTheoKhoaYeuCau() throws SQLException, ClassNotFoundException {
	        Map<String, Integer> khoaCounts = new HashMap<>();
	        List<String> khoaCodes = getAllMaKhoa();

	        List<String> maDichVu = ycdao.getAllYeuCau().stream().map(t -> {return t.getMaDV();}).collect(Collectors.toList());
	        // Tạo một map để lưu số lượng yêu cầu cho mỗi khoa
	        for (String maDv : maDichVu) {
	            // Tìm MaNguoiDung từ YeuCau hoặc DangKyNoiTru hoặc DangKyNgoaiTru, tùy thuộc vào cách bạn thiết kế
	            String maNguoiDung = findMaNguoiDungByMaDichVu(maDv);

	            // Nếu MaNguoiDung không null và thuộc khoa cần thống kê, thì tăng số lượng cho khoa đó
	            if (maNguoiDung != null && isMaNguoiDungInKhoa(maNguoiDung, khoaCodes)) {
	                String maKhoa = findMaKhoaByMaNguoiDung(maNguoiDung);
	                khoaCounts.put(maKhoa, khoaCounts.getOrDefault(maKhoa, 0) + 1);
	            }
	        }

	        // Chuyển đổi map thành danh sách ThongKeKhoa
	        List<ThongKeKhoa> thongKeKhoaList = new ArrayList<>();
	        Map<String, String> tenKhoaMap = getKhoaNameByKhoaCode(khoaCodes);
	        for (Map.Entry<String, Integer> entry : khoaCounts.entrySet()) {
	            ThongKeKhoa thongKe = new ThongKeKhoa(tenKhoaMap.getOrDefault(entry.getKey(), ""), entry.getValue());
	            thongKe.setKhoaType("Department");
	            thongKeKhoaList.add(thongKe);
	        }

	        return thongKeKhoaList;
	    }
	 private String findMaNguoiDungByMaDichVu( String maDichVu) throws SQLException {
	        String maNguoiDung = null;
	        Connection connection = JDBCUtils.getConnection();

	        // Viết logic để truy vấn cơ sở dữ liệu và trả về MaNguoiDung tương ứng với MaDichVu
	        String sql = "SELECT MaNguoiDung FROM YEUCAU WHERE MaDV = ?";
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, maDichVu);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    maNguoiDung = resultSet.getString("MaNguoiDung");
	                }
	            }
	        }

	        return maNguoiDung;
	    }

	    private boolean isMaNguoiDungInKhoa(String maNguoiDung, List<String> khoaCodes) throws SQLException {
	        // Viết logic để kiểm tra xem MaNguoiDung có thuộc vào danh sách khoa không
	        String sql = "select ng.MaKhoa from nguoidung n inner join lop l on n.MaLop = l.MaLop inner join nganh ng on ng.MaNganh = l.MaNganh WHERE n.MaNguoiDung = ?";
	        Connection connection = JDBCUtils.getConnection();
	        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, maNguoiDung);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    String maKhoa = resultSet.getString("MaKhoa");
	                    return khoaCodes.contains(maKhoa);
	                }
	            }
	        }

	        return false;
	    }

	    private String findMaKhoaByMaNguoiDung( String maNguoiDung) throws SQLException {
	        // Viết logic để truy vấn cơ sở dữ liệu và trả về MaKhoa tương ứng với MaNguoiDung
	        
	    	String sql = "SELECT MaKhoa FROM NGANH WHERE MaNganh IN (SELECT MaNganh FROM LOP WHERE MaLop IN (SELECT MaLop FROM NguoiDung WHERE MaNguoiDung = ?))";
	    	Connection connection = JDBCUtils.getConnection();
	    	try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            preparedStatement.setString(1, maNguoiDung);
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                if (resultSet.next()) {
	                    return resultSet.getString("MaKhoa");
	                }
	            }
	        }

	        return null;
	    }
	    
	    private List<String> getAllMaKhoa() throws SQLException {
	        // Viết logic để truy vấn cơ sở dữ liệu và trả về MaKhoa tương ứng với MaNguoiDung
	        List<String> khoaList = new ArrayList();
	    	String sql = "SELECT MaKhoa FROM Khoa";
	    	Connection connection = JDBCUtils.getConnection();
	    	try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	            try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                while(resultSet.next()) {
	                	khoaList.add(resultSet.getString("MaKhoa"));
	                }
	            }
	        }

	        return khoaList;
	    }
	    
	    private Map<String, String> getKhoaNameByKhoaCode(List<String> khoaCodeList){
	    	Map<String,String> khoaList = new HashMap<String, String>();
	    	 // Creating the placeholder string for the IN clause
	        String placeholders = String.join(",", java.util.Collections.nCopies(khoaCodeList.size(), "?"));

	        // SQL query with the IN clause
	        String sql = "SELECT MaKhoa, TenKhoa FROM Khoa WHERE MaKhoa IN (" + placeholders + ")";
	        try (Connection connection = JDBCUtils.getConnection();
	                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

	               // Setting values for the placeholders
	               for (int i = 0; i < khoaCodeList.size(); i++) {
	                   preparedStatement.setString(i + 1, khoaCodeList.get(i));
	               }

	               try (ResultSet resultSet = preparedStatement.executeQuery()) {
	                   while (resultSet.next()) {
	                       // Retrieve the Khoa code and Khoa name from the result set
	                       String maKhoa = resultSet.getString("MaKhoa");
	                       String tenKhoa = resultSet.getString("TenKhoa");

	                       // Add the Khoa code and Khoa name to the map
	                       khoaList.put(maKhoa, tenKhoa);
	                   }
	               }
	           } catch (SQLException e) {
	               e.printStackTrace();
	               // Handle SQLException
	           }

	           return khoaList;
	    }

}
