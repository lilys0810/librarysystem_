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

public class Borrow extends JFrame implements ActionListener {
    //JPanel topanel = new JPanel(new FlowLayout(FlowLayout.CENTER));

    JLabel bookLabel = new JLabel(" 图书编号：");
    JTextField bookText = new JTextField(15);

    JLabel readerLabel = new JLabel("读者编号：");
    JTextField readerText = new JTextField(15);
    JButton findBtn = new JButton("查询");

    // 图书信息
    JTextField bookname = new JTextField(20);
    JTextField author = new JTextField(20);
    JTextField publisher = new JTextField(20);
    JTextField publish_time = new JTextField(20);
    JTextField price = new JTextField(20);
    JTextField stock = new JTextField(20);

    // 读者信息
    JTextField readername = new JTextField(20);
    JTextField readertype = new JTextField(20);
    JTextField max_num = new JTextField(20);
    JTextField days_num = new JTextField(20);

    // 借阅信息
    JTextField borrowCount = new JTextField(20);
    JTextField isSelectBorrow = new JTextField(20);
    JTextField borrowDate = new JTextField(20);

    JButton borrowBtn = new JButton("借出");
    JButton returnBtn = new JButton("返回");


    public Borrow() {
        // 设置字体并放大
        Font f = new Font("宋体", Font.BOLD, 40);

        bookLabel.setFont(f);
        readerLabel.setFont(f);
        bookText.setFont(f);
        readerText.setFont(f);
        findBtn.setFont(f);

        bookname.setFont(f);
        author.setFont(f);
        publisher.setFont(f);
        publish_time.setFont(f);
        price.setFont(f);
        stock.setFont(f);

        readername.setFont(f);
        readertype.setFont(f);
        max_num.setFont(f);
        days_num.setFont(f);

        borrowCount.setFont(f);
        isSelectBorrow.setFont(f);
        borrowDate.setFont(f);
        borrowBtn.setFont(f);
        returnBtn.setFont(f);





        JPanel queryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        queryPanel.add(bookLabel);
        queryPanel.add(bookText);
        queryPanel.add(readerLabel);
        queryPanel.add(readerText);
        queryPanel.add(findBtn);

        // 图书
        JPanel bookPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font defaultFont = new Font("幼圆", Font.BOLD, 38);  // 创建新字体，并将字体大小设置为
        JLabel label1 = new JLabel("图书名称：");
        label1.setFont(defaultFont);
        bookPanel.add(label1);
        bookPanel.add(bookname);

        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font("幼圆", Font.BOLD, 38);
        JLabel label2 = new JLabel("出版社：");
        label2.setFont(font);
        authorPanel.add(label2);
        authorPanel.add(author);

        JPanel publisherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label3 = new JLabel("作者：");
        label3.setFont(font);
        publisherPanel.add(label3);
        publisherPanel.add(publisher);

        JPanel publish_timePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label4 = new JLabel("出版时间：");
        label4.setFont(font);
        publish_timePanel.add(label4);
        publish_timePanel.add(publish_time);

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label5 = new JLabel("定价：");
        label5.setFont(font);
        pricePanel.add(label5);
        pricePanel.add(price);

        JPanel stockPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label6 = new JLabel("库存：");
        label6.setFont(font);
        stockPanel.add(label6);
        stockPanel.add(stock);

        // 读者
        JPanel readernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label7 = new JLabel("读者姓名：");
        label7.setFont(font);
        readernamePanel.add(label7);
        readernamePanel.add(readername);

        JPanel readertypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label8= new JLabel("读者类型：");
        label8.setFont(font);
        readertypePanel.add(label8);
        readertypePanel.add(readertype);

        JPanel max_numPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label9 = new JLabel("最大可借数：");
        label9.setFont(font);
        max_numPanel.add(label9);
        max_numPanel.add(max_num);

        JPanel days_numPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label10 = new JLabel("最大可借天数：");
        label10.setFont(font);
        days_numPanel.add(label10);
        days_numPanel.add(days_num);

        // 借阅信息
        JPanel borrowCountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label11 = new JLabel("已借阅数量：");
        label11.setFont(font);
        borrowCountPanel.add(label11);
        borrowCountPanel.add(borrowCount);

        JPanel isSelectBorrowPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label12 = new JLabel("是否可借：");
        label12.setFont(font);
        isSelectBorrowPanel.add(label12);
        isSelectBorrowPanel.add(isSelectBorrow);

        JPanel borrowDatePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label13 = new JLabel("借阅日期：");
        label13.setFont(font);
        borrowDatePanel.add(label13);
        borrowDatePanel.add(borrowDate);

        setEditable();

        this.setSize(2400, 1300);  // 放大窗口尺寸
        this.setTitle("借阅图书");
        this.setLayout(new BorderLayout());

        Container c = this.getContentPane();

        JPanel jp1 = new JPanel(new GridLayout(6, 2));
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

        JPanel jp3 = new JPanel();
        jp3.add(borrowBtn);
        jp3.add(returnBtn);

        c.add(jp1, BorderLayout.CENTER);
        c.add("North", queryPanel);
        c.add("South", jp3);

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

    public String ifOkBorrow(int stockNum, int maxNum, int borrowCounted, String borrowFlag) {
//        stockNum = Integer.parseInt(stock.getText());
//        maxNum = Integer.parseInt(max_num.getText());
//        borrowCounted = Integer.parseInt(borrowCount.getText());
//        borrowFlag = isSelectBorrow.getText();
        String flag = "";
        if (stockNum <= 0) {
            flag = "库存量不足";
        }
        if (maxNum <= borrowCounted) {
            flag = "最大可借数超过已借阅数";
        }
        if (borrowFlag.equals("否")) {
            flag = "请确认是否已借过该书籍";
        }
        if (stockNum > 0 && maxNum > borrowCounted && borrowFlag.equals("是")) {
            flag = "借阅成功";
        }
        return flag;
    }

    public int addBorrowByIds(int bookId, int readerId, String borrowDate) {

        String sql = "INSERT INTO borrow(book_id,reader_id,`borrow-date`,if_back) VALUES" +
                "(" + "" + bookId + "," + "" + readerId + "," + "'" + borrowDate + "'" + ",'未归还')";
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
            if (countByReader > 0) {
                // 读者
                JOptionPane.showMessageDialog(null, "查询读者信息成功", "提示消息", JOptionPane.DEFAULT_OPTION);
                Reader reader = readerFun.findReaderById(Integer.parseInt(readerText.getText()));
                readername.setText(reader.getReadername());
                readertype.setText(reader.getReadtype());
                max_num.setText(String.valueOf(reader.getMax_num()));
                days_num.setText(String.valueOf(reader.getDays_num()));


                // 查询已借图书量
                int bookCountByBorrow = findBookCountByBorrow(Integer.parseInt(readerStr));
                borrowCount.setText(String.valueOf(bookCountByBorrow));

            } else if (countByReader <= 0) {
                JOptionPane.showMessageDialog(null, "没有该读者信息!", "提示消息", JOptionPane.ERROR_MESSAGE);
            }
            if (countByBook > 0) {
                // 图书
                JOptionPane.showMessageDialog(null, "查询图书信息成功", "提示消息", JOptionPane.DEFAULT_OPTION);
                Book book = bookFun.findBookById(Integer.valueOf(bookText.getText()));
                bookname.setText(book.getBookname());
                author.setText(book.getAuthor());
                publisher.setText(book.getPublisher());
                publish_time.setText(book.getPublish_time());
                price.setText(book.getPrice());
                stock.setText(String.valueOf(book.getStock()));
            } else if (countByBook <= 0) {
                JOptionPane.showMessageDialog(null, "没有该图书信息!", "提示消息", JOptionPane.ERROR_MESSAGE);
            }
            borrowDate.setText(sdf.format(new Date()));
            int readerNoBorrow = findReaderNoBorrow(Integer.parseInt(readerStr), Integer.parseInt(bookStr));
            if (readerNoBorrow > 0) {
                isSelectBorrow.setText("否");
            } else {
                isSelectBorrow.setText("是");
            }
        } else if (e.getSource() == findBtn && readerStr.equals("") && !bookStr.equals("")) {
            countByBook = bookFun.findBookCountById(Integer.parseInt(bookStr));
            if (countByBook > 0) {
                // 图书
                JOptionPane.showMessageDialog(null, "查询图书信息成功", "提示消息", JOptionPane.DEFAULT_OPTION);
                Book book = bookFun.findBookById(Integer.valueOf(bookText.getText()));
                bookname.setText(book.getBookname());
                author.setText(book.getAuthor());
                publisher.setText(book.getPublisher());
                publish_time.setText(book.getPublish_time());
                price.setText(book.getPrice());
                stock.setText(String.valueOf(book.getStock()));
            } else if (countByBook <= 0) {
                JOptionPane.showMessageDialog(null, "没有该图书信息!", "提示消息", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == findBtn && !readerStr.equals("") && bookStr.equals("")) {
            countByReader = readerFun.findReaderCountById(Integer.parseInt(readerStr));
            if (countByReader > 0) {
                // 读者
                JOptionPane.showMessageDialog(null, "查询读者信息成功", "提示消息", JOptionPane.DEFAULT_OPTION);
                Reader reader = readerFun.findReaderById(Integer.parseInt(readerText.getText()));
                readername.setText(reader.getReadername());
                readertype.setText(reader.getReadtype());
                max_num.setText(String.valueOf(reader.getMax_num()));
                days_num.setText(String.valueOf(reader.getDays_num()));

                // 查询已借图书量
                int bookCountByBorrow = findBookCountByBorrow(Integer.parseInt(readerStr));
                borrowCount.setText(String.valueOf(bookCountByBorrow));

            } else if (countByReader <= 0) {
                JOptionPane.showMessageDialog(null, "没有该读者信息!", "提示消息", JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == borrowBtn) {
            if (stock.getText().equals("") || max_num.getText().equals("") || borrowCount.getText().equals("") || isSelectBorrow.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "别乱按", "提示消息", JOptionPane.ERROR_MESSAGE);
            } else {
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


