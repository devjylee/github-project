package net.softsociety.exam.controller;

import java.util.ArrayList;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;
import net.softsociety.exam.service.BoardService;

/**
 * 상품게시판 관련 콘트롤러
 */
@Slf4j
@RequestMapping("board")
@Controller
public class BoardController {
	
	@Autowired
	BoardService service;
	
	//글 목록
	@GetMapping("list")
	public String list(Model model) {
		ArrayList<Board> list = service.getList();
		model.addAttribute("list", list);
		return "list";
	}
	
	//글 쓰기
	@GetMapping("write")
	public String write() {
		return "write";
	}
	
	@PostMapping("write")
	public String insertBoard(Board board, @AuthenticationPrincipal UserDetails user) {
		board.setMemberid(user.getUsername());
		service.insertBoard(board);
		return "redirect:list";
	}
	
	//글 읽기
	@GetMapping("read")
	public String selectBoard(int boardnum, Model model) {
		Board board = service.selectBoard(boardnum);
		ArrayList<Reply> replyList = service.getReplyList(boardnum);
		model.addAttribute("board", board);
		model.addAttribute("replyList", replyList);
		return "read";
	}
	
	//글 삭제
	@GetMapping("delete")
	public String deleteBoard(int boardnum) {
		service.deleteBoard(boardnum);
		return "redirect:list";
	}
	
	//구매하기
	@GetMapping("buy")
	public String buy(@AuthenticationPrincipal UserDetails user, int boardnum) {
		Board board=service.selectBoard(boardnum);
		board.setBuyerid(user.getUsername());
		service.buy(board);
		return "redirect:list";
	}
	
	//댓글 쓰기
	@PostMapping("replyWrite")
	public String replyWrite(@AuthenticationPrincipal UserDetails user, int boardnum, String replytext) {
		Reply reply=new Reply();
		reply.setBoardnum(boardnum);
		reply.setMemberid(user.getUsername());
		reply.setReplytext(replytext);
		service.insertReply(reply);
		return "redirect:read?boardnum="+boardnum;
	}
	
	//글 검색
	@GetMapping("search")
	public String search() {
		return "search";
	}
	
	//ajax 리스트
	@ResponseBody
	@GetMapping("list2")
	public ArrayList<Board> list2() {
		ArrayList<Board> list = service.getList();
		return list;
	}
	
	//카테고리 검색
	@ResponseBody
	@PostMapping("searchCategory")
	public ArrayList<Board> searchCategory (String category) {
		ArrayList<Board> categoryList = service.searchCategory(category);
		return categoryList;
	}
	
	//단어 검색
	@ResponseBody
	@PostMapping("searchWord")
	public ArrayList<Board> searchWord(String keyword, String category) {
		log.debug("keyword: {}",keyword);
		log.debug("keyword: {}",category);
		ArrayList<Board> searchList = service.searchWord(keyword,category);
		return searchList;
	}
	
}
