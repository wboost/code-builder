package util;

import vo.Column;
import vo.Table;

/**
 * 转换器
 * 将表名称、列名称、JDBC Type转换为Entity Name、Fields、Java Type
 * @author qhluo
 * @since 2016-05-16
 *
 */
public class ConvertHandler {

    /**
     * 表格信息转为java信息
     * @param table
     * @throws Exception 
     */
    public static void convert(Table table, String[] realNames) throws Exception {
        if (table == null || table.getTableName() == null || "".equals(table.getTableName())) {
            throw new Exception(ConvertHandler.class.getName() + "table or tableName can not be null !");
        }
        if (table.getColumns() == null || table.getColumns().size() == 0) {
            throw new Exception(ConvertHandler.class.getName() + ":table [" + table.getTableName()
                    + "] does not contain any column !");
        }

        //=============================将表名转换为类名=======================

        //=======================数据库类型转JAVA类型====================

        for (Column column : table.getColumns()) {
            columnConvert(column, realNames);
            if (column.getFieldName() == null)
                throw new Exception(ConvertHandler.class.getName() + ": ת��columnΪfieldʱ����,column:["
                        + column.getName() + "].");
        }
        columnConvert(table.getPk(), realNames);
    }

    /**
     * 变量名、类型、get、set方法转换
     * @param column
     */
    private static void columnConvert(Column column, String[] realNames) {
        if (column == null) {
            return;
        }
        if (column.getName() == null) {
            return;
        }
        //JDBC类型与java类型转换
        if ("NUMBER".equals(column.getType())) {
            if (column.getDigits() == 0) {
                if (column.getSize() == 20) {
                    column.setJavaType("Long");
                } else {
                    column.setJavaType("Integer");
                }
            } else {
                column.setJavaType("Double");
            }
        } else {
            column.setJavaType(JavaType.getJavaType(column.getType()));
        }

        //数据库字段名转换为实体类属性名，
        column.setName(column.getName().toLowerCase());

        if (realNames != null) {
            for (String realName : realNames) {
                if (realName.toLowerCase().equals(column.getName().toLowerCase())) {
                    column.setFieldName(realName);
                    break;
                }
            }
        }
        String fieldName = column.getFieldName();
        if (fieldName == null) {
            column.setFieldName(column2field(column.getName()));
            fieldName = column2field(column.getName());
        }
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        column.setSetMethod("set" + methodName);
        if (column.getJavaType().equalsIgnoreCase("boolean")) {
            column.setGetMethod("is" + methodName);
        } else {
            column.setGetMethod("get" + methodName);
        }

    }

    /**
     * 数据库字段名转为POJO属性名（骆驼法则）
     * @param columnName 数据库字段名
     * @return POJO属性名
     */
    public static String column2field(String columnName) {
        if (columnName == null || "".equals(columnName)) {
            return null;
        }
        columnName = columnName.toLowerCase();
        String[] arr = columnName.split("_");
        if (arr == null || arr.length == 0) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(arr[0]);
        for (int i = 1; i < arr.length; i++) {
            sb.append(Character.toUpperCase(arr[i].charAt(0)));
            sb.append(arr[i].substring(1));

        }
        return sb.toString();
    }

    public static void main(String[] args) {

        System.out.println(column2field("user_id"));
    }

}
