package com.cafe24.mysite.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cafe24.mysite.security.Auth;
import com.cafe24.mysite.security.AuthUser;
import com.cafe24.mysite.service.BoardService;
import com.cafe24.mysite.vo.BoardVo;
import com.cafe24.mysite.vo.UserVo;

@RequestMapping("/board")
@Controller
public class BoardController {
	
	public static final int PAGE_SIZE = 2;
	public static final int PAGE_RANGE = 5;
	
	@Autowired
	BoardService boardService;

	/**
	 * 게시글 리스트 조회
	 */
	@RequestMapping(value="", method=RequestMethod.GET)
	public String list(
			@RequestParam(value="kwd", required=false, defaultValue="") String keyword,
			@RequestParam(value="p", required=false, defaultValue="1") int pageNo,
			@AuthUser UserVo authUser,
			Model model
	) {
		int boardTotalCount = boardService.getBoardTotalCount(keyword);
		
		//(게시글 총수)/(페이지 사이즈)+1 = 전체 페이지 범위 총 수
		//(PAGE_RANGE) * (페이지 사이즈) = 한 페이지 구획 갯수
		int pageRangeNo = ((boardTotalCount/PAGE_SIZE)+1) / (PAGE_RANGE * PAGE_SIZE);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("keyword", keyword);
		map.put("pageNo", pageNo);
		map.put("pageSize", PAGE_SIZE);
		map.put("offset", PAGE_SIZE * (pageNo-1));
		
		model.addAttribute("list", boardService.getBoardList(map));
		model.addAttribute("authUser", authUser);
		model.addAttribute("pageNo", pageNo);
		model.addAttribute("pageRange", PAGE_RANGE);
		model.addAttribute("pageRangeNo", pageRangeNo);
		
		return "board/list";
	}
	
	/**
	 * 게시글 상세 조회
	 */
	@RequestMapping(value="/get/{no}", method=RequestMethod.GET)
	public String get(
			@PathVariable Long no,
			HttpServletRequest request,
			HttpServletResponse response,
			@AuthUser UserVo authUser,
			Model model
	) {
		if(authUser != null) {
			model.addAttribute("authUser", authUser);
		}
		
		boolean isFirstView = false; 
		
		Cookie[] cookies = request.getCookies();
		
		String visitBoardListString = "|";
		
		if(cookies != null && cookies.length > 0) {
			for(Cookie c : cookies) {
				if("visitBoardList".equals(c.getName())) {
					visitBoardListString = c.getValue();
					break;
				} 
			}
		}
			
		int indexOf = visitBoardListString.indexOf(String.valueOf(no));
		
		if(indexOf == -1) {
			//처음 진입하는 게시물이면 조회수 올리기
			isFirstView = true;
			
			//쿠키에 추가
			Cookie cookie = new Cookie("visitBoardList", visitBoardListString+"|"+String.valueOf(no));
			cookie.setMaxAge(24*60*60); //하루
			cookie.setPath(request.getContextPath());
			response.addCookie(cookie);
		}
		
		BoardVo boardVo = boardService.showBoardDetail(no, isFirstView);
		model.addAttribute("boardVo", boardVo);
		
		return "board/view";
	}
	
	/**
	 * 게시글 삭제
	 */
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value="/delete/{no}/{userNo}", method=RequestMethod.GET)
	public String delete(@PathVariable Long no, @AuthUser UserVo authUser) {
		
//		boardService.isCheckMyBoard(authUser, no);
		
		boardService.deleteBoard(no);
		
		return "redirect:/board";
	}
	
	/**
	 * 게시글 작성 화면 이동
	 * @return
	 */
	@Auth(role=Auth.Role.USER)
	@RequestMapping("/write")
	public String write() {
		return "board/write";
	}
	
	/**
	 * 게시글 작성
	 * @param boardVo
	 * @return
	 */
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(
			@ModelAttribute BoardVo boardVo,
			@AuthUser UserVo authUser
	) {
		boardVo.setUserNo(authUser.getNo());
		
		boardService.writeBoard(boardVo);
		
		return "redirect:/board";
	}
	
	/**
	 * 글쓰기 취소
	 * @return
	 */
	@RequestMapping("/cancle")
	public String cancle() {
		return "redirect:/board";
	}
	
	/**
	 * 게시글 수정 화면 접근
	 * @return
	 */
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value="/modify/{no}", method=RequestMethod.GET)
	public String modifyView(
			@PathVariable Long no,
			@AuthUser UserVo authUser,
			Model model) {
		BoardVo boardVo = boardService.getBoard(no);
		model.addAttribute("boardVo", boardVo);
		model.addAttribute("authUser", authUser);
		
		return "board/modify";
	}
	
	/**
	 * 게시글 수정
	 * @return
	 */
	@Auth(role=Auth.Role.USER)
	@RequestMapping(value="/modify/{no}/{userNo}", method=RequestMethod.POST)
	public String modify(
			@PathVariable Long no,
			@PathVariable Long userNo,
			@ModelAttribute BoardVo boardVo,
			Model model
		) {
		boardService.modifyBoard(boardVo);
		
		return "redirect:/board";
	}
	
}
