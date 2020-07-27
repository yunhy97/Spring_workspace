package kr.co.jhta.util;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class MybatisUtil {
	/*
	 * mybatis의 주요 API
	 * 		-SqlSession
	 * 			매퍼파일에 정의된 SQL문을 실행하는 객체다.
	 * 			Object			insert(String id)
	 * 			Object			insert(String id, Object param)
	 * 			int				delete(String id)
	 * 			int				delete(String id, Object param)
	 * 			int				update(String id)
	 *			int				update(String id, Object param)
	 *			Object 			selectOne(String id)
	 *			Object 			selectOne(String id, Object param)
	 *			List 			selectList(String id)
	 *			List 			selectList(String id, Object parm)
	 *			void			close()
	 *			void			commit()
	 *			void			rollback()
	 *			주의사항) 쿼리를 한 번 실행할 때 마다 SqlSessionFactory로부터 
	 *					새로운 SqlSession객체를 획득해서 사용하고, 폐기한다.
	 *
	 * 		-SqlSessionFactory
	 * 			쿼리 실행에 필요한 SqlSession 객체를 제공하는 객체다.
	 * 			애플리케이션이 실행되는 동안 딱 한번만 생성해서 계속 사용한다.
	 * 			SqlSession openSession()
	 * 				*새로운 SqlSession 객체를 반환한다.
	 * 
	 * 		-SqlSessionFactoryBuilder
	 * 			mybatis 환결설정파일(/src/main/resource/mybatis/mybaits-config.xml)를 이용해서 
	 * 			SqlSessionFactory객체를 생성한다.
	 * 			SqlSessionFactory build(InputStream inputStream)
	 */
	private static SqlSessionFactory sqlSessionFactory;
	
	static {
		try {
			//mybatis 환경설정파일의 경로 및 파일명 지정
			String resource = "mybatis/mybatis-config.xml";
			//mybatis 환경설정파일을 읽어오는 스트림생성
			InputStream inputStream = Resources.getResourceAsStream(resource);
			//스트림을 이용해서 mybatis 환경설정 파일을 읽어서 SqlSessionFactory 객체 획득
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * SqlSessionFactory 객체를 반환한다.
	 * @return SqlSessionFactory 객체
	 */
	public static SqlSessionFactory getSqlSessionFactory() {
		return sqlSessionFactory;
	}
	
	/**
	 * SQL 구문을 실행하는 SqlSession객체를 반환한다.
	 * @return SqlSession 객체(INSERT/UPDATE/DELETE/SELECT, 트랜잭션 기능 제공)
	 */
	public static SqlSession getSqlSession() {
		return sqlSessionFactory.openSession();
	}
}
