/**
 * 
 */
package config;

import generate.Generate;
import generate.JavaGenerate;
import generate.ProjectGenerate2;
import util.FileType;

/**
 * @ClassName: Constant
 * @author sjw
 * @date 2016年7月27日 下午2:40:40
 */
public class Constant {

    public static String DESKTOP = "C:/Users/Administrator/Desktop/";

    public enum GenerateOrmType {
        JPA, MYBATIS;
    }

    public enum GenerateDbType {
        ORACLE, MYSQL;
    }

    public enum GenerateCodeType {
        /**CONTROLLER**/
        CONTROLLER(FileType.CONTROLLER, JavaGenerate.class),
        /**ENTITY**/
        ENTITY(FileType.ENTITY, JavaGenerate.class),
        /**SERVICE**/
        SERVICE(FileType.SERVICE, JavaGenerate.class),
        /**SERVICE_IMPL**/
        SERVICE_IMPL(FileType.SERVICE_IMPL, JavaGenerate.class),
        /**REPOSITORY**/
        REPOSITORY(FileType.REPOSITORY, JavaGenerate.class),
        /**MAPPER**/
        XML_MAPPER(FileType.XML_MAPPER, JavaGenerate.class),
        /**MAPPER**/
        JAVA_MAPPER(FileType.JAVA_MAPPER, JavaGenerate.class),
        /**PROJECT**/
        PROJECT(FileType.PROJECT, ProjectGenerate2.class);

        public FileType fileType;
        public Class<? extends Generate> generate;

        private GenerateCodeType(FileType fileType, Class<? extends Generate> generate) {
            this.fileType = fileType;
            this.generate = generate;
        }

        public String getTemplatePath() {
            return fileType.getTemplate();
        }

    }

}
