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

</head>                                                                         
<%
List<Login> listTaiKhoan = new ArrayList<Login>();
LoginDao logindao = new LoginDao();
listTaiKhoan = logindao.selectAllLogins();

// Lấy kích thước của danh sách
int sizeOfList = listTaiKhoan.size();
%>

<div class='content'>
    <h2>Danh sách tài khoản</h2>
    <a href="<%=request.getContextPath()%>/views/QTVHT/themtaikhoan.jsp" class="add-account-button">Thêm tài khoản</a>
    <ul class="login-list">
        <li class="login-header">
            <span style="flex: 2;">ID</span>
            <span style="flex: 1.5;">Mã Người Dùng</span>
            <span style="flex: 1;">Tên Đăng Nhập</span>
            <span style="flex: 1;">Vai Trò</span>
            <span style="flex: 1;">Luợt Truy Cập</span>
        </li>

        <% for (int i = 0; i < listTaiKhoan.size(); i++) { %>
            <% Login login = listTaiKhoan.get(i); 
            String MaNguoiDung = login.getMaNguoiDung();
            %>
            <li class="login-item">
                <span style="flex: 0.5;"><%= login.getID() %></span>
                <span style="flex: 1.5;"><%= login.getMaNguoiDung() %></span>
                <span style="flex: 1;"><%= login.getTenDangNhap() %></span>
                <span style="flex: 1;"><%= login.getVaiTro() %></span>
                <span style="flex: 1;"><%= login.getLuotTruyCap() %></span>
                <div class="login-actions">
                    <a href="viewtaikhoan?sid=<%= MaNguoiDung %>" class="view button">View</a>
                    <a href="updatetaikhoan?sid=<%= MaNguoiDung %>" class="update button">Update</a>
                    <a href="deletetaikhoan?sid=<%= MaNguoiDung %>" class="delete button" onclick="return confirmDelete('<%= MaNguoiDung %>')">Delete</a>
                </div>
            </li>
        <% } %>
    </ul>
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
</div>


 <!-- Đoạn mã JavaScript để hiển thị cảnh báo -->
    <script nonce="<%= nonce %>">
        function confirmDelete(userId) {
            var result = confirm("Bạn có chắc chắn muốn xóa tài khoản có ID: " + userId + "?");
            return result;
        }
    </script>
</html>