package generate;

import java.io.StringWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import config.Constant.GenerateCodeType;
import util.Config;
import util.FileUtil;
import util.VelocityUtil;
import vo.Table;

/**
 * 生成java文件
 * @className JavaGenerate
 * @author jwSun
 * @date 2018年4月18日 上午10:45:31
 * @version 1.0.0
 */
public class JavaGenerate implements Generate {

    GenerateCodeType generateCodeType;

    public JavaGenerate() {
    }

    @Override
    public void generate(Table table) throws Exception {
        Template template = VelocityUtil.getTemplate(generateCodeType.getTemplatePath());
        VelocityContext context = Config.getContext(table);
        //设置文件相关信息
        String className = table.getClassName();
        String entityInstance = className.substring(0, 1).toLowerCase() + className.substring(1);
        context.put("entityName", className);
        context.put("entityInstance", entityInstance);
        context.put("columns", table.getColumns());
        context.put("remarks", table.getRemarks());
        context.put("pk", table.getPk());

        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        String str = writer.toString();
        System.out.println(Config.JAVA_STORE_PATH + "/" + generateCodeType.fileType.getPath().replaceAll("\\.", "/"));
        System.out.println(className + generateCodeType.fileType.getFileNameExtension());
        FileUtil.create(Config.JAVA_STORE_PATH + "/" + generateCodeType.fileType.getPath().replaceAll("\\.", "/"),
                className + generateCodeType.fileType.getFileNameExtension(), str);
    }

    @Override
    public void generate(List<Table> tables) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Generate init(GenerateCodeType generateCodeType) {
        this.generateCodeType = generateCodeType;
        return this;
    }

}
