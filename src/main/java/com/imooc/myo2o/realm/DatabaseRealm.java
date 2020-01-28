package com.imooc.myo2o.realm;

import com.imooc.myo2o.entity.User;
import com.imooc.myo2o.service.PermissionService;
import com.imooc.myo2o.service.RoleService;
import com.imooc.myo2o.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;


public class DatabaseRealm extends AuthorizingRealm {
 
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private PermissionService permissionService;

    /**
    * @ Description:  提供用户信息返回权限信息，查询根据认证的数据获取到的权限信息
    * @ Param:  principalCollection 包含了所有的已认证的安全数据
    * @ return:  AuthorizationInfo 授权数据
    * @ Author: phl
    * @ Date: 2019/10/2
    * @ version 1.0
    */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 能进入到这里，表示账号已经通过验证了
        // 得到唯一的安全数据
        // 1.
//        String userName =(String) principalCollection.getPrimaryPrincipal();
        //通过service获取角色和权限
//        Set<String> permissions = permissionService.listPermissions(userName);
//        Set<String> roles = roleService.listRoleNames(userName);
        User user = (User)principalCollection.getPrimaryPrincipal();
        Set<String> permissions=new HashSet<>();
        Set<String> roles=new HashSet<>();
        // 2.

        //授权对象
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        //把通过service获取到的角色和权限放进去
        s.setStringPermissions(permissions);
        s.setRoles(roles);
        return s;


    }


    /**
     * 提供账户信息返回认证信息,登录用
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取账号密码
        UsernamePasswordToken t = (UsernamePasswordToken) token;
        String userName= token.getPrincipal().toString();
        //获取数据库中的密码
        User user =userService.getByName(userName);
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        //认证信息里存放账号密码, getName() 是当前Realm的继承方法,通常返回当前类名 :databaseRealm
        //盐也放进去
        //这样通过applicationContext-shiro.xml里配置的 HashedCredentialsMatcher 进行自动校验
        SimpleAuthenticationInfo a = new SimpleAuthenticationInfo(userName,passwordInDB,ByteSource.Util.bytes(salt),getName());
        return a;
    }
 
}