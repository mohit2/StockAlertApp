package com.shanti.StockAlertApp.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.Permission;
import com.google.api.services.drive.model.PermissionList;
import com.shanti.StockAlertApp.Model.ScreenerPermissions;
import com.shanti.StockAlertApp.Services.GoogleDriveSevices;
import com.shanti.StockAlertApp.googleDrive.QuickStart;

@RestController
public class GoogleDriveApi {
	
	@Autowired
	private GoogleDriveSevices driveSevices;
	
	@Autowired
	private QuickStart quickStart;
	
	@PostMapping(value ="postPermissions/{fileId}")
	public void savePermissions(@PathVariable("fileId") String fileId) throws IOException{
		
        // Build a new authorized API client service.
        Drive service = quickStart.getDriveService();
        logging(service);
      
       // String fileId = "1Epki5pVbO16GzguDvkU_6EKHJ52qJ0SBk1Np3BXV33g";

        try {
            File file = service.files().get(fileId).execute();
            System.out.println("Title: " + file.getName());
           
            PermissionList filePermissions = service.permissions().list(fileId).setFields("permissions(emailAddress,id,kind,role,type)").execute();
            System.out.println("keyset: " + filePermissions.keySet().toString());
            List<Permission> permissions=filePermissions.getPermissions();

            driveSevices.savePermissions(permissions);
           
            System.out.println("Description: " + filePermissions.toPrettyString());
            System.out.println("Description: " + file.getDescription());
            System.out.println("MIME type: " + file.getMimeType());

        } catch (IOException e) {
            System.out.println("An error occurred: " + e);
        }
	}
	
	@DeleteMapping(value="deletePermissions/{fileId}")
	public void deletePermissions(@PathVariable("fileId") String fileId) throws IOException{
		
		System.out.println("File Id:" + fileId);
        Drive service = quickStart.getDriveService();      
		List<ScreenerPermissions> permissions = driveSevices.getExpiredPermission();
		List<String> permIds = new ArrayList<>();
		List<String> emailList = new ArrayList<>();
		permissions.stream().forEach(s->{ permIds.add(s.getPermId()); emailList.add(s.getEmail());});
		
        permIds.stream().forEach(permId-> {
			try {
				System.out.println("Permission IDS: "+ permId);
				service.permissions().delete(fileId, permId).execute();
			} catch (IOException e) {
				e.printStackTrace();
				return;
			}
		});
        
        driveSevices.deleteExpiredPerissionId(emailList);
	}

	// Print the names and IDs for up to 10 files.
	private void logging(Drive service) throws IOException {
		FileList result = service.files().list()
             .setPageSize(30)
             .setFields("nextPageToken, files(id, name)")
             .execute();
        List<File> files = result.getFiles();
        if (files == null || files.size() == 0) {
            System.out.println("No files found.");
        } else {
            System.out.println("Files:");
            for (File file : files) {
                System.out.printf("%s (%s)\n", file.getName(), file.getId());
            }
        }
	}

}
