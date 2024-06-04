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

<div class ='content'>
 <div class="container">
            <form action="themdichvu" method="post">
                <label for="MaDV">Mã dịch vụ:</label>
                <input type="text" id="MaDV" name="MaDV" required>

                <label for="TenDV">Tên dịch vụ:</label>
                <input type="text" id="TenDV" name="TenDV" required>

                <label for="MoTa">Mô tả:</label>
                <input type="text" id="MoTa" name="MoTa" required>

                <label for="NamHoc">Năm học:</label>
                <input type="text" id="NamHoc" name="NamHoc" required>

                <label for="HocKy">Học kỳ:</label>
                <input type="text" id="HocKy" name="HocKy" required>

                <button type="submit">Thêm dịch vụ</button>
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