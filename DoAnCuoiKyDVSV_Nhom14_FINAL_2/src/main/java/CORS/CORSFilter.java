package CORS;

import java.io.IOException;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CORSFilter implements Filter {
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        
        // Lấy Origin từ header của request
        String origin = httpRequest.getHeader("Origin");
        
        // Kiểm tra nếu Origin thuộc các trang web được phép
        if (origin != null && (origin.equals("https://fonts.gstatic.com") || origin.equals("https://fonts.googleapis.com")
                              || origin.equals("https://cdn.jsdelivr.net") || origin.equals("https://cdnjs.cloudflare.com"))) {
            // Thiết lập các header CORS
            httpResponse.setHeader("Access-Control-Allow-Origin", origin);
            httpResponse.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
            httpResponse.setHeader("Access-Control-Allow-Headers", "Content-Type");
            httpResponse.setHeader("Access-Control-Allow-Credentials", "true");
        }
        
        chain.doFilter(request, response);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        // Không cần thực hiện gì trong phương thức init
    }

    public void destroy() {
        // Không cần thực hiện gì trong phương thức destroy
    }
}

