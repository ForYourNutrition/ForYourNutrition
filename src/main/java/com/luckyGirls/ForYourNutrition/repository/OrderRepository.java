package com.luckyGirls.ForYourNutrition.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.luckyGirls.ForYourNutrition.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

}
