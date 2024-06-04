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
  </style>


    
</head>
<%
DKGiayXacNhanSinhVienDao gxnsvdao = new DKGiayXacNhanSinhVienDao();
List<DKGiayXacNhanSinhVien> giayxacnhanlist = new ArrayList<>();
giayxacnhanlist = gxnsvdao.getDKGiayXacNhanSinhVienByTinhTrang("Da Nhan");

List<DKGiayXacNhanSinhVien> giayxacnhanlistchuanhan = new ArrayList<>();
giayxacnhanlistchuanhan =  gxnsvdao.getDKGiayXacNhanSinhVienByTinhTrang("Chua Nhan");


List<DKGiayXacNhanSinhVien> giayxacnhanlistdain = new ArrayList<>();
giayxacnhanlistdain =  gxnsvdao.getDKGiayXacNhanSinhVienByTinhTrang("Da In");


List<DKGiayXacNhanSinhVien> mergedList = new ArrayList<>();
mergedList.addAll(giayxacnhanlistchuanhan);
mergedList.addAll(giayxacnhanlistdain);

session.setAttribute("giayXacNhanList", giayxacnhanlist);

%>
<div class ='content'>
  <div>
        <fieldset>
            <legend>Chưa nhận</legend>
            <table>
                <thead style="background-color: #3498db; color: white;">
                    <tr>
                        <th>Mã Người Dùng</th>
                        <th>Thời gian đăng ký</th>
                        <th>Tên giấy xác nhận</th>
                        <th>Số thứ tự</th>
                        <th>Số lượng</th>
                        <th>Thời gian nhận</th>
                        <th>Tình trạng</th>
                        <th>Chỉnh sửa</th>
                        <th>Xóa</th>
                        <!-- Add other headers as needed -->
                    </tr>
                </thead>
                <tbody>
                    <% 
                    for (int i = 0; i < mergedList.size(); i++) {
                        DKGiayXacNhanSinhVien giayXacNhan = mergedList.get(i);
                        String idgiayxacnhan = giayXacNhan.getIDGiayXacNhan();
                    %>
                    <%
String statusClass = "";
if (giayXacNhan.getTinhTrang().equals("Da Nhan")) {
    statusClass = "received";
} else if (giayXacNhan.getTinhTrang().equals("Chua Nhan")) {
    statusClass = "not-received";
} else {
    statusClass = "printed";
}
%>
                        <tr>
                            <td><strong><%= giayXacNhan.getMaNguoiDung() %></strong></td>
                            <td><%= giayXacNhan.getNgayDangKy() %></td>
                            <td><%= giayXacNhan.getTenDichVu() %></td>
                            <td><%= giayXacNhan.getStt() %></td>
                            <td><%= giayXacNhan.getSoLuong() %></td>
                            <td><%= giayXacNhan.getThoiGianNhan() %></td>
                            <td class="<%= giayXacNhan.getTinhTrang().equals("Da Nhan") ? "received" : (giayXacNhan.getTinhTrang().equals("Chua Nhan") ? "not-received" : "printed") %>">
                                <%= giayXacNhan.getTinhTrang() %>
                            </td>
                             <td><a href="updategiayxacnhan?gxnid=<%= idgiayxacnhan %>"><span style="color: blue;"><strong>Cập nhật</strong></span></a></td>
                            <td><a href="deletegiayxacnhan?gxnid=<%= idgiayxacnhan %>"><span style="color: red;"><strong>Xóa</strong></span></a></td>
                            <!-- Add other data cells as needed -->
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </fieldset>
        <fieldset>
            <legend>Đã nhận</legend>
            <table>
                <thead style="background-color: #3498db; color: white;">
                    <tr>
                        <th>Mã Người Dùng</th>
                        <th>Thời gian đăng ký</th>
                        <th>Tên giấy xác nhận</th>
                        <th>Số thứ tự</th>
                        <th>Số lượng</th>
                        <th>Thời gian nhận</th>
                        <th>Tình trạng</th>
                        
                        <th>Xóa</th>
                        <!-- Add other headers as needed -->
                    </tr>
                </thead>
                <tbody>
                  <% 
                    for (int i = 0; i < giayxacnhanlist.size(); i++) {
                        DKGiayXacNhanSinhVien giayXacNhan = giayxacnhanlist.get(i);
                        String idgiayxacnhan = giayXacNhan.getIDGiayXacNhan();
                    %>
                    <%
String statusClass = "";
if (giayXacNhan.getTinhTrang().equals("Da Nhan")) {
    statusClass = "received";
} else if (giayXacNhan.getTinhTrang().equals("Chua Nhan")) {
    statusClass = "not-received";
} else {
    statusClass = "printed";
}
%>
                        <tr>
                            <td><strong><%= giayXacNhan.getMaNguoiDung() %></strong></td>
                            <td><%= giayXacNhan.getNgayDangKy() %></td>
                            <td><%= giayXacNhan.getTenDichVu() %></td>
                            <td><%= giayXacNhan.getStt() %></td>
                            <td><%= giayXacNhan.getSoLuong() %></td>
                            <td><%= giayXacNhan.getThoiGianNhan() %></td>
                            <td class="<%= giayXacNhan.getTinhTrang().equals("Da Nhan") ? "received" : (giayXacNhan.getTinhTrang().equals("Chua Nhan") ? "not-received" : "printed") %>">
                                <%= giayXacNhan.getTinhTrang() %>
                            </td>
                           
                            <td><a href="deletegiayxacnhan?gxnid=<%= idgiayxacnhan %>"><span style="color: red;"><strong>Xóa</strong></span></a></td>
                            <!-- Add other data cells as needed -->
                        </tr>
                    <% } %>
                </tbody>
            </table>
        </fieldset>
    </div>
</div>
   
</div>


  <style nonce="<%= nonce %>">
   
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
</html>