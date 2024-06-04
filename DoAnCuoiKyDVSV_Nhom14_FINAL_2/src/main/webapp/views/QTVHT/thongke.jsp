<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="doancuoiky.model.*"%>
<%@ page import="doancuoiky.dao.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.util.ArrayList"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <% String nonce = (String) request.getAttribute("nonce"); %>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/index/style.css">
<!-- Font Awesome Cdn Link -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" />
<script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
<%@ include file="/views/common/header.jsp"%>
<%@include file="/views/common/QTVHT/menu.jsp"%>
<%@ include file="/views/common/footer.jsp"%>
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
<title>Insert title here</title>
<style nonce="<%= nonce %>">
body {
	font-family: Arial, sans-serif;
	background-color: #f4f4f4;
	margin: 0;
	padding: 0;
}
.container {
	max-width: 800px;
	margin: 20px auto;
	background-color: #fff;
	padding: 20px;
	border-radius: 8px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}
 form {
            max-width: 400px;
            margin: 0 auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        select {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        input[type="submit"] {
            background-color: #4caf50;
            color: #fff;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }
</style>
</head>
<div class='content'>
	<form method="get"
		action="<%=request.getContextPath()%>/views/QTVHT/thongkeyeucau">
		<select name="optionChoice">
			<option value="Khoas">Khóa</option>
			<option value="Khoa">Khoa</option>
		</select> <input type="submit" value="Submit">
	</form>
	<div class="container">
		<canvas id="myChart" width="600" height="300"></canvas>
		<%
 //Example usage
   YeuCauDao ycdao = new YeuCauDao();
   List<String> studentCodes = ycdao.getAllMaNguoiDungFromYeuCau();
   List<ThongKeKhoa> thongKeList = (List<ThongKeKhoa>)request.getAttribute("thongKeList");
   out.println("Tổng SL: " + studentCodes.size());
   //Display the results
   for (ThongKeKhoa thongKe : thongKeList) {
    out.println(" , " + thongKe.getKhoaType() + ": " + thongKe.getCourseName() + " có tổng SL học sinh: " + thongKe.getNumberOfStudents());
   }
   %>
		<script nonce="<%= nonce %>">
       // Extract data from the JSP page
       var courseNames = [];
       var numberOfStudents = [];
       <%
       for (ThongKeKhoa thongKekho : thongKeList) {
       %>
           courseNames.push('<%= thongKekho.getKhoaType() %>' + " " + '<%= thongKekho.getCourseName() %>');
           numberOfStudents.push(<%= thongKekho.getNumberOfStudents() %>);
       <%
           }
       %>
       // Use Chart.js to draw a column chart
       var ctx = document.getElementById('myChart').getContext('2d');
       var myChart = new Chart(ctx, {
           type: 'bar',
           data: {
               labels: courseNames,
               datasets: [
               	{
                       label: 'Number of students',
                       data: numberOfStudents,
                       backgroundColor: 'rgba(75, 192, 192, 0.2)',
                       borderColor: 'rgba(75, 192, 192, 1)',
                       borderWidth: 1
                   }
               ]
           },
           options: {
               scales: {
                   y: {
                       beginAtZero: true
                   }
               }
             
           }
       });
   </script>
	</div>
</div>
<style nonce="<%= nonce %>">
.content {
	margin-top: 120px; /* Khoảng cách phía trên */
	margin-right: 20px; /* Khoảng cách phía phải */
	margin-bottom: 100px; /* Khoảng cách phía dưới */
	margin-left: 330px;
	height: 85vh; /* Khoảng cách phía trái */
	z-index: 1; /* Đặt giá trị z-index để đảm bảo nội dung nằm trên menu */
	position: relative;
	background-color: white; /* Thêm màu nền trắng */
	padding: 20px;
	/* Thêm padding để tạo khoảng trắng xung quanh nội dung */
}
</style>
</html>