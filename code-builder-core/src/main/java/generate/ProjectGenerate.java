package generate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.StringWriter;
import java.net.URI;
import java.util.List;
import java.util.Scanner;

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
public class ProjectGenerate implements Generate {

    GenerateCodeType generateCodeType;

    public ProjectGenerate() {
    }

    @Override
    public void generate(Table table) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入项目名");
        String projectName = scan.next();
        System.out.println("请输入包名(如com.chinaoly)");
        String packageName = scan.next();
        System.out.println("请输入模块名(如demo)");
        String modelName = scan.next();
        System.out.println("请输入使用的ORM(mybaits/jpa)");
        String ormName = scan.next().toUpperCase();
        System.out.println("请输入启动类名称");
        String applicationRunName = scan.next();
        scan.close();
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
        if (ormName.equals("mybatis")) {
            context.put("use_orm_jar", "common-mybatis-spring-boot-starter");
        } else {
            context.put("use_orm_jar", "common-datasource-spring-boot-starter");
        }
        System.out.println("init pom.xml");
        Template template_pom = VelocityUtil.getTemplate("project_pom.vm");
        StringWriter writer_pom = new StringWriter();
        template_pom.merge(context, writer_pom);
        FileUtil.create("project/" + projectName, "pom.xml", writer_pom.toString());
        System.out.println("init .classpath");
        Template template_classpath = VelocityUtil.getTemplate("project_classpath.vm");
        StringWriter writer_classpath = new StringWriter();
        template_classpath.merge(context, writer_classpath);
        FileUtil.create("project/" + projectName, ".classpath", writer_classpath.toString());
        System.out.println("init .project");
        Template template_project = VelocityUtil.getTemplate("project_project.vm");
        StringWriter writer_project = new StringWriter();
        template_project.merge(context, writer_project);
        FileUtil.create("project/" + projectName, ".project", writer_project.toString());
        System.out.println("init .settings");
        URI uri = ProjectGenerate.class.getResource("/config/template/" + ormName + "/project_settings").toURI();
        File[] filesSetting = new File(uri).listFiles();
        File fileSetting = new File("project/" + projectName + "/.settings");
        copy(filesSetting, fileSetting);
        System.out.println("init files");
        packageName = packageName.replace(".", "/");
        new File("project/" + projectName + "/src/main/java/" + packageName + "/" + modelName + "/controller").mkdirs();
        new File("project/" + projectName + "/src/main/java/" + packageName + "/" + modelName + "/entity").mkdirs();
        new File("project/" + projectName + "/src/main/java/" + packageName + "/" + modelName + "/mapper").mkdirs();
        new File("project/" + projectName + "/src/main/java/" + packageName + "/" + modelName + "/repository").mkdirs();
        new File("project/" + projectName + "/src/main/java/" + packageName + "/" + modelName + "/service/impl")
                .mkdirs();
        new File("project/" + projectName + "/src/main/test").mkdirs();
        System.out.println("init resources");
        File fileResources = new File("project/" + projectName + "/src/main/resources");
        File[] filesResources = new File(
                ProjectGenerate.class.getResource("/config/template/" + ormName + "/resources").toURI()).listFiles();
        copy(filesResources, fileResources);
        System.out.println("init project ok...");
    }

    public static void copy(File[] fl, File file) {
        if (!file.exists()) // 如果文件夹不存在  
            file.mkdirs(); // 建立新的文件夹  
        for (int i = 0; i < fl.length; i++) {
            if (fl[i].isFile()) { // 如果是文件类型就复制文件  
                try {
                    FileInputStream fis = new FileInputStream(fl[i]);
                    FileOutputStream out = new FileOutputStream(
                            new File(file.getPath() + File.separator + fl[i].getName()));

                    int count = fis.available();
                    byte[] data = new byte[count];
                    if ((fis.read(data)) != -1) {
                        out.write(data); // 复制文件内容  
                    }
                    out.close(); // 关闭输出流  
                    fis.close(); // 关闭输入流  
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (fl[i].isDirectory()) { // 如果是文件夹类型  
                File des = new File(file.getPath() + File.separator + fl[i].getName());
                des.mkdir(); // 在目标文件夹中创建相同的文件夹  
                copy(fl[i].listFiles(), des); // 递归调用方法本身  
            }
        }
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
