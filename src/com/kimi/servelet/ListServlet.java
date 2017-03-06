package com.kimi.servelet;

import java.io.IOException;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kimi.entity.Page;
import com.kimi.service.QueryService;

/**
 * 列表页面初始化
 */
public class ListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ListServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		QueryService queryService = new QueryService();
		
		//1.设置页面编码
		request.setCharacterEncoding("UTF-8");
		//2.获取查询数据
		String command = request.getParameter("command");
		String description = request.getParameter("description");
		String currentPage = request.getParameter("currentPage");
		//3.创建分页对象
		Page page  = new Page();
		if(currentPage == null || !Pattern.compile("[0-9]{1,9}").matcher(currentPage).matches()){
			page.setCurrentPage(1);
		}else{
			page.setCurrentPage(Integer.valueOf(currentPage));
		}
		//4.查询
		request.setAttribute("messageList", queryService.queryMessageWithPage(command, description, page));
		//5.向页面传值
		request.setAttribute("command", command);
		request.setAttribute("description", description);
		request.setAttribute("page", page);
		//6.查询页面跳转
		request.getRequestDispatcher("/WEB-INF/jsp/back/list.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
