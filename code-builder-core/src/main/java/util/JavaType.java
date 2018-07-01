package util;

/**
 * JDBC 类型与 JAVA 类型对应关系枚举
 * @author qhluo
 * @since 2016-05-13
 *
 */
public enum JavaType {

    //字符串相关
    STRING("String"), CHAR("String"), VARCHAR("String"), VARCHAR2("String"), LONGVARCHAR("String"), TEXT("String"), CLOB(
            "String"),

    //数字相关
    /*NUMERIC("BigDecimal"), DECIMAL("BigDecimal"), TINYINT("Integer"), SMALLINT("Short"), INTEGER("Integer"), INT(
    		"Integer"), BIGINT("Long"), NUMBER("Integer"), REAL("Float"), FLOAT("Double"), DOUBLE("Double"),*/
    NUMERIC("BigDecimal"), DECIMAL("Long"), TINYINT("Integer"), SMALLINT("Short"), INTEGER("Integer"), INT("Integer"), BIGINT(
            "Long"), NUMBER("Integer"), REAL("Float"), FLOAT("Double"), DOUBLE("Double"),
    //字节相关
    BINARY("byte[]"), VARBINARY("byte[]"), LONGVARBINARY("byte[]"), BLOB("byte[]"),

    //日期相关
    DATE("Date"), DATETIME("Date"), TIME("Date"), TIMESTAMP("Date"),

    //布尔类型
    BIT("Boolean");

    private String typeName;

    private JavaType(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeName() {
        return typeName;
    }

    /**
     * 将JDBC转换为JAVA类型
     * @param JDBCType   JDBC类型
     * @return     JAVA类型
     */
    public static String getJavaType(String JDBCType) {
        JavaType javaType = null;
        if (JDBCType == null || "".equals(JDBCType))
            return STRING.getTypeName();
        javaType = JavaType.valueOf((JDBCType.replaceAll("\\(\\d{1,9}\\)$", "")).toUpperCase());
        if (javaType == null)
            return STRING.getTypeName();
        return javaType.getTypeName();
    }

}
