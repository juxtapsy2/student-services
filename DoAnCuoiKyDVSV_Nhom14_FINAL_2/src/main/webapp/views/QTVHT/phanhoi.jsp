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
<title>Yêu cầu</title>
     <% String nonce = (String) request.getAttribute("nonce"); %>
  <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/QTVHT/css/style.css"> 
   <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css"/>
    <%@ include file="/views/common/header.jsp" %>
  <%@include file="/views/common/QTVHT/menu.jsp" %>
  <%@ include file="/views/common/footer.jsp" %>
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
  	    #btnPH {
            display: inline-block;
            padding: 10px 20px;
            font-size: 16px;
            text-decoration: none;
            color: #fff;
            background-color: #3498db;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        #btnPH:hover {
            background-color: #2980b9;
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
  </style>
</head>
<body>
<%

List<YeuCau> listYC = new ArrayList<YeuCau>();
YeuCauDao ycDao = new YeuCauDao();
listYC = ycDao.getAllYeuCau();
PhanHoiController phController = new PhanHoiController();

%>
<div class ='content'>
 <form action="<%=request.getContextPath()%>/views/QTVHT/phanhoi" method="post">
 	<fieldset id="QTVHT_headField">
 		<legend>Phản hồi người dùng mã:</legend>
 		<textarea id="QTVHT_txtPhanHoiMaND" name="QTVHT_txtPhanHoiMaND"></textarea>
 		<legend>Phản hồi yêu cầu mã:</legend>
 		<textarea id="QTVHT_txtPhanHoiMaYC" name="QTVHT_txtPhanHoiMaYC"></textarea>
 		<legend>Tiêu đề</legend>
 		<textarea id="QTVHT_txtTieuDePH" name="QTVHT_txtTieuDePH" ></textarea>
 	</fieldset>
 	<fieldset id="QTVHT_contentField">
 		<legend>Nội dung</legend>
 		<textarea id="QTVHT_txtNoiDungPH" name="QTVHT_txtNoiDungPH"></textarea>
 	</fieldset>
 	<button id="btnPH" class="btn btn-success" type="submit" name="btnPH" value="Submit">Phản hồi</button>
 	<h2>Danh sách yêu cầu</h2>
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