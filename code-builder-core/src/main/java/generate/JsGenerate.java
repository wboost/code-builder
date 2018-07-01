package generate;

import java.io.StringWriter;
import java.util.List;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import config.Constant.GenerateCodeType;
import util.Config;
import util.FileType;
import util.FileUtil;
import util.VelocityUtil;
import vo.Table;

public class JsGenerate implements Generate {

    FileType javaFileType;

    public JsGenerate(FileType javaFileType) {
        this.javaFileType = javaFileType;
    }

    @Override
    public void generate(Table table) throws Exception {
        if (javaFileType == null) {
            throw new Exception(JavaGenerate.class.getName() + ":javaFileType is null !");
        }

        //get the template
        Template template = VelocityUtil.getTemplate(javaFileType.getTemplate());
        //created a context and add data
        List<String> condJsonData = table.getCondJsonData();
        List<String> validate = table.getValidate();
        VelocityContext context = Config.getContext(table);
        context.put("condJsonDatas", condJsonData);
        context.put("validates", validate);
        String className = table.getClassName();
        String entityInstance = className.substring(0, 1).toLowerCase() + className.substring(1);
        context.put("entityName", className);
        context.put("entityInstance", entityInstance);
        context.put("columns", table.getColumns());
        context.put("pk", table.getPk());
        StringWriter writer = new StringWriter();
        template.merge(context, writer);
        FileUtil.create(Config.JS_STORE_PATH + "/" + Config.PACKAGE_SUFFIX.replaceAll("\\.", "/"),
                entityInstance + javaFileType.getFileNameExtension(), writer.toString());

    }

    @Override
    public void generate(List<Table> tables) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Generate init(GenerateCodeType generateCodeType) {
        // TODO Auto-generated method stub
        return null;
    }
}
