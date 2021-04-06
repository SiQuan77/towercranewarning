package com.graduate.towercranewaring.csq.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: DruidConfig
 * @Description: Druid数据源的使用配置
 * @Author:csq
 * @Date 2021/4/6
 * @Version 1.0
 **/
//配置的第一步就是在类上添加注解@Configuration
@Configuration
public class DruidConfig {

    //第二步将yaml配置文件和这个配置类绑定起来
    @ConfigurationProperties(prefix = "spring.datasource")
    @Bean
    public DataSource druidDataSource(){
        return new DruidDataSource();
    }


    //后台监控功能（这段代码是死的）
    @Bean
    public ServletRegistrationBean listen_sql(){
        //监控页面是后台帮你写好的，可以直接拿来用
        ServletRegistrationBean<StatViewServlet> statViewServletServletRegistrationBean=
                new ServletRegistrationBean<>(new StatViewServlet(),"/druid/*");

        HashMap<String,String> initParameters=new HashMap<>();
        initParameters.put("loginUsername","admin");//固定的
        initParameters.put("loginPassword","c123456");//固定的

        //允许谁可以访问
        initParameters.put("allow","");




        //后台需要有人登陆，账号密码配置.
        statViewServletServletRegistrationBean.setInitParameters(initParameters);//用hashmap来设置初始化参数
        return statViewServletServletRegistrationBean;
    }

    //过滤器
    @Bean
    public FilterRegistrationBean webStatFilter(){
        FilterRegistrationBean bean=new FilterRegistrationBean();
        bean.setFilter(new WebStatFilter());

        //过滤哪些请求呢？
        Map<String,String> initParameters=new HashMap<>();
        //这些东西不进行统计
        initParameters.put("exclusions","*.js,*.css,/druid/*");
        bean.setInitParameters(initParameters);
        return bean;
    }


}

