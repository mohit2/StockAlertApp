package com.shanti.StockAlertApp.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.google.api.services.drive.model.Permission;
import com.shanti.StockAlertApp.Model.DriveResponse;
import com.shanti.StockAlertApp.Model.Permissions;
import com.shanti.StockAlertApp.Model.PremiumMember;
import com.shanti.StockAlertApp.Model.ScreenerPermissions;
import com.shanti.StockAlertApp.Repositories.MemberRepository;
import com.shanti.StockAlertApp.Repositories.PermissionsRepository;

@Service
public class GoogleDriveSevices {
	
	@Autowired
	private PermissionsRepository permRepository;
	
	@Autowired
	private MemberRepository memberRepository;
	
	String baseUrl = "https://www.googleapis.com/drive/v3/files/";
	
	public List<ScreenerPermissions> saveAllPermissions(String fileId){
		
		String url = baseUrl + fileId + "/permissions";
		RestTemplate template = new RestTemplate();
		
		ResponseEntity<DriveResponse> restResponse = template.postForEntity(url, null,DriveResponse.class);
		DriveResponse res=  restResponse.getBody();
		
		List<Permissions> permissions = res.getPermissions();
		
		for (Permissions perm : permissions) {
			String permissionId = perm.getId();
			getEmailFromPermissionId(permissionId, fileId);
		}
	
		List<ScreenerPermissions> permissionsEntities = new ArrayList<>();
		
		for (Permissions perm : permissions) {
			ScreenerPermissions sc = new ScreenerPermissions();
			sc.setPermId(perm.getId());
			permissionsEntities.add(sc);
		}	
		permRepository.save(permissionsEntities);	
		return permissionsEntities;
	}
	
	
	public void getEmailFromPermissionId(String permissionId, String fileId){
		
		String url = baseUrl + fileId + "/permissions" + permissionId;
		RestTemplate template = new RestTemplate();
		MultiValueMap<String, Object> map= new LinkedMultiValueMap();
		map.add("fields", "emailAddress");
		//ResponseEntity<DriveResponse> restResponse = template.getForEntity(url, map,DriveResponse.class);
	}
	
	public  void savePermissions(List<Permission> permissions){
	    	
	    	List<ScreenerPermissions> screenPermList = new ArrayList<>();
	     	 for (Permission perms:permissions) {
	     		System.out.println("Permissions Shanti :" + perms.getEmailAddress() + "             " + perms.getId() + "     " + perms.getRole());
	     		ScreenerPermissions  permission = new ScreenerPermissions();
	     		permission.setEmail(perms.getEmailAddress());
	     		permission.setPermId(perms.getId());
	     		permission.setRole(perms.getRole());
	            screenPermList.add(permission);
	       }
	     	 permRepository.save(screenPermList);
	}
	
	//find all expired permission ids
	public List<ScreenerPermissions> getExpiredPermission(){
		
		List<String> inactiveMembers = memberRepository.findInActiveMembers();
		return permRepository.findByEmailInAndRoleNot(inactiveMembers, "owner");		
	}
	
	public void deleteExpiredPerissionId(List<String> permissionIds){
		
		permissionIds.stream().forEach(permId -> permRepository.delete(permId));
	}

	

}
