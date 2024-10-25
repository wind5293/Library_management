package User;
import DocumentManager.Book;

import java.util.List;

public abstract class User {
    private String userId;
    private String userName;
    private String passWord;


    //Tao 1 account quan ly ->Constant
    //Them sua xoa trong de co danh sach muon sach la cua nguoi dung
    //Nguoi quan ly truy xuat so luong sach, nguoi dung,.....

    public void addBook(Book book){}
    public void deleteBook(Book book){}
}
