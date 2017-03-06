package com.kimi.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * �������ݿ�
 * @author geek
 *
 */
public class DBAccess {
	public SqlSession getSqlSession() throws IOException{
		//1.���������ļ���ȡ������Ϣ
		Reader reader = Resources.getResourceAsReader("com/kimi/config/Configuration.xml");
		//2.����һ��SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//3.ͨ��SqlSessionFactory��һ�����ݿ�Ự
		return sqlSessionFactory.openSession();
		
	}
}
