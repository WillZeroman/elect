package cn.xxx.elec.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.xxx.elec.service.ElecUserService;
import cn.xxx.elec.service.impl.ElecUserServiceImpl;

public class CheckLoginName extends HttpServlet {
	private ElecUserService eus = null;
	
	public CheckLoginName() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String loginName = request.getParameter("loginName");
		System.out.println(loginName);
		PrintWriter out = response.getWriter();
		/*
		 * loginFlag 表示数据库中是否有loginName
		 *  1：表示有重复值
		 *  0：无重复值
		 * 
		 * */
		int loginFlag = 0;
		loginFlag = eus.checkLoginName(loginName);
		out.print(loginFlag);
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		ApplicationContext atx = new ClassPathXmlApplicationContext("applicationContext.xml");
		eus = (ElecUserService) atx.getBean(ElecUserService.SERVICE_NAME);		
	}

}
