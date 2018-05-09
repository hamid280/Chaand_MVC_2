import model.DBHelper;
import user.User;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller extends HttpServlet {

    @Resource(name = "jdbc/project")
    private DataSource dataSource;


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String page;
        if (request.getParameterMap().containsKey("page")) {
            page = request.getParameter("page");
        } else {
            page = "index";
        }

        switch (page) {
            case "list_users":
                try {
                    listUsers(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "add_user":
                addUser(request, response);
                break;
            case "index":
                homepage(request, response);
                break;
            case "submitUser":
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                User user = new User(name, email);
                try {
                    submitUser(user, request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            default:
                error(request, response);
                break;
        }
    }

    private void submitUser(User user, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        new DBHelper().addUser(user, dataSource);
        request.getRequestDispatcher("submittedUser.jsp").forward(request, response);

    }

    private void error(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("error.jsp").forward(request, response);
    }

    private void homepage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    private void addUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("add_users.jsp").forward(request, response);

    }

    private void listUsers(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {

        ResultSet rs = new DBHelper().getUsers(dataSource);
        request.setAttribute("users", rs);
        request.getRequestDispatcher("list_users.jsp").forward(request, response);
    }
}
