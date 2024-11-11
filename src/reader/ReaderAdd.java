package reader;

import controller.ShowMain;
import util.DbOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class ReaderAdd extends JFrame implements ActionListener {

    JTextField query_id=new JTextField(7);
    JTextField reader_id=new JTextField(7);
    JTextField readername=new JTextField(7);
    JComboBox cbx;
    JTextField sex = new JTextField(7);
    JTextField max_num = new JTextField(7);
    JTextField days_num = new JTextField(7);
    JButton query = new JButton("查询");
    JButton addBtn = new JButton("添加");
    JButton returnBtn = new JButton("返回");


    public ReaderAdd() {

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
        panel04.add(new JLabel("读者性别:"));
        panel04.add(sex);

        JPanel panel05 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel05.add(new JLabel("最大可借数:"));
        panel05.add(max_num);

        JPanel panel06 = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panel06.add(new JLabel("可借天数:"));
        panel06.add(days_num);



        this.setSize(460,280);
        this.setTitle("新增读者");
        this.setLayout(new BorderLayout());
        Container c=this.getContentPane();


        JPanel jp1=new JPanel(new GridLayout(4,2));
        jp1.add(panel01);
        jp1.add(panel02);
        jp1.add(panel03);
        jp1.add(panel04);
        jp1.add(panel05);
        jp1.add(panel06);


        JPanel jp3=new JPanel();
        jp3.add(addBtn);
        jp3.add(query);
        jp3.add(returnBtn);

        JPanel jp2=new JPanel();
        jp2.add(new JLabel(" "));

        c.add(jp1,BorderLayout.CENTER);
        c.add("North",jp2);
        c.add("South",jp3);

        addBtn.addActionListener(this);
        returnBtn.addActionListener(this);
        query.addActionListener(this);

        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        new ReaderAdd();
    }

    public int findBookId(int id) {
        id = Integer.parseInt(reader_id.getText());
        String sql = "SELECT COUNT(*) readerCount FROM reader WHERE id = '" + id + "'";
        int count = 0;
        try {
            ResultSet resultSet = DbOp.executeQuery(sql);
            while (resultSet.next()) {
                count = resultSet.getInt("readerCount");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(count);
        return count;
    }

    public int addReader() {
        String readerIdText = reader_id.getText();
        String readernameText = readername.getText();
        String sexText = sex.getText();
        String maxNumText = max_num.getText();
        String daysNumText = days_num.getText();
        String readerTypeText = cbx.getSelectedItem().toString();

        String insertSQL = "INSERT INTO reader VALUES(" +
                "'"+readerIdText+"'," +
                "'"+readernameText+"'," +
                "'"+readerTypeText+"'," +
                "'"+sexText+"'," ;
        StringBuilder sb = new StringBuilder(insertSQL);
        if (maxNumText.equals("")) {
            sb.append("NULL" + ",");
        }else {
            sb.append(maxNumText + ",");
        }
        if (daysNumText.equals("")) {
            sb.append("NULL" + ")");
        }else {
            sb.append(daysNumText + ")");
        }
        System.out.println(sb.toString());
        int flag = 0;
        try {
            flag = DbOp.executeUpdate(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(flag);
        return flag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == query && !reader_id.getText().equals("")) {
            int count = findBookId(Integer.parseInt(reader_id.getText()));
            if (count > 0) {
                JOptionPane.showMessageDialog(null,"已存在该读者编号~","提示消息",JOptionPane.ERROR_MESSAGE);
            }else {
                JOptionPane.showMessageDialog(null,"该读者编号可用","提示消息",JOptionPane.DEFAULT_OPTION);
            }
        } else if (e.getSource() == query && reader_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"请输入读者编号进行查询~","提示消息",JOptionPane.WARNING_MESSAGE);
        }
        int flag = 0;
        if (e.getSource() == addBtn && !reader_id.getText().equals("")) {
            int count = findBookId(Integer.parseInt(reader_id.getText()));
            if (count > 0) {
                JOptionPane.showMessageDialog(null,"已存在该读者编号~","提示消息",JOptionPane.ERROR_MESSAGE);
            }else {
                flag = addReader();
                if (flag == 1) {
                    JOptionPane.showMessageDialog(null,"添加读者成功","提示消息",JOptionPane.DEFAULT_OPTION);
                }else {
                    JOptionPane.showMessageDialog(null,"添加读者失败 请联系系统管理员","提示消息",JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == addBtn && reader_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null,"请确认是否已填写读者信息！","提示消息",JOptionPane.ERROR_MESSAGE);
        }
        if(e.getSource() == returnBtn) {
            dispose();
            new ShowMain();
        }
    }
}
