<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <% String nonceHeader = (String) request.getAttribute("nonce"); %>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/common/css/header.css">
<style nonce="<%= nonceHeader %>">
.top_navbar {
  
   
    z-index: 1000; /* Số này phải lớn hơn các z-index của các thành phần khác để top_navbar nằm trên cùng */
}
</style>
<div class="wrapper">
<div class="top_navbar">
    <div class="top_menu">
        <div class="logo">
        <img src="/DoAnCuoiKyDVSV_Nhom14_FINAL_2/templates/dangnhap/image/logo.jpg" alt="Logo"/>
        </div>
        <ul>
            <li><a href="#">
                    <i class="fas fa-search"></i>
                </a></li>
            <li><a href="#">
                    <i class="fas fa-bell"></i>
                </a></li>
            <li><a href="#">
                    <i class="fas fa-user"></i>
                </a></li>
        </ul>
    </div>
</div>
</div>
</html>