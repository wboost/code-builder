package util;

import java.io.StringWriter;
import java.util.Properties;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.app.VelocityEngine;

/**
 * 获取velocity模版
 * @author qhluo
 * @since 2016-05-16
 */
public class VelocityUtil {

    public static Template getTemplate(String source) throws Exception {
        return getTemplate(Config.TEMPLATE_PATH.substring(1, Config.TEMPLATE_PATH.length()), source);
    }

    public static Template getTemplate(String path, String source) throws Exception {
        Properties prop = new Properties();
        prop.setProperty(Velocity.ENCODING_DEFAULT, "UTF-8");
        prop.setProperty(Velocity.OUTPUT_ENCODING, "UTF-8");
        prop.setProperty(Velocity.INPUT_ENCODING, "UTF-8");
        //prop.setProperty(VelocityEngine.FILE_RESOURCE_LOADER_PATH, Config.TEMPLATE_PATH);
//        prop.setProperty("resource.loader", "class");
//        prop.setProperty("class.resource.loader.class",
//                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        prop.setProperty("file.resource.loader.class",
                "util.ClassPathResourceLoader");
        VelocityEngine ve = new VelocityEngine();
        ve.init(prop);
        String template = path + "/" + source;
        System.out.println(template);
        return ve.getTemplate(template);
    }

    public static String merge(String path, String source, VelocityContext context) throws Exception {
        Template template_pom = VelocityUtil.getTemplate(path,source);
        StringWriter stringWriter = new StringWriter();
        template_pom.merge(context, stringWriter);
        return stringWriter.toString();
    }

}
