<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="doancuoiky.model.DKGiayXacNhanSinhVien" %>
<%@ page import="doancuoiky.model.Lop" %>
<%@ page import="doancuoiky.dao.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <% String nonce = (String) request.getAttribute("nonce"); %>
<title>Insert title here</title>
 <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/SV/css/thongtin.css">
  <!-- Font Awesome Cdn Link -->
  <%@ include file="/views/common/header.jsp" %>
  <%@include file="/views/common/SV/menu.jsp" %>
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
  <style nonce="<%= nonce %>">
    /* CSS cho fieldset */
        fieldset {
            border: 2px solid #333;
            border-radius: 5px;
            padding: 20px;
            background-color: #fff;
            margin-bottom: 20px;
        }

        /* CSS cho combobox và tiêu đề */
        .form-group {
            margin-bottom: 15px;
        }

        label {
            display: block;
            font-weight: bold;
            margin-bottom: 5px;
        }

        select {
            width: 700px;
            padding: 8px;
            border: 1px solid #ccc;
            border-radius: 4px;
             font-size: 16px
        }

        /* CSS cho nút đăng ký */
        .register-button {
            padding: 10px 20px;
            background-color: #4CAF50;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        /* CSS cho bảng */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #333;
        }

        th, td {
            padding: 10px;
            text-align: left;
        }
  
  </style>
</head>
<body>
<%

DKGiayXacNhanSinhVienDao gxnsvdao = new DKGiayXacNhanSinhVienDao();
List<DKGiayXacNhanSinhVien> giayxacnhanlist = new ArrayList<>();
giayxacnhanlist = gxnsvdao.getDKGiayXacNhanSinhVienListByMaNguoiDung((String)session.getAttribute("MaND"));
session.setAttribute("giayXacNhanList", giayxacnhanlist);

%>
<div class ='content'>
 <form action="<%=request.getContextPath()%>/DKGiayXacNhanSinhVienController" method="post">
         <div>
    <fieldset>
        <legend>Đăng ký giấy xác nhận</legend>
          <div class="form-group">
                <label for="GiayXacNhan">Chọn giấy xác nhận</label>
                <select id="GiayXacNhan" name="GiayXacNhan">
                    <option value="DV001">Giấy xác nhận sinh viên</option>
                </select>
            </div>

            <div class="form-group">
                <label for="SoLuong">Chọn số lượng</label>
                <select id="SoLuong" name="SoLuong">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                    <option value="5">5</option>
                </select>
            </div>
            
            <button type="submit"  class="register-button">Đăng ký</button>
            <table>
    <thead>
        <tr>
            <th>Thời gian đăng ký</th>
            <th>Tên giấy xác nhận</th>
            <th>Số thứ tự</th>
            <th>Số lượng</th>
            <th>Thời gian nhận</th>
            <th>Tình trạng</th>
           
            <!-- Add other headers as needed -->
        </tr>
    </thead>
    <tbody>
      <c:forEach var="giayXacNhan" items="${giayXacNhanList}">
            <tr>
                
              <td>${giayXacNhan.ngayDangKy}</td>
                <td>${giayXacNhan.tenDichVu}</td>
              <td>${giayXacNhan.stt}</td>
              <td>${giayXacNhan.soLuong}</td>
               <td>${giayXacNhan.thoiGianNhan}</td>
                <td>${giayXacNhan.tinhTrang}</td> 
            
                
                
                
             
           
                <!-- Add other data cells as needed -->
            </tr>
        </c:forEach>
    </tbody>
</table>
            
        
        
    </fieldset>
</div>
    </form>
 
<br />
<div>
    <fieldset>
        <legend>Ghi chú</legend>
        <span id="ctl00_ContentPlaceHolder1_ctl00_ctl00_ctl00_lbNote"><div dir="auto" style="margin: 0px; padding: 0px; font-family: Helvetica, Arial, sans-serif; font-size: 15px; line-height: 22.5px; color: #050505; white-space: pre-wrap;"><span style="font-family: Arial; font-size: 16px;">Quy tr&igrave;nh nhận Giấy x&aacute;c nhận đ&atilde; đăng k&yacute;:<br />
1. Sinh vi&ecirc;n nhận Giấy theo <strong><span style="text-decoration: underline; color: #ff0000;">thời gian nhận</span></strong>.<br />
2. Sinh vi&ecirc;n li&ecirc;n hệ Ph&ograve;ng Tuyển sinh v&agrave; C&ocirc;ng t&aacute;c sinh vi&ecirc;n <span style="color: #ff0000;"><strong>A1-203</strong> </span>để nhận giấy. Buổi s&aacute;ng từ 7h00 đến 11h30, buổi chiều từ 13h đến 16h30 c&aacute;c ng&agrave;y từ thứ hai đến thứ s&aacute;u v&agrave; s&aacute;ng thứ bảy.<br />
3. Sinh vi&ecirc;n <strong><em><span style="color: #ff0000;">c</span></em></strong><span style="color: #ff0000;"><em><strong><em>ung cấp Số thứ tự v&agrave; họ t&ecirc;n</em></strong></em></span> cho nh&acirc;n vi&ecirc;n trả giấy.<br />
4. Thời hạn nhận giấy x&aacute;c nhận đ&atilde; đăng k&yacute; l&agrave; 2 tuần, sau thời gian tr&ecirc;n c&aacute;c loại giấy qu&aacute; hạn sẽ bị hủy.<br />
Lưu &yacute;: nếu sinh vi&ecirc;n đ&atilde; đăng k&yacute; nhưng kh&ocirc;ng nhận giấy th&igrave; sẽ bị kh&oacute;a chức năng đăng k&yacute; của loại giấy đ&oacute;. Để được mở kh&oacute;a, Sinh vi&ecirc;n li&ecirc;n hệ Ph&ograve;ng Tuyển sinh v&agrave; c&ocirc;ng t&aacute;c sinh vi&ecirc;n A1-203 để xử l&yacute;.</span></div></span>
    </fieldset>
</div>
       
</body>
</html>