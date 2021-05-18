package com.yy.yeb;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableFill;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyAutoGenerator {

    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + ": ");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String next = scanner.next();
            if (StringUtils.isNotEmpty(next)) {
                return next;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip +"!");
    }

    public static void main(String[] args) {
// 需要构建一个 代码自动生成器 对象
        AutoGenerator autoGenerator = new AutoGenerator();
// 配置策略
// 1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");
        globalConfig.setOutputDir(projectPath + "/src/main/java");
        globalConfig.setOpen(false);
        globalConfig.setFileOverride(false); // 是否覆盖
        globalConfig.setServiceName("%sService"); // 去Service的I前缀
        globalConfig.setIdType(IdType.ID_WORKER);
        globalConfig.setDateType(DateType.ONLY_DATE);
        globalConfig.setBaseResultMap(true);//xml开启BaseResultMap
        globalConfig.setBaseColumnList(true);//xml 开启BaseColumn
        globalConfig.setSwagger2(true);
        autoGenerator.setGlobalConfig(globalConfig);
//2、设置数据源
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setUrl("jdbc:mysql://localhost:3306/yeb?useSSL=false&userUnicode=true&characterEncoding=UTF-8");
        dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
        dataSourceConfig.setUsername("root");
        dataSourceConfig.setPassword("123456");
        dataSourceConfig.setDbType(DbType.MYSQL);
        autoGenerator.setDataSource(dataSourceConfig);
//3、包的配置
        PackageConfig packageConfig = new PackageConfig();
//        packageConfig.setModuleName("blog");
        packageConfig.setParent("com.yy.yeb");
        packageConfig.setEntity("entity");
        packageConfig.setMapper("mapper");
        packageConfig.setService("service");
        packageConfig.setController("controller");
        autoGenerator.setPackageInfo(packageConfig);
//4、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setInclude(scanner("请输入表名，多个用英文逗号分割").split(",")); // 设置要映射的表名
//        strategyConfig.setInclude("t_admin,t_admin_role,t_appraise,t_department,t_employee,t_employee_ec,t_employee_remove,t_employee_train,t_joblevel,t_mail_log,t_menu,t_menu_role,t_nation,t_oplog,t_politics_status,t_position,t_role,t_salary,t_salary_adjust,t_sys_msg,t_sys_msg_content");
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setEntityLombokModel(true); // 自动lombok；
        strategyConfig.setLogicDeleteFieldName("deleted");
        strategyConfig.setTablePrefix("t_");
// 自动填充配置
        TableFill gmtCreate = new TableFill("create_time", FieldFill.INSERT);
        TableFill gmtModified = new TableFill("update_time", FieldFill.INSERT_UPDATE);
        ArrayList<TableFill> tableFills = new ArrayList<>();
        tableFills.add(gmtCreate);
        tableFills.add(gmtModified);
        strategyConfig.setTableFillList(tableFills);
// 乐观锁
        strategyConfig.setVersionFieldName("version");strategyConfig.setRestControllerStyle(true);
        strategyConfig.setControllerMappingHyphenStyle(true);  //localhost:8080/hello_id_2
        autoGenerator.setStrategy(strategyConfig);
        autoGenerator.execute(); //执行
    }
}