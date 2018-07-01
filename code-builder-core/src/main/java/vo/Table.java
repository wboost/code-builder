package vo;

import java.util.List;

public class Table {

	private String tableName;

	private Column pk; //主键

	private List<Column> fks; //外键

	private String tableType; // view,table etc...

	private List<Column> columns; //包含的列

	private String className; //表格对应的实体类名

	private List<String> condJsonData; //生成JS对应的搜索条件

	private List<String> validate;

	private String remarks;//备注

	public Table() {

	}

	public Table(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Column getPk() {
		return pk;
	}

	public void setPk(Column pk) {
		this.pk = pk;
	}

	public List<Column> getFks() {
		return fks;
	}

	public void setFks(List<Column> fks) {
		this.fks = fks;
	}

	public String getTableType() {
		return tableType;
	}

	public void setTableType(String tableType) {
		this.tableType = tableType;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getClassName() {
		return className;
	}

	public List<String> getCondJsonData() {
		return condJsonData;
	}

	public void setCondJsonData(List<String> condJsonData) {
		this.condJsonData = condJsonData;
	}

	public List<String> getValidate() {
		return validate;
	}

	public void setValidate(List<String> validate) {
		this.validate = validate;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
