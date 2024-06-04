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
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/QTVHT/css/style.css"> 
  <!-- Font Awesome Cdn Link -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <%@ include file="/views/common/header.jsp" %>
  <%@include file="/views/common/QTVHT/menu.jsp" %>
  <%@ include file="/views/common/footer.jsp" %>
<title>Tài khoản</title>
 <script nonce="<%= nonce %>">
 // Hàm kiểm tra MaND và chuyển hướng
 function checkMaND() {
     var maND = '<%= session.getAttribute("MaND") %>';
     console.log("maND:", maND); 
     if (maND === 'null') {
         window.location.href = '<%=request.getContextPath()%>/views/dangnhap/dangnhap.jsp';
     }
 }

 // Gọi hàm kiểm tra khi trang được load
 window.onload = checkMaND;
    </script>
<style nonce="<%= nonce %>">
@charset "UTF-8";

body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
}

h2 {
    text-align: center;
}

.nguoidung-list {
    list-style-type: none;
    padding: 0;
}

.nguoidung-header {
    background-color: #3498db;
    color: #fff;
    padding: 10px;
    text-align: center;
    font-weight: bold;
    display: flex;
    justify-content: space-between;
}

.nguoidung-item {
    background-color: #fff;
    margin: 10px 0;
    padding: 15px;
    border-radius: 5px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    display: flex;
    justify-content: space-between;
    align-items: center;
}

.nguoidung-item:hover {
    background-color: #f0f0f0;
}

.nguoidung-item span {
    flex: 1;
    margin-right: 10px;
}

.nguoidung-actions {
    display: flex;
    align-items: center;
}

.nguoidung-actions a {
    text-decoration: none;
    padding: 8px 12px;
    cursor: pointer;
    border-radius: 3px;
}

.view {
    background-color: #4caf50;
    color: #fff;
    margin-right: 5px;
}

.update {
    background-color: #2196f3;
    color: #fff;
    margin-right: 5px;
}

.delete {
    background-color: #f44336;
    color: #fff;
}

.account-container {
    text-align: center;
}

.add-account-button {
    display: inline-block;
    margin-bottom: 20px;
    padding: 10px 20px;
    font-size: 16px;
    font-weight: bold;
    text-align: center;
    text-decoration: none;
    background-color: #4caf50;
    color: #fff;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.add-account-button:hover {
    background-color: #45a049;
}

.custom-file-upload {
    cursor: pointer;
    display: inline-block;
    padding: 10px 20px;
    font-size: 16px;
    font-weight: bold;
    color: #fff;
    background-color: #3498db;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.custom-file-upload:hover {
    background-color: #2980b9;
}

.input-file {
    display: none;
}

.submit-button {
    margin-top: 10px;
    padding: 10px 20px;
    font-size: 16px;
    font-weight: bold;
    color: #fff;
    background-color: #2ecc71;
    border: none;
    border-radius: 5px;
    cursor: pointer;
    transition: background-color 0.3s ease;
}

.submit-button:hover {
    background-color: #27ae60;
}
#file-name-display {
    margin-top: 10px;
    font-size: 14px;
    color: #555;
}

</style>
</head>                                                                         
<%
List<NguoiDung> listnguoidung = new ArrayList<NguoiDung>();

NguoiDungDao nguoidungdao = new NguoiDungDao();
listnguoidung = nguoidungdao.selectAllNguoiDung();

// Lấy kích thước của danh sách
int sizeOfList = listnguoidung.size();
%>

<div class='content'>

   <h2>Danh sách người dùng</h2>
<a href="<%=request.getContextPath()%>/views/QTVHT/themnguoidung.jsp" class="add-account-button">Thêm người dùng</a>

 <form action="ImportServletSV" method="post" enctype="multipart/form-data" class="custom-form">
        <label for="file" class="custom-file-upload">
            Chọn file excel
        </label>
        <input type="file" name="file" id="file" class="input-file" onchange="displayFileName()" />
        <input type="submit" value="Import" class="submit-button" />
        <div id="file-name-display">No file selected</div>
    </form>
<ul class="nguoidung-list">
    <li class="nguoidung-header">
        <span style="flex: 0.3">Mã sinh viên</span>
        <span style="flex: 1;">Họ Tên</span>
        <span style="flex: 1;">Giới tính</span>
        <span style="flex: 1;">CMND</span>
        <!-- Thêm các cột khác tùy ý -->
    </li>

    <% for (int i = 0; i < listnguoidung.size(); i++) { %>
        <% NguoiDung nguoiDung = listnguoidung.get(i); %>
        <li class="nguoidung-item">
            <span style="flex: 2;"><c:out value="${nguoiDung.getMaNguoiDung()}" /></span>
			<span style="flex: 1;"><c:out value="${nguoiDung.getHoTen()}" /></span>
			<span style="flex: 1;"><c:out value="${nguoiDung.getGioiTinh()}" /></span>
			<span style="flex: 1;"><c:out value="${nguoiDung.getDanToc()}" /></span>
            
            <!-- Thêm các cột khác tùy ý -->

            <div class="nguoidung-actions">
                <a href="viewnguoidung?sid=<%= nguoiDung.getMaNguoiDung() %>" class="view button">View</a>
                <a href="updatenguoidung?sid=<%= nguoiDung.getMaNguoiDung() %>" class="update button">Update</a>
                <a href="deletenguoidung?sid=<c:out value="${nguoiDung.getMaNguoiDung()}" />" class="delete button" onclick="return confirmDelete('<c:out value="${nguoiDung.getMaNguoiDung()}" />')">Delete</a>
            </div>
        </li>
    <% } %>
</ul>
   
   
   


 <!-- Đoạn mã JavaScript để hiển thị cảnh báo -->
    <script nonce="<%= nonce %>">
        function confirmDelete(userId) {
            var result = confirm("Bạn có chắc chắn muốn xóa người dùng này?");
            return result;
        }
    </script>

   
</div>

  <style nonce="<%= nonce %>">
   
   .content {
    margin-top: 120px;    /* Khoảng cách phía trên */
    margin-right: 20px;   /* Khoảng cách phía phải */
    margin-bottom: 100px; /* Khoảng cách phía dưới */
    margin-left: 330px;  
    height: 200vh; /* Khoảng cách phía trái */
    z-index: 1;           /* Đặt giá trị z-index để đảm bảo nội dung nằm trên menu */
    position: relative;
    background-color: white; /* Thêm màu nền trắng */
    padding: 20px;         /* Thêm padding để tạo khoảng trắng xung quanh nội dung */
}
   
   </style>
<script nonce="<%= nonce %>">

function displayFileName() {
    const fileInput = document.getElementById('file');
    const fileNameDisplay = document.getElementById('file-name-display');

    if (fileInput.files.length > 0) {
        fileNameDisplay.textContent = fileInput.files[0].name;
    } else {
        fileNameDisplay.textContent = 'No file selected';
    }
}

</script>
</html>