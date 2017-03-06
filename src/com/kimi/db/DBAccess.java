package com.kimi.db;

import java.io.IOException;
import java.io.Reader;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

/**
 * 访问数据库
 * @author geek
 *
 */
public class DBAccess {
	public SqlSession getSqlSession() throws IOException{
		//1.加载配置文件获取配置信息
		Reader reader = Resources.getResourceAsReader("com/kimi/config/Configuration.xml");
		//2.构建一个SqlSessionFactory
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		//3.通过SqlSessionFactory打开一个数据库会话
		return sqlSessionFactory.openSession();
		
	}
}
