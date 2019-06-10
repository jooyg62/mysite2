package com.cafe24.mysite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cafe24.mysite.repository.MainDao;
import com.cafe24.mysite.vo.SiteVo;

@Service
public class MainService {
	
	@Autowired
	MainDao mainDao;
	
	public SiteVo getMainPage() {
		return mainDao.select();
	}

}
