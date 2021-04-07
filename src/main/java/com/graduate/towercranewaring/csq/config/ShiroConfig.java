package com.graduate.towercranewaring.csq.config;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @ClassName: ShiroConfig
 * @Description: 安全框架Shiro的配置类
 * @Author:csq
 * @Date 2021/4/6
 * @Version 1.0
 **/
@Configuration
public class ShiroConfig {

    //shiroFilterFactoryBean step3
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("SecurityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean=new ShiroFilterFactoryBean();

        //设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
         * @Author:csq
         * @Date 16:51 2021/4/6
         *
         * anon:无需认证就可以访问
         * authc:必须认证了才能访问
         * user:必须拥有  记住我  功能才能使用（较少使用）
         * perms:拥有对某个资源的权限才能访问
         * role:拥有某个角色权限才能访问
         **/

        Map<String,String> filterMap=new LinkedHashMap<>();



        filterMap.put("/login","anon");//login可以被所有用户访问
        filterMap.put("/equipment_list.html","perms[admin:all]");//认证了才能被访问
        filterMap.put("/equip/*","perms[admin:all]");//认证了才能被访问
        filterMap.put("/index","authc");//认证了才能被访问(认证了才能访问等同于登陆了才能被访问)

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);


        shiroFilterFactoryBean.setLoginUrl("/login");//若没有权限会跳转至login页面


        return shiroFilterFactoryBean;
    }



    //DefaultWebSecurityManager step2
    @Bean(name = "SecurityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager=new DefaultWebSecurityManager();
        //关联UserRealm
        defaultWebSecurityManager.setRealm(userRealm);
        //因为UserRealm被spring托管了，所以需要传参数来进行关联操作。通过Qualifier注释来


        return defaultWebSecurityManager;
    }

    //创建realm对象，需要自定义  step1。建议从下往上开始配置
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }



    @Bean
    //整合ShiroDialect：用来整合shiro thymeleaf
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }


}

