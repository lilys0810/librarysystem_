package borrow;

import book.ShareFunctionByBook;
import controller.ShowMain;
import pojo.Book;
import pojo.Reader;
import reader.ShareFunctionByReader;
import util.DbOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Borrow extends JFrame implements ActionListener{

    JLabel bookLabel = new JLabel(" 图书编号：");
    JTextField bookText = new JTextField(7);
    JLabel readerLabel = new JLabel("读者编号：");
    JTextField readerText = new JTextField(7);
    JButton findBtn = new JButton("查询");

    // 图书信息
    JTextField bookname = new JTextField(12);
    JTextField author = new JTextField(12);
    JTextField publisher = new JTextField(12);
    JTextField publish_time = new JTextField(12);
    JTextField price = new JTextField(12);
    JTextField stock = new JTextField(12);

    // 读者信息
    JTextField readername = new JTextField(12);
    JTextField readertype = new JTextField(12);
    JTextField max_num = new JTextField(12);
    JTextField days_num = new JTextField(12);

    // 借阅信息
    JTextField borrowCount = new JTextField(12);
    JTextField isSelectBorrow = new JTextField(12);
    JTextField borrowDate = new JTextField(12);

    JButton borrowBtn = new JButton("借出");
    JButton returnBtn = new JButton("返回");


    public Borrow() {
        JPanel queryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        queryPanel.add(bookLabel);
        queryPanel.add(bookText);
        queryPanel.add(readerLabel);
        queryPanel.add(readerText);
        queryPanel.add(findBtn);

        // 图书
        JPanel bookPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        bookPanel.add(new JLabel("图书名称："));
        bookPanel.add(bookname);
        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        authorPanel.add(new JLabel("作者："));
        authorPanel.add(author);
        JPanel publisherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        publisherPanel.add(new JLabel("出版社："));
        publisherPanel.add(publisher);
        JPanel publish_timePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        publish_timePanel.add(new JLabel("出版时间："));
        publish_timePanel.add(publish_time);
        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        pricePanel.add(new JLabel("定价："));
        pricePanel.add(price);
        JPanel stockPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        stockPanel.add(new JLabel("库存量："));
        stockPanel.add(stock);

        // 读者
        JPanel readernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        readernamePanel.add(new JLabel("读者姓名："));
        readernamePanel.add(readername);
        JPanel readertypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        readertypePanel.add(new JLabel("读者类型："));
        readertypePanel.add(readertype);
        JPanel max_numPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        max_numPanel.add(new JLabel("最大可借数："));
        max_numPanel.add(max_num);
        JPanel days_numPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        days_numPanel.add(new JLabel("最大可借天数："));
        days_numPanel.add(days_num);

        // 借阅信息
        JPanel borrowCountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        borrowCountPanel.add(new JLabel("该读者已借阅图书数量："));
        borrowCountPanel.add(borrowCount);
        JPanel isSelectBorrowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        isSelectBorrowPanel.add(new JLabel("该读者是否可借所选图书："));
        isSelectBorrowPanel.add(isSelectBorrow);
        JPanel borrowDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        borrowDatePanel.add(new JLabel("借阅日期："));
        borrowDatePanel.add(borrowDate);

        setEditable();

        this.setSize(500,450);
        this.setTitle("借阅图书");
        this.setLayout(new BorderLayout());

        Container c=this.getContentPane();

        JPanel jp1=new JPanel(new GridLayout(6,2));
        jp1.add(queryPanel);
        jp1.add(bookPanel);
        jp1.add(authorPanel);
        jp1.add(publisherPanel);
        jp1.add(publish_timePanel);
        jp1.add(pricePanel);
        jp1.add(stockPanel);
        jp1.add(readernamePanel);
        jp1.add(readertypePanel);
        jp1.add(max_numPanel);
        jp1.add(days_numPanel);
        jp1.add(borrowCountPanel);
        jp1.add(isSelectBorrowPanel);
        jp1.add(borrowDatePanel);



        JPanel jp3=new JPanel();
        jp3.add(borrowBtn);
        jp3.add(returnBtn);

        c.add(jp1,BorderLayout.CENTER);
        c.add("North",queryPanel);
        c.add("South",jp3);

        borrowBtn.addActionListener(this);
        returnBtn.addActionListener(this);

        findBtn.addActionListener(this);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public void setEditable() {
        bookname.setEditable(false);
        author.setEditable(false);
        publisher.setEditable(false);
        publish_time.setEditable(false);
        price.setEditable(false);
        stock.setEditable(false);
        readername.setEditable(false);
        readertype.setEditable(false);
        max_num.setEditable(false);
        days_num.setEditable(false);
        borrowCount.setEditable(false);
        borrowDate.setEditable(false);
        isSelectBorrow.setEditable(false);
    }

    public static void main(String[] args) {
        new Borrow();
    }

    // 查询库存量
    public int findStockById(int bookId) {
        String sql = "SELECT stock FROM book WHERE stock > 0 AND id = " + bookId;
        int stockCount = 0;
        try {
            ResultSet resultSet = DbOp.executeQuery(sql);

            while (resultSet.next()) {
                stockCount = resultSet.getInt("stock");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stockCount;
    }
    // 查询读者已借图书量
    public int findBookCountByBorrow(int readerId) {
        String sql = "SELECT COUNT(*) AS 'bookcount' FROM reader r,borrow b WHERE b.reader_id = r.id AND b.if_back = '未归还' AND r.id = " + readerId;
        int bookcount = 0;
        try {
            ResultSet resultSet = DbOp.executeQuery(sql);

            while (resultSet.next()) {
                bookcount = resultSet.getInt("bookcount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bookcount;
    }

    // 查询读者未借阅过该书籍
    public int findReaderNoBorrow(int readerId, int bookId) {
        String sql = "SELECT COUNT(*) AS 'bookcount' FROM borrow WHERE reader_id = " + readerId + " AND book_id = " + bookId + " AND if_back = '未归还'";
        int bookcount = 0;
        try {
            ResultSet resultSet = DbOp.executeQuery(sql);

            while (resultSet.next()) {
                bookcount = resultSet.getInt("bookcount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        return bookcount;
    }

    public String ifOkBorrow(int stockNum,int maxNum,int borrowCounted,String borrowFlag) {
//        stockNum = Integer.parseInt(stock.getText());
//        maxNum = Integer.parseInt(max_num.getText());
//        borrowCounted = Integer.parseInt(borrowCount.getText());
//        borrowFlag = isSelectBorrow.getText();
        String flag = "";
        if (stockNum <= 0) {
            flag = "库存量不足";
        }
        if (maxNum <= borrowCounted) {
            flag =  "最大可借数超过已借阅数";
        }
        if (borrowFlag.equals("否")) {
            flag =  "请确认是否已借过该书籍";
        }
        if (stockNum > 0 && maxNum > borrowCounted && borrowFlag.equals("是")) {
            flag = "借阅成功";
        }
        return flag;
    }

    public int addBorrowByIds(int bookId,int readerId,String borrowDate) {

        String sql = "INSERT INTO borrow(book_id,reader_id,`borrow-date`,if_back) VALUES (" +
                ""+bookId+"," +
                ""+readerId+"," +
                "'"+borrowDate+"'" +
                ",'未归还')";
        int flag = 0;
        try {
            flag = DbOp.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }
    public int updateBook(int bookId) {
        bookId = Integer.parseInt(bookText.getText());
        String sql = "UPDATE book SET stock = stock - 1 WHERE id = " + bookId;
        int flag = 0;
        try {
            flag = DbOp.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public void initTable() {
        bookname.setText(null);
        author.setText(null);
        publisher.setText(null);
        publish_time.setText(null);
        price.setText(null);
        stock.setText(null);
        readername.setText(null);
        readertype.setText(null);
        max_num.setText(null);
        days_num.setText(null);
        borrowCount.setText(null);
        isSelectBorrow.setText(null);
        borrowDate.setText(null);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        ShareFunctionByBook bookFun = new ShareFunctionByBook();
        ShareFunctionByReader readerFun = new ShareFunctionByReader();
        int countByReader = 0;
        int countByBook = 0;
        String readerStr = readerText.getText();
        String bookStr = bookText.getText();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if (e.getSource() == findBtn && !readerStr.equals("") && !bookStr.equals("")) {
            countByReader = readerFun.findReaderCountById(Integer.parseInt(readerStr));
            countByBook = bookFun.findBookCountById(Integer.parseInt(bookStr));
            if (countByReader > 0 ) {
                // 读者
                JOptionPane.showMessageDialog(null,"查询读者信息成功","提示消息",JOptionPane.DEFAULT_OPTION);
                Reader reader = readerFun.findReaderById(Integer.parseInt(readerText.getText()));
                readername.setText(reader.getReadername());
                readertype.setText(reader.getReadtype());
                max_num.setText(String.valueOf( reader.getMax_num()));
                days_num.setText(String.valueOf( reader.getDays_num()));


                // 查询已借图书量
                int bookCountByBorrow = findBookCountByBorrow(Integer.parseInt(readerStr));
                borrowCount.setText(String.valueOf( bookCountByBorrow));

            } else if (countByReader <= 0 ) {
                JOptionPane.showMessageDialog(null,"没有该读者信息!","提示消息",JOptionPane.ERROR_MESSAGE);
            }
            if (countByBook > 0) {
                // 图书
                JOptionPane.showMessageDialog(null,"查询图书信息成功","提示消息",JOptionPane.DEFAULT_OPTION);
                Book book = bookFun.findBookById(Integer.valueOf(bookText.getText()));
                bookname.setText(book.getBookname());
                author.setText(book.getAuthor());
                publisher.setText(book.getPublisher());
                publish_time.setText(book.getPublish_time());
                price.setText(book.getPrice());
                stock.setText(String.valueOf(book.getStock()));
            } else if (countByBook <= 0 ) {
                JOptionPane.showMessageDialog(null,"没有该图书信息!","提示消息",JOptionPane.ERROR_MESSAGE);
            }
            borrowDate.setText(sdf.format(new Date()));
            int readerNoBorrow = findReaderNoBorrow(Integer.parseInt(readerStr), Integer.parseInt(bookStr));
            if (readerNoBorrow > 0) {
                isSelectBorrow.setText("否");
            }else {
                isSelectBorrow.setText("是");
            }
        } else if (e.getSource() == findBtn && readerStr.equals("") && !bookStr.equals("")) {
            countByBook = bookFun.findBookCountById(Integer.parseInt(bookStr));
            if (countByBook > 0) {
                // 图书
                JOptionPane.showMessageDialog(null,"查询图书信息成功","提示消息",JOptionPane.DEFAULT_OPTION);
                Book book = bookFun.findBookById(Integer.valueOf(bookText.getText()));
                bookname.setText(book.getBookname());
                author.setText(book.getAuthor());
                publisher.setText(book.getPublisher());
                publish_time.setText(book.getPublish_time());
                price.setText(book.getPrice());
                stock.setText(String.valueOf(book.getStock()));
            } else if (countByBook <= 0 ) {
                JOptionPane.showMessageDialog(null,"没有该图书信息!","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == findBtn && !readerStr.equals("") && bookStr.equals("")) {
            countByReader = readerFun.findReaderCountById(Integer.parseInt(readerStr));
            if (countByReader > 0 ) {
                // 读者
                JOptionPane.showMessageDialog(null,"查询读者信息成功","提示消息",JOptionPane.DEFAULT_OPTION);
                Reader reader = readerFun.findReaderById(Integer.parseInt(readerText.getText()));
                readername.setText(reader.getReadername());
                readertype.setText(reader.getReadtype());
                max_num.setText(String.valueOf( reader.getMax_num()));
                days_num.setText(String.valueOf( reader.getDays_num()));

                // 查询已借图书量
                int bookCountByBorrow = findBookCountByBorrow(Integer.parseInt(readerStr));
                borrowCount.setText(String.valueOf( bookCountByBorrow));

            } else if (countByReader <= 0 ) {
                JOptionPane.showMessageDialog(null,"没有该读者信息!","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == borrowBtn) {
            if (stock.getText().equals("") || max_num.getText().equals("") || borrowCount.getText().equals("") || isSelectBorrow.getText().equals("")) {
                JOptionPane.showMessageDialog(null,"别乱按","提示消息",JOptionPane.ERROR_MESSAGE);
            }else {
                int bookId = Integer.parseInt(bookText.getText());
                int readerId = Integer.parseInt(readerText.getText());
                String borrowDate = this.borrowDate.getText();
                String messgae = ifOkBorrow(Integer.parseInt(stock.getText()), Integer.parseInt(max_num.getText()), Integer.parseInt(borrowCount.getText()), isSelectBorrow.getText());
                if (messgae.equals("借阅成功")) {
                    addBorrowByIds(bookId, readerId, borrowDate);
                    updateBook(bookId);
                    initTable();
                }
                JOptionPane.showMessageDialog(null, messgae, "提示消息", JOptionPane.DEFAULT_OPTION);
            }
        }
        if (e.getSource() == returnBtn) {
            dispose();
            new ShowMain();
        }
    }
}

