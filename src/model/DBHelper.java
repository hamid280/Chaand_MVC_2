package model;

import user.User;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBHelper {
    private ResultSet users = null;

    public List<User> getUsers(DataSource dataSource) throws SQLException {

        Connection conn = null;
        Statement stmt = null;
        List<User> usersList = new ArrayList<>();


        conn = dataSource.getConnection();
        stmt = conn.createStatement();
        String query = "select * from users";
        users = stmt.executeQuery(query);

        //convert resultSet users to list
        while (users.next()){
            int user_id = users.getInt("user_id");
            String name = users.getString("name");
            String email = users.getString("email");
            User temp = new User(user_id, name, email);
            usersList.add(temp);
        }

        //close the connections
        conn.close();
        stmt.close();

        return usersList;
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

    public User getUser(int user_id, DataSource dataSource) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        User user= null;

        try{
            conn = dataSource.getConnection();
            String query = "select * from users where user_id=?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, user_id);
            rs = stmt.executeQuery();

            //now execute name and email from result set
            String name = null;
            String email = null;
            if(rs.next()){
                name = rs.getString("name");
                email = rs.getString("email");
            }
            user = new User(user_id, name, email);

        } catch (Exception e){
            e.printStackTrace();
        } finally {
            conn.close();
            stmt.close();
            rs.close();
        }

        return user;
    }

    public boolean updateUser(User user, DataSource dataSource) throws SQLException {

        Connection conn = null;
        PreparedStatement stmt = null;
        try{
            conn = dataSource.getConnection();
            String query = "update users set name=?, email=? where user_id=?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1,user.getName());
            stmt.setString(2,user.getEmail());
            stmt.setInt(3,user.getUser_id());
            return stmt.execute();

        } finally {
            conn.close();
            stmt.close();
        }
    }

    public boolean deleteUser(int user_id, DataSource dataSource) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;

        try{
            conn = dataSource.getConnection();
            String query = "delete from users where user_id=?";
            stmt = conn.prepareStatement(query);
            stmt.setInt(1, user_id);
            return stmt.execute();
        } finally {
            conn.close();
            stmt.close();
        }


    }
}
