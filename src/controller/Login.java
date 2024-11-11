package controller;
import util.DbOp;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Login extends JFrame implements ActionListener{

    JTextField name = new JTextField(10);
    JTextField psw = new JTextField(10);
    JButton loginBtn = new JButton("登录");
    JButton updateBtn = new JButton("修改密码");
    private String usernmae;
    private String password;

    public static void main(String[] args) {
        new Login();
    }

    public Login() {
        setTitle("登录页面");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePanel.add(new JLabel("用户名"));
        usernamePanel.add(name);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passwordPanel.add(new JLabel("密码"));
        passwordPanel.add(psw);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(loginBtn);
        btnPanel.add(updateBtn);

        Box box = Box.createVerticalBox();
        box.add(usernamePanel);
        box.add(passwordPanel);
        box.add(btnPanel);

        setContentPane(box);

        //为事件源（组件）注册监听
        loginBtn.addActionListener(this);
        updateBtn.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    public void login(String username,String password) {
        String loginsql = "select * from user where username = '" + username + "' and password = '" + password +"'";
        System.out.println(loginsql);
        ResultSet resultSet = DbOp.executeQuery(loginsql);
        System.out.println(resultSet);
        try {
            while (resultSet.next()) {
                this.usernmae = resultSet.getString("username");
                this.password = resultSet.getString("password");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginBtn && !name.getText().equals("") && !psw.getText().equals("")) {
            login(name.getText(), psw.getText());
            if (name.getText().equals(usernmae) && psw.getText().equals(password)) {
                JOptionPane.showMessageDialog(null,"登录成功","提示消息",JOptionPane.DEFAULT_OPTION);
                dispose();
                new ShowMain();
            } else {
                JOptionPane.showMessageDialog(null,"用户名或密码错误","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == loginBtn && (name.getText().equals("") || psw.getText().equals(""))){
            JOptionPane.showMessageDialog(null,"请输入用户名或密码","提示消息",JOptionPane.ERROR_MESSAGE);
        }
        if (e.getSource() == updateBtn) {
            dispose();
            new UpdatePassword();
        }
    }
}
