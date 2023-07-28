package net.softsociety.exam.service;

import java.util.ArrayList;

import net.softsociety.exam.domain.Board;
import net.softsociety.exam.domain.Reply;

public interface BoardService {

	public void insertBoard(Board board);

	public ArrayList<Board> getList();

	public Board selectBoard(int boardnum);

	public void deleteBoard(int boardnum);

	public void buy(Board board);

	public ArrayList<Reply> getReplyList(int boardnum);

	public void insertReply(Reply reply);

	public ArrayList<Board> searchCategory(String category);

	public ArrayList<Board> searchWord(String keyword, String category);

}
