package LAB2;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import LAB1.User;
import DAO.UserDAOImpl;
import DAO.UserDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.beanutils.BeanUtils;

@WebServlet({
    "/user/crud/index",
    "/user/crud/edit/*",
    "/user/crud/create",
    "/user/crud/update",
    "/user/crud/delete",
    "/user/crud/reset"
})
public class UserCRUDServlet extends HttpServlet {

    private UserDAO dao = new UserDAOImpl(); // Khởi tạo đối tượng UserDAO để làm việc với CSDL

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User form = new User();
        try {
            // Ánh xạ các tham số từ request vào đối tượng User
            BeanUtils.populate(form, req.getParameterMap());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            req.setAttribute("error", "Lỗi khi ánh xạ dữ liệu người dùng: " + e.getMessage());
        }

        String message = "Nhập thông tin người dùng";
        String path = req.getServletPath();

        if (path.contains("edit")) {
            // Lấy ID từ URL để chỉnh sửa
            String id = req.getPathInfo() != null ? req.getPathInfo().substring(1) : null;
            if (id != null) {
                form = dao.findById(id); // Truy vấn người dùng từ CSDL theo ID
                message = "Chỉnh sửa: " + id;
            } else {
                message = "ID không hợp lệ";
            }
        } else if (path.contains("create")) {
            dao.create(form); // Thêm người dùng mới vào CSDL
            message = "Đã tạo người dùng: " + form.getId();
            form = new User();
        } else if (path.contains("update")) {
            dao.update(form); // Cập nhật thông tin người dùng trong CSDL
            message = "Đã cập nhật người dùng: " + form.getId();
        } else if (path.contains("delete")) {
            String id = form.getId();
            dao.deleteById(id); // Xóa người dùng khỏi CSDL theo ID
            message = "Đã xóa người dùng: " + id;
            form = new User();
        } else if (path.contains("reset")) {
            form = new User(); // Đặt lại thông tin form
        }

        List<User> list = dao.findAll(); // Truy vấn tất cả người dùng từ CSDL
        req.setAttribute("message", message);
        req.setAttribute("user", form);
        req.setAttribute("users", list);

        req.getRequestDispatcher("/view/user.jsp").forward(req, resp); // Chuyển tiếp tới trang JSP để hiển thị
    }
}

