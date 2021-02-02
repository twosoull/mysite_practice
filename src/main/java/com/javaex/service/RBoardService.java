package com.javaex.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaex.dao.RBoardDao;
import com.javaex.vo.RBoardVo;

@Service
public class RBoardService {

	@Autowired
	private RBoardDao rBoardDao;

	public List<RBoardVo> list() {

		System.out.println("Service : list");
		List<RBoardVo> rbvList = rBoardDao.selectRboardList();
		//다시짜야할 부분 공백이 들어가지않음
		
		for (int i = 0; i < rbvList.size(); i++) {
			String title = "";
			if(!(rbvList.get(i).getTitle() == null)) {
				
			for (int j = 1; j <= rbvList.get(i).getDepth(); j++) {
				
				title += "\\p";
				
				System.out.println(title + "타이틀은? "+ title + "??");
			}
			title += rbvList.get(i).getTitle();
			System.out.println(title);
			rbvList.get(i).setTitle(title);

			}
		}

		return rbvList;

	}

	public RBoardVo read(int no) {
		System.out.println("read");
		rBoardDao.updateHit(no);
		
		return rBoardDao.selectRBoard(no);

	}

	public void write(RBoardVo rBoardVo) {
		System.out.println("Service : write");

		rBoardDao.insertRBoard(rBoardVo);

	}

	public void writeReply(RBoardVo rBoardVo) {
		System.out.println("Service : writeReply");

		List<RBoardVo> rbvList = rBoardDao.selectGroupNoRBoardList(rBoardVo.getGroupNo());
		
		int nowOrderNo = rBoardVo.getOrderNo();
		
		for (int i = 0; i < rbvList.size(); i++) {
			
			int listOrderNo = rbvList.get(i).getOrderNo();
			
			if ((nowOrderNo + 1) == listOrderNo) {
				int upOrderNo = listOrderNo + 1;

				rbvList.get(i).setOrderNo(upOrderNo);
				
				rBoardDao.updateOrderNo(rbvList.get(i));
			}
		}

		int orderNo = rBoardVo.getOrderNo() + 1;
		int depth = rBoardVo.getDepth() + 1;
		rBoardVo.setOrderNo(orderNo);
		rBoardVo.setDepth(depth);

		rBoardDao.insertRBoardReply(rBoardVo);

	}
	
	public void remove(int no) {
		System.out.println("Service :  remove()");
		
		rBoardDao.updateRBoardTitleEmpty(no);
	}
	
	public RBoardVo modifyForm(int no) {
		System.out.println("Service : modifyForm()");
		
		 return rBoardDao.selectRBoard(no);
		 
		
		 
	}
	
	public void modify(RBoardVo rBoardVo) {
		System.out.println("Service : modify");
		
		rBoardDao.updateRBoard(rBoardVo);
		
	}
}
