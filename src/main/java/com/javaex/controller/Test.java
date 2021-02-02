package com.javaex.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;

@Controller
public class Test {
	@Autowired
	RBoardDao rBoardDao;
	@RequestMapping(value="test")
	public void test() {
		System.out.println("test");
		List<RBoardVo>rvList = rBoardDao.selectRboardList();
		System.out.println(rvList);
		List<ArrayList<RBoardVo>> pageList = null;
		List<RBoardVo> plist = new ArrayList<RBoardVo>();

		for(int i = 0; i<rvList.size();i++) {
			RBoardVo rVo = rvList.get(i);
			plist.add(rVo);
			
			System.out.println("dPDk");
			System.out.println(plist.size());
			if(plist.size() != 0 && (plist.size()%10) == 0 ) {
				System.out.println(plist);
				pageList.add((ArrayList<RBoardVo>) plist);
				plist.clear();
			}
		}
		for(int i = 0; i<pageList.size(); i++) {
		System.out.println(i + " :" + pageList.get(i) );
		}
	}
}
