package generate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.util.List;
import java.util.Scanner;

import config.Constant;
import config.InfoSetting;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;

import config.Constant.GenerateCodeType;
import util.Config;
import util.FileCopyUtil;
import util.FileUtil;
import util.VelocityUtil;
import vo.Table;

/**
 * 生成java文件
 *
 * @author jwSun
 * @version 1.0.0
 * @className JavaGenerate
 * @date 2018年4月18日 上午10:45:31
 */
public class ProjectGenerate2 implements Generate {

    GenerateCodeType generateCodeType;

    String projectName;
    String packageName;
    String modelName;
    String ormName;
    String applicationRunName;

    public ProjectGenerate2() {
    }

    @Override
    public void generate(Table table) throws Exception {
        InfoSetting.Setting setting = InfoSetting.setting();
        projectName = setting.getProjectName();
        packageName = setting.getPackageName();
        modelName = setting.getModelName();
        ormName = setting.getOrm().toString();
        applicationRunName = setting.getAppName();
        System.out.println("projectName:" + projectName);
        System.out.println("packageName:" + packageName);
        System.out.println("modelName:" + modelName);
        System.out.println("ormName:" + ormName);
        System.out.println("applicationRunName:" + applicationRunName);
        System.out.println("init...");
        File file = new File("project/" + projectName);
        if (!file.exists()) {
            file.mkdirs();
        }
        VelocityContext context = Config.getContext(table);
        //设置文件相关信息
        context.put("projectName", projectName);
        context.put("packageName", packageName);
        context.put("modelName", modelName);
        context.put("applicationRunName", applicationRunName);
        System.out.println("init pom.xml");
        FileUtil.create("project/" + projectName, "pom.xml", VelocityUtil.merge("config/template", "project_pom.vm",context));
//        System.out.println("init .classpath");
//        Template template_classpath = VelocityUtil.getTemplate("config/template/" + ormName, "project_classpath.vm");
//        StringWriter writer_classpath = new StringWriter();
//        template_classpath.merge(context, writer_classpath);
//        FileUtil.create("project/" + projectName, ".classpath", writer_classpath.toString());
//        System.out.println("init .project");
//        Template template_project = VelocityUtil.getTemplate("config/template/" + ormName, "project_project.vm");
//        StringWriter writer_project = new StringWriter();
//        template_project.merge(context, writer_project);
//        FileUtil.create("project/" + projectName, ".project", writer_project.toString());
//        System.out.println("init .settings");
//        FileCopyUtil.loadRecourseFromJarByFolder("config/template/" + ormName + "/project_settings",
//                "project/" + projectName + "/.settings");
        System.out.println("init files");
        packageName = packageName.replace(".", "/");
        new File("project/" + projectName + "/src/main/java/" + packageName + "/" + modelName + "/controller").mkdirs();
        new File("project/" + projectName + "/src/main/java/" + packageName + "/" + modelName + "/entity").mkdirs();
        new File("project/" + projectName + "/src/main/java/" + packageName + "/" + modelName + "/service/impl")
                .mkdirs();
        new File("project/" + projectName + "/src/main/test").mkdirs();
        System.out.println("init resources");
        //if (setting.getOrm() != Orm.NO) {
        FileCopyUtil.loadRecourseFromJarByFolder("config/template/" + ormName + "/resources",
                "project/" + projectName + "/src/main/resources");
        //}
        if (setting.getOrm() == Constant.GenerateOrmType.MYBATIS) {
            generatorMybatis();
        } else if (setting.getOrm() == Constant.GenerateOrmType.JPA) {
            generatorJpa();
        }
        System.out.println("init applicationRun");
        FileUtil.create("project/" + projectName + "/src/main/java/" + packageName, applicationRunName + ".java",
                VelocityUtil.merge("config/template", "project_applicationRun.vm",context));
        System.out.println("init bootstrap.yml");
        FileUtil.create("project/" + projectName + "/src/main/resources", "bootstrap.yml",
                VelocityUtil.merge("config/template", "yml_bootstrap.vm",context));
        System.out.println("init application.yml");
        FileUtil.create("project/" + projectName + "/src/main/resources", "application.yml",
                VelocityUtil.merge("config/template", "yml_application.vm",context));
        System.out.println("init application-dev.yml");
        FileUtil.create("project/" + projectName + "/src/main/resources", "application-dev.yml",
                VelocityUtil.merge("config/template", "yml_application_dev.vm",context));
        System.out.println("init project ok...");
    }

    private void generatorMybatis() {
        new File("project/" + projectName + "/src/main/java/" + packageName + "/" + modelName + "/mapper").mkdirs();
    }

    private void generatorJpa() {
        new File("project/" + projectName + "/src/main/java/" + packageName + "/" + modelName + "/repository")
                .mkdirs();
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
