package com.cafe24.mysite.repository;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.SiteVo;

@Repository
public class MainDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public SiteVo select() {
		return sqlSession.selectOne("main.getMainpage"); 
	}
}
