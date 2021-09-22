package sample.data;

public class Cash {

    private String cash;
    private String username;
    private String id;

    public Cash(String id, String username, String cash) {
        this.cash = cash;
        this.username = username;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCash() {
        return cash;
    }

    public void setCash(String cash) {
        this.cash = cash;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Cash(String cash, String username) {
        this.cash = cash;
        this.username = username;
    }
}
