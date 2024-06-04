package CSP;

import java.io.IOException;
import java.security.SecureRandom;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Base64;


@WebFilter("/*")
public class SerletFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	        throws IOException, ServletException {
	    HttpServletResponse httpResponse = (HttpServletResponse) response;

	    // Lấy giá trị nonce từ request attribute nếu đã được tạo trước đó
	    HttpServletRequest httpRequest = (HttpServletRequest) request;
	    String nonce = (String) httpRequest.getAttribute("nonce");
	    if (nonce == null) {
	    	
	        // Nếu nonce chưa được tạo, tạo một nonce mới
	        SecureRandom random = new SecureRandom();
	        byte[] nonceBytes = new byte[16];
	        random.nextBytes(nonceBytes);
	        nonce = Base64.getEncoder().encodeToString(nonceBytes);

	        // Lưu giá trị nonce vào request attribute để sử dụng lại trong cùng một request
	        httpRequest.setAttribute("nonce", nonce);
	    }

	    String cspPolicy = "default-src 'self' https://cdnjs.cloudflare.com https://cdn.jsdelivr.net https://localhost:8080; "
	            + "script-src 'self' https://cdnjs.cloudflare.com https://cdn.jsdelivr.net 'nonce-" + nonce + "'; "
	            + "style-src 'self' https://cdnjs.cloudflare.com https://cdn.jsdelivr.net https://fonts.googleapis.com 'nonce-" + nonce + "'; "
	            + "img-src 'self'; "
	            + "connect-src 'self'; "
	            + "frame-src 'self'; "
	            + "font-src 'self' https://cdnjs.cloudflare.com https://cdn.jsdelivr.net https://fonts.googleapis.com https://fonts.gstatic.com; "
	            + "media-src 'self'; "
	            + "object-src 'none'; "
	            + "manifest-src 'self'; "
	            + "form-action 'self'; "
	            + "frame-ancestors 'none'";

	    httpResponse.setHeader("Content-Security-Policy", cspPolicy);
	    chain.doFilter(request, response);
	}

    
    // Các phương thức init và destroy không cần thay đổi



    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Do nothing
    }

    @Override
    public void destroy() {
        // Do nothing
    }
}
