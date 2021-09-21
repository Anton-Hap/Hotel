package sample;

public class Query {

    private String UserName;
    private String IdRoom;
    private String DateStart;
    private String CountDay;

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getIdRoom() {
        return IdRoom;
    }

    public void setIdRoom(String IDRoom) {
        this.IdRoom = IDRoom;
    }

    public String getDateStart() {
        return DateStart;
    }

    public void setDateStart(String dateStart) {
        DateStart = dateStart;
    }

    public String getCountDay() {
        return CountDay;
    }

    public void setCountDay(String countDay) {
        CountDay = countDay;
    }

    public Query(String userName, String IDRoom, String dateStart, String countDay) {
        UserName = userName;
        this.IdRoom = IDRoom;
        DateStart = dateStart;
        CountDay = countDay;
    }
}
