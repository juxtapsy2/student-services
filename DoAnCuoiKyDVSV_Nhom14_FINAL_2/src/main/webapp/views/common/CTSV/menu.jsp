<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <% String nonceMenu = (String) request.getAttribute("nonce"); %>
<title>Insert title here</title>
 <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/common/css/menu.css"> 

</head>
<aside>

<div class="wrapper">
  
  <div class="sidebar">
      <ul>
          <%   String MaND = "";
          String TenND = "";
          String PhanQuyen = "";
          String TenTinhTrang = "";

          Object MaNDObj = session.getAttribute("MaND");
          Object TenNDOjb = session.getAttribute("TenNguoiDung");
          Object PhanQuyenObj = session.getAttribute("PhanQuyen");
          Object TenTinhTrangObj = session.getAttribute("TenTinhTrang");

          if (MaNDObj != null) {
              MaND = (String) MaNDObj;
          }

          if (TenNDOjb != null) {
              TenND = (String) TenNDOjb;
          }

          if (PhanQuyenObj != null) {
              PhanQuyen = (String) PhanQuyenObj;
          }

          if (TenTinhTrangObj != null) {
              TenTinhTrang = (String) TenTinhTrangObj;
          }

           %>
           <style  nonce="<%= nonceMenu %>">
  .Info {
    display: flex;
    flex-direction: column;
    align-items: center;
    color: white;
  }
</style>
         <li><a href="">
             <span class="Info" style="color: white;"><strong><%= PhanQuyen + ". " + TenND%></strong><br><%="(" + TenTinhTrang + ")"%>
             </span>
          </a></li>
            <li><a href="<%=request.getContextPath()%>/views/CTSV/index.jsp"<% if (request.getRequestURI().endsWith("/index.jsp")) { %>class="active"<% } %>>
          <span class="icon"><i class="fas fa-book"></i></span>
          <span class="title">Thông báo</span>
          </a></li>
        <li><a href="<%=request.getContextPath()%>/views/<%= PhanQuyen %>/thongtin.jsp"<% if (request.getRequestURI().endsWith("/thongtin.jsp")) { %>class="active"<% } %>>
          <span class="icon"><i class="fas fa-book"></i></span>
          <span class="title">Thông tin cá nhân</span>
          </a></li>
       
        <li><a href="<%=request.getContextPath()%>/views/<%= PhanQuyen %>/quanlygiayxacnhan.jsp"<% if (request.getRequestURI().endsWith("/quanlygiayxacnhan.jsp")) { %>class="active"<% } %>>
          <span class="icon"><i class="fas fa-book"></i></span>
          <span class="title">Quản lý giấy xác nhận</span>
          </a></li>
          <li><a href="<%=request.getContextPath()%>/views/<%= PhanQuyen %>/quanlydichvu.jsp"<% if (request.getRequestURI().endsWith("/quanlydichvu.jsp")) { %>class="active"<% } %>>
          <span class="icon"><i class="fas fa-book"></i></span>
          <span class="title">Quản lý dịch vụ</span>
          </a></li>
       
        <li><a href="<%=request.getContextPath()%>/views/CTSV/phanhoi.jsp"<% if (request.getRequestURI().endsWith("/phanhoi.jsp")) { %>class="active"<% } %>>
          <span class="icon"><i class="fas fa-leaf"></i></span>
          <span class="title">Phản hồi yêu cầu</span>
          </a></li>
             <li><a href="dangxuat"<% if (request.getRequestURI().endsWith("/HuongDan.jsp")) { %>class="active"<% } %>>
          <span class="icon"><i class="fas fa-leaf"></i></span>
          <span class="title">Đăng xuất</span>
          </a></li>
    </ul>
  </div>
</div>
</aside>


 

</html>