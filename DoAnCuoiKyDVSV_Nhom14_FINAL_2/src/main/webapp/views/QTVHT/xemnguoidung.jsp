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
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/SV/css/thongtin.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/QTVHT/css/style.css"> 
  <!-- Font Awesome Cdn Link -->
  <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <%@ include file="/views/common/header.jsp" %>
  <%@include file="/views/common/QTVHT/menu.jsp" %>
  <%@ include file="/views/common/footer.jsp" %>
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
<title>Tài khoản</title>


</head>                                                                         
 <% NguoiDung nguoidung = (NguoiDung) request.getAttribute("xemnguoidung");
    TinhTrangDao tinhtrangdao = new TinhTrangDao();
    TinhTrang tinhtrang  = tinhtrangdao.getTinhTrangByMaTinhTrang(nguoidung.getMaTinhTrang());
 %>
<div class='content'>
   <div class="container">
  <div class="ThongTinContainer-info">
    <div class="ThongTin-info">
      <h2>Thông tin Nhân Viên</h2>
      <table>
        <tbody>
          <tr>
            <th>Mã số nhân viên</th>
            <td><%= nguoidung.getMaNguoiDung() %></td>
          </tr>
          <tr>
            <th>Họ và tên</th>
            <td><%= nguoidung.getHoTen() %></td>
          </tr>
          <tr>
            <th>Ngày sinh</th>
            <td><%= nguoidung.getNgaySinh() %></td>
          </tr>
          <tr>
            <th>Giới tính</th>
            <td><%= nguoidung.getGioiTinh() %></td>
          </tr>
           <tr>
            <th>Dân tộc</th>
            <td><%= nguoidung.getDanToc() %></td>
          </tr>
           <tr>
            <th>Số CMND</th>
            <td><%= nguoidung.getCmnd() %></td>
          </tr>
           <tr>
            <th>Tôn giáo</th>
            <td><%= nguoidung.getTonGiao() %></td>
          </tr>
           <tr>
            <th>Địa chỉ</th>
            <td><%= nguoidung.getDiaChi() %></td>
          </tr>
           <tr>
            <th>Quê quán</th>
            <td><%= nguoidung.getQueQuan()%></td>
          </tr>
           <tr>
            <th>Tình trạng</th>
            <td><%= tinhtrang.getTenTinhTrang()%></td>
          </tr>
            <tr>
            <th>Lớp sinh viên</th>
            <td><%= nguoidung.getMaLop() %></td>
          
        </tbody>
      </table>
    </div>

    <div class="ThongTin-info">
      <h2>Thông tin Liên lạc</h2>
      <table>
        <tbody>
          <tr>
            <th>Số điện thoại</th>
            <td><%= nguoidung.getSdt() %></td>
          </tr>
          <tr>
            <th>Email</th>
            <td><%= nguoidung.getEmail() %></td>
          </tr>
        </tbody>
      </table>
 
    </div>
    
    
  </div>
 
</body>
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
</div>



</html>