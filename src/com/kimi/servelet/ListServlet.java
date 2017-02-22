package com.kimi.servelet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kimi.service.ListService;

/**
 * �б�ҳ���ʼ��
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
		ListService listService = new ListService();
		
		//1.����ҳ�����
		request.setCharacterEncoding("UTF-8");
		//2.��ȡ��ѯ����
		String command = request.getParameter("command");
		String description = request.getParameter("description");
		request.setAttribute("command", command);
		request.setAttribute("description", description);
		//3.��ѯ
		request.setAttribute("messageList", listService.queryMessage(command, description));
		//4.��ѯҳ����ת
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
