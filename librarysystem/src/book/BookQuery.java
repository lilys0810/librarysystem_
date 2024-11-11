package book;
import controller.ShowMain;
import util.DbOp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

public class BookQuery extends JFrame implements ActionListener{

    Vector rowData=new Vector();
    JTable table=new JTable() ;

    JLabel bookLabel = new JLabel(" 图书名称：");                 //标签
    JTextField bookText = new JTextField(15);            //文本框
    JLabel authorLabel = new JLabel(" 作者：");                 //标签
    JTextField authorText = new JTextField(15);            //文本框
    JLabel publishLabel = new JLabel(" 出版社：");                 //标签
    JTextField publishText = new JTextField(15);            //文本框
    JLabel publishTimeLabel = new JLabel(" 出版时间(年-月)：");                 //标签
    JTextField publishTimeText = new JTextField(15);            //文本框
    //创建表格模型
    DefaultTableModel tm=new DefaultTableModel(new String[0][0],
            new String[] {"图书编号","图书名称","图书类别","作者","译者","出版社","出版日期","定价","库存数量"});
    JButton findBtn = new JButton("查询");
    JButton returnBtn = new JButton("返回");

    public void TableData(String bookname,String author,String publish,String publish_time) {
        rowData = new Vector();
        StringBuilder sql = new StringBuilder("SELECT * FROM book where 1 = 1 ");
        try {
            if (!bookname.equals("")) {
               sql.append(" and bookname like '%"+bookText.getText() + "%'");
            }
            if (!author.equals("")) {
               sql.append("and author like '%" + authorText.getText() + "%'");
            }
            if (!publish.equals("")) {
                sql.append(" and publisher like '%" + publishText.getText() + "%'");
            }
            if (!publish_time.equals("")) {
                sql.append("and publish_time like '%" + publishTimeText.getText() + "%'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        try {
            ResultSet resultSet = DbOp.executeQuery(sql.toString());
            while (resultSet.next()) {
                tm.addRow(new String[] {
                        resultSet.getString(1).trim(),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9)
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.setModel(tm);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        for (Object data : rowData) {
            System.out.println(data);
        }
    }

    public BookQuery(){
        this.setTitle("查询图书信息");
        this.setSize(800,600);//设置窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置按下右上角X号后关闭
        Container container = this.getContentPane();
        JPanel panel01 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel01.add(bookLabel);
        panel01.add(bookText);
        panel01.add(authorLabel);
        panel01.add(authorText);

        JPanel panel02 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel02.add(publishLabel);
        panel02.add(publishText);
        panel02.add(publishTimeLabel);
        panel02.add(publishTimeText);

        JPanel panel03 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel03.add(findBtn);
        panel03.add(returnBtn);

        Box vBox = Box.createVerticalBox();
        vBox.add(panel01);
        vBox.add(panel02);
        vBox.add(panel03);
        vBox.add(container);

        this.setContentPane(vBox);

        findBtn.addActionListener(this);
        returnBtn.addActionListener(this);
        table.setModel(tm);
        JScrollPane jsp = new JScrollPane(table);
        container.add(jsp);

        this.setLocationRelativeTo(null);//窗口居中
        this.setVisible(true);//窗口可见
    }

    public static void main(String[] args) {
        new BookQuery();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findBtn) {
//            table.setModel(tm);
            tm.getDataVector().clear();//清空表格数据
            TableData(bookText.getText(),authorText.getText(),publishText.getText(),publishTimeText.getText());
            JOptionPane.showMessageDialog(null,"查询成功","提示消息",JOptionPane.DEFAULT_OPTION);
        }
        if (e.getSource() == returnBtn) {
            dispose();
            new ShowMain();
        }
    }

}