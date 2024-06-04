<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ page import="doancuoiky.dao.*" %>
<%@ page import="doancuoiky.model.*" %>
<%@ page import="doancuoiky.web.*" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <% String nonce = (String) request.getAttribute("nonce"); %>
<title>Yêu cầu</title>
<%--   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
 
  <%@ include file="/views/common/header.jsp" %>
  <%@include file="/views/common/SV/menu.jsp" %>
  <%@ include file="/views/common/footer.jsp" %>
   --%>
  
  
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
   <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/QTVHT/css/style.css"> 
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
        
        textarea {
        	margin: 0 auto;
        	width: 100%;
        }
        #txtTieuDeYC {
        	font-weight: bold;
        }
        #txtNoiDungYC {
        	height: 70%;
        	
        }
        #fieldNoiDung {
        	height: 300px;
        }
  		#btnYC {
  			margin: 0 auto;
  		}
	   
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
	#btnYC {
  display: inline-block;
  padding: 10px 20px;
  font-size: 16px;
  text-align: center;
  text-decoration: none;
  cursor: pointer;
  border-radius: 25px; /* Điều này làm cho góc bo tròn, bạn có thể điều chỉnh giá trị tùy ý */
  transition: background-color 0.3s ease;
}

#btnYC:hover {
  background-color: #4CAF50; /* Màu nền khi di chuột qua, bạn có thể thay đổi theo ý muốn */
  color: white; /* Màu chữ khi di chuột qua, bạn có thể thay đổi theo ý muốn */
}
	
  </style>
</head>
<body>
<%

YeuCauDao ycDao = new YeuCauDao();
List<YeuCau> listYC = new ArrayList<YeuCau>();
String MaNguoiDungYC = (String) session.getAttribute("MaND");
listYC = ycDao.getAllYeuCauByMaNguoiDung(MaNguoiDungYC);
/*  listYC = ycDao.getAllYeuCau(); */
PhanHoiDao phDao = new PhanHoiDao();
List<PhanHoi> listPH = new ArrayList<PhanHoi>();
listPH = phDao.getPhanHoiByMaND((String)session.getAttribute("MaND"));
DichVuDao dvDao = new DichVuDao();
List<DichVu> listdichvu = dvDao.selectAllDichVu();

out.print("SL:"+ listYC.size());
%>
<div class ='content'>

 <form action="<%=request.getContextPath()%>/views/SV/yeucau" method="post">
 	<fieldset id="fieldDV">
 		<legend>Chọn dịch vụ:</legend>
 		<select id="cmbDV" name="cmbDV">
 			<option value="">Select</option>
 			 <% for (DichVu dv : listdichvu) { %>
            <option value="<%= dv.getMaDV() %>"><%= dv.getTenDV() %></option>
        <% } %>
 		</select>
 	</fieldset>
 	<fieldset id="fieldTieuDe">
 		<legend>Tiêu đề</legend>
 		<textarea id="txtTieuDeYC" name="txtTieuDeYC" ></textarea>
 	</fieldset>
 	<fieldset id="fieldNoiDung">
 		<legend>Chi tiết nội dung yêu cầu</legend>
 		<textarea id="txtNoiDungYC" name="txtNoiDungYC"></textarea>
 	</fieldset>
 	<button id="btnYC" class="btn btn-success" type="submit" name="btnYC" value="Submit">Gửi</button>
 	<h2>Yêu cầu đã gửi: <%= listYC.size() %></h2>
    <ul class="login-list">
        <li class="login-header">
            <span style="flex: 1;">Mã YC</span>
            <span style="flex: 1;">Mã DV</span>
            <span style="flex: 1;">Mã người dùng</span>
            <span style="flex: 1;">STT</span>
            <span style="flex: 1;">Tiêu đề</span>
            <span style="flex: 1;">Nội dung</span>
            <span style="flex: 1;">Ngày YC</span>
            <span style="flex: 1;">Trạng thái</span>
        </li>

        <% for (int i = 0; i < listYC.size(); i++) { %>
            <% YeuCau yc = listYC.get(i); 
            String MaNguoiDung = yc.getMaNguoiDung();
            %>
            <li class="login-item" style="text-align:center;">
                <span style="flex: 1;"><%= yc.getMaYC() %></span>
                <span style="flex: 1.5;"><%= yc.getMaDV() %></span>
                <span style="flex: 1;"><%= yc.getMaNguoiDung() %></span>
                <span style="flex: 1.5;"><%= yc.getSTT() %></span>
                <span style="flex: 1.5;"><%= yc.getTieuDeYC() %></span>
                <span style="flex: 1;"><%= yc.getNoiDungYC() %></span>
                <span style="flex: 1.5;"><%= yc.getNgayYC() %></span>
                <span style="flex: 1;"><%= yc.getTrangThai() %></span>
            </li>
        <% } %>
    </ul>
 </form>
	
</div>
       
</body>
</html>