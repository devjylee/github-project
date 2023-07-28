package net.softsociety.exam.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.undo.AbstractUndoableEdit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import net.softsociety.exam.dao.BoardDAO;
import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;


@Transactional
@Service
public class BoardSeviceImpl implements BoardService {

	@Autowired
	BoardDAO dao;
	
	@Override
	public void insertBoard(Board board) {
		dao.insertBoard(board);
	}

	@Override
	public ArrayList<Board> getList() {
		return dao.getList();
	}

	@Override
	public Board selectBoard(int boardnum) {
		return dao.selectBoard(boardnum);
	}

	@Override
	public void deleteBoard(int boardnum) {
		dao.deleteBoard(boardnum);
	}

	@Override
	public void buy(Board board) {
		dao.buy(board);
	}

	@Override
	public ArrayList<Reply> getReplyList(int boardnum) {
		return dao.getReplyList(boardnum);
	}

	@Override
	public void insertReply(Reply reply) {
		dao.insertReply(reply);
	}

	@Override
	public ArrayList<Board> searchCategory(String category) {
		return dao.searchCategory(category);
	}

	//변수 여러 개 해시 맵으로 넘기기
	@Override
	public ArrayList<Board> searchWord(String keyword, String category) {
		HashMap<String, String> map = new HashMap<>();
		map.put("category", category);
		map.put("keyword", keyword);
		
		return dao.searchWord(map);
	}


}
