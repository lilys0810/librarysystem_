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
        try {
            UIManager.put("ComboBox.font", new java.awt.Font("宋体", Font.BOLD, 40));
        } catch (Exception e) {
            e.printStackTrace();
        }

        new BookAdd();
    }

    JTextField book_id = new JTextField(20);
    JTextField bookname = new JTextField(20);
    JComboBox cbx;
    JList list;


    JTextField author = new JTextField(20);
    JTextField translator = new JTextField(20);
    JTextField publisher = new JTextField(20);
    JTextField publish_time = new JTextField(20);
    JTextField price = new JTextField(20);
    JTextField stock = new JTextField(20);
    JButton save = new JButton("保存");
    JButton returnBtn = new JButton("返回");


    public BookAdd() {


        // 设置字体并放大
        Font f = new Font("宋体", Font.BOLD, 40);

        book_id.setFont(f);
        bookname.setFont(f);

        translator.setFont(f);
        author.setFont(f);
        publisher.setFont(f);
        publish_time.setFont(f);
        price.setFont(f);
        stock.setFont(f);

        save.setFont(f);//保存按钮
        returnBtn.setFont(f);//返回按钮

        this.setSize(2400, 1300);
        this.setTitle("添加图书");
        this.setLayout(new BorderLayout());


        Font font = new Font("幼圆", Font.BOLD, 40);

        JPanel jp1 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label1 = new JLabel("图书编号：");
        label1.setFont(font);
        jp1.add(label1);
        jp1.add(book_id);

        JPanel jp2 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label2 = new JLabel("图书名称：");
        label2.setFont(font);
        jp2.add(label2);
        jp2.add(bookname);


        JPanel jp3 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String data[] = {"科技", "文学","工学"};
        cbx = new JComboBox(data);
        cbx.setFont(font);
        JLabel label0 = new JLabel("图书类别：");
        label0.setFont(font);
        jp3.add(label0);
        jp3.add(cbx);

        JPanel jp4 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label3 = new JLabel("图书作者：");
        label3.setFont(font);
        jp4.add(label3);
        jp4.add(author);

        JPanel jp5 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label4 = new JLabel("译者：");
        label4.setFont(font);
        jp5.add(label4);
        jp5.add(translator);

        JPanel jp6 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label5 = new JLabel("出版社：");
        label5.setFont(font);
        jp6.add(label5);
        jp6.add(publisher);

        JPanel jp7 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label6 = new JLabel("出版时间：");
        label6.setFont(font);
        jp7.add(label6);
        jp7.add(publish_time);

        JPanel jp8 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label7 = new JLabel("定价：");
        label7.setFont(font);
        jp8.add(label7);
        jp8.add(price);

        JPanel jp9 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel label8 = new JLabel("库存数量：");
        label8.setFont(font);
        jp9.add(label8);
        jp9.add(stock);

        Container c = this.getContentPane();

        JPanel jp0 = new JPanel(new GridLayout(5, 2));
        jp0.add(jp1);
        jp0.add(jp2);
        jp0.add(jp3);
        jp0.add(jp4);
        jp0.add(jp5);
        jp0.add(jp6);
        jp0.add(jp7);
        jp0.add(jp8);
        jp0.add(jp9);



        JPanel jp = new JPanel();
        FlowLayout layout = new FlowLayout(FlowLayout.CENTER, 40, 80);
        jp.setLayout(layout);
        jp.add(save);
        jp.add(returnBtn);



        c.add(jp0,BorderLayout.CENTER);

        c.add("South",jp);


        save.addActionListener(this);
        returnBtn.addActionListener(this);


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