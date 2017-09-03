package com.shanti.StockAlertApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shanti.StockAlertApp.Model.ScreenerPermissions;

public interface PermissionsRepository extends CrudRepository<ScreenerPermissions, String> {

	List<ScreenerPermissions> findAll();
	
	/*@Query("select p.permId from ScreenerPermissions p where p.email in :emailIds ")
	List<String> findByEmailIn(@Param("emailIds") List<String> emailIds);*/
	
	List<ScreenerPermissions> findByEmailInAndRoleNot(List<String> emailIds, String role);
}
