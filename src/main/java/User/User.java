package User;
import DocumentManager.Book;

import java.util.List;

public abstract class User {
    private String userId;
    private String userName;
    private String password;

    public User(String userId, String userName,
                String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return password;
    }

    public void setPassWord(String password) {
        this.password = password;
    }

    //public abstract void addBook(Book book);

    //public abstract String getUserType();

    @Override
    public String toString() {
        return "User ID: " + userId + ", User Name: " + userName;
    }
}
