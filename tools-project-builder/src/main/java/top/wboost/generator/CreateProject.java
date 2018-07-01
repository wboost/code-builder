package top.wboost.generator;

import config.Constant.GenerateCodeType;
import config.Constant.GenerateDbType;
import config.Constant.GenerateOrmType;
import factory.CodeGenerateFactory;

/**
 * 创建代码，具体配置在src/config/config.properties中
 * @ClassName: CreateCode
 * @author sjw
 * @date 2016年7月1日 上午10:24:24
 */
public class CreateProject {

    public static void main(String[] args) throws Exception {

        /*String source = "project_pom.vm";
        Properties prop = new Properties();
        prop.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        prop.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        prop.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        //prop.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, Config.TEMPLATE_PATH);
        prop.setProperty("resource.loader", "class");
        prop.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngine ve = new VelocityEngine();
        ve.init(prop);
        String template = "config/template/JPA/" + source;
        System.out.println(template);
        ve.getTemplate(template);
        if (args.length == 0)
            return;*/
        /**是否开启@EnableBaseRestful注解**/
        boolean enableBaseRestful = false;
        /**是否使用RestController注解**/
        boolean enableRestful = false;
        /**选择使用JPA或MYBATIS**/
        GenerateOrmType generateOrmType = GenerateOrmType.JPA;
        /**选择使用oracle或mysql**/
        GenerateDbType generateDbType = GenerateDbType.ORACLE;
        /**初始化**/
        CodeGenerateFactory.initConfig("gbk", enableBaseRestful, enableRestful, generateOrmType, generateDbType);

        CodeGenerateFactory.createCode(
                /**CONTROLLER**/
                GenerateCodeType.PROJECT);

    }

}
