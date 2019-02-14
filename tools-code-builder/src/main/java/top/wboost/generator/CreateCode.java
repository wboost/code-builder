package top.wboost.generator;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.Scanner;

import config.Constant.GenerateCodeType;
import config.Constant.GenerateDbType;
import config.Constant.GenerateOrmType;
import factory.CodeGenerateFactory;
import util.Config;

/**
 * 创建代码，具体配置在src/config/config.properties中
 * @ClassName: CreateCode
 * @author sjw
 * @date 2016年7月1日 上午10:24:24
 */
public class CreateCode {



    public static void main(String[] args) throws Exception {
        String charset = null;
        if (args.length == 0) {
            System.out.println("未输入编码，默认使用GBK");
            charset = "GBK";
        } else {
            charset = args[0];
        }
        //初始化表格信息
        /*String[] realNames = new String[] { "msgId", "msgTime", "assetId", "oId", "oIdname", "subOId", "alarmValue",
        		"alarmLevel", "message", "code", "secondSsinceepoch", "userName", "result", "reason", "procTime",
        		"method", "assetName", "memory", "cpu", "disk", "middleWare" };*/
        System.out.println("init..");
        Properties prop = new Properties();
        prop.load(new FileInputStream("config.properties"));
        /**是否开启@EnableBaseRestful注解**/
        boolean enableBaseRestful = Boolean.parseBoolean(prop.get("enableBaseRestful").toString());
        /**是否使用RestController注解**/
        boolean enableRestful = Boolean.parseBoolean(prop.get("enableRestful").toString());
        /**选择使用JPA或MYBATIS**/
        GenerateOrmType generateOrmType = GenerateOrmType.valueOf(prop.get("generateOrmType").toString().toUpperCase());
        /**选择使用oracle或mysql**/
        GenerateDbType generateDbType = GenerateDbType.valueOf(prop.get("generateDbType").toString().toUpperCase());
        /**初始化**/
        CodeGenerateFactory.initConfig(charset, enableBaseRestful, enableRestful, generateOrmType, generateDbType);
        Config.orm.set(generateOrmType);
        Scanner scan = new Scanner(System.in, "gbk");
        System.out.println("请输入表名(如T_DEMO)");
        String tableName = scan.next();
        System.out.println("请输入实体类名(如Demo)");
        String entityName = scan.next();
        System.out.println("请输入注释(如示例)");
        String remark = scan.next();
        scan.close();
        System.out.println("begin");
        CodeGenerateFactory.initTable(tableName, entityName, null, remark);

        CodeGenerateFactory.createCode(
                /**CONTROLLER**/
                GenerateCodeType.CONTROLLER,
                /**SERVICE**/
                GenerateCodeType.SERVICE,
                /**ENTITY**/
                GenerateCodeType.ENTITY,
                /**SERVICE_IMPL**/
                GenerateCodeType.SERVICE_IMPL);
        if (generateOrmType == GenerateOrmType.MYBATIS) {
            CodeGenerateFactory.createCode(
                    /**JAVA_MAPPER**/
                    GenerateCodeType.JAVA_MAPPER,
                    /**XML_MAPPER**/
                    GenerateCodeType.XML_MAPPER);
        } else {
            CodeGenerateFactory.createCode(
                    /**REPOSITORY**/
                    GenerateCodeType.REPOSITORY);
        }

        //创建js文件
        /*String[] validates = new String[] {};//这里填写rules的东西
        String[] condJsonDatas = new String[] {};//这里填写搜索需要的东西
        CodeGenerateFactory.createJs(validates, condJsonDatas);
        //创建jsp文件，生成的jsp文件在桌面/newJsp文件夹里
        CodeGenerateFactory.createJsp();*/
    }

}
