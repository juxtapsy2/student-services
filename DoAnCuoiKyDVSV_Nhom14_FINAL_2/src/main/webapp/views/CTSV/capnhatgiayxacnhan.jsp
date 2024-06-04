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

<%--  <script>
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
    </script>  --%>
    
</head>
<style nonce="<%= nonce %>">

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
DKGiayXacNhanSinhVien gxn = (DKGiayXacNhanSinhVien) request.getAttribute("updategiayxacnhan"); 
%>
<div class ='content'>

<div class='content'>
  <div class="container">
    <form action="updategiayxacnhan" method="post">
     
      <label for="MaNguoiDung">ID:</label>
      <input type="text" id="IDGiay" name="IDGiay" value="<%= gxn.getIDGiayXacNhan() %>" class="readonly-input" readonly required>
      <label for="MaNguoiDung">Mã Người Dùng:</label>
      <input type="text" id="MaNguoiDung" name="MaNguoiDung" value="<%= gxn.getMaNguoiDung() %>" class="readonly-input" readonly required>


      <label for="STT">STT:</label>
      <input type="text" id="STT" name="STT" value="<%=gxn.getStt()%>" class="readonly-input" readonly required>

      <label for="SoLuong">Số lượng:</label>
      <input type="text" id="SoLuong" name="SoLuong" value="<%= gxn.getSoLuong()%>" class="readonly-input" readonly required>

      <label for="NgayDangKy">Ngày Đăng Ký:</label>
      <input type="date" id="NgayDangKy" name="NgayDangKy" value="<%= gxn.getNgayDangKy() %>" class="readonly-input" readonly required>

      <label for="ThoiGianNhan">Thời Gian Nhận:</label>
      <input type="date" id="ThoiGianNhan" name="ThoiGianNhan" value="<%= gxn.getThoiGianNhan() %>" required>

          <label for="TinhTrang">Tình Trạng:</label>
      <select id="TinhTrang" name="TinhTrang" required>
        <option value="Chua Nhan" <%= gxn.getTinhTrang().equals("Chu Nhan") ? "selected" : "" %>>Chưa nhận</option>
        <option value="Da in" <%= gxn.getTinhTrang().equals("Da in") ? "selected" : "" %>>Đã in</option>
          <option value="Da nhan" <%= gxn.getTinhTrang().equals("Da nhan ") ? "selected" : "" %>>Đã nhận</option>
        <!-- Thêm các option khác nếu cần -->
      </select>


      <button type="submit">Cập nhật</button>
    </form>
  </div>
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