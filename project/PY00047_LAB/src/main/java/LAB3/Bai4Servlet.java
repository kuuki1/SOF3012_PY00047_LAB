package LAB3;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.FavoriteDAOImpl;  // Import the implementation class

@WebServlet("/Bai4Servlet")
public class Bai4Servlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Declare the DAO object to access data
    private FavoriteDAOImpl favouriteDAO = new FavoriteDAOImpl();
    
    public Bai4Servlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get the list of all favorites from the DAO
        List<Favorite> favorites = favouriteDAO.findAll();  // Call findAll on the DAO instance

        // Set the list of favorites as an attribute in the request
        request.setAttribute("favorites", favorites);

        // Forward the request to the JSP page to display the list of favorites
        request.getRequestDispatcher("view/Lab3/bai4.jsp").forward(request, response);
    }
}
