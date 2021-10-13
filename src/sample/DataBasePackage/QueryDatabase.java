package sample.DataBasePackage;

import sample.DataPackage.Query;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryDatabase extends DatabaseHandler {

    public void setQuery(String username, String idroom, String date, String day) {
        String insert = "INSERT INTO " + Const.QUERY_TABLE + "(" + Const.QUERY_USERNAMECLIENT + "," + Const.QUERY_IDROOM + "," + Const.QUERY_DATESTART + "," +
                Const.QUERY_COUNTDAY + ")" + "VALUES(?,?,?,?)";;

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);

            statement.setString(1, username);
            statement.setString(2, idroom);
            statement.setString(3, date);
            statement.setString(4, day);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
    }

    public ResultSet getQuery() {
        ResultSet result = null;
        String select = "SELECT * FROM " + Const.QUERY_TABLE;

        try {
            Statement statement = getDbConnection().createStatement();

            result = statement.executeQuery(select);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return  result;
    }

    public void deleteQuery(Query query) {
        String delete = "DELETE FROM " + Const.QUERY_TABLE + " WHERE " + Const.QUERY_USERNAMECLIENT + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(delete);

            statement.setString(1, query.getUserName());

            statement.executeUpdate();
        } catch(SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void deleteQueryByUserName(String username) {
        String delete = "DELETE FROM " + Const.QUERY_TABLE + " WHERE " + Const.QUERY_USERNAMECLIENT + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(delete);

            statement.setString(1, username);

            statement.executeUpdate();
        } catch(SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
