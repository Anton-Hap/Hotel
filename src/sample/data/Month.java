package sample.data;

public class Month {

    private String Month;
    private int count;

    public String getMonth() {
        return Month;
    }

    public void setMonth(String month) {
        Month = month;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Month(String month, int count) {
        Month = month;
        this.count = count;
    }
}
