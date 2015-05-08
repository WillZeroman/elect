package cn.xxx.elec.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.xxx.elec.domain.ElecUser;

public class LoginFilter implements Filter {
	private List<String> list = new ArrayList<String>();
	@Override
	public void init(FilterConfig arg0) throws ServletException {		
		list.add("/index.jsp");
		list.add("/image.jsp");
		list.add("/system/elecMenuAction_home.do");
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		HttpServletRequest request = (HttpServletRequest) servletRequest;
		//获取页面的访问路径
		String path = request.getServletPath();
		if(list!=null && list.contains(path)){
			chain.doFilter(request, response);
			return;
		}
		//从session中获取global_user
		ElecUser elecUser = (ElecUser) request.getSession().getAttribute("global_user");
		if(elecUser!=null){
			chain.doFilter(request, response);
			return;
		}
		response.sendRedirect(request.getContextPath() + "/");
	}



}
