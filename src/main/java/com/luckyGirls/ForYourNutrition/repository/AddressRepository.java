package com.luckyGirls.ForYourNutrition.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer>{
	@Query("SELECT a FROM Address a WHERE a.member.member_id = :memberId")
    List<Address> findByMemberId(@Param("memberId") int memberId);
}
