package controller;

import book.*;
import borrow.Back;
import borrow.Borrow;
import reader.ReaderAdd;
import reader.ReaderDelete;
import reader.ReaderQuery;
import reader.ReaderUpdate;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class ShowMain extends JFrame implements ActionListener {

    JMenuItem addBook = new JMenuItem("添加图书");
    JMenuItem delBook = new JMenuItem("删除图书");
    JMenuItem updateBook = new JMenuItem("更新图书");
    JMenuItem findBook = new JMenuItem("查找图书");
    JMenuItem bookList = new JMenuItem("图书列表");
    JMenuItem borrowBook = new JMenuItem("借阅图书");
    JMenuItem addReader = new JMenuItem("添加读者");
    JMenuItem deleteReader = new JMenuItem("删除读者");
    JMenuItem findReader = new JMenuItem("查询读者");
    JMenuItem updateReader = new JMenuItem("更改读者");
    JMenuItem backBook = new JMenuItem("还书");


    public ShowMain(){
        JMenuBar jmb=new JMenuBar();
        this.setJMenuBar(jmb);
        JMenu baseMenu=new JMenu("基础维护");
        JMenu borrowMenu=new JMenu("借阅管理");
        JMenu selectMenu=new JMenu("查询管理");
        JMenu systemMenu=new JMenu("系统管理");
        jmb.add(baseMenu);
        jmb.add(borrowMenu);
        jmb.add(selectMenu);
        jmb.add(systemMenu);

        baseMenu.add(addBook);
        baseMenu.add(delBook);
        baseMenu.add(updateBook);
        baseMenu.add(bookList);
        baseMenu.add(findBook);
        borrowMenu.add(borrowBook);
        borrowMenu.add(backBook);
        selectMenu.add(addReader);
        selectMenu.add(deleteReader);
        selectMenu.add(updateReader);
        selectMenu.add(findReader);
        baseMenu.addSeparator();

        addBook.addActionListener(this);
        delBook.addActionListener(this);
        bookList.addActionListener(this);
        findBook.addActionListener(this);
        updateBook.addActionListener(this);
        borrowBook.addActionListener(this);
        backBook.addActionListener(this);
        addReader.addActionListener(this);
        deleteReader.addActionListener(this);
        updateReader.addActionListener(this);
        findReader.addActionListener(this);

        addBook.addActionListener(this);

        this.setTitle("图书管理系统");
//        this.setSize(600, 400);
        setBounds(150, 150, 600, 400);
        this.setVisible(true);

    }

    public static void main(String[] args) {
        new ShowMain();
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addBook) {
            dispose();
            new BookAdd();
        } else if (e.getSource() == delBook) {
            dispose();
            new BookDelete();
        } else if (e.getSource() == updateBook) {
            dispose();
            new BookUpdate();
        } else if (e.getSource() == findBook) {
            dispose();
            new BookQuery();
        } else if (e.getSource() == bookList ){
            dispose();
            new FindAllBook();
        } else if (e.getSource() == borrowBook) {
            dispose();
            new Borrow();
        } else if (e.getSource() == backBook) {
            dispose();
            new Back();
        } else if (e.getSource() == addReader) {
            dispose();
            new ReaderAdd();
        }else if (e.getSource() == deleteReader) {
            dispose();
            new ReaderDelete();
        }else if (e.getSource() == updateReader) {
            dispose();
            new ReaderUpdate();
        }else if (e.getSource() == findReader) {
            dispose();
            new ReaderQuery();
        }
    }

}
