package com.pi.developi.noticeBoard.service;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.pi.developi.noticeBoard.dao.NoticeBoardDao;
import com.pi.developi.noticeBoard.domain.NoticeBoardDTO;
import com.pi.developi.util.Criteria;

@Service
public class NoticeBoardServiceImpl implements NoticeBoardService {

	@Inject
	NoticeBoardDao dao;

	@Override
	public List<NoticeBoardDTO> listAll(int boardNo) {
		// TODO Auto-generated method stub
		return dao.listAll(boardNo);
	}

	@Override
	public NoticeBoardDTO detail(int articleNo) {
		return dao.detail(articleNo);
	}
	
	@Override
	public void modify(NoticeBoardDTO notice) {
		dao.modify(notice);
	}
	
	@Override
	public void write(NoticeBoardDTO notice) {
		dao.write(notice);
	}

	@Override
	public void delete(int articleNo) {
		dao.delete(articleNo);
	}
	@Override
	public List<NoticeBoardDTO> search(Criteria cri) {
		return dao.search(cri);
	}
	
	/** 조회수 증가 */
	@Override
	public void hitUp(int articleNo) {
		dao.hitUp(articleNo);
	}

}
