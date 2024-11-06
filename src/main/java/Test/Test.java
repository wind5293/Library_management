package Test;

import DataBaseSQL.BookDataBase;
import DocumentManager.Author;
import DocumentManager.Book;
import Function.BookManagement;
import Function.SuggestionFunc;
import GUI.MainStage;
import javafx.event.ActionEvent;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Test {
    public static void main(String[] args) {
     /**   Author author1 = new Author("Hung","12/9/1998","Nam Dinh");
        Author author2 = new Author("THung","12/9/1998","Nam Dinh");
        Author author3 = new Author("PHung","12/9/1998","Nam Dinh");

        Book a1 = new Book("S0111","Những Điều Thầm Kín Của Con Gái Tuổi Teen", author1);
        Book a2 = new Book("S0112","Hành Trang Vào Đời Của Tuổi Teen", author3);
        Book a3 = new Book("S0113","Dũng Cảm Lên Em", author2);
        Book a4 = new Book("S0114","Nói Teen, Teen Nghe - Nghe Teen, Teen Nói",author3);
        Book a5 = new Book("S0114","Hoa xương rồng nở trên giày cao gót",author1);


        BookManagement b = new BookManagement();
        b.addBook(a1);
        b.addBook(a2);
        b.addBook(a3);
        b.addBook(a5);
        b.addBook(a4);
        b.getBookListInfo();

        SuggestionFunc s = new SuggestionFunc();
        List<Book> suggestionBook1 = s.getSuggestionBook1("Ha",b);
        System.out.println("Goi y theo chu cai dau tien");
        for(Book book : suggestionBook1) {
            System.out.println(String.format("Sach:%s",book.getTitle()));
        }


        System.out.println("Goi y theo ten tac gia");
        List<Book> suggestionBook2 = s.getSuggestionBook2(a2,b);
        for(Book book : suggestionBook2) {
            System.out.println(String.format("Sach:%s\nTac gia:%s\n",book.getTitle(),book.getAuthor().getAuthorName()));
        }
      **/
        BookDataBase bdb = new BookDataBase();
        try{
            //bdb.addToDataBase("Nếu một mai tôi có bay lên trời", "Nguyễn Nhật Ánh", 5);
            ResultSet rs = bdb.searchFromDataBase("Nếu một mai tôi có bay lên trời");
            while(rs.next()){
                int id = rs.getInt("bookId"); // Adjust column name as necessary
                String title = rs.getString("title");
                String author = rs.getString("author");
                System.out.println("ID: " + id + ", title " + title + ", author: " + author);
            }
            //bdb.deleteFromDataBase("Nếu một mai tôi có bay lên trời", "Nguyễn Nhật Ánh");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
