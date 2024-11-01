package DataBaseSQL;

/** import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
    public static void main(String[] args) {
        BookDataBase bdb = new BookDataBase();
        try{
            ResultSet rs = bdb.searchFromDataBase("Mắt biếc");
            while(rs.next()){
                int id = rs.getInt("bookId"); // Adjust column name as necessary
                String title = rs.getString("title");
                String author = rs.getString("author");
                System.out.println("ID: " + id + ", title " + title + ", author: " + author);
            }
            //db.addDocument("Mắt biếc", "Nguyễn Nhật Ánh");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
 **/
