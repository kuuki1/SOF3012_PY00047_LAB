package test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@WebServlet("/showVideo")
public class ShowVideoServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String videoUrl = request.getParameter("videoUrl");
        if (videoUrl == null || videoUrl.isEmpty()) {
            out.println("<html>");
            out.println("<head><title>Nhúng Video YouTube</title></head>");
            out.println("<body>");
            out.println("<h3>Nhập đường dẫn video YouTube</h3>");
            out.println("<form action='showVideo' method='get'>");
            out.println("    <label for='videoUrl'>URL Video:</label>");
            out.println("    <input type='text' id='videoUrl' name='videoUrl' placeholder='https://www.youtube.com/watch?v=9wrBeWc944U' required>");
            out.println("    <input type='submit' value='Hiển thị Video'>");
//            out.println("<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/1vi0QIU6F5A?si=eUKyddlX4uTRM25z\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
            return;
        }
//        if (!videoUrl.contains("youtube.com/watch?v=")) {
//            out.println("<p style='color:red;'>URL không hợp lệ. Vui lòng nhập một URL YouTube hợp lệ.</p>");
//            return;
//        }
        String videoId = videoUrl.split("v=")[1].split("&")[0];
        out.println("<html>");
        out.println("<head><title>Video YouTube</title></head>");
        out.println("<body>");
        out.println("<h3>Video từ YouTube</h3>");
        out.println("<iframe width='560' height='315' src='https://www.youtube.com/embed/" + videoId + "' frameborder='0' allow='accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture' allowfullscreen></iframe>");
        out.println("</body>");
        out.println("</html>");
    }
}

