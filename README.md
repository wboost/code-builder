wboost ： code-builder
=========================
项目/代码 生成构建工具
=========================
[![Build Status](http://www.wboost.top:18080/build.svg)](http://www.wboost.top)
[![Packagist](http://www.wboost.top:18080/version.svg)](http://www.wboost.top)
[![Yii2](http://www.wboost.top:18080/auth.svg)](http://www.wboost.top)

[Home Page](http://www.wboost.top) | [官方主页](http://www.wboost.top) | [中文说明]() | [文档手册](http://www.wboost.top/framework/spring-boot-starter-support/docs).

如果你喜欢，可以开始使用以下生成工具了 : )

* code-builder-core 生成器核心代码
* tools-project-builder 项目生成器
* tools-code-builder 代码生成器

code
------------
```
git clone https://github.com/wboost/code-builder.git
```

Quick Start
------------

* mvn clean package
* copy tools-code-builder-jar-with-dependencies.jar、tools-project-builder-jar-with-dependencies.jar from target to file 
* copy tools-code-builder\config.properties or tools-project-builder\config.properties to same file
* java -Dfile.encoding=UTF-8 -jar (use).jar