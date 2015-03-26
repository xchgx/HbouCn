package com.xchgx.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xchgx.cons.CommonConstant;
import com.xchgx.domain.User;

public class ManagerFilter implements Filter{

	private static final String FILTERED_REQUEST = "@@session_context_filtered_request";
	
//	/**
//	 * 不需要登陆即可访问的URI资源
//	 */
//	private static final String[] INHERENT_ESCAPE_URIS={
//		"/loginView.do"
//	};
	
	@Override
	public void destroy() {
		
	}


	protected User getSessionUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(CommonConstant.USER_CONTEXT);
	}
	/**
	 * 执行过滤
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		//1保证该过滤器在一次请求中只被调用一次
		if(request != null && request.getAttribute(FILTERED_REQUEST) != null){
			chain.doFilter(request, response);
		}else{
			System.out.println("进入managerfilter过滤器设置过滤标识，防止一次请求多次过滤");
			//设置过滤标识，防止一次请求多次过滤
			request.setAttribute(FILTERED_REQUEST, Boolean.TRUE);
			HttpServletRequest httpRequest = (HttpServletRequest) request;
			User userContext = getSessionUser(httpRequest);
			
			//用户未登录，且当前URI资源需要登陆才能访问
			//if(userContext == null && !isURILogin(httpRequest.getRequestURI(), httpRequest)){
			if(userContext == null){
				/*
				 
				String toUrl = httpRequest.getRequestURI().toString();
				if(!StringUtils.isEmpty(httpRequest.getQueryString())){
					toUrl += "?"+httpRequest.getQueryString();
				}
				
				//将用户的请求URL保存在session中，用于登陆成功之后，跳到目标URL
				httpRequest.getSession().setAttribute(CommonConstant.LOGIN_TO_URL, toUrl);
				*/
				
				
				//转发到登陆页面
				//request.getRequestDispatcher("/user/loginView.do").forward(request, response);
				HttpServletResponse res = (HttpServletResponse)response;
				HttpServletRequest req = (HttpServletRequest) request;
				res.sendRedirect(req.getContextPath() + "/jumpLoginView.jsp");
				return;
			}
			chain.doFilter(request, response);
		}
	}
//	/**
//	 * 当前URI资源是否需要登录才能访问
//	 * @param requestURI
//	 * @param request
//	 * @return
//	 */
//	private boolean isURILogin(String requestURI, HttpServletRequest request) {
//		if (request.getContextPath().equalsIgnoreCase(requestURI)
//				|| (request.getContextPath() + "/").equalsIgnoreCase(requestURI))
//			return true;
//		for (String uri : INHERENT_ESCAPE_URIS) {
//			if (requestURI != null && requestURI.indexOf(uri) >= 0) {
//				return true;
//			}
//		}
//		return false;
//	}
	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	
}
