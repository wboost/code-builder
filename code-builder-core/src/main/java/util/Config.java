package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.velocity.VelocityContext;

import factory.CodeGenerateFactory;
import vo.Table;

public final class Config {

    /*************数据库配置********************/
    public static String DATABASE_CHOOSE = "";
    public static String DRIVER = "";
    public static String USERNAME = "";
    public static String PASSWORD = "";
    public static String URL = "";

    /*****作者******/
    public static String AUTHOR = "";

    /************ 模板配置 ************/
    public static String TEMPLATE_PATH = "";
    public static String TEMPLATE_ENTITY = "";
    public static String TEMPLATE_XML_MAPPER = "";
    public static String TEMPLATE_JAVA_MAPPER = "";
    public static String TEMPLATE_SERVICE = "";
    public static String TEMPLATE_SERVICE_IMPL = "";
    public static String TEMPLATE_CONTROLLER = "";
    public static String TEMPLATE_CONTROLLER_ANNOTATION = "";
    public static String TEMPLATE_CONTROLLER_REST = "";
    public static String TEMPLATE_REPOSITORY = "";
    public static String TEMPLATE_BASE;

    public static String TEMPLATE_JS = "";
    public static String TEMPLATE_JSP = "";

    /******************文件路径****************/
    public static String JAVA_STORE_PATH = "";
    public static String JS_STORE_PATH = "";
    public static String JSP_STORE_PATH = "";
    /*****************文件包配置********************************/
    public static String PACKAGE_NAME = "";
    public static String PACKAGE_SUFFIX = "";
    public static String PACKAGE_ENTITY = "";
    public static String PACKAGE_XML_MAPPER = "";
    public static String PACKAGE_JAVA_MAPPER = "";
    public static String PACKAGE_SERVICE = "";
    public static String PACKAGE_CONTROLLER = "";
    public static String PACKAGE_JS = "";
    public static String PACKAGE_JSP = "";
    public static String PACKAGE_REPOSITORY = "";

    static {
        Properties prop = new Properties();
        Properties userprop = new Properties();
        InputStream in = Config.class.getResourceAsStream("/config/config.properties");
        try {
            InputStream userin = new FileInputStream("config.properties");
            userprop.load(userin);
            prop.load(in);
            //数据库信息配置
            DRIVER = userprop.getProperty("db.driver").trim();
            URL = userprop.getProperty("db.url").trim();
            USERNAME = userprop.getProperty("db.username").trim();
            PASSWORD = userprop.getProperty("db.password").trim();
            AUTHOR = userprop.getProperty("author").trim();
            PACKAGE_SUFFIX = userprop.getProperty("package_suffix").trim();
            /**生成文件路径**/
            JAVA_STORE_PATH = userprop.getProperty("java_store_path").trim();
            //模版路径配置
            TEMPLATE_BASE = prop.getProperty("template_path").trim();
            System.out.println("TEMPLATE_BASE:" + TEMPLATE_BASE);
            TEMPLATE_PATH = TEMPLATE_BASE + "/" + CodeGenerateFactory.userConfig.get().getGenerateOrmType();
            TEMPLATE_CONTROLLER = prop.getProperty("template_controller").trim();
            TEMPLATE_CONTROLLER_ANNOTATION = prop.getProperty("template_controller_annotation").trim();
            TEMPLATE_CONTROLLER_REST = prop.getProperty("template_controller_rest").trim();
            TEMPLATE_ENTITY = prop.getProperty("template_entity").trim();
            TEMPLATE_SERVICE = prop.getProperty("template_service").trim();
            TEMPLATE_SERVICE_IMPL = prop.getProperty("template_service_impl").trim();
            TEMPLATE_REPOSITORY = prop.getProperty("template_repository").trim();
            TEMPLATE_XML_MAPPER = CodeGenerateFactory.userConfig.get().getGenerateDbType().name().toLowerCase()
                    + prop.getProperty("template_mapper").trim();
            TEMPLATE_JAVA_MAPPER = prop.getProperty("template_java_mapper").trim();

            /**生成JS文件路径**/

            JS_STORE_PATH = prop.getProperty("js_store_path").trim();
            /**生成JSP文件路径**/
            JSP_STORE_PATH = prop.getProperty("jsp_store_path").trim();
            TEMPLATE_JS = prop.getProperty("template_js").trim();
            TEMPLATE_JSP = prop.getProperty("template_jsp").trim();
            //初始化文件包路径
            PACKAGE_ENTITY = replace(prop.getProperty("package_entity").trim(), userprop);
            PACKAGE_XML_MAPPER = replace(prop.getProperty("package_mapper").trim(), userprop);
            PACKAGE_JAVA_MAPPER = replace(prop.getProperty("package_mapper").trim(), userprop);
            PACKAGE_SERVICE = replace(prop.getProperty("package_service").trim(), userprop);
            PACKAGE_CONTROLLER = replace(prop.getProperty("package_controller").trim(), userprop);
            PACKAGE_JS = replace(prop.getProperty("package_js").trim(), userprop);
            PACKAGE_JSP = replace(prop.getProperty("package_jsp").trim(), userprop);
            PACKAGE_REPOSITORY = replace(prop.getProperty("package_repository").trim(), userprop);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static VelocityContext getContext(Table table) {
        VelocityContext context = new VelocityContext();
        if (table == null)
            return context;
        context.put("tableName", table.getTableName().toLowerCase());
        context.put("tableNameReal", table.getTableName());
        context.put("mapperXmlPackage", Config.PACKAGE_XML_MAPPER);
        context.put("mapperJavaPackage", Config.PACKAGE_JAVA_MAPPER);
        context.put("entityPackage", Config.PACKAGE_ENTITY);
        context.put("respositoryPackage", Config.PACKAGE_REPOSITORY);
        context.put("packageName", Config.PACKAGE_NAME);
        context.put("servicePackage", Config.PACKAGE_SERVICE);
        context.put("controllerPackage", Config.PACKAGE_CONTROLLER);
        context.put("jsPackage", Config.PACKAGE_JS);
        context.put("jspPackage", Config.PACKAGE_JSP);
        context.put("packageSuffix", Config.PACKAGE_SUFFIX);
        context.put("author", Config.AUTHOR);
        context.put("createTime", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        context.put("basePage.offset", "#{basePage.offset}");
        context.put("basePage.limit", "#{basePage.limit}");
        context.put("basePage.sort", "${basePage.sort}");
        context.put("basePage.order", "${basePage.order}");
        context.put("offset", "#{offset}");
        context.put("limit", "#{limit}");
        context.put("sort", "${sort}");
        context.put("order", "${order}");
        context.put("key", "${key}");
        context.put("value", "value");
        context.put("isOk", "isOk");
        context.put("id", "id");
        return context;
    }

    public static String replace(String text, Properties properties) {
        String patternCompile = "\\$\\{(.*?)\\}";
        Pattern p = Pattern.compile(patternCompile);
        Matcher matcher = p.matcher(text);
        while (matcher.find()) {
            String match = matcher.group(1);
            text = text.replace("${" + match + "}", properties.getProperty(match));
        }
        return text;
    }
}
