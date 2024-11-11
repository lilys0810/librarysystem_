package reader;
import controller.ShowMain;
import pojo.Reader;
import reader.ShareFunctionByReader;
import util.DbOp;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class ReaderDelete extends JFrame implements ActionListener{


    JTextField query_id=new JTextField(7);
    JTextField reader_id=new JTextField(7);
    JTextField readername=new JTextField(7);
    JTextField readerType=new JTextField(7);
    JTextField sex=new JTextField(7);
    JTextField max_num=new JTextField(7);
    JTextField days_num=new JTextField(7);
    JButton query=new JButton("查询");
    JButton delete=new JButton("删除");
    JButton close=new JButton("返回");


    public ReaderDelete() {

        JPanel panel01 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel01.add(new JLabel("读者编号:"));
        panel01.add(reader_id);

        JPanel panel02 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel02.add(new JLabel("读者姓名:"));
        panel02.add(readername);

        JPanel panel03 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel03.add(new JLabel("读者类型:"));
        panel03.add(readerType);

        JPanel panel04 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel04.add(new JLabel("图书性别:"));
        panel04.add(sex);

        JPanel panel05 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel05.add(new JLabel("最大可借数:"));
        panel05.add(max_num);

        JPanel panel06 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel06.add(new JLabel("可借天数:"));
        panel06.add(days_num);



        this.setSize(460,280);
        this.setTitle("删除读者");
        this.setLayout(new BorderLayout());
        Container c=this.getContentPane();

        JPanel jp2=new JPanel();
        jp2.add(new JLabel("读者编号"));
        jp2.add(query_id);
        jp2.add(query);


        JPanel jp1=new JPanel(new GridLayout(4,2));
        jp1.add(panel01);
        jp1.add(panel02);
        jp1.add(panel03);
        jp1.add(panel04);
        jp1.add(panel05);
        jp1.add(panel06);


        JPanel jp3=new JPanel();
        jp3.add(delete);
        jp3.add(close);

        c.add(jp1,BorderLayout.CENTER);
        c.add("North",jp2);
        c.add("South",jp3);

        delete.addActionListener(this);
        close.addActionListener(this);
        query.addActionListener(this);

        reader_id.setEditable(false);
        readername.setEditable(false);
        readerType.setEditable(false);
        sex.setEditable(false);
        max_num.setEditable(false);
        days_num.setEditable(false);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new ReaderDelete();
    }
    public void reQuery() {
//        query_id.setText(null);
        reader_id.setText(null);
        readername.setText(null);
        readerType.setText(null);
        sex.setText(null);
        max_num.setText(null);
        days_num.setText(null);
    }

    public int deleteReaderById(int id) {
        id = Integer.parseInt(reader_id.getText());
        String deleteSQL = "DELETE FROM reader WHERE id = '" + id +"'";
        int flag = 0;
        try {
            flag = DbOp.executeUpdate(deleteSQL);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("delete:"+flag);
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int count = 0;
        if (e.getSource() == query && !query_id.getText().equals("")) {
            count = new ShareFunctionByReader().findReaderCountById(Integer.parseInt(query_id.getText()));
            if (count > 0) {
                JOptionPane.showMessageDialog(null,"查询成功","提示消息",JOptionPane.DEFAULT_OPTION);
                Reader reader = new ShareFunctionByReader().findReaderById(Integer.parseInt(query_id.getText()));
                reader_id.setText(String.valueOf(reader.getId()));
                readername.setText(reader.getReadername());
                readerType.setText(reader.getReadtype());
                sex.setText(reader.getSex());
                max_num.setText(String.valueOf(reader.getMax_num()));
                days_num.setText(String.valueOf(reader.getDays_num()));
            }else {
                JOptionPane.showMessageDialog(null,"不存在该读者~","提示消息",JOptionPane.WARNING_MESSAGE);
                reQuery();
            }
        } else if (e.getSource() == query && query_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"请先输入读者编号！","提示消息",JOptionPane.WARNING_MESSAGE);
        }
        if (e.getSource() == delete && !reader_id.getText().equals("")) {
            int flag = 0;
            flag = deleteReaderById(Integer.parseInt(reader_id.getText()));
            if (flag == 1) {
                JOptionPane.showMessageDialog(null,"删除读者成功","提示消息",JOptionPane.DEFAULT_OPTION);
                reQuery();
            }else {
                JOptionPane.showMessageDialog(null,"删除读者失败","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == delete && reader_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"请先查询读者！","提示消息",JOptionPane.WARNING_MESSAGE);
        }
        if(e.getSource() == close) {
            dispose();
            new ShowMain();
        }
    }

}

