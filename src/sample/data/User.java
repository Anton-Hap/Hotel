package sample.data;

public class User {
    private String Id;
    private String FirstName;
    private String LastName;
    private String UserName;
    private String Password;
    private String Gender;
    private String Age;
    private String Admin;

    public User(String id, String firstName, String lastName, String userName, String password, String gender, String age, String admin, String book) {
        Id = id;
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        Password = password;
        Gender = gender;
        Age = age;
        Admin = admin;
        Book = book;
    }

    public User(String firstName, String lastName, String userName, String password, String gender, String age, String admin, String book) {
        FirstName = firstName;
        LastName = lastName;
        UserName = userName;
        Password = password;
        Gender = gender;
        Age = age;
        Admin = admin;
        Book = book;
    }

    private String Book;

    public String getBook() {
        return Book;
    }

    public void setBook(String book) {
        Book = book;
    }

    public User() {}

    public String getId() {
        return Id;
    }

    public void setId(String ID) {
        this.Id = ID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public String getAge() {
        return Age;
    }

    public void setAge(String age) {
        Age = age;
    }

    public String getAdmin() {
        return Admin;
    }

    public void setAdmin(String admin) {
        Admin = admin;
    }

    public User(String userName, String password) {
        UserName = userName;
        Password = password;
    }

}
