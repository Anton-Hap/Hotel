package sample;

import sample.DataBasePackage.Const;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Data {
    public static String ID = "";

    public ArrayList<String> RoomID = new ArrayList<>();
    public ArrayList<String> RoomType = new ArrayList<>();
    public ArrayList<String> RoomCountSleepingPlace = new ArrayList<>();
    public ArrayList<String> RoomSilenceLevel = new ArrayList<>();
    public ArrayList<String> RoomPrice = new ArrayList<>();

    public User user;
    public String cash;

    public static User resultToUser(ResultSet result) {
        User user = new User();

        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            try {
                user.setId(result.getString(Const.USER_ID));
                user.setFirstName(result.getString(Const.USER_FIRSTNAME));
                user.setLastName(result.getString(Const.USER_LASTNAME));
                user.setUserName(result.getString(Const.USER_USERNAME));
                user.setPassword(result.getString(Const.USER_PASSWORD));
                user.setGender(result.getString(Const.USER_GENDER));
                user.setAge(result.getString(Const.USER_AGE));
                user.setAdmin(result.getString(Const.USER_ADMIN));
                user.setBook(result.getString(Const.USER_BOOK));
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }

        return user;
    }

    public static void resultToID(ResultSet result) {
        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
            try {
                ID = result.getString(Const.ROOM_ID);
            } catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
    }

    public void getRoomsData(ResultSet result) {
        RoomID.clear();

        while (true) {
            try {
                if (!result.next()) break;
            RoomID.add(result.getString(Const.ROOM_ID));
            RoomType.add(result.getString(Const.ROOM_TYPE));
            RoomCountSleepingPlace.add(result.getString(Const.ROOM_SLEEPINGPLACECOUNT));
            RoomSilenceLevel.add(result.getString(Const.ROOM_SILENCELEVEL));
            RoomPrice.add(result.getString(Const.ROOM_PRICE));

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
    }
}
