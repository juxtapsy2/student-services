<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%@ page import="doancuoiky.model.*" %>
 <%@ page import="doancuoiky.dao.*" %>
 <%@ page import="java.util.*" %>
 <%@ page import="java.util.ArrayList" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
      <% String nonce = (String) request.getAttribute("nonce"); %>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/index/style.css">
  <!-- Font Awesome Cdn Link -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <%@ include file="/views/common/header.jsp" %>
  <%@include file="/views/common/QTVHT/menu.jsp" %>
  <%@ include file="/views/common/footer.jsp" %>
<title>Insert title here</title>

 <script nonce="<%= nonce %>">
 // Hàm kiểm tra MaND và chuyển hướng
 function checkMaND() {
     var maND = '<%= session.getAttribute("MaND") %>';
     console.log("maND:", maND); 
     if (maND === 'null') {
         window.location.href = '<%=request.getContextPath()%>/dangnhap.jsp';
     }
 }

 // Gọi hàm kiểm tra khi trang được load
 window.onload = checkMaND;
    </script> 

    
    
<%
List<String> listMaNguoiDung = new ArrayList<String>();
LoginDao logindao = new LoginDao();
listMaNguoiDung = logindao.selectMaNguoiDungChuaCoTrongDangNhap();
%>

</head>

 <style nonce="<%= nonce %>">
  body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f4f4f4;
    }

   
     form {
        max-width: 10000px; /* Đặt giới hạn chiều rộng tối đa của form */
        width: 1000px; /* Chiều rộng tự động theo nội dung */
        height: 900px
    }
    .column {
        float: left;
        width: 50%;
        box-sizing: border-box;
        padding: 15px;
    }

    .form-group {
        margin-bottom: 0px;
    }

    label {
        display: block;
        margin-bottom: 5px;
    }
    .container h2 {
  text-align: center;
}
    .container {
  margin-left: 120px; /* Điều chỉnh giá trị theo mong muốn */
}
    input,
    select {
        width: 100%;
        padding: 8px;
        box-sizing: border-box;
        margin-top: 5px;
        margin-bottom: 10px;
        border: 1px solid #ccc;
        border-radius: 4px;
    }

    button {
        background-color: #4caf50;
        color: #fff;
        padding: 10px 15px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
    }

    button:hover {
        background-color: #45a049;
    }
    </style>
</head>
<body>
 <div class="content">
   <form action="themnguoidung" method="post">
   
      <div class="container">
       <h2>Thêm người dùng mới</h2>
        <div class="column">
           
            <div class="form-group">
                <label for="maNguoiDung">Mã Người Dùng:</label>
                <input type="text" id="maNguoiDung" name="maNguoiDung" maxlength="10" required>
            </div>
            <div class="form-group">
                <label for="hoTen">Họ Tên:</label>
                <input type="text" id="hoTen" name="hoTen"   required>
            </div>
            
              <div class="form-group">
			    <label for="ngaySinh">Ngày sinh</label>
			    <input type="date" id="ngaySinh" name="ngaySinh" required>
			    <span id="ngaySinhError" style="color: red; display: none;">Ngày sinh không hợp lệ</span>
			</div>
			
			<script>
			    document.getElementById('ngaySinh').addEventListener('input', function() {
			        var ngaySinh = this.value;
			        var isValid = /\d{4}-\d{2}-\d{2}/.test(ngaySinh);
			        
			        if (!isValid) {
			            document.getElementById('ngaySinhError').style.display = 'inline';
			        } else {
			            document.getElementById('ngaySinhError').style.display = 'none';
			        }
			    });
			</script>
              <div class="form-group">
                 <label for="gioiTinh">Giới tính</label>
            <select id="gioiTinh" name="gioiTinh" required>
             <option value="">Chọn giới tính</option>
                <option value="Nam">Nam</option>
                <option value="Nu">Nữ</option>
                <option value="Khác">Khác</option>
            </select>
            </div>
             <div class="form-group">
             <label for="danToc">Dân tộc</label>
            <input type="text" id="danToc" name="danToc"  required>
            </div>
              <div class="form-group">
                  <label for="cmnd">CMND</label>
            <input type="text" id="cmnd" name="cmnd" required>
            </div>
              <div class="form-group">
                <label for="tonGiao">Tôn giáo</label>
            <input type="text" id="tonGiao" name="tonGiao"  required>
            </div>
           <div class="form-group">
               <label for="queQuan">Quê quán</label>
            <input type="text" id="queQuan" name="queQuan"  required>
               
            </div>
                  <div class="form-group">
                <label for="maLop">Mã lớp</label>
    <select id="maLop" name="maLop" required>
        <option value="" disabled selected>Select MaLop</option>
        <% 
            LopDao lopdao = new LopDao();
            List<Lop> lopList = lopdao.getAllLops();
            for (Lop lop : lopList) {
        %>
        <option value="<%= lop.getMaLop() %>"><%= lop.getMaLop() %></option>
        <% } %>
    </select>
            </div>
            
            
        </div>

        <div class="column">
        
           <div class="form-group">
               <label for="vaiTro">Vai trò</label>
            <select id="vaiTro" name="vaiTro"  required>
                <option value="">Chọn vai trò</option>
                <option value="SV">Sinh viên</option>
                <option value="CTSV">Giáo viên</option>
                <option value="QTVHT">Quản trị viên</option>
            </select>
            
            </div>
             <div class="form-group">
             
              
              
              
              
           
                <label for="maTinhTrang">Mã tình trạng</label>
            <select id="maTinhTrang" name="maTinhTrang" required>
            <option value="" disabled selected>Chọn mã tình trạng</option>
		    <% 
		        TinhTrangDao tinhtrangdao = new TinhTrangDao();
		        List<TinhTrang> tinhTrangList = tinhtrangdao.getAllTinhTrang();
		        for (TinhTrang tinhTrang : tinhTrangList) {
		    %>
		    <option value="<%= tinhTrang.getMaTinhTrang() %>"></option>
		    <% } %>
        </select>                 
            </div>
            <div class="form-group">
                <label for="diaChi">Địa Chỉ:</label>
                <input type="text" id="diaChi" name="diaChi" >
            </div>
            <div class="form-group">
                <label for="sdt">Số Điện Thoại:</label>
                <input type="text" id="sdt" name="sdt" >
            </div>
             <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email"  >
            </div>
            <!-- Thêm các trường thông tin liên hệ khác tương tự -->
        </div>

        <button type="submit">Thêm Thông Tin</button>
    </div>
    </form>
    </div>

  <style nonce="<%= nonce %>">
   
   .content {
    margin-top: 120px;    /* Khoảng cách phía trên */
    margin-right: 20px;   /* Khoảng cách phía phải */
    margin-bottom: 100px; /* Khoảng cách phía dưới */
    margin-left: 330px;  
    height: 85vh; /* Khoảng cách phía trái */
    z-index: 1;           /* Đặt giá trị z-index để đảm bảo nội dung nằm trên menu */
    position: relative;
    background-color: white; /* Thêm màu nền trắng */
    padding: 20px;         /* Thêm padding để tạo khoảng trắng xung quanh nội dung */
}
   
   </style>

</html>