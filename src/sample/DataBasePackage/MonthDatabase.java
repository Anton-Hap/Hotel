package sample.DataBasePackage;

import sample.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MonthDatabase extends DatabaseHandler {

    public void updateMonth(String month, int count) {
        String update = "UPDATE " + Const.MONTH_MONTH + " SET " + Const.MONTH_COUNT + "=? " + "WHERE " + Const.MONTH_MONTH + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(update);

            statement.setString(1, Integer.toString(count));
            statement.setString(2, month);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getMonth() {
        ResultSet result = null;
        String insert = "SELECT * FROM " + Const.MONTH_TABLE;

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);

            result = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return  result;
    }

    public ResultSet getCountByMonth(String month) {
        ResultSet result = null;
        String query = "SELECT * FROM " + Const.MONTH_TABLE + " WHERE " + Const.MONTH_MONTH + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(query);

            statement.setString(1, month);

            result = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }
}
