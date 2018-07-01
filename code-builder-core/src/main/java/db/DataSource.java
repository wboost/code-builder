package db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import vo.Table;

public abstract class DataSource {

	protected String[] types = new String[] { "TABLE" };
	protected Connection conn = null;

	/**
	 * 获取java数据库链接
	 * @param tableName  表明称
	 * 
	 * @return  表格数据信息 Table对象接收
	 * @throws SQLException
	 */
	public abstract Table getTable(String tableName, String className, Table table) throws SQLException;

	/**
	 * ��ȡ��ݿ����б�Ľṹ
	 * @return
	 * @throws SQLException
	 */
	public abstract List<Table> getTables() throws SQLException;

	public DataSource(Connection conn) {
		this.conn = conn;
	}

	public DataSource() {

	}

	protected void connectionTest(Connection conn) throws SQLException {
		if (conn == null) {
			throw new SQLException(DataSource.class.getName() + ":没有连接");
		}
	}

	protected void close(Connection conn, PreparedStatement pstmt, ResultSet rs, ResultSet rstb) throws SQLException {

		if (rs != null) {
			rs.close();
		}
		if (pstmt != null) {
			pstmt.close();
		}
		if (conn != null) {
			conn.close();
		}

		if (rstb != null) {
			rstb.close();
		}

	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

}
