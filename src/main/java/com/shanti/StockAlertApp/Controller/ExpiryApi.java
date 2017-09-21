package com.shanti.StockAlertApp.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shanti.StockAlertApp.Model.PremiumMember;
import com.shanti.StockAlertApp.Services.ExcelUploadService;
import com.shanti.StockAlertApp.Services.ExpiryServices;
import com.shanti.StockAlertApp.Services.ReminderService;

@RestController
public class ExpiryApi {
	
	@Autowired
	private ExpiryServices service;
	
	@Autowired
	private ExcelUploadService excelService;
	
	@Autowired
	private ReminderService reminderService;
	
	@GetMapping(value = "getExpiredMembers")
	public List<String> getExpiredMembers(){
		return service.getExpiredMembers();
	}
	
	@PostMapping(value="sendReminderToExpired")
	public String sendReminderToExpired(){
		List<PremiumMember> members = reminderService.getExpiredMembersFor3Days();
		 reminderService.sendSms(members);	 
		// reminderService.sendMailReminder(members);
		 return "Sucess";
	}
	
	@PostMapping(value="sendRemindersToComingExpired")
	public void sendReminderToComingExpired(){
		List<PremiumMember> members = reminderService.getMembersWhoWillBeExpired();
		 reminderService.sendSms(members);	 
		 reminderService.sendMailReminder(members);
		 
	}
	
	@PostMapping(value ="uploadInitialData")
	public void uploalInitDb(){
		excelService.ReadExcel("C:\\Users\\shant\\Downloads\\premium sales.xlsx");
	}
	
	@PostMapping(value ="/uploadInstaMojoData/{filename}")
	public void uploadInstaMojoDataDb(@PathVariable("filename") String filename){
		excelService.ReadInstaMojoExcel("C:\\Users\\shant\\Downloads\\" + filename + ".xlsx");
	}
	
	
	

}
