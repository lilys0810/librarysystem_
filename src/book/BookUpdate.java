package book;

import controller.ShowMain;
import pojo.Book;
import util.DbOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookUpdate extends JFrame implements ActionListener {

    JTextField query_id = new JTextField(20);
    JTextField book_id = new JTextField(20);
    JTextField bookname = new JTextField(20);
    JComboBox cbx;
    JTextField author = new JTextField(20);
    JTextField translator = new JTextField(20);
    JTextField publisher = new JTextField(20);
    JTextField publish_time = new JTextField(20);
    JTextField price = new JTextField(20);
    JTextField stock = new JTextField(20);
    JButton query = new JButton("查询");
    JButton save = new JButton("保存");
    JButton returnBtn = new JButton("返回");
    JButton clear = new JButton("清空");
    Book book = new Book();

    public BookUpdate() {

        //设置标签及文本字体样式以及大小
        Font fo = new Font("宋体", Font.BOLD, 40);
        query_id.setFont(fo);
        book_id.setFont(fo);
        bookname.setFont(fo);
        author.setFont(fo);
        translator.setFont(fo);
        publisher.setFont(fo);
        publish_time.setFont(fo);
        price.setFont(fo);
        stock.setFont(fo);
        query.setFont(fo);
        query.setFont(fo);


        Font f1 = new Font("宋体", Font.BOLD, 50);
        save.setFont(f1);
        clear.setFont(f1);
        returnBtn.setFont(f1);

        Font f = new Font("幼圆", Font.BOLD, 40);

        JPanel panel01 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel l1 = new JLabel("图书编号：");
        l1.setFont(f);
        panel01.add(l1);
        panel01.add(book_id);

        JPanel panel02 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel l2 = new JLabel("图书名称：");
        l2.setFont(f);
        panel02.add(l2);
        panel02.add(bookname);

        JPanel panel03 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String data[] = {"科技","文学","工学"};
        cbx = new JComboBox(data);
        cbx.setFont(f);
        JLabel l3 = new JLabel("图书类别：");
        l3.setFont(f);
        panel03.add(l3);
        panel03.add(cbx);

        JPanel panel04 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel l4 = new JLabel("图书作者：");
        l4.setFont(f);
        panel04.add(l4);
        panel04.add(author);

        JPanel panel05 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel l5 = new JLabel("译者：");
        l5.setFont(f);
        panel05.add(l5);
        panel05.add(translator);

        JPanel panel06 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel l6 = new JLabel("出版社：");
        l6.setFont(f);
        panel06.add(l6);
        panel06.add(publisher);

        JPanel panel07 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel l7 = new JLabel("出版时间：");
        l7.setFont(f);
        panel07.add(l7);
        panel07.add(publish_time);

        JPanel panel08 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel l8 = new JLabel("定价：");
        l8.setFont(f);
        panel08.add(l8);
        panel08.add(price);

        JPanel panel09 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel l9 = new JLabel("库存数量：");
        l9.setFont(f);
        panel09.add(l9);
        panel09.add(stock);

        this.setSize(2400,1300);
        this.setTitle("修改图书");
        this.setLayout(new BorderLayout());
        Container c=this.getContentPane();

        JPanel jp2=new JPanel();
        JLabel l0 = new JLabel("图书编号：");
        l0.setFont(f);
        panel01.add(l0);
        jp2.add(l0);

        jp2.add(query_id);
        jp2.add(query);


        JPanel jp1=new JPanel(new GridLayout(5,2));
        jp1.add(panel01);
        jp1.add(panel02);
        jp1.add(panel03);
        jp1.add(panel04);
        jp1.add(panel05);
        jp1.add(panel06);
        jp1.add(panel07);
        jp1.add(panel08);
        jp1.add(panel09);


        JPanel jp3=new JPanel();
        FlowLayout layout2 = new FlowLayout(FlowLayout.CENTER, 160, 80);
        jp3.setLayout(layout2);
        jp3.add(save);
        jp3.add(clear);
        jp3.add(returnBtn);

        c.add(jp1, BorderLayout.CENTER);

        c.add("North",jp2);
        FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 30, 60);
        jp2.setLayout(layout1);
        c.add("South",jp3);


        save.addActionListener(this);
        returnBtn.addActionListener(this);
        query.addActionListener(this);
        clear.addActionListener(this);


        book_id.setEditable(false);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new BookUpdate();
    }
    public void reQuery() {
        query_id.setText(null);
        book_id.setText(null);
        bookname.setText(null);
        author.setText(null);
        translator.setText(null);
        publisher.setText(null);
        publish_time.setText(null);
        price.setText(null);
        stock.setText(null);
    }

    public int updateBook(int bookId) {
        bookId = Integer.parseInt(book_id.getText());
        String booknameText = bookname.getText();
        String booktype = cbx.getSelectedItem().toString().trim();
        String authorText = author.getText();
        String translatorText = translator.getText();
        String publisherText = publisher.getText();
        String publish_timeText = publish_time.getText();
        String priceText = price.getText().trim();
        String stockText = stock.getText().trim();
        String updateSQL = "";
        if (!stockText.equals("")){
            updateSQL = "UPDATE book SET bookname = '" + booknameText + "'," +
                    "booktype = '" + booktype + "'," +
                    "author = '"+ authorText +"'," +
                    "translator = '" + translatorText + "'," +
                    "publisher = '" + publisherText + "'," +
                    "publish_time = '" + publish_timeText + "'," +
                    "price = '" + priceText + "'," +
                    "stock ='" + stockText + "'" +
                    "WHERE id = '" + bookId + "'";
        }else {
            updateSQL = "UPDATE book SET bookname = '" + booknameText + "'," +
                    "booktype = '" + booktype + "'," +
                    "author = '"+ authorText +"'," +
                    "translator = '" + translatorText + "'," +
                    "publisher = '" + publisherText + "'," +
                    "publish_time = '" + publish_timeText + "'," +
                    "price = '" + priceText + "'," +
                    "stock = null " +
                    "WHERE id = '" + bookId + "'";
        }
        System.out.println(updateSQL);
        return DbOp.executeUpdate(updateSQL);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ShareFunctionByBook fun = new ShareFunctionByBook();
        int bookCountFlag = 0;
        if (e.getSource() == query && !query_id.getText().equals("")) {
            bookCountFlag = fun.findBookCountById(Integer.parseInt(query_id.getText()));
            if (bookCountFlag > 0) {
                Book book = fun.findBookById(Integer.parseInt(query_id.getText()));
                book_id.setText(String.valueOf(book.getId()));
                bookname.setText(book.getBookname());
                author.setText(book.getAuthor());
                translator.setText(book.getTranslator());
                publisher.setText(book.getPublisher());
                publish_time.setText(book.getPublish_time());
                price.setText(book.getPrice());
                stock.setText(String.valueOf(book.getStock()));
                JOptionPane.showMessageDialog(null,"查询成功","提示消息",JOptionPane.DEFAULT_OPTION);
            } else {
                JOptionPane.showMessageDialog(null,"没有该图书信息！","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == query && query_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"请先输入图书编号!","提示消息",JOptionPane.ERROR_MESSAGE);
        }
        if (e.getSource() == save && !book_id.getText().equals("")) {
            int flag = updateBook(Integer.valueOf(book_id.getText()));
            System.out.println("flag:"+flag);
            if (flag == 1) {
                JOptionPane.showMessageDialog(null,"修改图书成功","提示消息",JOptionPane.DEFAULT_OPTION);
            }else {
                JOptionPane.showMessageDialog(null,"修改失败","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == save && book_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"请先确认是否已对图书进行查询并修改！","提示消息",JOptionPane.ERROR_MESSAGE);
        }
        if (e.getSource() == clear) {
            reQuery();
        }
        if (e.getSource() == returnBtn) {
            dispose();
            new ShowMain();
        }
    }
}
