package com.kimi.dao;

import java.io.IOException;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.BlockJUnit4ClassRunner;

import com.kimi.bean.Message;
import com.kimi.db.DBAccess;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
/**
 * 数据库连接以及操作
 * @author geek
 *
 */

public class MessageDao {
	
	/**
	 * JDBC 查询
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> JDBCQueryMessage(String command, String description) {
		List<Message> messageList = new ArrayList<Message>();
		
		try {
			// 1.连接数据库
			Class.forName("com.mysql.jdbc.Driver");
			Connection conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/micro_message", "root", "1111");
			// 2.拼接查询SQL
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
			// 3.执行查询语句
			ResultSet rs = (ResultSet) st.executeQuery();
			// 4.查询结果封装
			while (rs.next()) {
				Message message = new Message();
				message.setId(rs.getString("ID"));
				message.setCommand(rs.getString("COMMAND"));
				message.setDescription(rs.getString("DESCRIPTION"));
				message.setContent(rs.getString("CONTENT"));
				messageList.add(message);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return messageList;
	}
	
	/**
	 * 查询
	 * @param command
	 * @param description
	 * @return
	 */
	public List<Message> queryMessage(Map<String,Object> parameter) {
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		List<Message> messageList = new ArrayList<Message>();		
		try {
			//1.获取SqlSession
			sqlSession = access.getSqlSession();
			//2.执行SQL语句
			//messageList = sqlSession.selectList("Message.queryMessage", message);
			//使用接口编程方式
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			messageList = imessage.queryMessageList(parameter);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
		return messageList;
	}
	/**
	 * 根据查询条件查询消息列表的条数
	 * @param message
	 * @return
	 */
	public int count(Message message){
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		int result = 0;
		try {
			//1.获取SqlSession
			sqlSession = access.getSqlSession();
			//2.执行SQL语句
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			result = imessage.count(message);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
		return result;
		
	}
	/**
	 * 单条删除
	 * @param id
	 */
	public void deleteOneMessage(int id){
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		try {
			//1.获取SqlSession
			sqlSession = access.getSqlSession();
			//2.执行SQL语句
//			sqlSession.delete("Message.deleteOne", id);
			//接口式编程
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.deleteOne(id);
			//3.事务提交
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
	}
	/**
	 * 批量删除
	 * @param ids
	 */
	public void deleteBatch(List<Integer> ids){
		DBAccess access = new DBAccess();
		SqlSession sqlSession = null;
		try {
			//1.获取SqlSession
			sqlSession = access.getSqlSession();
			//2.执行SQL语句
//			sqlSession.delete("Message.deleteBatch", ids);
			//接口式编程
			IMessage imessage = sqlSession.getMapper(IMessage.class);
			imessage.deleteBatch(ids);
			//3.事务提交
			sqlSession.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(sqlSession !=null){
				sqlSession.close();
			}
		}
	}
	
}
