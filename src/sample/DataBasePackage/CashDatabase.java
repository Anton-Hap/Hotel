package sample.DataBasePackage;

import sample.Main;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CashDatabase extends DatabaseHandler {

    public ResultSet getCash() {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.CASH_TABLE;

        try {
            Statement statement = getDbConnection().createStatement();
            result = statement.executeQuery(select);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public void setCashClient(String username) {
        String insert = "INSERT INTO " + Const.CASH_TABLE + "(" + Const.CASH_USERNAME + "," + Const.CASH_CASH + ")" + "VALUES(?,?)";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);

            statement.setString(1, username);
            statement.setString(2, "0");

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public void updateCash(String username, int cash) {
        String update = "UPDATE " + Const.CASH_TABLE + " SET " + Const.CASH_CASH + "=? " + "WHERE " + Const.CASH_USERNAME + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(update);

            statement.setString(1, Integer.toString(cash));
            statement.setString(2, username);

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getCashByUserName(String username) {
        ResultSet result = null;
        String select = "SELECT * FROM " + Const.CASH_TABLE + " WHERE " + Const.CASH_USERNAME + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);

            statement.setString(1, username);

            result = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return  result;
    }

    public void deleteCash(String username) {
        String delete = "DELETE FROM " + Const.CASH_TABLE + " WHERE " + Const.CASH_USERNAME + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(delete);

            statement.setString(1, username);

            statement.executeUpdate();
        } catch(SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }
}
