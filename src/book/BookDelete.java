package book;

import controller.ShowMain;
import pojo.Book;
import util.DbOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BookDelete extends JFrame implements ActionListener{


    //文本框
    JTextField query_id=new JTextField(20);
    JTextField book_id=new JTextField(20);
    JTextField bookname=new JTextField(20);
    JComboBox cbx;
    JTextField author=new JTextField(20);
    JTextField translator=new JTextField(20);
    JTextField publisher=new JTextField(20);
    JTextField publish_time=new JTextField(20);
    JTextField price=new JTextField(20);
    JTextField stock=new JTextField(20);

    //按钮
    JButton query=new JButton("查询");
    JButton delete=new JButton("删除");
    JButton close=new JButton("返回");
    JButton clear=new JButton("重新查询");

    public BookDelete() {

// 设置字体并加粗
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

        Font fo1 = new Font("宋体", Font.BOLD, 50);

        delete.setFont(fo1);
        close.setFont(fo1);
        clear.setFont(fo1);



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
        cbx.setFont(fo);
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
        this.setTitle("删除图书");
        this.setLayout(new BorderLayout());
        Container c=this.getContentPane();

        JPanel jp2=new JPanel();
        JLabel l0 = new JLabel("图书编号：");
        l0.setFont(f);
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
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 160, 80);
        jp3.setLayout(layout);
        jp3.add(delete);
        jp3.add(clear);
        jp3.add(close);

        c.add(jp1,BorderLayout.CENTER);

        FlowLayout layout1 = new FlowLayout(FlowLayout.CENTER, 40, 80);
        jp2.setLayout(layout1);
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
