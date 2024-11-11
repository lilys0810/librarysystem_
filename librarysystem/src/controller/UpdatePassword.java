package controller;

import util.DbOp;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class UpdatePassword extends JFrame implements ActionListener{

    JTextField username = new JTextField(10);
    JTextField oldPassword = new JTextField(10);
    JTextField newPassword = new JTextField(10);
    JTextField confirmPassword = new JTextField(10);
    JButton updateBtn = new JButton("修改");

    public static void main(String[] args) {
        new UpdatePassword();
    }

    public UpdatePassword() {
        setTitle("修改密码");
        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel usernamePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        usernamePanel.add(new JLabel("用户名："));
        usernamePanel.add(username);
        JPanel oldPasswordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        oldPasswordPanel.add(new JLabel("原密码："));
        oldPasswordPanel.add(oldPassword);

        JPanel passwordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        passwordPanel.add(new JLabel("新密码"));
        passwordPanel.add(newPassword);

        JPanel confirmPasswordPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmPasswordPanel.add(new JLabel("确认密码："));
        confirmPasswordPanel.add(confirmPassword);

        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnPanel.add(updateBtn);

        Box box = Box.createVerticalBox();
        box.add(usernamePanel);
        box.add(oldPasswordPanel);
        box.add(passwordPanel);
        box.add(confirmPasswordPanel);
        box.add(btnPanel);

        setContentPane(box);

        //为事件源（组件）注册监听
        updateBtn.addActionListener(this);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }


    // 查询是否存在该用户
    public int isExitUser(String username,String password) {
        String isExitUserSQL = "SELECT count(id) AS flag FROM `user` WHERE " +
                "username = '"+username+"'" +
                " AND " +
                "`password` = '"+password+"'";
        System.out.println(isExitUserSQL);
        ResultSet resultSet = DbOp.executeQuery(isExitUserSQL);
        int flag = 0;
        try {
            while (resultSet.next()) {
                flag = resultSet.getInt("flag");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return flag;
    }

    // 判断 新密码与确认密码框内容
    public boolean judgePassword() {
        if (newPassword.getText().equals(confirmPassword.getText())) {
            return true;
        } else {
            return false;
        }
    }

    // 根据用户名 修改密码
    public int updateUser(String username,String password) {
        String updateSQL = "UPDATE user SET " +
                "`password` = '"+password+"'" +
                " WHERE " +
                "`username` = '"+username+"'";
        System.out.println(updateSQL);
        int updateUserFlag = 0;
        try {
            updateUserFlag = DbOp.executeUpdate(updateSQL);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return updateUserFlag;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String inputUsername = username.getText();
        String inputOldPassword = oldPassword.getText();
        String inputNewPassword = newPassword.getText();
        String inputConfirmPawword = confirmPassword.getText();
        int isExitUserFlag = 0;
        int isUpdateUserFlag = 0;
        if (e.getSource() == updateBtn && !inputUsername.equals("") && !inputNewPassword.equals("") && !inputConfirmPawword.equals("") && !inputOldPassword.equals("")) {
            isExitUserFlag = isExitUser(inputUsername,inputOldPassword);
            if (isExitUserFlag > 0 && judgePassword()) {
                isUpdateUserFlag = updateUser(inputUsername,inputNewPassword);
                if (isUpdateUserFlag == 1) {
                    JOptionPane.showMessageDialog(null,"修改成功！","提示消息",JOptionPane.DEFAULT_OPTION);
                    dispose();
                    new Login();
                } else {
                    JOptionPane.showMessageDialog(null,"修改失败！","提示消息",JOptionPane.ERROR_MESSAGE);
                }
            } else if (isExitUserFlag <= 0) {
                JOptionPane.showMessageDialog(null,"不存在该用户！","提示消息",JOptionPane.ERROR_MESSAGE);
            } else if (judgePassword() == false) {
                JOptionPane.showMessageDialog(null,"密码与确认密码不一致!","提示消息",JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null,"请填写完整信息！","提示消息",JOptionPane.ERROR_MESSAGE);
        }
    }
}
