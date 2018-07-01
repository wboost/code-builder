package db;

import util.Config;

public class DBFactory {

	/**
	 * 获取指定类型的数据库链接
	 * dialect 数据库方言
	 * 			oracle,mysql,db2,sqlserver
	 * @return
	 * @throws Exception
	 */
	public static DataSource create(DataSource db) throws Exception {
		String dialect = Config.DRIVER;
		if (dialect == null || "".equals(dialect)) {
			throw new Exception(DBFactory.class.getName() + ":>>>>>>>>请指定数据库.......");
		}
		if (dialect.contains("Oracle")) {
			db = new Oracle(db);
		} else if (dialect.contains("mysql")) {
			db = new Mysql(db);
		}

		return db;
	}

}
