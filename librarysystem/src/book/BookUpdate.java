package book;

import controller.ShowMain;
import pojo.Book;
import util.DbOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookUpdate extends JFrame implements ActionListener {

    JTextField query_id = new JTextField(7);
    JTextField book_id = new JTextField(7);
    JTextField bookname = new JTextField(7);
    JComboBox cbx;
    JTextField author = new JTextField(7);
    JTextField translator = new JTextField(7);
    JTextField publisher = new JTextField(7);
    JTextField publish_time = new JTextField(7);
    JTextField price = new JTextField(7);
    JTextField stock = new JTextField(7);
    JButton query = new JButton("查询");
    JButton save = new JButton("保存");
    JButton returnBtn = new JButton("返回");
    JButton clear = new JButton("清空");
    Book book = new Book();

    public BookUpdate() {
        JPanel panel01 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel01.add(new JLabel("图书编号:"));
        panel01.add(book_id);

        JPanel panel02 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel02.add(new JLabel("图书名称:"));
        panel02.add(bookname);

        JPanel panel03 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String data[] = {"科技","文学","工学"};
        cbx = new JComboBox(data);
        panel03.add(new JLabel("图书类别:"));
        panel03.add(cbx);

        JPanel panel04 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel04.add(new JLabel("图书作者:"));
        panel04.add(author);

        JPanel panel05 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel05.add(new JLabel("译者:"));
        panel05.add(translator);

        JPanel panel06 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel06.add(new JLabel("出版社:"));
        panel06.add(publisher);

        JPanel panel07 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel07.add(new JLabel("出版时间:"));
        panel07.add(publish_time);

        JPanel panel08 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel08.add(new JLabel("定价:"));
        panel08.add(price);

        JPanel panel09 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel09.add(new JLabel("库存数量:"));
        panel09.add(stock);

        this.setSize(600,300);
        this.setTitle("修改图书");
        this.setLayout(new BorderLayout());
        Container c=this.getContentPane();

        JPanel jp2=new JPanel();
        jp2.add(new JLabel("图书编号"));
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

        jp3.add(save);
        jp3.add(clear);
        jp3.add(returnBtn);

        c.add(jp1, BorderLayout.CENTER);
        c.add("North",jp2);
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
