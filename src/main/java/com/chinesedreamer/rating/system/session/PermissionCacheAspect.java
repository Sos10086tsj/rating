//package com.chinesedreamer.rating.system.session;
//
//import javax.servlet.http.HttpServletRequest;
//
//import org.aspectj.lang.annotation.Aspect;
//import org.aspectj.lang.annotation.Before;
//import org.aspectj.lang.annotation.Pointcut;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
//import com.cyyun.ga.contact.common.base.SessionFilter;
//import com.cyyun.ga.contact.common.cache.BaseCacheAspect;
//import com.cyyun.ga.contact.system.permission.exception.PermissionDefinedException;
//import com.cyyun.ga.contact.system.permission.service.PermissionValid;
//import com.cyyun.ga.contact.system.session.model.UserPermission;
//
///**
// * Description: 
// * @author Paris
// * @date Feb 6, 201511:57:10 AM
// * @version beta
// */
//@Component
//@Aspect
//public class PermissionCacheAspect extends BaseCacheAspect{
//	
//	private final Logger logger = LoggerFactory.getLogger(PermissionCacheAspect.class);
//
//	private final String userPermissionCacheName = "userPermission";
//	private final String userPermissionPrefix = "user-permission-";
//	
//	public PermissionCacheAspect(){
//		setCacheName(userPermissionCacheName);
//	}
//	
//	@Pointcut("@annotation(com.cyyun.ga.contact.system.permission.service.PermissionValid)")
//	public void checkUserPermission(){};
//
//	@Before(value = "com.cyyun.ga.contact.system.permission.cache.PermissionCacheAspect.checkUserPermission()&&"
//			+ "@annotation(permissionValid)", argNames = "permissionValid")
//	public void checkPermission(PermissionValid pv){		
//		HttpServletRequest request = SessionFilter.SessionContext.getContext();
//		UserPermission userSession = (UserPermission)get( userPermissionPrefix + request.getSession().getId());
//		logger.info("访问清幽用户:{} 没有权限:{}:{}！" , request.getParameter("user"), pv.resource(),pv.operation());
//		if (null == userSession || !userSession.getPermissions().contains(pv.resource() + ":" + pv.operation())) {
//			throw new PermissionDefinedException("您没有访问权限，请重新登录或联系管理员!");
//		}
//	}
//}
