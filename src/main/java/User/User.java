package User;
import DocumentManager.Book;

import java.util.List;

public abstract class User {
    private String userId;
    private String userName;
    private String passWord;

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
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    //Tao 1 account quan ly ->Constant
    //Them sua xoa trong de co danh sach muon sach la cua nguoi dung
    //Nguoi quan ly truy xuat so luong sach, nguoi dung,.....
}
