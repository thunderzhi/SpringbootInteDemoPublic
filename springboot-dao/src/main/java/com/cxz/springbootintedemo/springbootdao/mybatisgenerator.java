package com.cxz.springbootintedemo.springbootdao;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.VelocityTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author cxz
 * @Title:
 * @Package
 * @Description:
 * @date 2021/8/31 9:30
 */
public class mybatisgenerator {
    public static String containsEntityPath = "src/main/java/com/cxz/springbootdomain/entity/";

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public static void main(String[] args)      {
//        System.out.println(System.getProperty("user.dir"));
//        List<String> warnings = new ArrayList<String>();
//        boolean overwrite = true;
//        File configFile = new File(" /src/main/resources/mybatis-generator.xml");
//        ConfigurationParser cp = new ConfigurationParser(warnings);
//        Configuration config = cp.parseConfiguration(configFile);
//        DefaultShellCallback callback = new DefaultShellCallback(overwrite);
//        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
//        if (warnings.isEmpty()) {
//            System.out.println("MyBatis文件生成成功！！");
//        } else {
//            System.err.println(warnings);
//        }
//        myBatisGenerator.generate(null);

        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();
        boolean changePath = scanner("输入y生成到d盘根目录，默认回车生成到项目").equalsIgnoreCase("y");
        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
//        gc.setOutputDir(projectPath + "/codegenerator/src/main/java");
        gc.setOutputDir(containsEntityPath);

        String generatePath =  System.getProperty("user.dir") + "/springbootdao/src/main/";

        gc.setAuthor("cxz");
        gc.setFileOverride(true);
        gc.setActiveRecord(false);
        gc.setEnableCache(false);// XML 二级缓存
        gc.setBaseResultMap(true);// XML ResultMap
        gc.setBaseColumnList(true);// XML columList
        gc.setOpen(false);
        // 自定义文件命名，注意 %s 会自动填充表实体属性！
        gc.setMapperName("%sMapper");
        gc.setXmlName("%sMapper");
        //gc.setServiceName("I%sService");
        //gc.setServiceImplName("%sServiceImpl");
        //gc.setControllerName("%sController");
        // gc.setSwagger2(true); 实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);
        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        String dbname = "TestDB";
        dsc.setUrl("jdbc:mysql://x/"+dbname+"?useUnicode=true&characterEncoding=utf8");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("sssss");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        //pc.setModuleName(scanner("模块名"));
        pc.setParent("");

        pc.setMapper("com.cxz.springbootdao."+dbname.toLowerCase()+"testdb.mapper"); // 这里是控制器包名，默认web
        pc.setEntity("com.cxz.springbootdomain.entity."+dbname.toLowerCase());
        pc.setXml("xml");
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        //String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity

        String templatePathMapperXml = "/templates/mapper.xml.vm";
        String templatePathMapperJava = "/templates/mapper.java.vm";
        String templatePathMapperEntity = "/templates/entity.java.vm";
        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出 mapper.xml
        focList.add(new FileOutConfig(templatePathMapperXml) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/springboot-dao/src/main/resources/mapper/" +dbname.toLowerCase()
                        + "/" + tableInfo.getName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        // 自定义配置会被优先输出 MapperJava.xml
        focList.add(new FileOutConfig(templatePathMapperJava) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/springboot-dao/src/main/java/com/cxz/springbootdao/"+dbname.toLowerCase()+"/mapper/"
                        + "/" +tableInfo.getName()  + "Mapper" + StringPool.DOT_JAVA;
            }
        });
        // 自定义配置会被优先输出 Entity.java
        focList.add(new FileOutConfig(templatePathMapperEntity) {

            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
                return projectPath + "/springboot-domain/src/main/java/com/cxz/springbootdomain/entity/"+dbname.toLowerCase()+ "/"
                        + tableInfo.getName() + StringPool.DOT_JAVA;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录，自定义目录用");
                if (fileType == FileType.MAPPER) {
                    // 已经生成 mapper 文件判断存在，不想重新生成返回 false
                    return !new File(filePath).exists();
                }
                // 允许生成模板文件
                return true;
            }
        });
        */
        cfg.setFileOutConfigList(focList);
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        templateConfig.setEntity("templates/entity.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
        //strategy.setSuperEntityClass("你自己的父类实体,没有就不用设置!");
        strategy.setEntityLombokModel(true);
        strategy.setRestControllerStyle(true);
        // 公共父类
        //strategy.setSuperControllerClass("你自己的父类控制器,没有就不用设置!");
        // 写于父类中的公共字段
        strategy.setSuperEntityColumns("id");
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));
        strategy.setControllerMappingHyphenStyle(true);
        strategy.setTablePrefix(pc.getModuleName() + "_");
        mpg.setStrategy(strategy);
        mpg.setTemplateEngine(new VelocityTemplateEngine());
        mpg.execute();
    }
}
