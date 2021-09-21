package sample.DataBasePackage;

import sample.*;

import java.sql.*;

public class DatabaseHandler extends Configs {
    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {
        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbName;

        Class.forName("com.mysql.jdbc.Driver");

        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPass);

        return dbConnection;
    }


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
            Statement statement = Main.Handler.getDbConnection().createStatement();
            result = statement.executeQuery(select);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ResultSet getCash() {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.CASH_TABLE;

        try {
            Statement statement = Main.Handler.getDbConnection().createStatement();
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
            Statement statement = Main.Handler.getDbConnection().createStatement();
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

    public void setRoom(Room room) {
        String insert = "INSERT INTO " + Const.ROOM_TABLE + "(" + Const.ROOM_TYPE + "," + Const.ROOM_PRICE + "," + Const.ROOM_SLEEPINGPLACECOUNT + "," +
                Const.ROOM_SILENCELEVEL + ")" + "VALUES(?,?,?,?)";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(insert);

            statement.setString(1, room.getType());
            statement.setString(2, room.getPrice());
            statement.setString(3, room.getSleepingPlaceCount());
            statement.setString(4, room.getSilenceLevel());

            statement.executeUpdate();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getRooms() {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.ROOM_TABLE;

        try {
            Statement statement = Main.Handler.getDbConnection().createStatement();
            result = statement.executeQuery(select);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public void deleteRoom(Room room) {
        String delete = "DELETE FROM " + Const.ROOM_TABLE + " WHERE " + Const.ROOM_ID + "=" + room.getId();

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(delete);

            statement.executeUpdate();
        } catch(SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public ResultSet getBookRoom() {
        ResultSet result = null;

        String insert = "SELECT * FROM " + Const.BOOKROOM_TABLE;

        try {
            Statement statement = Main.Handler.getDbConnection().createStatement();
            result = statement.executeQuery(insert);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ResultSet getFoundRoom(String type, String sleepingplacecount, String silencelevel) {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.ROOM_TABLE + " WHERE " + Const.ROOM_TYPE + "=? AND " + Const.ROOM_SLEEPINGPLACECOUNT + "=?" +
                " AND " + Const.ROOM_SILENCELEVEL + "=?";

        try {
            PreparedStatement statement = getDbConnection().prepareStatement(select);

            statement.setString(1, type);
            statement.setString(2, sleepingplacecount);
            statement.setString(3, silencelevel);

            result = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
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

    public ResultSet getBookRoomByUserName(String username) {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.BOOKROOM_TABLE + " WHERE " + Const.BOOKROOM_USERNAMECLIENT + "=?";

        try {
            PreparedStatement statement = Main.Handler.getDbConnection().prepareStatement(select);

            statement.setString(1, username);

            result = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public ResultSet getRoomByID(String id) {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.ROOM_TABLE + " WHERE " + Const.ROOM_ID + "=" + id;

        try {
            Statement statement = Main.Handler.getDbConnection().createStatement();
            result = statement.executeQuery(select);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }

    public void setQuery(String username, String idroom, String date, String day) {
        String insert = "INSERT INTO " + Const.QUERY_TABLE + "(" + Const.QUERY_USERNAMECLIENT + "," + Const.QUERY_IDROOM + "," + Const.QUERY_DATESTART + "," +
                Const.QUERY_COUNTDAY + ")" + "VALUES(?,?,?,?)";;

        try {
            PreparedStatement statement = Main.Handler.getDbConnection().prepareStatement(insert);

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
            Statement statement = Main.Handler.getDbConnection().createStatement();

            result = statement.executeQuery(select);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return  result;
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
            PreparedStatement statement = Main.Handler.getDbConnection().prepareStatement(select);

            statement.setString(1, username);

            result = statement.executeQuery();
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return  result;
    }

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

    public ResultSet getMonth() {
        ResultSet result = null;
        String insert = "SELECT * FROM " + Const.MONTH_TABLE;

        try {
            PreparedStatement statement = Main.Handler.getDbConnection().prepareStatement(insert);

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
