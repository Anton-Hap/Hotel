package sample.DataBasePackage;

import sample.DataPackage.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersDatabase extends DatabaseHandler {

    public void setUser(User user) {
        String insert = "INSERT INTO " + Const.USER_TABLE + "(" + Const.USER_FIRSTNAME + "," + Const.USER_LASTNAME + "," + Const.USER_USERNAME + "," +
                Const.USER_PASSWORD + "," + Const.USER_GENDER + "," + Const.USER_AGE + "," + Const.USER_ADMIN + "," + Const.USER_BOOK + ")" + "VALUES(?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getGender());
            statement.setString(6, user.getAge());
            statement.setString(7, user.getAdmin());
            statement.setString(8, user.getBook());

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getUserByUserName(String username) {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_USERNAME + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);

            statement.setString(1, username);

            result = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public ResultSet getUserByUserNameAndPassword(String username, String password) {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_USERNAME + "=? AND " + Const.USER_PASSWORD + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);

            statement.setString(1, username);
            statement.setString(2, password);

            result = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }

    public ResultSet getUserByID(String value) {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + " = " + value;

        try {
            Statement statement = getDbConnection().createStatement();
            result = statement.executeQuery(select);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ResultSet getUser() {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.USER_TABLE;

        try {
            Statement statement = getDbConnection().createStatement();
            result = statement.executeQuery(select);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public void updateUser (User user) {
        String update = "UPDATE " + Const.USER_TABLE + " SET " + Const.USER_FIRSTNAME + "=?, " + Const.USER_LASTNAME + "=?, " + Const.USER_USERNAME + "=?, " +
                Const.USER_PASSWORD + "=?, " + Const.USER_GENDER + "=?, " + Const.USER_AGE + "=?, " + Const.USER_ADMIN + "=?, " + Const.USER_BOOK + "=? " + "WHERE " + Const.USER_ID + "= " +
                user.getId();

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(update);

            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getGender());
            statement.setString(6, user.getAge());
            statement.setString(7, user.getAdmin());
            statement.setString(8, user.getBook());

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteUser(User user) {
        String delete = "DELETE FROM " + Const.USER_TABLE + " WHERE " + Const.USER_ID + "=" + user.getId();

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(delete);

            statement.executeUpdate();
        } catch(SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateUserBook (String UserName, String book) {
        String update = "UPDATE " + Const.USER_TABLE + " SET " + Const.USER_BOOK + "=? " + "WHERE " + Const.USER_USERNAME + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(update);

            statement.setString(1, book);
            statement.setString(2, UserName);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
