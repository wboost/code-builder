package vo;

public class Column {

	/*************JDBC******************/

	private String name; //数据库字段名
	private String type; //字段类型
	private int size; //字段长度
	private boolean nullable; //是否为空
	private String defaultValue; //默认值
	private int digits; //精度
	private String comment = ""; //备注

	/************java**************/

	private String javaType; //对应的java类型
	private String fieldName; //对应的实体类属性名
	private String setMethod; //set方法
	private String getMethod; //get方法
	private Table table; //所属表格

	/******自定义字段名******/
	private String realName; //自定义字段名

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public String getDefaultValue() {
		return defaultValue;
	}

	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	public int getDigits() {
		return digits;
	}

	public void setDigits(int digits) {
		this.digits = digits;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getSetMethod() {
		return setMethod;
	}

	public void setSetMethod(String setMethod) {
		this.setMethod = setMethod;
	}

	public String getGetMethod() {
		return getMethod;
	}

	public void setGetMethod(String getMethod) {
		this.getMethod = getMethod;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	@Override
	public String toString() {
		return "Column [name=" + name + ", type=" + type + ", size=" + size
				+ ", nullable=" + nullable + ", defaultValue=" + defaultValue
				+ ", digits=" + digits + ", comment=" + comment + ", javaType="
				+ javaType + ", fieldName=" + fieldName + ", setMethod="
				+ setMethod + ", getMethod=" + getMethod + ", table=" + table
				+ ", realName=" + realName + "]";
	}
	
	

}
