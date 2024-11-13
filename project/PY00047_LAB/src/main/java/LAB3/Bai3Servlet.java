package LAB3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAO;
import DAO.UserDAOImpl;
import LAB1.User;

@WebServlet("/Bai3Servlet")
public class Bai3Servlet extends HttpServlet {
    private UserDAO userDAO = new UserDAOImpl();
    private static final long serialVersionUID = 1L;

    public Bai3Servlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userId = request.getParameter("userId");
        if (userId != null && !userId.trim().isEmpty()) {
            User user = userDAO.findById(userId);
            if (user != null) {
                List<Favorite> favorites = user.getFavorites();
                System.out.println("User found: " + user.getFullname());
                System.out.println("Favorites count: " + (favorites != null ? favorites.size() : 0));
                request.setAttribute("user", user);
                request.setAttribute("favorites", favorites);
            } else {
                request.setAttribute("error", "Không tìm thấy người dùng với ID: " + userId);
            }
        } else {
            request.setAttribute("error", "Cần nhập ID người dùng.");
        }
        request.getRequestDispatcher("view/Lab3/bai3.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        userDAO.close();  // Đóng EntityManager khi servlet bị hủy
    }
}
