package db;

import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import util.ConvertHandler;
import vo.Column;
import vo.Table;

/**
 * 获取oracle数据库表信息
 * @author jwsun
 */
public class Oracle extends DataSource {

	@Override
	public Table getTable(String tableName, String className, Table table) throws SQLException {
		connectionTest(conn);
		if (table == null) {
			table = new Table(tableName);
		}
		table.setClassName(className);
		ResultSet rs = null;
		ResultSet rstb = null;
		if (table.getColumns() == null) {
			table.setColumns(new ArrayList<Column>());
		}
		try {
			DatabaseMetaData dmd = conn.getMetaData();
			//获取所有的列信息
			/*if (table.getRemarks() != null) {*/
				rs = dmd.getColumns(null, null, tableName, null);
				getColumns(rs, table);
				//获取主键信息
				rs = dmd.getPrimaryKeys(null, null, tableName);
				table.setPk(getPk(rs));
			/*} else {
				rstb = dmd.getTables(null, null, tableName, new String[] { "TABLE" });
				while (rstb.next()) {
					String remarks = rstb.getString("REMARKS");
					table.setRemarks(remarks);
				}
			}*/
		} catch (SQLException e) {
			throw e;
		} finally {
			if (rs != null) {
				close(conn, null, rs, rstb);
			}
		}
		return table;
	}

	@Override
	public List<Table> getTables() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 设置Column的属性值
	 * @throws SQLException 
	 */
	private void getColumns(ResultSet rs, Table table) throws SQLException {
		while (rs.next()) {
			Column col = new Column();
			col.setName(rs.getString("COLUMN_NAME"));
			col.setType(rs.getString("TYPE_NAME"));
			col.setSize(rs.getInt("COLUMN_SIZE"));
			col.setNullable(rs.getBoolean("NULLABLE"));
			col.setDigits(rs.getInt("DECIMAL_DIGITS"));
			col.setDefaultValue(rs.getString("COLUMN_DEF"));
			col.setComment(rs.getString("REMARKS") == null?"":rs.getString("REMARKS"));
			table.getColumns().add(col);
		}
	}

	/**
	 * 获取主键信息
	 * @param rs
	 * @return
	 * @throws SQLException
	 */
	private Column getPk(ResultSet rs) throws SQLException {
		Column pk = new Column();
		while (rs.next()) {
			pk.setName(rs.getString("COLUMN_NAME"));
			pk.setFieldName(ConvertHandler.column2field(pk.getName()));
		}
		return pk;
	}

	public Oracle(DataSource db) {
		conn = new DBConnection().getConn(db);
	}

}