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

public class ReaderUpdate extends JFrame implements ActionListener{

    JTextField query_id=new JTextField(7);
    JTextField reader_id=new JTextField(7);
    JTextField readername=new JTextField(7);
    JComboBox cbx;
    JTextField sex = new JTextField(7);
    JTextField max_num = new JTextField(7);
    JTextField days_num = new JTextField(7);
    JButton query = new JButton("查询");
    JButton save = new JButton("保存");
    JButton returnBtn = new JButton("返回");


    public ReaderUpdate() {

        JPanel panel01 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel01.add(new JLabel("读者编号:"));
        panel01.add(reader_id);

        JPanel panel02 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel02.add(new JLabel("读者姓名:"));
        panel02.add(readername);

        JPanel panel03 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        String data[] = {"学生","职教工","社会人士","其它"};
        cbx = new JComboBox(data);
        panel03.add(new JLabel("读者类型:"));
        panel03.add(cbx);

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
        this.setTitle("修改读者信息");
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
        jp3.add(save);
        jp3.add(returnBtn);

        c.add(jp1,BorderLayout.CENTER);
        c.add("North",jp2);
        c.add("South",jp3);

        save.addActionListener(this);
        returnBtn.addActionListener(this);
        query.addActionListener(this);

        reader_id.setEditable(false);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new ReaderUpdate();
    }

    public void reQuery() {
//        query_id.setText(null);
        reader_id.setText(null);
        readername.setText(null);
        sex.setText(null);
        max_num.setText(null);
        days_num.setText(null);
    }

    public int updateReader(int readerId) {
        readerId = Integer.parseInt(reader_id.getText());
        String readernameText = readername.getText();
        String readerType = cbx.getSelectedItem().toString().trim();
        String sexText = sex.getText().trim();
        String maxNumText = max_num.getText().trim();
        String daysNumText = days_num.getText().trim();
        String updateSQL = "UPDATE reader set " +
                "readername = '"+readernameText+"'," +
                "readtype = '"+readerType+"'," +
                "sex = '"+sexText+"'," +
                "max_num = ' ";
        StringBuilder sb = new StringBuilder(updateSQL);
        if (maxNumText.equals("")) {
            sb.append("1' ,");
        }else {
            sb.append(maxNumText + "',");
        }
        if (maxNumText.equals("")) {
            sb.append("days_num = '7' where id = " + readerId);
        }else {
            sb.append("days_num = '" + daysNumText + "' where id = " + readerId);
        }
        System.out.println(sb.toString());
        int flag = 0;
        try {
            flag  = DbOp.executeUpdate(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int count = 0;
        int flag = 0;
        if (e.getSource() == query && !query_id.getText().equals("")) {
            count = new ShareFunctionByReader().findReaderCountById(Integer.parseInt(query_id.getText()));
            if (count > 0) {
                JOptionPane.showMessageDialog(null,"查询成功","提示消息",JOptionPane.DEFAULT_OPTION);
                Reader reader = new ShareFunctionByReader().findReaderById(Integer.parseInt(query_id.getText()));
                reader_id.setText(String.valueOf(reader.getId()));
                readername.setText(reader.getReadername());
                cbx.setSelectedItem(reader.getReadtype());
                sex.setText(reader.getSex());
                max_num.setText(String.valueOf(reader.getMax_num()));
                days_num.setText(String.valueOf(reader.getDays_num()));
            }else {
                JOptionPane.showMessageDialog(null,"不存在该读者~","提示消息",JOptionPane.WARNING_MESSAGE);
                reQuery();
            }
        } else if (e.getSource() == query && query_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"请先输入读者编号~","提示消息",JOptionPane.WARNING_MESSAGE);
        }
        if (e.getSource() == save && !reader_id.getText().equals("")) {
            flag = updateReader(Integer.parseInt(reader_id.getText()));
            if (flag == 1) {
                JOptionPane.showMessageDialog(null,"修改读者信息成功","提示消息",JOptionPane.DEFAULT_OPTION);
            }else {
                JOptionPane.showMessageDialog(null,"修改读者信息失败","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == save && reader_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"请先确认是否已查询过该读者!","提示消息",JOptionPane.ERROR_MESSAGE);
        }
        if(e.getSource() == returnBtn) {
            dispose();
            new ShowMain();
        }
    }

}

