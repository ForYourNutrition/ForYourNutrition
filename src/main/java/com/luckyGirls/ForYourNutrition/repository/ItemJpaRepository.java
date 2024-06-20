package com.luckyGirls.ForYourNutrition.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luckyGirls.ForYourNutrition.domain.Item;

@Repository
public interface ItemJpaRepository extends JpaRepository<Item, Integer> {
	Item findById(int id);
	List<Item> findAllByCategory(String category);

	Page<Item> findAll(Pageable pageable);
	@Query("SELECT i FROM Item i WHERE LOWER(i.name) LIKE LOWER(CONCAT('%', :name, '%'))")
	Page<Item> findByNameContainingIgnoreCase(@Param("name") String name, Pageable pageable);

	Page<Item> findAllByCategory(String category, Pageable pageable);

	Page<Item> findAllByEffect(int effect, Pageable pageable);

	Page<Item> findAllByTarget(int target, Pageable pageable);

	@Query("SELECT i FROM Item i ORDER BY i.sales DESC")
	List<Item> findAllOrderBySalesDESC();
	@Query("SELECT i FROM Item i WHERE i.dcRate > 0")
	Page<Item> findAllByDcRate( Pageable pageable);
}
