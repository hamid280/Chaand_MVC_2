package model;

import user.User;
import javax.sql.DataSource;
import java.sql.*;

public class DBHelper {
    private ResultSet users = null;

    public ResultSet getUsers(DataSource dataSource) throws SQLException {

        Connection conn;
        Statement stmt;

        conn = dataSource.getConnection();
        stmt = conn.createStatement();
        String query = "select * from users";
        users = stmt.executeQuery(query);

        return users;
    }

    public boolean addUser(User user, DataSource dataSource) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        conn = dataSource.getConnection();
        String name = user.getName();
        String email = user.getEmail();
        String query = "insert into users (name, email) values (?, ?)";
        stmt = conn.prepareStatement(query);
        stmt.setString(1, name);
        stmt.setString(2, email);
        return stmt.execute();

    }
}
