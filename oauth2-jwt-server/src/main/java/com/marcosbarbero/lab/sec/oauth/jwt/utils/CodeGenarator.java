package com.marcosbarbero.lab.sec.oauth.jwt.utils;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @Description
 * @Author kzh
 * @Date 2019/7/2 11:39
 */
public class CodeGenarator {

    public static void main(String[] args) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig
                .setAuthor("MR.zt")
                .setOutputDir("D:\\data")
                .setFileOverride(true)
                .setIdType(IdType.ID_WORKER)
                .setServiceName("%sService")
                .setMapperName("%sDao")
                .setActiveRecord(true)
                .setBaseResultMap(true)
                .setBaseColumnList(true);

        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig
                .setDbType(DbType.MYSQL)
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://39.108.130.98:30062/others?serverTimezone=GMT%2B8")
                .setUsername("root")
                .setPassword("SEesceNfyL");

        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig
                .setInclude("job_industry","job_position")
                .setCapitalMode(true)
                .setNaming(NamingStrategy.underline_to_camel)
                .setEntityLombokModel(true)
                .setEntityBuilderModel(true)
                .setRestControllerStyle(true)
                .entityTableFieldAnnotationEnable(true)
        ;

        PackageConfig packageConfig = new PackageConfig();
        packageConfig
                .setParent("cn.boringkiller.others")
                .setMapper("repository")
                .setService("service")
                .setController("controller")
                .setEntity("entity")
                .setXml("mapper");

        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator
                .setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig)
                .setTemplateEngine(new FreemarkerTemplateEngine());
        autoGenerator.execute();



    }


}
