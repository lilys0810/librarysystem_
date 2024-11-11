// DbOp.java
package util;


import java.sql.*;

import javax.swing.JOptionPane;

public class DbOp {
	// JDBC-ODBC驱动程序
	private static String driver = "com.mysql.cj.jdbc.Driver";

	// 数据库url路径
	private static String url = "jdbc:mysql://localhost:3306/librarysystem?serverTimezone=UTC";
	private static String user="root";
	private static String passwd="123456";
	private static Connection con = null;
	private static PreparedStatement ps;

	// 构造方法。如果数据库未打开，则通过创建连接打开数据库
	private DbOp() {
		try {
			// 如果当前未创建连接，则加载JDBC驱动程序，然后创建连接
			if (con == null) {
				Class.forName(driver);
				System.out.println("driver success");
//				log.debug("driver success");
				con = DriverManager.getConnection(url,user,passwd);
				System.out.println("conn success");
			}
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "数据库未能打开！");
		}
	}

	public void login() {
		String loginsql = "";
		DbOp.executeQuery("");
	}

	// 执行数据库查询工作。如果出现异常，返回null
	public static ResultSet executeQuery(String sql) {
		try {
			// 如果未创建数据库连接，则创建连接
			if (con == null)
				new DbOp();
			// 返回查询结果
			return con.createStatement().executeQuery(sql);
		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "数据库不存在，或存在错误！");
			return null;
		}
	}

	// 执行数据库更新操作。如果有问题，则返回-1
	public static int executeUpdate(String sql) {
		try {
			// 如果未创建数据库连接，则创建连接
			if (con == null)
				new DbOp();
			// 返回操作结果
			return con.createStatement().executeUpdate(sql);
		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null, "数据有误，记录无法正常保存或更新！");
			return -1;
		}
	}

	// 关闭数据库
	public static void Close() {
		try {
			// 如果数据库已打开，则关闭之
			if (con != null)
				con.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "数据库未打开！");
		}
	}
}