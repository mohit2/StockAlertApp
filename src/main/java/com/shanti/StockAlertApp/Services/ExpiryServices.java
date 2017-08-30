package com.shanti.StockAlertApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shanti.StockAlertApp.Repositories.MemberRepository;

@Service
public class ExpiryServices {

	@Autowired
	private MemberRepository repository;
	
	public List<String> getExpiredMembers(){
		
		return repository.findInActiveMembers("N");
	}
}
