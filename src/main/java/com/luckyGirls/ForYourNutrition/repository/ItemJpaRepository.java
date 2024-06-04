package com.luckyGirls.ForYourNutrition.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.Item;

@Repository
public interface ItemJpaRepository extends JpaRepository<Item, Integer> {
	Item findById(int id);

	List<Item> findAllByCategory(int category);
}
