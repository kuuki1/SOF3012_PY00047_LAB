package LAB5;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class Bai2Listener implements ServletContextListener, HttpSessionListener {

    private static final String COUNT_FILE_PATH = "bai2_visitor_count.txt"; // Tên file lưu số lượt truy cập

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        int visitorCount = 0;

        // Đọc số đếm từ file (nếu tồn tại)
        File countFile = new File(COUNT_FILE_PATH);
        if (countFile.exists()) {
            try {
                String countStr = Files.readString(countFile.toPath()).trim();
                visitorCount = Integer.parseInt(countStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // Lưu số đếm vào application scope
        context.setAttribute("visitors", visitorCount);
    }

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        ServletContext context = se.getSession().getServletContext();
        synchronized (context) {
            // Tăng số đếm và cập nhật vào application scope
            int visitorCount = (int) context.getAttribute("visitors");
            visitorCount++;
            context.setAttribute("visitors", visitorCount);
        }
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        // Không cần xử lý trong trường hợp này
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        int visitorCount = (int) context.getAttribute("visitors");

        // Ghi số đếm vào file khi ứng dụng ngừng hoạt động
        try (FileWriter writer = new FileWriter(COUNT_FILE_PATH)) {
            writer.write(String.valueOf(visitorCount));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
