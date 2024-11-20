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
import dto.VideoShareInfoDTO;

@WebServlet("/shareSummary")
public class ShareSummaryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private VideoDAO videoDAO = new VideoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<VideoShareInfoDTO> videoShareSummary = videoDAO.getVideoShareSummary();
        request.setAttribute("videoShareSummary", videoShareSummary);
        request.getRequestDispatcher("/view/lab4/shareSummary.jsp").forward(request, response);
    }
}

