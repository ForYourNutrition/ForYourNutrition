package com.luckyGirls.ForYourNutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.IRecommend;

@Repository
public interface IRecommendJpaRepository extends JpaRepository<IRecommend, Integer> {
	// Optional<IRecommend> findByIrecommend_idAndCtype(int irecommend_id, int ctype);
}
