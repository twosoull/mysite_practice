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

		for (int i = 0; i < rbvList.size(); i++) {
			String title = "";
			
			for (int j = 1; j <= rbvList.get(i).getDepth(); j++) {
				String str = "00";
				title = title + str;
				
				System.out.println(title + "타이틀은? "+ title + "??");
			}
			title = title + rbvList.get(i).getTitle();
			
			rbvList.get(i).setTitle(title);

		}

		return rbvList;

	}

	public RBoardVo read(int no) {
		System.out.println("read");

		return rBoardDao.selectRBoard(no);

	}

	public void write(RBoardVo rBoardVo) {
		System.out.println("Service : write");

		rBoardDao.insertRBoard(rBoardVo);

	}

	public void writeReply(RBoardVo rBoardVo) {
		System.out.println("Service : writeReply");

		List<RBoardVo> rbvList = rBoardDao.selectGroupNoRBoardList(rBoardVo.getGroupNo());

		for (int i = 0; i < rbvList.size(); i++) {
			if ((rBoardVo.getOrderNo() + 1) == rbvList.get(i).getOrderNo()) {
				int upOrderNo = rbvList.get(i).getOrderNo() + 1;

				rbvList.get(i).setOrderNo(upOrderNo);
				System.out.println("어디가문제여  :  " + rbvList.get(i).getOrderNo());
				rBoardDao.updateOrderNo(rbvList.get(i));
			}
		}

		int orderNo = rBoardVo.getOrderNo() + 1;
		int depth = rBoardVo.getDepth() + 1;
		rBoardVo.setOrderNo(orderNo);
		rBoardVo.setDepth(depth);

		rBoardDao.insertRBoardReply(rBoardVo);

	}
}
