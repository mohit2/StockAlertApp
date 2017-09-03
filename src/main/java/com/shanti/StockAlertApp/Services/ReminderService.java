package com.shanti.StockAlertApp.Services;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.shanti.StockAlertApp.Model.PremiumMember;
import com.shanti.StockAlertApp.Repositories.MemberRepository;

@Service
@PropertySource("classpath:constant.properties")
public class ReminderService {
	
	@Autowired
	private MemberRepository repository;
	
	@Autowired
	private JavaMailSender sender;
	
	@Value("${smsHead}")
	private String smsHead;
	
	@Value("${smsTrail}")
	private String smsTrail;
	
	@Value("${emailMessage}")
	private String emailMessage;
	
	@Value("${apiKey}")
	private String apiKey;
	
	@Value("${smsSender}")
	private String smsSender;
	
	@Value("${mailSender}")
	private String mailSender;
	
	String baseUrl = "https://api.textlocal.in/send";

	public void sendSms(){
	
		RestTemplate restTemplate = new RestTemplate();
		
		StringBuilder msg;
		
		MultiValueMap<String, Object> map= new LinkedMultiValueMap();
		map.add("apikey", apiKey);
		map.add("sender", "");
		List<PremiumMember> inactiveMembers = repository.findInactiveMembersForLast5Days();

		for (PremiumMember premiumMember : inactiveMembers) {
			msg = new StringBuilder(smsHead);
			msg.append(premiumMember.getExpirationDate()).append(smsTrail);
			map.add("message", msg);
			map.add("numbers", premiumMember.getPhnNo());
			ResponseEntity<String> entity = restTemplate.postForEntity(baseUrl, map, String.class);
		}
	}
	
	public void sendMailReminder(){
		
		List<PremiumMember> inactiveMembers = repository.findInactiveMembersForLast5Days();
		StringBuilder msg;
		try{
			MimeMessage message = sender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message);
			for (PremiumMember premiumMember : inactiveMembers) {
				//System.out.println(premiumMember.getGmail());
				if(premiumMember.getGmail()!=null && !premiumMember.getGmail().isEmpty()){
					System.out.println(premiumMember.getGmail());
					msg = new StringBuilder("Hello " + premiumMember.getName() + ",\n\n" + smsHead + " ");
					msg.append(premiumMember.getExpirationDate()).append("\n").append(smsTrail)
					.append("\n \n Regards \n Team JaanoAurSeekho");
					helper.setTo(premiumMember.getGmail());
					helper.setFrom(mailSender);
					helper.setText(msg.toString());
					helper.setSubject("JaanoAurSeekho-Reminder for Renewal");
					sender.send(message);
				}
				
			}
			
			/*System.out.println(mailSender);
			msg = new StringBuilder("Hello " + inactiveMembers.get(0).getName() + ",\n\n" + smsHead + " ");
			msg.append(inactiveMembers.get(0).getExpirationDate()).append("\n").append(smsTrail)
			
			helper.setTo("shanti1490@gmail.com");
			helper.setFrom(mailSender);
			helper.setText(msg.toString());
			helper.setSubject("Reminder for Renewal");
			sender.send(message);*/
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getPhnNosOfInactiveMembers(){
		
		return repository.findInactiveMembersForFivedays();
		
	}

}
