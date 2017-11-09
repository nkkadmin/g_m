package com.xsx.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @Title: IfNeedLogin.java 
 * @Package com.xsx.annotation 
 * @Description: 自定义注解，需要登陆
 * @author xsx
 * @date 2017年10月31日 下午5:24:37 
 * @version V1.0
 */

@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface IfNeedLogin {

	public boolean needLogin() default true;
}
