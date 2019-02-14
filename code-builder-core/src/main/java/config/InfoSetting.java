package config;

import lombok.Data;
import util.Config;

import java.util.Scanner;

/**
 * @Auther: jwsun
 * @Date: 2019/2/13 17:23
 */
public class InfoSetting {

    @Data
    public static class Setting {
        String projectName;
        String packageName;
        String modelName;
        Constant.GenerateOrmType orm;
        String appName;
    }

    public static Setting setting() {
        Scanner scan = new Scanner(System.in);
        System.out.println("请输入项目名");
        String projectName = scan.next();
        System.out.println("请输入包名(如com.chinaoly)");
        String packageName = scan.next();
        System.out.println("请输入模块名(如demo)");
        String modelName = scan.next();
        System.out.println("请输入使用的ORM(mybaits/jpa/no)");
        String ormName = scan.next().toUpperCase();
        Constant.GenerateOrmType orm;
        try {
            orm = Constant.GenerateOrmType.valueOf(ormName);
            Config.orm.set(Constant.GenerateOrmType.valueOf(ormName));
        }catch (Exception e) {
            orm = Constant.GenerateOrmType.NO;
        }
        System.out.println("请输入启动类名称 默认ApplicationRun");
        String applicationRunName = scan.next();
        if (applicationRunName == null || "".equals(applicationRunName)) {
            applicationRunName = "ApplicationRun";
        }
        scan.close();
        Setting setting = new Setting();
        setting.setAppName(applicationRunName);
        setting.setModelName(modelName);
        setting.setOrm(orm);
        setting.setPackageName(packageName);
        setting.setProjectName(projectName);
        return setting;
    }
}
