package com.shanti.StockAlertApp.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.shanti.StockAlertApp.Model.PremiumMember;

public interface MemberRepository extends CrudRepository<PremiumMember, String>{

	@Query("select m.gmail from PremiumMember m where m.expirationDate < SUBDATE(now(),1) and m.gmail is not null")
	List<String> findInActiveMembers();
	
	@Query("select m.phnNo from PremiumMember m where now() > m.expirationDate and m.expirationDate between now() and now()-5")
	List<String>  findInactiveMembersForFivedays();
	
	@Query("from PremiumMember m where m.expirationDate > now() and m.expirationDate < ADDDATE(now(),3)")
	List<PremiumMember> findActiveMembersGoingToBeExpired();
	
	@Query("from PremiumMember m where m.expirationDate > SUBDATE(now(),7) and m.expirationDate < SUBDATE(now(),1)")
	List<PremiumMember>  findInactiveMembersForLast3Days();
}
