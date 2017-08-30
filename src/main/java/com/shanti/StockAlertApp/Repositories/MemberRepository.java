package com.shanti.StockAlertApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shanti.StockAlertApp.Model.PremiumMember;

public interface MemberRepository extends CrudRepository<PremiumMember, String>{

	@Query("select m.email from PremiumMember m where m.active=:active")
	List<String> findInActiveMembers(@Param("active") String active);
}
