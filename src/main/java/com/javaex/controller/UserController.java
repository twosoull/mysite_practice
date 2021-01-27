package com.javaex.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.javaex.dao.UserDao;
import com.javaex.vo.UserVo;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	@Autowired
	private UserDao userDao;

	// 회원가입폼
	@RequestMapping(value = "/joinForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String joinForm() {
		System.out.println("joinForm");

		return "user/joinForm";
	}

	// 회원가입
	@RequestMapping(value = "/join", method = { RequestMethod.GET, RequestMethod.POST })
	public String join(@ModelAttribute UserVo userVo) {
		System.out.println("join");
		System.out.println("controller join UserVo : " + userVo);

		int count = userDao.insert(userVo);

		return "user/joinOk";
	}

	// 로그인폼
	@RequestMapping(value = "/loginForm", method = { RequestMethod.GET, RequestMethod.POST })
	public String loginForm() {
		System.out.println("loginForm");

		return "user/loginForm";
	}

	// 로그인
	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public String login(@ModelAttribute UserVo userVo, HttpSession session) {
		System.out.println("/user/login");
		System.out.println(userVo);

		UserVo authUser = userDao.selectUser(userVo);
		System.out.println("login : "+authUser);
		// 실패했을때
		if (authUser == null) {
			
			return "redirect:/user/loginForm?result=fail";
		} else {
			// 성공했을때
			session.setAttribute("authUser", authUser);

			return "redirect:/";
		}
	}
	@RequestMapping(value = "/logout", method = {RequestMethod.GET,RequestMethod.POST})
	public String logout(HttpSession session) {
		System.out.println("logout");
		
		session.removeAttribute("authUser");
		session.invalidate();
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/modifyForm", method = {RequestMethod.GET,RequestMethod.POST})
	public String modifyForm(Model model ,HttpSession session) {
		System.out.println("controller : modifyForm");
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		
		
		model.addAttribute("userVo",userDao.selectUser2(authUser.getNo()));
		
		return "user/modifyForm";
	}
	@RequestMapping(value = "/modify", method = {RequestMethod.GET,RequestMethod.POST})
	public String modify(@ModelAttribute UserVo userVo,HttpSession session) {
		System.out.println("countroller : modify");
		System.out.println(userVo);
		
		userDao.update(userVo);
		
		//같은주소이므로 변수지정만해서 이름만 바꿔준다
		UserVo authUser = (UserVo)session.getAttribute("authUser");
		authUser.setName(userVo.getName());
		
		return "redirect:/";
	}
}
