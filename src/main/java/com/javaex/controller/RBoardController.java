package com.javaex.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.RBoardService;
import com.javaex.vo.RBoardVo;

@Controller
@RequestMapping(value="/rboard" , method = {RequestMethod.GET,RequestMethod.POST})
public class RBoardController {
	
	@Autowired
	private RBoardService rBoardService;
	
	@RequestMapping(value="/list" , method = {RequestMethod.GET,RequestMethod.POST})
	public String list(Model model) {
		System.out.println("controller : list");
		
		model.addAttribute("rBoardList",rBoardService.list());
		
		return "rboard/list";
	}
	@RequestMapping(value="/writeForm" , method = {RequestMethod.GET,RequestMethod.POST})
	public String writeForm() {
		System.out.println("controller : writeForm");
		
		return "rboard/writeForm";
	}
	@RequestMapping(value="/write" , method = {RequestMethod.GET,RequestMethod.POST})
	public String write(@ModelAttribute RBoardVo rBoardVo) {
		System.out.println("controller : write");
		
		rBoardService.write(rBoardVo);
		
		return "redirect:/rboard/list" ;
	}
	
	@RequestMapping(value="/read" , method = {RequestMethod.GET,RequestMethod.POST})
	public String read(@RequestParam("no")int no,Model model) {
		System.out.println("controller : read");
		
		model.addAttribute("rBoardVo",rBoardService.read(no));
		
		return "rboard/read";
	}
	@RequestMapping(value="/writeReplyForm" , method = {RequestMethod.GET,RequestMethod.POST})
	public String writeReplyForm(@ModelAttribute RBoardVo rBoardVo,Model model) {
		System.out.println("controller : writeReplyForm");
	
		model.addAttribute("rBoardVo",rBoardVo);
		return "rboard/writeReplyForm";
	}

	
	@RequestMapping(value="/writeReply" , method = {RequestMethod.GET,RequestMethod.POST})
	public String writeReply(@ModelAttribute RBoardVo rBoardVo) {
		System.out.println("controller : writeReply");
		
		rBoardService.writeReply(rBoardVo);
		return "redirect:/rboard/list";
	}
}
