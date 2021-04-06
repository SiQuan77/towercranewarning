package com.graduate.towercranewaring.csq.config;

import com.graduate.towercranewaring.csq.pojo.User;
import com.graduate.towercranewaring.csq.service.UserServiceImpl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @ClassName: UserRealm
 * @Description: 用于配合ShiroConfig进行配置
 * @Author:csq
 * @Date 2021/4/6
 * @Version 1.0
 **/
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private UserServiceImpl userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();

        Subject subject = SecurityUtils.getSubject();
        User currentUser=(User) subject.getPrincipal();//拿到user对象

        //设置当前用户的权限
        info.addStringPermission(currentUser.getPerms());

        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token=(UsernamePasswordToken) authenticationToken;
        User user=userService.getUserById(token.getUsername());
        if(user==null){//说明无这个用户
            return null;
        }

        //密码可以加密：MD5或者MD5盐值加密
        //密码认证是shiro来做的！
        return new SimpleAuthenticationInfo(user,user.getPassword(),"");//第一个传的user，则后面的授权就可以直接取user出来了
    }
}
