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
     <style nonce="<%= nonce %>">
      body {
            margin: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            display: flex;
            align-items: center;
         
            height: 100vh;
            background-color: #f4f4f4;
        }

        .content {
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .container {
           
            
            width: 700px; /* Điều chỉnh kích thước container tại đây */
            padding: 30px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            text-align: center;
        }

        form {
            display: flex;
            flex-direction: column;
            align-items: center;
        }

        label {
            font-size: 14px;
            margin-bottom: 8px;
            color: #333;
        }

        input, select {
            width: 100%;
            padding: 10px;
            margin-bottom: 16px;
            box-sizing: border-box;
            border: 1px solid #ccc;
            border-radius: 4px;
            font-size: 14px;
        }

        select {
            appearance: none;
            -webkit-appearance: none;
            -moz-appearance: none;
            background: url('/templates/common/image/logo.jpg') no-repeat right center / 15px 15px; /* Thay đổi URL hình ảnh theo yêu cầu */
            cursor: pointer;
        }
      
        button {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<% Login taikhoan = (Login) request.getAttribute("updatetaikhoan"); %>
<div class='content'>
       <div class="container">
        <form action="updatetaikhoan" method="post">
            <label for="MaND">Mã người dùng:</label>
            <select id="MaND" name="MaND" required>
         
                   <option value="<%= taikhoan.getMaNguoiDung() %>" selected ><%= taikhoan.getMaNguoiDung() %></option>
                 
             
            </select>

            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" value ="<%= taikhoan.getTenDangNhap()%>" required >

            <label for="password">Mật khẩu:</label>
      
          
                    <input type="text" id="password" name="password" value="<%= taikhoan.getMatKhau()%>" required>

            <label for="role">Vai trò:</label>
            <select id="role" name="role" required>
                <option value="SV" <%= taikhoan.getVaiTro().equals("SV") ? "selected" : "" %>>Sinh viên</option>
                <option value="QTVHT" <%= taikhoan.getVaiTro().equals("QTVHT") ? "selected" : "" %>>Quản trị viên hệ thống</option>
                <option value="CTSV" <%= taikhoan.getVaiTro().equals("CTSV") ? "selected" : "" %>>Phòng công tác sinh viên</option>
            </select>

            <button type="submit">Cập nhật tài khoản</button>
        </form>
    </div>
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

  <script nonce="<%= nonce %>">
  function myFunction() {
	  var x = document.getElementById("password");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}
    </script>
</html>