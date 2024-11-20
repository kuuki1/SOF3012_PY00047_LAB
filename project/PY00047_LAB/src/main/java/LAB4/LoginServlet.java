package LAB4;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.UserDAOImpl;
import LAB1.User;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDAOImpl userDAO = new UserDAOImpl();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/view/lab4/login.jsp").forward(req, resp);
	}

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String identifier = request.getParameter("usernameOrEmail");
        String password = request.getParameter("password");

        User user = userDAO.findByIdOrEmail(identifier);

        if (user != null && user.getPassword().equals(password)) {
            request.getSession().setAttribute("currentUser", user);
            request.getRequestDispatcher("/view/lab4/home.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Invalid username/email or password.");
            request.getRequestDispatcher("/view/lab4/login.jsp").forward(request, response);
        }
    }
}

