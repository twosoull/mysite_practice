package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.javaex.service.GuestService;
import com.javaex.vo.GuestVo;

@Controller
@RequestMapping(value="api/guest")
public class ApiGuestbookController {
	
	@Autowired
	GuestService guestService = new GuestService();
	
	@ResponseBody
	@RequestMapping(value="/write")
	public GuestVo write(@ModelAttribute GuestVo guestVo) {
		System.out.println("[ApiGuestbookController]: write");
		System.out.println(guestVo);
		
		GuestVo vo = guestService.writeResultVo(guestVo);
		
		return vo;
		
		
	}
	
	@ResponseBody
	@RequestMapping(value="/list")
	public List<GuestVo> list() {
		System.out.println("[ApiGuestbookController]:list");
		return guestService.list();
		
		
	}
	
	
}
