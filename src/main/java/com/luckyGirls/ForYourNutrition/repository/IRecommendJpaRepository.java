package com.luckyGirls.ForYourNutrition.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.IRecommend;
import com.luckyGirls.ForYourNutrition.domain.Item;

@Repository
public interface IRecommendJpaRepository extends JpaRepository<IRecommend, Integer> {
	// Optional<IRecommend> findByIrecommend_idAndCtype(int irecommend_id, int ctype);
	IRecommend findByItemAndCtype(Item item, int ctype);
	List<IRecommend> findAllByItem(Item item);
}
