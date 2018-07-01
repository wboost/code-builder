package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

import util.Config;

/**
 * 创建数据库链接
 * @author qhluo
 * @since 2016-05-12
 */
public class DBConnection {

	public Connection getConn(DataSource db) {//0为没有链接，1为有连接
		Connection conn = null;
		try {
			if (db != null) {
				conn = db.getConn();
			}
			Class.forName(Config.DRIVER).newInstance();

			Properties props = new Properties();
			props.setProperty("user", Config.USERNAME);
			props.setProperty("password", Config.PASSWORD);

			if (conn == null) {
				// 	props.setProperty("useInformationSchema", "true");//设置可以获取tables remarks信息,但不能获得列信息
			} else {
				conn.close();
			}
			conn = DriverManager.getConnection(Config.URL, props);
			System.out.println(conn);
			if (db != null) {
				db.setConn(conn);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

}
