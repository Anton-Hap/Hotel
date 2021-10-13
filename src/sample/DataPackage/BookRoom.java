package sample.DataPackage;

public class BookRoom {

    private String Id;
    private String IdRoom;
    private String Date;
    private String CountDay;
    private String UserName;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getIdRoom() {
        return IdRoom;
    }

    public void setIdRoom(String idRoom) {
        IdRoom = idRoom;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCountDay() {
        return CountDay;
    }

    public void setCountDay(String countDay) {
        CountDay = countDay;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public BookRoom(String id, String idRoom, String date, String countDay, String userName) {
        Id = id;
        IdRoom = idRoom;
        Date = date;
        CountDay = countDay;
        UserName = userName;
    }
}
