package com.shinhan.myapp.controller;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.firstzone.myapp.emp.EmpDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.shinhan.myapp.board.BoardDTO;
import com.shinhan.myapp.board.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService bService;
	
	@RequestMapping("/selectAll.do") 
	public String test1(Model model, HttpServletRequest request) {
		
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		String message = "";
		if(flashMap != null) {
			message = (String) flashMap.get("resultMessage");
		}
		
		List<BoardDTO> blist = bService.selectAll();
		model.addAttribute("blist", blist);
		model.addAttribute("resultMessage", message);
		return "board/boardlist";	//forward
	}
	
	@GetMapping("/boardinsert.do")
	public void test2() {
		//forward : ���λ� + "board/boardinsert" + ���̻�
	};
	
	@PostMapping("/boardInsert.do")	
	public String test3(MultipartHttpServletRequest multipart, HttpSession session) throws UnsupportedEncodingException {
		BoardDTO board = new BoardDTO();
		HttpServletRequest request = (HttpServletRequest) multipart;
		board.setTitle(request.getParameter("title"));
		board.setContent(request.getParameter("content"));
		EmpDTO emp = (EmpDTO)session.getAttribute("emp");
		String writer = null;
		if(emp==null) {
			writer = "�մ�";
		} else {
			writer = emp.getFirst_name() + emp.getLast_name();
		}
		board.setWriter(writer);
		List<MultipartFile> fileList = multipart.getFiles("pic");
		String path = request.getSession().getServletContext().getRealPath("./resources/uploads");
		File fileDir = new File(path);
		if (!fileDir.exists()) {
			fileDir.mkdirs();
		}
		long time = System.currentTimeMillis();
		for (MultipartFile mf : fileList) {
			String originFileName = mf.getOriginalFilename(); //
			String saveFileName = String.format("%d_%s", time, originFileName);
			board.setPic(saveFileName);
			try {
				//upload�ϱ�
				mf.transferTo(new File(path, saveFileName));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		System.out.println("board:" + board);
		bService.insertBoard(board);
		// forward:��û�� ����
		// redirect:���û
		return "redirect:selectAll.do";
	}
	
	//@PostMapping("/boardInsert.do")
	//public String test3(String title, String content, String pic) {
	public String test3(BoardDTO board, RedirectAttributes attr, HttpSession session) {
		//BoardDTO board = new BoardDTO();
		//board.settitle(request.getParameter("title"))
		
		//BoardDTO board = new BoardDTO(0, title, content,"�ۼ���", pic, null);
		EmpDTO emp = (EmpDTO)session.getAttribute("emp");
		String writer = emp.getFirst_name() + emp.getLast_name();
		board.setWriter(writer);
		
		int result = bService.insertBoard(board);
		String message;
		if(result > 0) {
			message = "insert success";
		} else {
			message = "insert fail";
		}
		//forward : ��û�� ����
		//redirect : ���û
		attr.addFlashAttribute("resultMessage", message);
		return "redirect:selectAll.do";
		//response.sendRedirect("selectAll.do");
	};
	
	@GetMapping("/boardDetail.do")
	public String detail(@RequestParam("bno") Integer bno, Model model) {
		model.addAttribute("board", bService.selectById(bno));
	
		return "board/boarddetail";
	}
	
	@PostMapping("/boardDetail.do")
	public String update(BoardDTO board, RedirectAttributes attr) {
		int result = bService.updateBoard(board);
		String message;
		if(result>0) {
			message = "update success";
		} else {
			message = "update fail";
		}
		attr.addFlashAttribute("resultMessage", message);
		
		return "redirect:selectAll.do";
	}
	
	//@RequestMapping(value = "/boardDelete.do", method = RequestMethod.GET)
	@GetMapping("/boardDelete.do")
	public String delete(Integer bno,  RedirectAttributes attr) {
		int result = bService.deleteBoard(bno);
		String message;
		if(result>0) {
			message = "update success";
		} else {
			message = "update fail";
		}
		attr.addFlashAttribute("resultMessage", message);
		System.out.println("/board/boardDelete.do ��û");
		return "redirect:selectAll.do";
	}
	
	@GetMapping("/selectDelete.do")
	public String selectDelete(Integer[] checkBno) {
		
		int result = bService.deleteBoardArray(checkBno);
		
		return "redirect:selectAll.do";

	}
}
