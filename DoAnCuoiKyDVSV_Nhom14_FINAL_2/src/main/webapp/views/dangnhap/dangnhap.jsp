<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <% String nonce = (String) request.getAttribute("nonce"); %>
    <title>Đăng nhập</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">

     <link rel="stylesheet" type="text/css" href="/templates/dangnhap/css/dangnhap.css">
      <script nonce="<%= nonce %>">
 // Hàm kiểm tra MaND và chuyển hướng
 function checkMaND() {
     var maND = '<%= session.getAttribute("MaND") %>';
     console.log("maND:", maND); 
     if (maND != 'null') {
    	 
         window.location.href = '<%=request.getContextPath()%>/index.jsp';
     }
 }
 // Gọi hàm kiểm tra khi trang được load
 window.onload = checkMaND;
    </script>  
</head>
<style nonce="<%= nonce %>">

@charset "UTF-8";
/* style.css */
body {
    font-family: Arial, sans-serif;
    background-color: #f4f4f4;
    margin: 0;
    padding: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    flex-direction: column;
    height: 100vh;
}

form {
    background-color: #fff;
    padding: 20px;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    text-align: center; /* Đặt text-align để căn giữa nội dung trong form */
}

.logo-container {
    margin-bottom: 20px;
}

.logo-container img {
    width: 200px; /* Điều chỉnh chiều rộng của logo */
    height: auto; /* Giữ tỷ lệ khích thước tự động */
}

input {
    width: 100%;
    padding: 10px;
    margin-bottom: 10px;
    box-sizing: border-box;
    border: 1px solid #ccc;
    border-radius: 4px;
}

input[type="submit"] {
    background-color: #2e4ead;
    color: #fff;
    cursor: pointer;
}

input[type="submit"]:hover {
    background-color: #1c3763;
}

</style>
<body>
    <form action="<%=request.getContextPath()%>/dangnhap" method="post">
         <div class="logo-container">
            <img src="/DoAnCuoiKyDVSV_Nhom14_FINAL_2/templates/dangnhap/image/logo.jpg" alt="Logo">
        </div>
        <p class ="text-danger">${mess } <p>
        
       </div>
        <input type="text" name="txtName" placeholder="Tên đăng nhập">
        <input type="password" name="txtPass" placeholder="Mật khẩu">
        <input type="submit" value="Đăng nhập">
    </form>
</body>
</html>

