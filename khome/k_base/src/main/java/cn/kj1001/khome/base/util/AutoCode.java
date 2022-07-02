package cn.kj1001.khome.base.util;


import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * kj1001
 *
 * @Description : 代码生成器 可以直接运行
 * @Author : MrZhang
 * @Date: 2022/3/20 14:35
 */
public class AutoCode {
   static String moudelName = "admin";

    /**
    * 参数介绍:
    * @description: 功能描述 代码生成器 可以直接运行
    * @author: MrZhang
    * @date: 2022/3/20 22:30 
    * @param: [args]
    * @return: void  
    **/
    public static void main(String[] args) {

        //1.创建代码生成器对象 generator(英译:生成器)
        AutoGenerator generator = new AutoGenerator();

        //2.完成全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        //设置作者
        globalConfig.setAuthor("kj1001");
        //设置mapper前缀 通过数据库表名创建
        globalConfig.setMapperName("%sDao");
        //设置service
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        //设置代码输出的路径
        globalConfig.setOutputDir("e:\\myCode");
        //新生成则覆盖
        globalConfig.setFileOverride(true);
        //将全局配置对象存入 代码生成器中
        generator.setGlobalConfig(globalConfig);

        //3.配置数据源
        DataSourceConfig sourceConfig = new DataSourceConfig();
        //设置数据库类型
        sourceConfig.setDbType(DbType.MYSQL);
        //配置四大件
        sourceConfig.setDriverName("com.mysql.cj.jdbc.Driver");
        sourceConfig.setUrl("jdbc:mysql://127.0.0.1:3306/khome_dev?serverTimezone=Asia/Shanghai");
        sourceConfig.setUsername("root");
        sourceConfig.setPassword("123456");
        //将数据源对象存入 代码生成器中
        generator.setDataSource(sourceConfig);

        //4.策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        //去前缀
        strategyConfig.setTablePrefix("or","que","sto","sys","u");
        //将带下划线的字段转驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        //开启lombok
        strategyConfig.setEntityLombokModel(true);
        //开启链式编程
        strategyConfig.setChainModel(true);
        //开启restful风格 统一访问资源接口
        strategyConfig.setRestControllerStyle(true);
        //将策略存入 代码生成器中
        generator.setStrategy(strategyConfig);

        //包生成策略
        PackageConfig packageConfig = new PackageConfig();
        //设置项目包名
        packageConfig.setParent("cn.kj1001.khome");
        packageConfig.setMapper("base.dao");
        packageConfig.setEntity("base.entity");
        //moudelName(admin)
        packageConfig.setService(moudelName+".service");
        packageConfig.setServiceImpl(moudelName+".service.impl");
        packageConfig.setController(moudelName+".controller");
        generator.setPackageInfo(packageConfig);

        //6.执行并生成代码
        generator.execute();
    }
}
 
