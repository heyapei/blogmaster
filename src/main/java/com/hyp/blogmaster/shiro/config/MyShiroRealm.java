package com.hyp.blogmaster.shiro.config;

import com.hyp.blogmaster.shiro.pojo.modal.AdminResources;
import com.hyp.blogmaster.shiro.pojo.modal.AdminUser;
import com.hyp.blogmaster.shiro.service.AdminResourcesService;
import com.hyp.blogmaster.shiro.service.AdminUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.mgt.RealmSecurityManager;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.util.ByteSource;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by yangqj on 2017/4/21.
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {

    @Resource
    private AdminUserService adminUserService;

    @Resource
    private AdminResourcesService adminResourcesService;

    @Autowired
    private RedisSessionDAO redisSessionDAO;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AdminUser user = (AdminUser) SecurityUtils.getSubject().getPrincipal();//User{id=1, username='admin', password='3ef7164d1f6167cb9f2658c07d3c2f0a', enable=1}
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userid", user.getId());
        log.info("授权信息：{}" + map.toString());
        List<AdminResources> resourcesList = adminResourcesService.loadUserResources(map);
        for (AdminResources adminResources : resourcesList) {
            log.info("查询结果：" + adminResources.toString());
        }
        // 权限信息对象info,用来存放查出的用户的所有的角色（role）及权限（permission）
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (AdminResources resources : resourcesList) {
            info.addStringPermission(resources.getResUrl());
            /*这里没有加 感觉够了好吧*/
            /*info.addRole();*/
        }

        log.info("授权结果：" + info.getStringPermissions().toString());

        return info;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //获取用户的输入的账号.
        String username = (String) token.getPrincipal();
        AdminUser user = adminUserService.selectByUsername(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        if (1 == user.getEnable()) {
            throw new LockedAccountException(); // 帐号锁定
        }
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
                user, //用户
                user.getPassWord(), //密码
                ByteSource.Util.bytes(username),
                getName()  //realm name
        );
        // 当验证都通过后，把用户信息放在session里
        Session session = SecurityUtils.getSubject().getSession();
        session.setAttribute("userSession", user);
        session.setAttribute("userSessionId", user.getId());
        return authenticationInfo;
    }


    /**
     * 根据userId 清除当前session存在的用户的权限缓存
     *
     * @param userIds 已经修改了权限的userId
     */
    public void clearUserAuthByUserId(List<Integer> userIds) {
        if (null == userIds || userIds.size() == 0) {
            return;
        }
        //获取所有session
        Collection<Session> sessions = redisSessionDAO.getActiveSessions();
        //定义返回
        List<SimplePrincipalCollection> list = new ArrayList<SimplePrincipalCollection>();
        for (Session session : sessions) {
            //获取session登录信息。
            Object obj = session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
            if (null != obj && obj instanceof SimplePrincipalCollection) {
                //强转
                SimplePrincipalCollection spc = (SimplePrincipalCollection) obj;
                //判断用户，匹配用户ID。
                obj = spc.getPrimaryPrincipal();
                if (null != obj && obj instanceof AdminUser) {
                    AdminUser user = (AdminUser) obj;
                    System.out.println("user:" + user);
                    //比较用户ID，符合即加入集合
                    if (null != user && userIds.contains(user.getId())) {
                        list.add(spc);
                    }
                }
            }
        }
        RealmSecurityManager securityManager =
                (RealmSecurityManager) SecurityUtils.getSecurityManager();
        MyShiroRealm realm = (com.hyp.blogmaster.shiro.config.MyShiroRealm) securityManager.getRealms().iterator().next();
        for (SimplePrincipalCollection simplePrincipalCollection : list) {
            realm.clearCachedAuthorizationInfo(simplePrincipalCollection);
        }
    }
}
