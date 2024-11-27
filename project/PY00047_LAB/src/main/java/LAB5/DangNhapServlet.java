package LAB5;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import LAB1.User;

@WebServlet("/DangNhapServlet")
public class DangNhapServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public DangNhapServlet() {
        super();
    }

    @Override
    public void init() throws ServletException {
        // Khởi tạo DAO chỉ một lần
        userDAO = new UserDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false); // Lấy session hiện tại nếu có, không tạo mới

        if (session != null && session.getAttribute("user") != null) {
            // Nếu đã đăng nhập, hiển thị thông báo chào mừng
            User user = (User) session.getAttribute("user");
            req.setAttribute("welcomeMessage", "Xin chào, " + user.getFullname() + "!");
        }

        // Chuyển hướng tới trang đăng nhập mà không đặt successMessage
        req.getRequestDispatcher("/view/DangNhap.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        try {
            // Xác thực người dùng qua DAO
            User user = userDAO.authenticate(username, password);

            if (user != null) {
                // Tạo session và lưu thông tin người dùng
            	HttpSession session = request.getSession();
            	session.setAttribute("user", user);  // Lưu toàn bộ đối tượng User
            	session.setAttribute("username", user.getFullname());  // Lưu tên người dùng vào session


                // Đăng nhập thành công
                request.setAttribute("successMessage", "Đăng nhập thành công!");
            } else {
                // Nếu xác thực không thành công
                request.setAttribute("errorMessage", "Tên đăng nhập hoặc mật khẩu không đúng.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Đã xảy ra lỗi khi xác thực người dùng. Vui lòng thử lại.");
        }

        // Quay lại trang đăng nhập
        request.getRequestDispatcher("/view/DangNhap.jsp").forward(request, response);
    }
}
