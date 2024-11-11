package book;
import controller.ShowMain;
import util.DbOp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;

public class BookAdd extends JFrame implements ActionListener {

    public static void main(String[] args) {
        new BookAdd();
    }

    JTextField book_id = new JTextField(7);
    JTextField bookname = new JTextField(7);
    JComboBox cbx;
    JList list;


    JTextField author = new JTextField(7);
    JTextField translator = new JTextField(7);
    JTextField publisher = new JTextField(7);
    JTextField publish_time = new JTextField(7);
    JTextField price = new JTextField(7);
    JTextField stock = new JTextField(7);
    JButton save = new JButton("保存");
    JButton returnBtn = new JButton("返回");


    public BookAdd() {

        this.setSize(600, 300);
        this.setTitle("添加图书");
        this.setLayout(new BorderLayout());
        Container c = this.getContentPane();

        JPanel jp = new JPanel();
        jp.add(new JLabel("图书编号"));
        jp.add(book_id);
        jp.add(new JLabel("图书名称"));
        jp.add(bookname);

        String data[] = {"科技", "文学","工学"};
        cbx = new JComboBox(data);
        jp.add(new JLabel("图书类别"));
        jp.add(cbx);
        jp.add(new JLabel("图书作者"));
        jp.add(author);
        jp.add(new JLabel("译者"));
        jp.add(translator);
        jp.add(new JLabel("出版社"));
        jp.add(publisher);
        jp.add(new JLabel("出版时间"));
        jp.add(publish_time);
        jp.add(new JLabel("定价"));
        jp.add(price);
        jp.add(new JLabel("库存数量"));
        jp.add(stock);
        jp.add(save);
        jp.add(returnBtn);
        save.addActionListener(this);
        returnBtn.addActionListener(this);
        c.add(jp);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public ArrayList<String > selectColumn() {
        ArrayList<String> columnList = new ArrayList<>();
        String sql = "SELECT COLUMNS_NAME FROM information_schema.`COLUMNS` WHERE TABLE_NAME = 'BOOK'";
        ResultSet resultSet = DbOp.executeQuery(sql);
        try {
            while (resultSet.next()) {
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();
                for (int i = 0; i < columnCount; i++) {
                    columnList.add(metaData.getColumnName(i+1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return columnList;
    }
    public int columnCount() {
        String sql = "SELECT COUNT(*) FROM information_schema.`COLUMNS` WHERE TABLE_NAME = 'BOOK'";
        ResultSet resultSet = DbOp.executeQuery(sql);
        try {
            while (resultSet.next()) {

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 1;
    }


    public int addBook() {
        String book_idText = book_id.getText();
        String booknameText = bookname.getText();
        String booktype = cbx.getSelectedItem().toString().trim();
        String authorText = author.getText();
        String translatorText = translator.getText();
        String publisherText = publisher.getText();
        String publish_timeText = publish_time.getText();
        String priceText = price.getText().trim();
        String stockText = stock.getText().trim();
        String addsql = null;
        if (priceText.equals("") || priceText.equals("")) {
            addsql = "insert into book" +
                    "(id,bookname,booktype,author,translator,publisher,publish_time,price,stock) values " +
                    "('"+book_idText+"','"+booknameText+"','" + booktype +"','" + authorText + "','" + translatorText + "','" +
                    publisherText + "','" + publish_timeText + "'," + null + "," + null + ")";
        } else {
            addsql = "insert into book" +
                    "(id,bookname,booktype,author,translator,publisher,publish_time,price,stock) values " +
                    "('"+book_idText+"','"+booknameText+"','" + booktype +"','" + authorText + "','" + translatorText + "','" +
                    publisherText + "','" + publish_timeText + "','" + priceText + "','" + stockText + "')";
        }

        System.out.println(addsql);
        return DbOp.executeUpdate(addsql);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == save) {
            int count = addBook();
            if (count == 1) {
                JOptionPane.showMessageDialog(null,"添加图书成功","提示消息",JOptionPane.DEFAULT_OPTION);
                dispose();
                new FindAllBook();
            } else {
                JOptionPane.showMessageDialog(null,"请填写完整的图书信息~","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        }
        if (e.getSource() == returnBtn) {
            dispose();
            new ShowMain();
        }
    }
}