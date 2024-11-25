//package Function;
//
//import DocumentManager.Book;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//public class SearchBook {
//
//
//
//
//
//
//
//    /**
//     * Use for commandline version.
//     */
//    /**
//     * Tim kiem gan dung.
//     * Tra trong danh sach co Sach nao chua cac chu cai trong Input
//     * Input la ten tim kiem
//     */
//    public List<Book> searchByWordsInSentence(BookManagement bookManagement, String input) {
//        Set<String> keywords = extractWords(input);
//        List<Book> result = new ArrayList<>();
//        List<Book> bookList = bookManagement.getBookList();
//        for (Book book : bookList) {
//            // Lấy tiêu đề của cuốn sách hiện tại
//            String title = book.getBookName();
//
//            // Kiểm tra xem tiêu đề có chứa bất kỳ từ khóa nào không
//            if (containsAnyWord(title, keywords)) {
//                result.add(book);  // Thêm sách vào kết quả nếu có từ khóa trùng
//            }
//        }
//        return result;
//    }
//
//    private static Set<String> extractWords(String sentence) {
//        String[] words = sentence.split("\\s+");
//        Set<String> wordSet = new HashSet<>();
//        for (String word : words) {
//            wordSet.add(word.toLowerCase());
//        }
//        return wordSet;
//    }
//    private static boolean containsAnyWord(String sentence, Set<String> keywords) {
//        String[] words = sentence.split("\\s+");
//        for (String word : words) {
//            if (keywords.contains(word.toLowerCase())) {
//                return true;
//            }
//        }
//        return false;
//    }
//
//    /**
//     * Tim kiem chinh xac ten sach.
//     */
//    public Book searchExactly (BookManagement bookManagement, String input) {
//        List<Book> bookList = bookManagement.getBookList();
//        for(Book book : bookList) {
//            if(book.getBookName().equalsIgnoreCase(book.getBookName())) {
//                return book;
//            }
//        }
//        return null;
//    }
//
//    public boolean searchSucceed (BookManagement bookManagement, String input) {
//        List<Book> bl = searchByWordsInSentence(bookManagement, input);
//        if (bl.size() == 0 && searchExactly(bookManagement, input) == null) {
//            return false;
//        }
//        return true;
//    }
//}
