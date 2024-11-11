package reader;

import controller.ShowMain;
import util.DbOp;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.Vector;

public class ReaderQuery extends JFrame implements ActionListener{

    Vector rowData=new Vector();
    JTable table=new JTable() ;

    JLabel readerLabel = new JLabel(" 读者名称：");
    JTextField readerText = new JTextField(15);
    JLabel readerTypeLabel = new JLabel("读者类型：");
    JTextField readerTypeText = new JTextField(15);
    //创建表格模型
    DefaultTableModel tm=new DefaultTableModel(new String[0][0],
            new String[] {"读者编号","读者名称","读者类型","读者性别","最大可借数","可借天数"});
    JButton findBtn = new JButton("查询");
    JButton returnBtn = new JButton("返回");



    public ReaderQuery(){
        this.setTitle("查询图书信息");
        this.setSize(800,600);//设置窗口大小
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置按下右上角X号后关闭
        Container container = this.getContentPane();
        JPanel panel01 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel01.add(readerLabel);
        panel01.add(readerText);
        panel01.add(readerTypeLabel);
        panel01.add(readerTypeText);

        JPanel panel03 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel03.add(findBtn);
        panel03.add(returnBtn);

        Box vBox = Box.createVerticalBox();
        vBox.add(panel01);
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
        new ReaderQuery();
    }
    public void findReaderByCondition(String readername,String readertype) {
        rowData = new Vector();
        StringBuilder sql = new StringBuilder("SELECT * FROM reader where 1 = 1");
        try {
            if (!readername.equals("")) {
                sql.append(" and readername like '%" + readerText.getText()+ "%' ");
            }
            if (!readertype.equals("")) {
               sql.append("and readtype like '%"+readerTypeText.getText()+ "%'");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(sql);
        try {
            ResultSet resultSet = DbOp.executeQuery(sql.toString());
            while (resultSet.next()) {
                tm.addRow(new String[] {
                        String.valueOf(resultSet.getInt(1)),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        String.valueOf(resultSet.getInt(5)),
                        String.valueOf(resultSet.getInt(6))
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        table.setModel(tm);
        for (Object data : rowData) {
            System.out.println(data);
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == findBtn) {
            tm.getDataVector().clear();//清空表格数据
            findReaderByCondition(readerText.getText(),readerTypeText.getText());
            JOptionPane.showMessageDialog(null,"查询成功","提示消息",JOptionPane.DEFAULT_OPTION);
        }
        if (e.getSource() == returnBtn) {
            dispose();
            new ShowMain();
        }
    }

}