package sample.DataBasePackage;

import javafx.stage.Stage;

public class Const {
    public static final String USER_TABLE = "user";

    public static final String USER_ID = "IDUser";
    public static final String USER_LASTNAME = "LastName";
    public static final String USER_FIRSTNAME = "FirstName";
    public static final String USER_USERNAME = "UserName";
    public static final String USER_PASSWORD = "Password";
    public static final String USER_GENDER = "Gender";
    public static final String USER_AGE = "Age";
    public static final String USER_ADMIN = "Admin";
    public static final String USER_BOOK = "Book";



    public static final String ROOM_TABLE = "room";

    public static final String ROOM_ID = "IDRoom";
    public static final String ROOM_TYPE = "Type";
    public static final String ROOM_PRICE = "Price";
    public static final String ROOM_SLEEPINGPLACECOUNT = "SleepingPlaceCount";
    public static final String ROOM_SILENCELEVEL = "SilenceLevel";


    public static final String BOOKROOM_TABLE = "bookroom";

    public static final String BOOKROOM_ID = "Id";
    public static final String BOOKROOM_IDROOM = "IDRoom";
    public static final String BOOKROOM_DATEBOOK = "DateBook";
    public static final String BOOKROOM_COUNTDAY = "CountDay";
    public static final String BOOKROOM_USERNAMECLIENT = "UserNameClient";


    public static final String QUERY_TABLE = "dataquery";

    public static final String QUERY_USERNAMECLIENT = "UserNameClient";
    public static final String QUERY_IDROOM = "IdRoom";
    public static final String QUERY_DATESTART = "DateStart";
    public static final String QUERY_COUNTDAY = "CountDay";


    public static final String CASH_TABLE = "cash";

    public static final String CASH_ID = "ID";
    public static final String CASH_USERNAME = "UserName";
    public static final String CASH_CASH = "Cash";


    public static final String MONTH_TABLE = "month";

    public static final String MONTH_ID = "ID";
    public static final String MONTH_MONTH = "Month";
    public static final String MONTH_COUNT = "Count";
}
