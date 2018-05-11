import model.DBHelper;
import sun.security.pkcs11.Secmod;
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
import java.util.List;

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
            case "updateUser":
                int user_id = Integer.parseInt(request.getParameter("user_id"));
                try {
                    updateUser(user_id, request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "deleteUser":
                int user_id1 = Integer.parseInt(request.getParameter("user_id"));
                try {
                    deleteUser(user_id1, request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            default:
                error(request, response);
                break;
        }
    }

    private void deleteUser(int user_id, HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        new DBHelper().deleteUser(user_id, dataSource);
        listUsers(request,response);
    }


    private void updateUser(int user_id, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        if (request.getParameterMap().containsKey("action")) {
            if (request.getParameter("action").equals("submit")) {

                //update the record code goes
                String name = request.getParameter("name");
                String email = request.getParameter("email");
                User tempUser = new User(user_id, name, email);
                new DBHelper().updateUser(tempUser, dataSource);
                request.setAttribute("message", "Record Updated!!");
                request.getRequestDispatcher("message.jsp").forward(request, response);
            }
        } else {

            //create a user reference
            User user = null;

            //Read user from DB Helper
            try {
                user = new DBHelper().getUser(user_id, dataSource);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            //set the attribute after we got user from the internet
            request.setAttribute("User", user);
            request.getRequestDispatcher("updateUser.jsp").forward(request, response);
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

        List<User> usersList = new DBHelper().getUsers(dataSource);
        request.setAttribute("users", usersList);
        request.getRequestDispatcher("list_users.jsp").forward(request, response);
    }
}
