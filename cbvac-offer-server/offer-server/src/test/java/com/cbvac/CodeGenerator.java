package com.cbvac;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @Author: zhuqing
 * @Date: 2020-04-17-11:41
 * @Description:
 */
public class CodeGenerator {

    public static void main(String[] args) {
        AutoGenerator mpg = new AutoGenerator();

        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        gc.setOutputDir(projectPath + "/cbvac-offer-server/offer-server/src/main/java");
        gc.setAuthor("zhuqing");   // 作者
        gc.setOpen(false);      //生成代码后是否打开文件夹
        gc.setServiceName("%sService");  // 设置Service接口生成名称,这样生成接口前面就不会有 I
        gc.setEntityName("%sEntity");

        mpg.setGlobalConfig(gc);

        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://192.168.60.20:3306/cbvac_offer_server?useUnicode=true&serverTimezone=GMT&useSSL=false&characterEncoding=utf8");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("root");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(""); // 模块名称, 这里可以根据不同模块来写
        pc.setParent("com.cbvac"); // 父包名
        mpg.setPackageInfo(pc);



        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);
       // strategy.setSuperControllerClass("com.lcy.demo.sys.controller.BaseController");
        strategy.setSuperEntityClass("com.cbvac.entity.BaseEntity");
        strategy.setEntityLombokModel(true);
        strategy.setInclude("cbvac_project","cbvac_system","cbvac_parts","cbvac_material_bom","cbvac_process_pom");  // 如果要生成多个,这里可以传入String[]
        mpg.setStrategy(strategy);
        mpg.execute();
    }



}
