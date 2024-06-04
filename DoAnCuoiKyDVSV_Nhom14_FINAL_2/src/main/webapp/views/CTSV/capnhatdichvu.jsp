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
  <%@include file="/views/common/CTSV/menu.jsp" %>
  <%@ include file="/views/common/footer.jsp" %>
<title>Insert title here</title>

 <script  nonce="<%= nonce %>">
 // Hàm kiểm tra MaND và chuyển hướng
 function checkMaND() {
     var maND = '<%= session.getAttribute("MaND") %>';
     NguoiDung nguoidung1 = (NguoiDung)session.getAttribute("NguoiDung");
     console.log("maND:", maND); 
     if (maND === 'null' || nguoidung1 ==null) {
         window.location.href = '<%=request.getContextPath()%>/dangnhap.jsp';
     }
 }

 // Gọi hàm kiểm tra khi trang được load
 window.onload = checkMaND;
    </script> 
    <style nonce="<%= nonce %>">
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
    }

    .notification-table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
    }

    .notification-table th, .notification-table td {
        border: 1px solid #ddd;
        padding: 8px;
        text-align: left;
    }

    .notification-content {
        padding: 10px;
        border: 1px solid #ddd;
        margin-top: 10px;
    }

    .Inbox_header_dl {
        background-color: #0087C5;
        color: White;
        font-weight: bold;
    }

    .notification-title {
        font-weight: bold;
        color: #000; /* Màu mặc định của tiêu đề */
        text-decoration: none; /* Loại bỏ gạch chân mặc định của thẻ a */
        transition: color 0.3s; /* Hiệu ứng chuyển đổi màu trong 0.3 giây */
    }

    .notification-title:hover {
        color: green; /* Màu xanh khi di chuột qua */
    }
       .add-user-button {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            text-decoration: none;
            color: #fff;
            background-color: #3498db;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .add-user-button:hover {
            background-color: #2980b9;
        }
</style> 
 <script type="text/javascript" nonce="<%= nonce %>">
        <%-- Lấy thông báo từ request attribute --%>
        var thongBao = '<%= request.getAttribute("thongBao") %>';
        
        <%-- Kiểm tra xem có thông báo không thành công không --%>
        if (thongBao != null && thongBao.trim().length() > 0) {
            alert(thongBao);
        }
    </script>
    
</head>
<style nonce="<%= nonce %>">
 .content {
            width: 100%;
            display: flex;
            justify-content: center;
        }

        .container {
            width: 700px;
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
            background: url('https://imageurl.com/arrow-down.png') no-repeat right center / 15px 15px;
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

.readonly-input {
        background-color: #f0f0f0; /* Màu làm mờ có thể được điều chỉnh theo ý muốn của bạn */
        pointer-events: none; /* Ngăn chặn sự kiện chuột để không thể chỉnh sửa */
    }
</style>
<%
DichVu dichvu = (DichVu) request.getAttribute("updatedichvu"); 
%>
<div class ='content'>
  <div class="container">
            <form action="updatedichvu" method="post">
                <label for="MaDV">Mã dịch vụ:</label>
                <input type="text" id="MaDV" name="MaDV"  value ="<%= dichvu.getMaDV()%>" class="readonly-input" required readonly >

                <label for="TenDV">Tên dịch vụ:</label>
                <input type="text" id="TenDV" name="TenDV"  value ="<%= dichvu.getTenDV()%>" required>

                <label for="MoTa">Mô tả:</label>
                <input type="text" id="MoTa" name="MoTa"  value ="<%= dichvu.getMoTa()%>" required>

                <label for="NamHoc">Năm học:</label>
                <input type="text" id="NamHoc" name="NamHoc"  value ="<%= dichvu.getNamHoc()%>" required>

                <label for="HocKy">Học kỳ:</label>
                <input type="text" id="HocKy" name="HocKy"  value ="<%= dichvu.getHocKy()%>" required>

                <button type="submit">Cập nhật dịch vụ</button>
            </form>
        </div>
</div>


  <style nonce="<%= nonce %>">
   
   .content {
    margin-top: 120px;    /* Khoảng cách phía trên */
    margin-right: 20px;   /* Khoảng cách phía phải */
    margin-bottom: 100px; /* Khoảng cách phía dưới */
    margin-left: 330px;  
    height: 100vh; /* Khoảng cách phía trái */
    z-index: 1;           /* Đặt giá trị z-index để đảm bảo nội dung nằm trên menu */
    position: relative;
    background-color: white; /* Thêm màu nền trắng */
    padding: 20px;         /* Thêm padding để tạo khoảng trắng xung quanh nội dung */
}
   
   </style>
</html>