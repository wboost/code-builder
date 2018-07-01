package factory;

import org.apache.commons.lang.StringUtils;

import config.Constant.GenerateCodeType;
import config.Constant.GenerateDbType;
import config.Constant.GenerateOrmType;
import db.DBFactory;
import db.DataSource;
import util.ConvertHandler;
import util.UserConfig;
import vo.Table;

/**
 * 代码生成工厂
 * @author sunjw
 * @date 2017年1月9日 上午11:32:24
 */
public class CodeGenerateFactory {

    private static DataSource db;
    private static Table table;
    public static ThreadLocal<UserConfig> userConfig = new ThreadLocal<UserConfig>();

    /**
     * 初始化用户选择配置
     * @param charset 
     * @param enableBaseRestful
     * @param enableRestful 
     * @param generateOrmType
     * @param generateDbType
     */
    public static void initConfig(String charset, boolean enableBaseRestful, boolean enableRestful,
            GenerateOrmType generateOrmType, GenerateDbType generateDbType) {
        userConfig.set(new UserConfig(charset, enableBaseRestful, enableRestful, generateOrmType, generateDbType));
    }

    /**
     * 表格信息初始化
     * @param tableName  表名
     * @param className  对应的实体类名
     * @param enableBaseRestful 是否开启enableBaseRestful注解
     */
    public static void initTable(String tableName, String className, String[] realNames, String remark) {
        try {
            table = new Table(tableName);
            db = DBFactory.create(db);
            db.getTable(tableName, className, table);
            ConvertHandler.convert(table, realNames);
            if (StringUtils.isNotBlank(remark)) {
                table.setRemarks(remark);
            }
            System.out.println("init:OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始创建代码
     * @param types
     * @throws Exception
     */
    public static void createCode(GenerateCodeType... types) throws Exception {
        for (GenerateCodeType type : types) {
            type.generate.newInstance().init(type).generate(table);
            System.out.println("create " + type + " OK");
        }
    }

    /**
     * 创建JS
     */
    /*public static void createJs(String[] validates, String[] condJsonDatas) {
        try {
            gen = new JsGenerate(FileType.JS_SCRIPT);
            List<String> condJsonDataList = new ArrayList<String>();
            List<String> validateList = new ArrayList<String>();
            for (String condJsonData : condJsonDatas) {
                condJsonDataList.add(condJsonData);
            }
            for (String validate : validates) {
                validateList.add(validate);
            }
            table.setCondJsonData(condJsonDataList);
            table.setValidate(validateList);
            gen.generate(table);
            System.out.println("createJs:OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    /**
     * 创建JSP
     */
    /*public static void createJsp() {
        try {
            gen = new JspGenerate(FileType.JSP_PAGE);
            gen.generate(table);
            System.out.println("createPage:OK");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}
