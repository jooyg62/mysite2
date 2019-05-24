package com.cafe24.mysite.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cafe24.mysite.vo.BoardVo;

@Repository
public class BoardDao {
	
	@Autowired
	SqlSession sqlSession;
	
	public int insert(BoardVo boardVo) {
		int count = sqlSession.insert("board.insert", boardVo);
		return count; 
	}
	
	public List<BoardVo> select(Map<String, Object> map) {
		List<BoardVo> boardList = sqlSession.selectList("board.select", map);
		return boardList;
	}
	
	public BoardVo get(Long no) {
		BoardVo boardVo = sqlSession.selectOne("board.selectOne", no);
		return boardVo; 
	}
	
	public int delete(Long no) {
		int count = sqlSession.delete("board.delete", no);
		return count;
	}

	public int update(BoardVo boardVo) {
		int count = sqlSession.update("board.update", boardVo);
		return count;
	}

	public int addHitCount(Long no) {
		int count = sqlSession.update("board.updateHitCount", no);
		return count;
	}

	public int getBoardTotalCount(String keyword) {
		int count = sqlSession.update("board.getBoardTotalCount", keyword);
		return count;
	}
}
