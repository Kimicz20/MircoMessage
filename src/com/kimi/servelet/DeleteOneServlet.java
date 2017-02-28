package com.kimi.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.kimi.service.DeleteOneService;

public class DeleteOneServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DeleteOneService deleteOneService = new DeleteOneService();
		//1.����ҳ�����
		request.setCharacterEncoding("UTF-8");
		//2.��ȡ��ѯ����
		String id = request.getParameter("id");
		System.out.println(id);
		//3.��ѯ
		deleteOneService.deleteOneMessage(id);
		//4.��ѯҳ����ת
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
