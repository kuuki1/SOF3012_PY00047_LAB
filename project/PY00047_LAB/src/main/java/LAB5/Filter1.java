package LAB5;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")  // Áp dụng cho tất cả các request
public class Filter1 implements Filter {

    public Filter1() {
        // Constructor mặc định
    }

    // Phương thức init được gọi khi Filter được tạo ra lần đầu
    public void init(FilterConfig fConfig) throws ServletException {
    }

    // Phương thức doFilter sẽ được gọi mỗi khi có request
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Thiết lập thuộc tính "hello"
        request.setAttribute("hello", "Tôi là filter 1");

        // Tiếp tục chuỗi filter (cho phép request đi tiếp)
        chain.doFilter(request, response);
    }

    // Phương thức destroy sẽ được gọi khi filter bị hủy
    public void destroy() {
    }
}
