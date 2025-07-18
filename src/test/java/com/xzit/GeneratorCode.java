package com.xzit;


import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;

@SpringBootTest
public class GeneratorCode {

    private static final String AUTHOR="LuHaoRan";
    private static final String JDBC_URL="jdbc:mysql://localhost:3306/auto_rental?serverTimezone=UTC";
    private static final String JDBC_USERNAME="root";
    private static final String JDBC_PASSWORD="123456";
    private static final String OUT_DIR=".\\src\\main\\java";
    private static final String PACKAGE_NAME="com.xzit";
    private static final String MODULE_NAME="rental";
    private static final String[] TABLES_NAMES={
            "auto_marker","auto_brand","auto_info",
            "sys_dept","sys_permission","sys_role","sys_user","sys_role_permission","sys_user_role",
            "busi_customer","busi_maintain","busi_violation","busi_order","busi_rental_type"
    };
    private static final String[] PREFIX={"busi_","sys_"};

    @Test
    void generatorCode(){
        FastAutoGenerator.create(JDBC_URL,JDBC_USERNAME,JDBC_PASSWORD)
                .globalConfig(builder -> {
                    builder.author(AUTHOR)
                            .outputDir(OUT_DIR);
                })
                .packageConfig(builder -> {
                    builder.parent(PACKAGE_NAME)
                            .moduleName(MODULE_NAME)
                            .pathInfo(Collections.singletonMap(OutputFile.xml,".\\src\\main\\resources\\mapper"));
                })
                .strategyConfig(builder -> {
                    builder.addInclude(TABLES_NAMES)
                            .addTablePrefix(PREFIX)
                            .entityBuilder()
                            .enableLombok()
                            .enableChainModel()
                            .controllerBuilder()
                            .enableRestStyle();
                })
                .templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
