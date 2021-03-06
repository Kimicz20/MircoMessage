package com.kimi.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kimi.service.MaintainService;

public class DeleteBatchServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		MaintainService maintainService = new MaintainService();
		//1.设置页面编码
		request.setCharacterEncoding("UTF-8");
		//2.获取查询数据
		String[] ids = request.getParameterValues("id");
		//3.查询
		maintainService.deleteBatch(ids);
		//4.查询页面跳转
		request.getRequestDispatcher("/List.action").forward(request, response);
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
