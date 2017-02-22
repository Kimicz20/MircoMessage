package com.kimi.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.kimi.bean.Message;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
/**
 * ���ݿ������Լ�����
 * @author geek
 *
 */
public class MessageDao {
	
	
	public List<Message> queryMessage(String command, String description) {
		List<Message> messageList = new ArrayList<Message>();
		
		try {
			// 1.�������ݿ�
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message", "root", "1111");
			// 2.ƴ�Ӳ�ѯSQL
			StringBuffer sql = new StringBuffer("SELECT ID,COMMAND,DESCRIPTION,CONTENT FROM message WHERE 1=1 ");
			List<String> paramList = new ArrayList<String>();
			if (command != null && !"".equals(command)) {
				sql.append(" and COMMAND = ?");
				paramList.add(command);
			}
			if (description != null && !"".equals(description)) {
				sql.append(" and DESCRIPTION like '%' ? '%'");
				paramList.add(description);
			}
			PreparedStatement st = (PreparedStatement) conn.prepareStatement(sql.toString());
			for (int i = 0; i < paramList.size(); i++) {
				st.setString(i + 1, paramList.get(i));
			}
			// 3.ִ�в�ѯ���
			ResultSet rs = (ResultSet) st.executeQuery();
			// 4.��ѯ�����װ
			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
				messageList.add(message);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return messageList;
	}
}