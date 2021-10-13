package sample.DataBasePackage;

import sample.DataPackage.Room;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class RoomDatabase extends DatabaseHandler {

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
            Statement statement = getDbConnection().createStatement();
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

    public ResultSet getRoomByID(String id) {
        ResultSet result = null;

        String select = "SELECT * FROM " + Const.ROOM_TABLE + " WHERE " + Const.ROOM_ID + "=" + id;

        try {
            Statement statement = getDbConnection().createStatement();
            result = statement.executeQuery(select);
        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return result;
    }
}
