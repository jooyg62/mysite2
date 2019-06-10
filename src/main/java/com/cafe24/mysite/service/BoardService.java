package com.cafe24.mysite.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cafe24.mysite.repository.BoardDao;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.FileVo;
import com.cafe24.mysite.vo.UserVo;

@Service
public class BoardService {
	
	@Autowired
	BoardDao boardDao;
	
	@Autowired
	FileUploadService fileUploadService;
	
	public Boolean writeBoard(BoardVo boardVo) {
		MultipartFile attach = boardVo.getAttach();
		FileVo fileVo = fileUploadService.restore(attach);
		boardVo.setOriginalFilename(fileVo.getOriginalFilename());
		boardVo.setSaveFileName(fileVo.getSaveFileName());
		boardVo.setExtName(fileVo.getExtName());
		boardVo.setImgUrl(fileVo.getUrl());
		
		return 1 == boardDao.insert(boardVo);
	}
	
	public List<BoardVo> getBoardList(Map<String, Object> map) {
		return boardDao.select(map);
	}
	
	public BoardVo getBoard(Long no) {
		return boardDao.get(no);
	}

	public Boolean deleteBoard(Long no) {
		return 1 == boardDao.delete(no);
	}

	public Boolean modifyBoard(BoardVo boardVo) {
		return 1 == boardDao.update(boardVo);
	}

	public BoardVo showBoardDetail(Long no, boolean isFirstView) {
		if(isFirstView) {
			boardDao.addHitCount(no);
		}
		
		return boardDao.get(no);
	}

	public int getBoardTotalCount(String keyword) {
		return boardDao.getBoardTotalCount(keyword);
	}
	
	public boolean isCheckMyBoard(Long boardUserNo, UserVo authUser) {
		if(authUser == null && authUser.getNo() != boardUserNo) {
			return false;
		}
		
		return true;
	}

}
