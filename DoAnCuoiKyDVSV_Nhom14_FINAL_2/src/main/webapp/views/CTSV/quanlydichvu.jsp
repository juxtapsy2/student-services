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
    .received {
        color: green;
    }

    .not-received {
        color: red;
    }

    .printed {
        color: purple;
    }
    
.add-account-button {
    display: inline-block;
    margin-bottom: 20px;
    padding: 10px 20px;
    font-size: 16px;
    font-weight: bold;
    text-align: center;
    text-decoration: none;
    background-color: #4caf50;
    color: #fff;
    border-radius: 5px;
    transition: background-color 0.3s ease;
}

.add-account-button:hover {
    background-color: #45a049;
}
</style>
<%


DichVuDao dvdao = new DichVuDao();
List<DichVu> listdichvu = dvdao.selectAllDichVu();


%>
<div class ='content'>
    <a href="<%=request.getContextPath()%>/views/CTSV/themdichvu.jsp" class="add-account-button">Thêm dịch vụ</a>
    

     <fieldset>
            <legend>Quản lý dịch vụ</legend>
            <table>
                <thead style="background-color: #3498db; color: white;">
                    <tr>
                        <th>Mã dịch vụ</th>
                        <th>Tên dịch vụ</th>
               
                        <th>Mô tả</th>
                        <th>Năm học</th>
                        <th>Học kỳ</th>
              
                        <th>Chỉnh sửa</th>
                        <th>Xóa</th>
                        <!-- Add other headers as needed -->
                    </tr>
                </thead>
                <tbody>
                    <% 
                    for (int i = 0; i < listdichvu.size(); i++) {
                    	DichVu dv = listdichvu.get(i);
                    	
                    	String iddichvu = dv.getMaDV();
                    	
                       
                    %>

                        <tr>
                            <td><strong><%= dv.getMaDV() %></strong></td>
                            <td><%= dv.getTenDV()%></td>
                            <td><%= dv.getMoTa()%></td>
                            <td><%=dv.getNamHoc() %></td>
                            <td><%=dv.getHocKy() %></td>
                           
                             <td><a href="updatedichvu?dvid=<%= iddichvu %>"><span style="color: blue;"><strong>Cập nhật</strong></span></a></td>
                            <td><a href="deletedichvu?dvid=<%= iddichvu %>"><span style="color: red;"><strong>Xóa</strong></span></a></td>
                            <!-- Add other data cells as needed -->
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </fieldset>


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