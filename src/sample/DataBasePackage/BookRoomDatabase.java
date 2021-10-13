package sample.DataBasePackage;

import sample.DataPackage.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookRoomDatabase extends DatabaseHandler {

    public ResultSet getBookRoom() {
        ResultSet result = null;

        String insert = "SELECT * FROM " + Const.BOOKROOM_TABLE;

        try {
            Statement statement = getDbConnection().createStatement();
            result = statement.executeQuery(insert);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public void setBookRoom(Query query) {
        String insert = "INSERT INTO " + Const.BOOKROOM_TABLE + "(" + Const.BOOKROOM_IDROOM + "," + Const.BOOKROOM_DATEBOOK + "," + Const.BOOKROOM_COUNTDAY + "," + Const.BOOKROOM_USERNAMECLIENT + ")" + "VALUES(?,?,?,?)";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);

            statement.setString(1, query.getIdRoom());
            statement.setString(2, query.getDateStart());
            statement.setString(3, query.getCountDay());
            statement.setString(4, query.getUserName());

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getBookRoomByUserName(String username) {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.BOOKROOM_TABLE + " WHERE " + Const.BOOKROOM_USERNAMECLIENT + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);

            statement.setString(1, username);

            result = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public void deleteBook(String username) {
        String delete = "DELETE FROM " + Const.BOOKROOM_TABLE + " WHERE " + Const.BOOKROOM_USERNAMECLIENT + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(delete);

            statement.setString(1, username);

            statement.executeUpdate();
        } catch(SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
