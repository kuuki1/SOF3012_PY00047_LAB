package LAB4;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.VideoDAO;
import DAO.VideoDAOImpl;
import LAB3.Video;

@WebServlet("/search")
public class SearchServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private VideoDAO videoDAO = new VideoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String keyword = request.getParameter("keyword");
        List<Video> videos = videoDAO.findByTitle(keyword);

        request.setAttribute("videos", videos);
        request.getRequestDispatcher("/view/lab4/searchResults.jsp").forward(request, response);
    }
}

