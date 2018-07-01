package util;

import factory.CodeGenerateFactory;

/**
 * 文件相关信息：包名，包路径，模版路径，文件扩展名
 * @author qhluo
 * @since 2016-06-03
 */

public enum FileType {

    /**ENTITY**/
    ENTITY("entity", Config.PACKAGE_ENTITY, Config.TEMPLATE_ENTITY, ".java"),
    /**MAPPER**/
    XML_MAPPER("mapper", Config.PACKAGE_XML_MAPPER, Config.TEMPLATE_XML_MAPPER, "Mapper.xml"),
    /**SERVICE**/
    SERVICE("service", Config.PACKAGE_SERVICE, Config.TEMPLATE_SERVICE, "Service.java"),
    /**SERVICE_IMPL**/
    SERVICE_IMPL("service.impl", Config.PACKAGE_SERVICE + ".impl", Config.TEMPLATE_SERVICE_IMPL, "ServiceImpl.java"),
    /**CONTROLLER**/
    CONTROLLER("controller", Config.PACKAGE_CONTROLLER, Config.TEMPLATE_CONTROLLER, "Controller.java"), JS_SCRIPT("controller", Config.PACKAGE_JS, Config.TEMPLATE_JS, ".js"),
    /**JSP_PAGE**/
    JSP_PAGE("controller", Config.PACKAGE_JSP, Config.TEMPLATE_JSP, ".jsp"),
    /**REPOSITORY**/
    REPOSITORY("repository", Config.PACKAGE_REPOSITORY, Config.TEMPLATE_REPOSITORY, "Repository.java"),
    /**REPOSITORY**/
    JAVA_MAPPER("mapper", Config.PACKAGE_JAVA_MAPPER, Config.TEMPLATE_JAVA_MAPPER, "Mapper.java"),
    /**PROJECT**/
    PROJECT("project", null, null, null);

    private String type;
    private String path;
    private String template;
    private String fileNameExtension;

    private FileType(String type, String path, String template, String fileNameExtension) {
        this.type = type;
        this.path = path;
        this.template = template;
        this.fileNameExtension = fileNameExtension;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getTemplate() {
        if (FileType.CONTROLLER == this) {
            if (CodeGenerateFactory.userConfig.get().getEnableBaseRestful()) {
                return Config.TEMPLATE_CONTROLLER_ANNOTATION;
            } else if (CodeGenerateFactory.userConfig.get().getEnableRestful()) {
                return Config.TEMPLATE_CONTROLLER_REST;
            }
        }
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getFileNameExtension() {
        return fileNameExtension;
    }

    public void setFileNameExtension(String fileNameExtension) {
        this.fileNameExtension = fileNameExtension;
    }

}
