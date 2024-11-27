package LAB5;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@WebFilter("/*")  // Áp dụng filter cho tất cả các request đến
public class AppFilter implements Filter {

    public AppFilter() {
        // Constructor mặc định
    }

    // Phương thức init được gọi khi Filter được tạo ra lần đầu
    public void init(FilterConfig fConfig) throws ServletException {
    }

    // Phương thức doFilter sẽ được gọi mỗi khi có request
    public void doFilter(javax.servlet.ServletRequest request, javax.servlet.ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

    // In ra để kiểm tra khi filter hoạt động
    System.out.println("AppFilter có hoạt động!");

    // Thiết lập mã hóa UTF-8 cho tất cả các trang web
    request.setCharacterEncoding("UTF-8");
    response.setContentType("text/html;charset=UTF-8");

    HttpServletRequest req = (HttpServletRequest) request;

    // Kiểm tra xem người dùng đã đăng nhập chưa
    HttpSession session = req.getSession(false);

    // Kiểm tra và in thông tin session
    if (session != null) {
        System.out.println("Session exists: " + session.getId());  // In ID session
        System.out.println("Session username: " + session.getAttribute("username"));
        
    } else {
        System.out.println("No session found.");
    }

    if (session != null && session.getAttribute("user") != null) {
        // Người dùng đã đăng nhập, ghi nhận thông tin truy cập
        String url = req.getRequestURI();  // Lấy URL của trang hiện tại
        String username = (String) session.getAttribute("username");  // Lấy tên người dùng từ session

        // Tạo một đối tượng Log để ghi nhận thông tin truy cập
        Date date = new Date();
        Log log = new Log(url, username, date);

        // Ghi vào CSDL bằng DAO
        try {
            LogDAOImpl logDAO = new LogDAOImpl();
            logDAO.create(log);  // Gọi phương thức create của DAO để lưu thông tin truy cập vào CSDL
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Tiếp tục chuỗi filter (cho phép request đi tiếp)
    chain.doFilter(request, response);
}


    // Phương thức destroy sẽ được gọi khi filter bị hủy
    public void destroy() {
    }
}
