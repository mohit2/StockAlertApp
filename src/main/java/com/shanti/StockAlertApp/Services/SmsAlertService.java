package com.shanti.StockAlertApp.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.shanti.StockAlertApp.Repositories.MemberRepository;

@Service
public class SmsAlertService {
	
	@Autowired
	private MemberRepository repository;
	
	String baseUrl = "https://api.textlocal.in/send";

	public String sendSms2(){
		List<String> phnNos = getPhnNosOfInactiveMembers();
		RestTemplate restTemplate = new RestTemplate();
		
		System.out.println("phn nos:" + phnNos);
		MultiValueMap<String, Object> map= new LinkedMultiValueMap();
		map.add("apikey", "");
		map.add("message", "Your jaanoAurSeekho Premium Membership expires tomorrow. Renew it immediately to continue services. To renew , click https://imjo.in/pr3mCB and make payment.");
		map.add("sender", "");
		map.add("numbers", "9163693551");
	    
		ResponseEntity<String> entity = restTemplate.postForEntity(baseUrl, map, String.class);
		return entity.toString();
	}
	
	public List<String> getPhnNosOfInactiveMembers(){
		
		return repository.findInactiveMembersForFivedays();
		
	}

}
