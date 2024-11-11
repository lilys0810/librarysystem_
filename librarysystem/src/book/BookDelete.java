package book;

import controller.ShowMain;
import pojo.Book;
import util.DbOp;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class BookDelete extends JFrame implements ActionListener{


    JTextField query_id=new JTextField(7);
    JTextField book_id=new JTextField(7);
    JTextField bookname=new JTextField(7);
    JComboBox cbx;
    JTextField author=new JTextField(7);
    JTextField translator=new JTextField(7);
    JTextField publisher=new JTextField(7);
    JTextField publish_time=new JTextField(7);
    JTextField price=new JTextField(7);
    JTextField stock=new JTextField(7);
    JButton query=new JButton("查询");
    JButton delete=new JButton("删除");
    JButton close=new JButton("返回");
    JButton clear=new JButton("重新查询");


    public BookDelete() {

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
        this.setTitle("删除图书");
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
        jp3.add(delete);
        jp3.add(clear);
        jp3.add(close);

        c.add(jp1,BorderLayout.CENTER);
        c.add("North",jp2);
        c.add("South",jp3);

        delete.addActionListener(this);
        close.addActionListener(this);
        query.addActionListener(this);
        clear.addActionListener(this);

        book_id.setEditable(false);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    private void clearText() {
        query_id.setText("");
        book_id.setText("");
        bookname.setText("");
        cbx.setSelectedIndex(0);
        author.setText("");
        translator.setText("");
        publisher.setText("");
        publish_time.setText("");
        price.setText("");
        stock.setText("");
    }

    public static void main(String[] args) {
        new BookDelete();
    }

    public int deleteBook(int bookId) {
//        Book book = new Book();
        bookId = Integer.valueOf(query_id.getText());
        String delSQL = "DELETE FROM book WHERE id = '" + bookId + "'";
        int flag = DbOp.executeUpdate(delSQL);
        if (flag == 1) {
            return 1;
        }else {
            return -1;
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        ShareFunctionByBook fun = new ShareFunctionByBook();
        int bookCountFlag = 0;
        // 查询
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
        // 删除
        if (e.getSource() == delete && !book_id.getText().equals("")) {
            int flag = deleteBook(Integer.parseInt(book_id.getText()));;
            if (flag == 1) {
                JOptionPane.showMessageDialog(null,"删除成功","提示消息",JOptionPane.DEFAULT_OPTION);
            }else {
                JOptionPane.showMessageDialog(null,"删除失败,请联系管理员","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        }else if (e.getSource() == delete && book_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"请先确认是否已对图书进行查询！","提示消息",JOptionPane.ERROR_MESSAGE);
        }
        if (e.getSource() == clear) {
            reQuery();
            JOptionPane.showMessageDialog(null,"重置成功","提示消息",JOptionPane.DEFAULT_OPTION);
        }
        if (e.getSource() == close) {
            dispose();
            new ShowMain();
        }
    }

}
