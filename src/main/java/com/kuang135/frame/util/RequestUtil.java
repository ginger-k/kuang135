package com.kuang135.frame.util;

import java.beans.PropertyDescriptor;
import java.text.SimpleDateFormat;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang3.StringUtils;



public class RequestUtil {

	private static ThreadLocal<HttpServletRequest> requestLocal= new ThreadLocal<HttpServletRequest>();
	private static ThreadLocal<HttpServletResponse> responseLocal= new ThreadLocal<HttpServletResponse>();
	
	private RequestUtil(){
		throw new AssertionError();
	}
	
	public static HttpServletRequest getRequest() {
		return (HttpServletRequest)requestLocal.get();
	}
	public static void setRequest(HttpServletRequest request) {
			requestLocal.set(request);
	}
	public static HttpServletResponse getResponse() {
		return (HttpServletResponse)responseLocal.get();
	}
	public static void setResponse(HttpServletResponse response) {
		responseLocal.set(response);
	}
	public static HttpSession getSession() {
		return (HttpSession)((HttpServletRequest)requestLocal.get()).getSession();
	}
	
	
	/**将request中的数据封装到bean中，支持类型String,Integer,Date(yyyy-MM-dd),Double,Float,Long
	 * @param request
	 * @param clazz
	 * @return
	 * @throws Exception
	 */
	public static <T> T getBean(HttpServletRequest request,Class<T> clazz) throws Exception{
			T bean=clazz.newInstance();
			@SuppressWarnings("unchecked")
			Enumeration<String> names = request.getParameterNames();
			while(names.hasMoreElements()){
				String name = names.nextElement();
				String value = request.getParameter(name);
				if(StringUtils.isNotBlank(value)){
					//如果没有这个字段，就判断下个字段
					PropertyDescriptor descriptor=null;
					try{
						descriptor=new PropertyDescriptor(name,clazz);
					}catch(java.beans.IntrospectionException e){
						continue;
					}
					Class<?> type = descriptor.getPropertyType();
					Object obj=null;
					if(type==String.class){
						obj=value;
					}else if(type==Integer.class){
						obj=Integer.parseInt(value);
					}else if(type==java.util.Date.class){
						obj= new SimpleDateFormat("yyyy-MM-dd").parse(value);
					}else if(type==Double.class){
						obj=Double.parseDouble(value);
					}else if(type==Float.class){
						obj=Float.parseFloat(value);
					}else if(type==Long.class){
						obj=Long.parseLong(value);
					}else{
						throw new RuntimeException("不支持该类型");
					}
					BeanUtils.setProperty(bean, name, obj);
				}
			}
			return bean;
	}
	
	/**获取请求的真实ip(包括nginx代理的情况)
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			//多次反向代理后会有多个ip值，第一个ip才是真实ip
			int index = ip.indexOf(",");
	        if(index != -1){
	            return ip.substring(0,index);
	        }else{
	           return ip;
	        }
		}
		ip = request.getHeader("X-Real-IP");
		if(StringUtils.isNotEmpty(ip) && !"unKnown".equalsIgnoreCase(ip)){
			return ip;
		}
	    return request.getRemoteAddr();
	}
	
}
