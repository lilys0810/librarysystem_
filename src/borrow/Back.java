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

public class Back extends JFrame implements ActionListener{

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

    // 还书信息
    JTextField backDate = new JTextField(20);

    JButton backBtn = new JButton("还书");
    JButton returnBtn = new JButton("返回");


    public Back() {

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
        returnBtn.setFont(f);
        backBtn.setFont(f);
        backDate.setFont(f);



        JPanel queryPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        queryPanel.add(bookLabel);
        queryPanel.add(bookText);
        queryPanel.add(readerLabel);
        queryPanel.add(readerText);
        queryPanel.add(findBtn);

        // 图书
        JPanel bookPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Font font = new Font("幼圆", Font.BOLD, 38);
        JLabel label2 = new JLabel("图书名称：");
        label2.setFont(font);
        bookPanel.add(label2);
        bookPanel.add(bookname);

        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label3 = new JLabel("作者：");
        label3.setFont(font);
        authorPanel.add(label3);
        authorPanel.add(author);

        JPanel publisherPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label4 = new JLabel("出版社：");
        label4.setFont(font);
        publisherPanel.add(label4);
        publisherPanel.add(publisher);

        JPanel publish_timePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label5 = new JLabel("出版时间：");
        label5.setFont(font);
        publish_timePanel.add(label5);
        publish_timePanel.add(publish_time);

        JPanel pricePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label6 = new JLabel("定价：");
        label6.setFont(font);
        pricePanel.add(label6);
        pricePanel.add(price);

        JPanel stockPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label7 = new JLabel("库存量：");
        label7.setFont(font);
        stockPanel.add(label7);
        stockPanel.add(stock);

        // 读者
        JPanel readernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label8 = new JLabel("读者姓名：");
        label8.setFont(font);
        readernamePanel.add(label8);
        readernamePanel.add(readername);

        JPanel readertypePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label9 = new JLabel("读者类型：");
        label9.setFont(font);
        readertypePanel.add(label9);
        readertypePanel.add(readertype);

        JPanel max_numPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label10 = new JLabel("最大可借数：");
        label10.setFont(font);
        max_numPanel.add(label10);
        max_numPanel.add(max_num);

        JPanel days_numPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label11 = new JLabel("最大可借天数：");
        label11.setFont(font);
        days_numPanel.add(label11);
        days_numPanel.add(days_num);

        initTable();
        setEditable();
        // 借阅信息
        JPanel borrowCountPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label12 = new JLabel("还书日期：");
        label12.setFont(font);
        borrowCountPanel.add(label12);
        borrowCountPanel.add(backDate);

        this.setSize(2400,1300);
        this.setTitle("还回图书");
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


        JPanel jp3=new JPanel();
        jp3.add(backBtn);
        jp3.add(returnBtn);

        c.add(jp1,BorderLayout.CENTER);
        c.add("North",queryPanel);
        c.add("South",jp3);



        findBtn.addActionListener(this);
        backBtn.addActionListener(this);
        returnBtn.addActionListener(this);


        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new Back();
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
        backDate.setText(null);
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
        backDate.setEditable(false);
    }

    // 判断该读者是否曾经借过此书且未归还
    public int isReaderBackBook(int readerId, int bookId) {
        String sql = "SELECT COUNT(*) AS 'count' FROM borrow br,book b,reader r " +
                "WHERE r.id = br.reader_id AND b.id = br.book_id AND br.if_back = '未归还' AND" +
                " r.id = "+readerId+" " +
                "AND" +
                " b.id = "+bookId+"";
        int count = 0;
        try {
            ResultSet resultSet = DbOp.executeQuery(sql);
            while (resultSet.next()) {
                count = resultSet.getInt("count");
            }
        } catch (Exception e) {

        }
        return count;
    }

    // 更新图书库存
    public int updateBookStock(int bookId) {
        String sql = "UPDATE book SET stock = stock + 1 WHERE id = " + bookId;
        int flag = 0;
        try {
            flag = DbOp.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    // 更新图书借阅表
    public int updateBorrow(int readerId, int bookId,String backDate) {
        String sql = "UPDATE borrow SET " +
                "back_date = '"+backDate+"'," +
                "if_back = '已归还' WHERE" +
                " book_id = "+bookId+" " +
                "AND" +
                " reader_id = "+readerId+"";
        int flag = 0;
        try {
            flag = DbOp.executeUpdate(sql);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
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
            backDate.setText(sdf.format(new Date()));
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
            } else if (countByReader <= 0 ) {
                JOptionPane.showMessageDialog(null,"没有该读者信息!","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        }
        // 归还
        if (e.getSource() == backBtn) {
            int flagByReaderBackBook = 0;
            int flagByUpdateBookStock = 0;
            int flagByUpdateBorrow = 0;
            if (!readerStr.equals("") && !bookStr.equals("")) {
                flagByReaderBackBook = isReaderBackBook(Integer.parseInt(readerStr), Integer.parseInt(bookStr));
                flagByUpdateBookStock = updateBookStock(Integer.parseInt(bookStr));
                flagByUpdateBorrow = updateBorrow(Integer.parseInt(readerStr), Integer.parseInt(bookStr),backDate.getText());
                System.out.println("flagByReaderBackBook:"+ flagByReaderBackBook);
                System.out.println("flagByUpdateBookStock:" + flagByUpdateBookStock);
                System.out.println("flagByUpdateBorrow:"+ flagByUpdateBorrow);
                if (flagByReaderBackBook > 0 && flagByUpdateBookStock == 1 && flagByUpdateBorrow > 0) {
                    JOptionPane.showMessageDialog(null,"归还成功!","提示消息",JOptionPane.DEFAULT_OPTION);
                    initTable();
                }else {
                    JOptionPane.showMessageDialog(null,"请确认该读者是否借阅过该书籍!","提示消息",JOptionPane.WARNING_MESSAGE);
                    initTable();
                }
            }
        }
        if (e.getSource() == returnBtn) {
            dispose();
            new ShowMain();
        }
    }
}