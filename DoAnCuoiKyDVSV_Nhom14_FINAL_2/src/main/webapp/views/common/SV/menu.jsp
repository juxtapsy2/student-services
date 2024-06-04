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
          <li><a href="<%=request.getContextPath()%>/views/SV/index.jsp"<% if (request.getRequestURI().endsWith("/index.jsp")) { %>class="active"<% } %>>
          <span class="icon"><i class="fas fa-book"></i></span>
          <span class="title">Thông báo</span>
          </a></li>
        <li><a href="<%=request.getContextPath()%>/views/SV/thongtin.jsp"<% if (request.getRequestURI().endsWith("/thongtin.jsp")) { %>class="active"<% } %>>
          <span class="icon"><i class="fas fa-book"></i></span>
          <span class="title">Thông tin cá nhân</span>
          </a></li>
        <li><a href="<%=request.getContextPath()%>/views/SV/dangkygiayxacnhan.jsp"" <% if (request.getRequestURI().endsWith("/dangkygiayxacnhan.jsp")) { %>class="active"<% } %>>
          <span class="icon"><i class="fas fa-file-video"></i></span>
          <span class="title">Đăng ký giấy xác nhận</span>
          </a></li>
        <li><a href="<%=request.getContextPath()%>/views/SV/yeucau.jsp" <% if (request.getRequestURI().endsWith("/yeucau.jsp")) { %>class="active"<% } %>>
          <span class="icon"><i class="fas fa-blog"></i></span>
          <span class="title">Yêu cầu dịch vụ khác</span>
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