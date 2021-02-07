package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.BoardService;
import com.javaex.vo.BoardVo;

@Controller
@RequestMapping(value = "/board", method = { RequestMethod.GET, RequestMethod.POST })
public class BoardController {

	@Autowired
	private BoardService boardService;

	@RequestMapping(value = "/list", method = { RequestMethod.GET, RequestMethod.POST })
	public String list(Model model,@RequestParam("pageNum")int pageNum) {
		System.out.println("boardController : boardList()");
		

		model.addAttribute("boardList", boardService.list(pageNum));
		model.addAttribute("boardPage",boardService.pageAmount(pageNum));
		return "board/list";
	}

	
	@RequestMapping(value = "/read", method = { RequestMethod.GET, RequestMethod.POST })
	public String read(@RequestParam("no") int no,@RequestParam("pageNum") int pageNum, Model model) {
		System.out.println("boardController : read()");
		model.addAttribute("boardPage",boardService.pageAmount(pageNum));
		model.addAttribute("boardVo", boardService.read(no));
		return "board/read";
	}
	
	
	@RequestMapping(value = "/writeForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String writeForm() {
		System.out.println("controller : writeForm()");
		
		return "board/writeForm";
	}
	
	
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo) {
		System.out.println("controller : write()");
		boardService.write(boardVo);
	
		return "redirect:/board/list";
	}

	
	/* write() session을 service에서 처리하는 방식
	@RequestMapping(value = "/write", method = { RequestMethod.GET, RequestMethod.POST })
	public String write(@ModelAttribute BoardVo boardVo,HttpSession session) {
		System.out.println("controller : write()");
		boardService.write(boardVo,session);
		
		return "redirect:/board/list";
	}
	*/
	
	
	@RequestMapping(value = "/modifyForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String modifyForm(@RequestParam("no") int no, Model model) {
		System.out.println("controller : modifyForm()");
		// 사실 보여주는 결과는 같기때문에
		// boardService.read(no); 를 사용할 수 있는데 그럴경우 필요없는
		// user_no 값이 생긴다
		// 그래서 서비스에서 setUserNo(0)을 넣어주면서 정보를 가리는건 어떨지 모르겠다..
		
		model.addAttribute("boardVo",boardService.modifyForm(no));

		return "board/modifyForm";
	}
	
	@RequestMapping(value= "modify", method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute BoardVo boardVo) {
		System.out.println("controller : modify()");
		System.out.println(boardVo);
		boardService.modify(boardVo);
		
		return "redirect:/board/list";
	}
	@RequestMapping(value= "remove", method = {RequestMethod.GET,RequestMethod.POST})
	public String remove(@RequestParam("no") int no) {
		System.out.println("controller : remove()");
		
		boardService.remove(no);
		return "redirect:/board/list";
	}
	

}
