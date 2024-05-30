package com.shinhan.myapp.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

//Service : ÎπÑÏ??ãà?ä§ Î°úÏßÅ ?ûë?Ñ±

@Service("bService2") //@Service = @Component + ?ÑúÎπÑÏä§ ?ó≠?ï†
public class BoardService {
	
	//BoardDAO boardDao = new BoardDAO();

	//********2.¿˚øÎ«œ±‚ : @Autowired
	@Autowired
	BoardDAO boardDao;
	
	public int deleteBoard(int bno) {
		return boardDao.deleteBoard(bno);
	}
	
	public int updateBoard(BoardDTO board) {
		return boardDao.updateBoard(board);
	}
	
	public int insertBoard(BoardDTO board) {
		return boardDao.insertBoard(board);
	}
	
	public BoardDTO selectById(int bno) {
		return boardDao.selectById(bno);
	}
	
	public List<BoardDTO> selectAll() {
		return boardDao.selectAll();
	}

	public int deleteBoardArray(Integer[] checkBno) {
		// TODO Auto-generated method stub
		return boardDao.deleteBoardArray(checkBno);
	}
}
