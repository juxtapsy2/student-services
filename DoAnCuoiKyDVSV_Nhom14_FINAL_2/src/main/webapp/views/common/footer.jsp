<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <% String nonceFooter = (String) request.getAttribute("nonce"); %>
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/templates/common/css/footer.css">
<style nonce="<%= nonceFooter %>">
.footer {
  
   
    z-index: 1000; /* Số này phải lớn hơn các z-index của các thành phần khác để top_navbar nằm trên cùng */
}
</style>
</head>
<body>
  <div class="footer">
        <p>&copy; 2023 Nhóm 14 - Hệ thống dịch vụ sinh viên . All rights reserved.</p>
       
    </div>
</html>