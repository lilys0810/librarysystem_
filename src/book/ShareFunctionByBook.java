package book;

import pojo.Book;
import util.DbOp;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ShareFunctionByBook {

    public int findBookCountById(int bookId) {
        String sql = "SELECT COUNT(*) bookCount FROM book WHERE id = '" + bookId + "'";
        int count = 0;
        try {
            ResultSet resultSet = DbOp.executeQuery(sql);
            while (resultSet.next()) {
                count = resultSet.getInt("bookCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(count);
        return count;
    }


    public Book findBookById(int bookId) {
        Book book = new Book();
        List<Book> bookList = new ArrayList<>();

        String selectSQL = "select * from book where id = '" + bookId + "'";
        ResultSet resultSet = DbOp.executeQuery(selectSQL);
        try {
            while (resultSet.next()) {
                book.setId(resultSet.getInt("id"));
                book.setBookname(resultSet.getString("bookname"));
                book.setBooktype(resultSet.getString("booktype"));
                book.setAuthor(resultSet.getString("author"));
                book.setTranslator(resultSet.getString("translator"));
                book.setPublisher(resultSet.getString("publisher"));
                book.setPublish_time(resultSet.getString("publish_time"));
                book.setPrice(resultSet.getString("price"));
                book.setStock(resultSet.getInt("stock"));
                bookList.add(book);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(selectSQL);
        for (Book book1 : bookList) {
            System.out.println(book1+"...");
        }
        return book;
    }


}
