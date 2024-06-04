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

 <script nonce="<%= nonce %>">
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
<%
List<ThongBao> listthongbao = new ArrayList<ThongBao>();
ThongBaoDao thongbaodao  = new ThongBaoDao();
NguoiDung nguoidung1 = (NguoiDung)session.getAttribute("NguoiDung");
listthongbao = thongbaodao.getThongBaosByMaNguoiNhan(nguoidung1.getMaNguoiDung());
 

List<ThongBao> listthongbaonhan = new ArrayList<ThongBao>();

listthongbaonhan = thongbaodao.getThongBaosByMaNguoiGui(nguoidung1.getMaNguoiDung());









// Lấy kích thước của danh sách
int sizeOfList = listthongbao.size();
%>
<div class ='content'>
  <a href="<%=request.getContextPath()%>/views/CTSV/guithongbao.jsp" class="add-user-button">Gửi thông báo</a>
<h2>Thông báo đã nhận</h2>
   <table class="notification-table">
        <thead>
            <tr class="Inbox_header_dl">
                <th>Tiêu đề</th>
                <th>Người gửi</th>
                <th>Thời gian gửi</th>
            </tr>
        </thead>
        <tbody>
           
            <%for (int i = 0; i < listthongbao.size(); i++) { %>
             <% ThongBao thongbao = listthongbao.get(i); 
               String MaThongBao = thongbao.getMaTB();
               NguoiDungDao nguoidungdao = new NguoiDungDao();
               NguoiDung nguoidung = nguoidungdao.getNguoiDungByMaND(thongbao.getMaNguoiGui());
            %>
                <tr>
                    <td class="notification-title"><a href="xemnoidungCTSV?ndid=<%= MaThongBao %>"><%= thongbao.getTieuDe() %></a></td>
                    <td><%=nguoidung.getHoTen() %></td>
                    <td><%= thongbao.getNgayGui() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <h2>Thông báo đã gửi</h2>
   <table class="notification-table">
        <thead>
            <tr class="Inbox_header_dl">
                <th>Tiêu đề</th>
                <th>Người gửi</th>
                <th>Thời gian gửi</th>
            </tr>
        </thead>
        <tbody>
           
            <%for (int i = 0; i < listthongbaonhan.size(); i++) { %>
             <% ThongBao thongbao = listthongbaonhan.get(i); 
               String MaThongBao = thongbao.getMaTB();
               NguoiDungDao nguoidungdao = new NguoiDungDao();
               NguoiDung nguoidung = nguoidungdao.getNguoiDungByMaND(thongbao.getMaNguoiGui());
            %>
                <tr>
                    <td class="notification-title"><a href="xemnoidungCTSV?ndid=<%= MaThongBao %>"><%= thongbao.getTieuDe() %></a></td>
                    <td><%=nguoidung.getHoTen() %></td>
                    <td><%= thongbao.getNgayGui() %></td>
                </tr>
            <% } %>
        </tbody>
    </table>
    <div class="notification-content">
       ${noidungthongbao} 
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