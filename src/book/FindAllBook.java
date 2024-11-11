package book;

import util.DbOp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class FindAllBook extends JFrame implements ActionListener {

    public static void main(String[] args) throws SQLException {
        new FindAllBook();
    }

    //从数据库中取出信息
    //rowData用来存放行数据
    //columnNames存放列名
    Vector rowData, columnNames;
    JTable table = null;
    JScrollPane jsp = null;
    ResultSet resultSet = null;

    JScrollPane scrollPane = new JScrollPane(table);

    public FindAllBook() {
        setTitle("图书详情列表");
        setSize(2400,1300);//设置窗口大小
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置按下右上角X号后关闭
        initFrame();// 调用函数初始化窗体的组件
        setLocationRelativeTo(null);//窗口居中
        setVisible(true);//窗口可见
//        findBtn.addActionListener(this);

    }
    public void initFrame(){

        Container container = this.getContentPane();



        columnNames = new Vector();
        //设置列名
        columnNames.add("图书编号");
        columnNames.add("图书名称");
        columnNames.add("图书类别");
        columnNames.add("作者");
        columnNames.add("译者");
        columnNames.add("出版社");
        columnNames.add("出版日期");
        columnNames.add("定价");
        columnNames.add("库存数量");

        rowData = new Vector();

        try {
            resultSet = DbOp.executeQuery("select * from book");
            while (resultSet.next()) {
                //rowData可以存放多行
                Vector hang = new Vector();
                hang.add(resultSet.getInt(1));
                hang.add(resultSet.getString(2));
                hang.add(resultSet.getString(3));
                hang.add(resultSet.getString(4));
                hang.add(resultSet.getString(5));
                hang.add(resultSet.getString(6));
                hang.add(resultSet.getString(7));
                hang.add(resultSet.getString(8));
                hang.add(resultSet.getString(9));
                //加入到rowData
                rowData.add(hang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table = new JTable(rowData,columnNames);
        jsp = new JScrollPane(table);
//        this.add(jsp);
        container.add(jsp);

    }



 /*   public void findByCondition(String bookname,String author,String publisher,String publish_time) throws SQLException {
        Book book = new Book();

        String findSQL = "SELECT\n" +
                "\t* \n" +
                "FROM\n" +
                "\tbook \n" +
                "WHERE\n" +
                "\tbookname = '" + bookname + "' \n" +
                "\tOR author = '" + author + "'  \n" +
                "\tOR publisher = '" + publisher + "' \n" +
                "\tOR publish_time = '" + publish_time + "'";
        ResultSet resultSet = DbOp.executeQuery(findSQL);
        try{
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
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println(findSQL);
        System.out.println(book);
//        return book;
    }

    // 查询列数
    public static int columnCount() {
        String sql = "SELECT * FROM BOOK";
        ResultSet resultSet = DbOp.executeQuery(sql);
        int count = 0;
        try {
            while (resultSet.next()) {
                count = resultSet.getMetaData().getColumnCount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }
    // 查询列数
    public static int countByCondition() throws SQLException {
        String bookname = bookText.getText();
        String author = authorText.getText();
        String publisher = publishText.getText();
        String publish_time = publishTimeText.getText();
        String findSQL = "SELECT\n" +
                "\tcount(*) \n" +
                "FROM\n" +
                "\tbook \n" +
                "WHERE\n" +
                "\tbookname = '" + bookname + "' \n" +
                "\tOR author = '" + author + "'  \n" +
                "\tOR publisher = '" + publisher + "' \n" +
                "\tOR publish_time = '" + publish_time + "'";
        ResultSet resultSet = DbOp.executeQuery(findSQL);
        int count = 0;
        try{
            while (resultSet.next()) {
               count = resultSet.getMetaData().getColumnCount();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("================================");
        System.out.println(findSQL);
        System.out.println(count);
        System.out.println("=================================");
        return count;
    } */

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}