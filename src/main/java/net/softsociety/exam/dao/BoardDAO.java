package net.softsociety.exam.dao;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

/**
 * 게시판 관련 매퍼
 */
@Mapper
public interface BoardDAO {

	public void insertBoard(Board board);

	public ArrayList<Board> getList();

	public Board selectBoard(int boardnum);

	public void deleteBoard(int boardnum);

	public void buy(Board board);

	public ArrayList<Reply> getReplyList(int boardnum);

	public void insertReply(Reply reply);

	public ArrayList<Board> searchCategory(String category);

	public ArrayList<Board> searchWord(HashMap<String, String> map);

}
