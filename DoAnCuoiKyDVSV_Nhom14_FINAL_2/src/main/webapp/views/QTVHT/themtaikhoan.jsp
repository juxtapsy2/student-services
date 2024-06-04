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
            background: url('https://imageurl.com/arrow-down.png') no-repeat right center / 15px 15px; /* Thay đổi URL hình ảnh theo yêu cầu */
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
    
    
<%
List<String> listMaNguoiDung = new ArrayList<String>();
LoginDao logindao = new LoginDao();
listMaNguoiDung = logindao.selectMaNguoiDungChuaCoTrongDangNhap();
%>

</head>

<div class="content">
       <div class="container">
        <form action="themtaikhoan" method="post">
            <label for="MaND">Mã người dùng:</label>
            <select id="MaND" name="MaND" required>
                <% for (int i = 0; i < listMaNguoiDung.size(); i++) { %>
            <% 
            String MaNguoiDung = listMaNguoiDung.get(i);
            %>
           
                    <option value="<%= MaNguoiDung %>"><%= MaNguoiDung %></option>
             
        <% } %>
               
            </select>

            <label for="username">Tên đăng nhập:</label>
            <input type="text" id="username" name="username" required>

            <label for="password">Mật khẩu:</label>
            <input type="password" id="password" name="password" required>

            <label for="confirm_password">Xác nhận mật khẩu:</label>
            <input type="password" id="confirm_password" name="confirm_password" required>

            <label for="role">Vai trò:</label>
            <select id="role" name="role" required>
                <option value="SV">Sinh viên</option>
                <option value="QTVHT">Quản trị viên hệ thống</option>
                <option value="CTSV">Chủ tịch sinh viên</option>
            </select>

            <button type="submit">Tạo tài khoản</button>
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

</html>